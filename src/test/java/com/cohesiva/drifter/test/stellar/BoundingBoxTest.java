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
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.stellar.BoundingBox;
import com.cohesiva.drifter.stellar.IBoundingBox;
import com.cohesiva.drifter.stellar.IStellar;

/**
 * The <code>SpaceTest</code> represents the bounding box unit test.
 * 
 * @author bkarmelita
 * 
 */
public class BoundingBoxTest {

	private IStellar start;
	private IStellar end;
	private Location targetLocation;

	@Before
	public void setUp() {
		targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
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

		IBoundingBox box = BoundingBox.newInstance(stellars);

		IBoundingBox[] splitted = (IBoundingBox[]) box.split(targetLocation, 0);

		IBoundingBox frontBottomLeft = splitted[0];
		IBoundingBox frontBottomRight = splitted[1];
		IBoundingBox frontTopLeft = splitted[2];
		IBoundingBox frontTopRight = splitted[3];
		IBoundingBox rearBottomLeft = splitted[4];
		IBoundingBox rearBottomRight = splitted[5];
		IBoundingBox rearTopLeft = splitted[6];
		IBoundingBox rearTopRight = splitted[7];

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
