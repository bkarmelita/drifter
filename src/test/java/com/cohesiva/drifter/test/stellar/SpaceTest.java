/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.stellar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IBounding;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.split.containers.BoundingBox;
import com.cohesiva.drifter.split.containers.Volume;

/**
 * The <code>SpaceTest</code> represents a space unit test.
 * 
 * @author carmel
 * 
 */
public class SpaceTest {
	private IStellar start;
	private IStellar end;
	private IStellar inside;
	private IStellar outside;
	private Location targetLocation;
	private ISplitContext ctx;

	@Before
	public void setUp() {
		targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		// mock the split context cince Volume does not make much use of it
		ctx = mock(ISplitContext.class);
		
		start = mock(IStellar.class);
		when(start.locate()).thenReturn(
				new Location(10, 0, 0, DistanceUnit.LIGHT_YEAR));

		end = mock(IStellar.class);
		when(end.locate()).thenReturn(
				new Location(-10, 0, 0, DistanceUnit.LIGHT_YEAR));

		inside = mock(IStellar.class);
		when(inside.locate()).thenReturn(targetLocation);

		outside = mock(IStellar.class);
		when(outside.locate()).thenReturn(
				new Location(-11, 0, 0, DistanceUnit.LIGHT_YEAR));
	}

	@Test
	public void testStellarBounds() {
		List<IStellar> stellars = new LinkedList<IStellar>();
		stellars.add(start);
		stellars.add(end);

		BoundingBox box = BoundingBox.newInstance(stellars);

		assertEquals(new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR),
				box.center());
		assertEquals(10, box.radius(), 0);
	}

	@Test
	public void testSurrounding() {
		BoundingBox box = new BoundingBox(new Location(0, 0, 0,
				DistanceUnit.LIGHT_YEAR), 10, 1);

		assertTrue(box.isSurrounding(inside.locate(), 0));
		assertFalse(box.isSurrounding(outside.locate(), 0));
	}

	@Test
	public void testSplitSpace() {
		List<IStellar> stellars = new LinkedList<IStellar>();
		stellars.add(start);
		stellars.add(end);

		BoundingBox box = BoundingBox.newInstance(stellars);
		Volume volume = new Volume(stellars, box, 0, new Random(0));
		
		SplitDegree splitDegree = volume.splitDegree();
		Volume[] splitted = new Volume[splitDegree.value()];
		for (IOffset offset : splitDegree.offsets()) {
			splitted[offset.offsetIndex()] = (Volume) volume.onSplit(ctx, offset);
		}
		volume.onSplitComplete(ctx, splitted);

		Volume frontBottomLeftSpace = splitted[0];
		IBounding frontBottomLeftBox = frontBottomLeftSpace.bounds();
		Volume frontBottomRightSpace = splitted[1];
		IBounding frontBottomRightBox = frontBottomRightSpace.bounds();
		Volume frontTopLeftSpace = splitted[2];
		IBounding frontTopLeftBox = frontTopLeftSpace.bounds();
		Volume frontTopRightSpace = splitted[3];
		IBounding frontTopRightBox = frontTopRightSpace.bounds();
		Volume rearBottomLeftSpace = splitted[4];
		IBounding rearBottomLeftBox = rearBottomLeftSpace.bounds();
		Volume rearBottomRightSpace = splitted[5];
		IBounding rearBottomRightBox = rearBottomRightSpace.bounds();
		Volume rearTopLeftSpace = splitted[6];
		IBounding rearTopLeftBox = rearTopLeftSpace.bounds();
		Volume rearTopRightSpace = splitted[7];
		IBounding rearTopRightBox = rearTopRightSpace.bounds();

		assertEquals(new Location(-5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomLeftBox.center());
		assertEquals(5, frontBottomLeftBox.radius(), 0);
		assertEquals(1, frontBottomLeftSpace.contents().size());
		assertEquals(new Location(5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomRightBox.center());
		assertEquals(5, frontBottomRightBox.radius(), 0);
		assertEquals(1, frontBottomRightSpace.contents().size());
		assertEquals(new Location(-5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopLeftBox.center());
		assertEquals(5, frontTopLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.contents());
		assertEquals(new Location(5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopRightBox.center());
		assertEquals(5, frontTopRightBox.radius(), 0);
		assertNull(frontTopRightSpace.contents());
		assertEquals(new Location(-5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomLeftBox.center());
		assertEquals(5, rearBottomLeftBox.radius(), 0);
		assertNull(rearBottomLeftSpace.contents());
		assertEquals(new Location(5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomRightBox.center());
		assertEquals(5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.contents());
		assertEquals(new Location(-5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopLeftBox.center());
		assertEquals(5, rearTopLeftBox.radius(), 0);
		assertNull(rearTopLeftSpace.contents());
		assertEquals(new Location(5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopRightBox.center());
		assertEquals(5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.contents());

		
		for (IOffset offset : splitDegree.offsets()) {
			splitted[offset.offsetIndex()] = (Volume) frontBottomLeftSpace.onSplit(ctx, offset);
		}
		frontBottomLeftSpace.onSplitComplete(ctx, splitted);

		frontBottomLeftSpace = splitted[0];
		frontBottomLeftBox = frontBottomLeftSpace.bounds();
		frontBottomRightSpace = splitted[1];
		frontBottomRightBox = frontBottomRightSpace.bounds();
		frontTopLeftSpace = splitted[2];
		frontTopLeftBox = frontTopLeftSpace.bounds();
		frontTopRightSpace = splitted[3];
		frontTopRightBox = frontTopRightSpace.bounds();
		rearBottomLeftSpace = splitted[4];
		rearBottomLeftBox = rearBottomLeftSpace.bounds();
		rearBottomRightSpace = splitted[5];
		rearBottomRightBox = rearBottomRightSpace.bounds();
		rearTopLeftSpace = splitted[6];
		rearTopLeftBox = rearTopLeftSpace.bounds();
		rearTopRightSpace = splitted[7];
		rearTopRightBox = rearTopRightSpace.bounds();

		assertEquals(new Location(-7.5, -7.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontBottomLeftBox.center());
		assertEquals(2.5, frontBottomLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.contents());
		assertEquals(new Location(-2.5, -7.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontBottomRightBox.center());
		assertEquals(2.5, frontBottomRightBox.radius(), 0);
		assertNull(frontBottomRightSpace.contents());
		assertEquals(new Location(-7.5, -2.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontTopLeftBox.center());
		assertEquals(2.5, frontTopLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.contents());
		assertEquals(new Location(-2.5, -2.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontTopRightBox.center());
		assertEquals(2.5, frontTopRightBox.radius(), 0);
		assertNull(frontTopRightSpace.contents());
		assertEquals(new Location(-7.5, -7.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearBottomLeftBox.center());
		assertEquals(2.5, rearBottomLeftBox.radius(), 0);
		assertNull(rearBottomLeftSpace.contents());
		assertEquals(new Location(-2.5, -7.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearBottomRightBox.center());
		assertEquals(2.5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.contents());
		assertEquals(new Location(-7.5, -2.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearTopLeftBox.center());
		assertEquals(2.5, rearTopLeftBox.radius(), 0);
		assertNotNull(rearTopLeftSpace.contents());
		assertEquals(new Location(-2.5, -2.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearTopRightBox.center());
		assertEquals(2.5, rearTopRightBox.radius(), 0);
		assertNull(rearTopRightSpace.contents());
	}

}
