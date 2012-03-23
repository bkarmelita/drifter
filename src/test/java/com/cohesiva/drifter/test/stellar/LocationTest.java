/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.stellar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>LocationTest</code> represents a location unit test.
 * 
 * @author carmel
 * 
 */
public class LocationTest {

	private Location locationOne;
	private Location locationTwo;

	@Before
	public void setUp() {
		locationOne = new Location(100, 100, 100, DistanceUnit.KILOMETER);
		locationTwo = new Location(2, 4, 6, DistanceUnit.KILOMETER);
	}

	@Test
	public void testAdd() {
		Location result = locationOne.add(locationTwo);
		double goodX = 102;
		double goodY = 104;
		double goodZ = 106;
		Assert.assertEquals(goodX, result.x(), 0);
		Assert.assertEquals(goodY, result.y(), 0);
		Assert.assertEquals(goodZ, result.z(), 0);
	}

	@Test
	public void testSubtract() {
		Location result = locationOne.subtract(locationTwo);
		double goodX = 98;
		double goodY = 96;
		double goodZ = 94;
		Assert.assertEquals(goodX, result.x(), 0);
		Assert.assertEquals(goodY, result.y(), 0);
		Assert.assertEquals(goodZ, result.z(), 0);
	}

	@Test
	public void testMultiply() {
		Location result = locationOne.multiply(3);
		double good = 300;

		Assert.assertEquals(good, result.x(), 0);
		Assert.assertEquals(good, result.y(), 0);
		Assert.assertEquals(good, result.z(), 0);

		result = locationTwo.multiply(3);
		double goodX = 6;
		double goodY = 12;
		double goodZ = 18;

		Assert.assertEquals(goodX, result.x(), 0);
		Assert.assertEquals(goodY, result.y(), 0);
		Assert.assertEquals(goodZ, result.z(), 0);
	}

}
