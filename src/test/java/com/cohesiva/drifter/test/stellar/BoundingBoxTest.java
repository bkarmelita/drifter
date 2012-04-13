/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.stellar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.split.containers.BoundingBox;

/**
 * The <code>SpaceTest</code> represents the bounding box unit test.
 * 
 * @author carmel
 * 
 */
public class BoundingBoxTest {

	private IStellar start;
	private IStellar end;
	private ISplitContext ctx;

	@Before
	public void setUp() {
		// mock the split context since BoundingBox does not make much use of it
		ctx = mock(ISplitContext.class);
		
		start = mock(IStellar.class);
		when(start.locate()).thenReturn(
				new Location(-10, 0, 0, DistanceUnit.LIGHT_YEAR));

		end = mock(IStellar.class);
		when(end.locate()).thenReturn(
				new Location(10, 0, 0, DistanceUnit.LIGHT_YEAR));
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
	public void testSplitBounds() {
		List<IStellar> stellars = new LinkedList<IStellar>();
		stellars.add(start);
		stellars.add(end);

		BoundingBox box = BoundingBox.newInstance(stellars);
		SplitDegree splitDegree = box.splitDegree();
		BoundingBox[] splitted = new BoundingBox[splitDegree.value()];
		for (IOffset offset : splitDegree.offsets()) {
			splitted[offset.offsetIndex()] = (BoundingBox) box.onSplit(ctx, offset);
		}

		BoundingBox frontBottomLeft = splitted[0];
		BoundingBox frontBottomRight = splitted[1];
		BoundingBox frontTopLeft = splitted[2];
		BoundingBox frontTopRight = splitted[3];
		BoundingBox rearBottomLeft = splitted[4];
		BoundingBox rearBottomRight = splitted[5];
		BoundingBox rearTopLeft = splitted[6];
		BoundingBox rearTopRight = splitted[7];

		assertEquals(new Location(-5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomLeft.center());
		assertEquals(5, frontBottomLeft.radius(), 0);
		assertEquals(new Location(5, -5, -5, DistanceUnit.LIGHT_YEAR),
				frontBottomRight.center());
		assertEquals(new Location(-5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopLeft.center());
		assertEquals(new Location(5, 5, -5, DistanceUnit.LIGHT_YEAR),
				frontTopRight.center());
		assertEquals(new Location(-5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomLeft.center());
		assertEquals(new Location(5, -5, 5, DistanceUnit.LIGHT_YEAR),
				rearBottomRight.center());
		assertEquals(new Location(-5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopLeft.center());
		assertEquals(new Location(5, 5, 5, DistanceUnit.LIGHT_YEAR),
				rearTopRight.center());
	}

}
