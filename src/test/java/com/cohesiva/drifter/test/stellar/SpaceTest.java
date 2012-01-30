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

import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.stellar.BoundingBox;
import com.cohesiva.drifter.stellar.IBoundingBox;
import com.cohesiva.drifter.stellar.ISpace;
import com.cohesiva.drifter.stellar.IStellar;
import com.cohesiva.drifter.stellar.Space;
import com.cohesiva.drifter.stellar.SpaceFactory;

/**
 * The <code>SpaceTest</code> represents a space unit test.
 * 
 * @author bkarmelita
 * 
 */
public class SpaceTest {
	private IStellar start;
	private IStellar end;
	private IStellar inside;
	private IStellar outside;
	private Location targetLocation;

	@Before
	public void setUp() {
		targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
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
		ISpace space = SpaceFactory.getInstance(Space.class).produceSpace(stellars, box);

		ISpace[] subspaces = (ISpace[]) space.split(targetLocation, 0);

		ISpace frontBottomLeftSpace = subspaces[0];
		IBoundingBox frontBottomLeftBox = frontBottomLeftSpace.bounds();
		ISpace frontBottomRightSpace = subspaces[1];
		IBoundingBox frontBottomRightBox = frontBottomRightSpace.bounds();
		ISpace frontTopLeftSpace = subspaces[2];
		IBoundingBox frontTopLeftBox = frontTopLeftSpace.bounds();
		ISpace frontTopRightSpace = subspaces[3];
		IBoundingBox frontTopRightBox = frontTopRightSpace.bounds();
		ISpace rearBottomLeftSpace = subspaces[4];
		IBoundingBox rearBottomLeftBox = rearBottomLeftSpace.bounds();
		ISpace rearBottomRightSpace = subspaces[5];
		IBoundingBox rearBottomRightBox = rearBottomRightSpace.bounds();
		ISpace rearTopLeftSpace = subspaces[6];
		IBoundingBox rearTopLeftBox = rearTopLeftSpace.bounds();
		ISpace rearTopRightSpace = subspaces[7];
		IBoundingBox rearTopRightBox = rearTopRightSpace.bounds();

		assertEquals(new Location(-5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomLeftBox.center());
		assertEquals(5, frontBottomLeftBox.radius(), 0);
		assertEquals(1, frontBottomLeftSpace.stellars().size());
		assertEquals(new Location(5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomRightBox.center());
		assertEquals(5, frontBottomRightBox.radius(), 0);
		assertEquals(1, frontBottomRightSpace.stellars().size());
		assertEquals(new Location(-5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopLeftBox.center());
		assertEquals(5, frontTopLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.stellars());
		assertEquals(new Location(5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopRightBox.center());
		assertEquals(5, frontTopRightBox.radius(), 0);
		assertNull(frontTopRightSpace.stellars());
		assertEquals(new Location(-5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomLeftBox.center());
		assertEquals(5, rearBottomLeftBox.radius(), 0);
		assertNull(rearBottomLeftSpace.stellars());
		assertEquals(new Location(5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomRightBox.center());
		assertEquals(5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.stellars());
		assertEquals(new Location(-5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopLeftBox.center());
		assertEquals(5, rearTopLeftBox.radius(), 0);
		assertNull(rearTopLeftSpace.stellars());
		assertEquals(new Location(5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopRightBox.center());
		assertEquals(5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.stellars());

		subspaces = (ISpace[]) frontBottomLeftSpace.split(targetLocation, 0);

		frontBottomLeftSpace = subspaces[0];
		frontBottomLeftBox = frontBottomLeftSpace.bounds();
		frontBottomRightSpace = subspaces[1];
		frontBottomRightBox = frontBottomRightSpace.bounds();
		frontTopLeftSpace = subspaces[2];
		frontTopLeftBox = frontTopLeftSpace.bounds();
		frontTopRightSpace = subspaces[3];
		frontTopRightBox = frontTopRightSpace.bounds();
		rearBottomLeftSpace = subspaces[4];
		rearBottomLeftBox = rearBottomLeftSpace.bounds();
		rearBottomRightSpace = subspaces[5];
		rearBottomRightBox = rearBottomRightSpace.bounds();
		rearTopLeftSpace = subspaces[6];
		rearTopLeftBox = rearTopLeftSpace.bounds();
		rearTopRightSpace = subspaces[7];
		rearTopRightBox = rearTopRightSpace.bounds();

		assertEquals(new Location(-7.5, -7.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontBottomLeftBox.center());
		assertEquals(2.5, frontBottomLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.stellars());
		assertEquals(new Location(-2.5, -7.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontBottomRightBox.center());
		assertEquals(2.5, frontBottomRightBox.radius(), 0);
		assertNull(frontBottomRightSpace.stellars());
		assertEquals(new Location(-7.5, -2.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontTopLeftBox.center());
		assertEquals(2.5, frontTopLeftBox.radius(), 0);
		assertNull(frontTopLeftSpace.stellars());
		assertEquals(new Location(-2.5, -2.5, -7.5, DistanceUnit.LIGHT_YEAR),
				frontTopRightBox.center());
		assertEquals(2.5, frontTopRightBox.radius(), 0);
		assertNull(frontTopRightSpace.stellars());
		assertEquals(new Location(-7.5, -7.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearBottomLeftBox.center());
		assertEquals(2.5, rearBottomLeftBox.radius(), 0);
		assertNull(rearBottomLeftSpace.stellars());
		assertEquals(new Location(-2.5, -7.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearBottomRightBox.center());
		assertEquals(2.5, rearBottomRightBox.radius(), 0);
		assertNull(rearBottomRightSpace.stellars());
		assertEquals(new Location(-7.5, -2.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearTopLeftBox.center());
		assertEquals(2.5, rearTopLeftBox.radius(), 0);
		assertNotNull(rearTopLeftSpace.stellars());
		assertEquals(new Location(-2.5, -2.5, -2.5, DistanceUnit.LIGHT_YEAR),
				rearTopRightBox.center());
		assertEquals(2.5, rearTopRightBox.radius(), 0);
		assertNull(rearTopRightSpace.stellars());
	}

}
