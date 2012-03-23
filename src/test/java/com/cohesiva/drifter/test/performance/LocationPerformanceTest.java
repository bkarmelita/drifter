/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.performance;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>LocationPerformanceTest</code> represents a location unit performance test.
 * 
 * @author carmel
 */
public class LocationPerformanceTest {
	
	private List<Location> locations250K = new LinkedList<Location>();
	private Random random = new Random();
	
	@Before
	public void setUp() {
		// {{ generate 250K stars
		for (int i = 0; i < 250000; i++) {
			Location location = new Location(random.nextDouble() * 100, random.nextDouble() * 100, random.nextDouble() * 100, DistanceUnit.LIGHT_YEAR);
			locations250K.add(location);
		}
		// }}
	}
	
	@Test
	public void test250KAdd() {
		Location sum = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
		for (Location location : locations250K) {
			sum = sum.add(location);
		}
	}
	
	@Test
	public void test250KAddAndStore() {
		Location sum = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
		for (Location location : locations250K) {
			sum.addAndStore(location);
		}
	}
	
	@Test
	public void test250KSubtract() {
		Location subtraction = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
		for (Location location : locations250K) {
			subtraction = subtraction.subtract(location);
		}
	}
	
	@Test
	public void test250KSubstractAndStore() {
		Location subtraction = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
		for (Location location : locations250K) {
			subtraction.subtractAndStore(location);
		}
	}
	
	@Test
	public void test250KMultiply() {
		Location multi = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		double multiplier = 33.3;
		
		for (int i = 0; i < locations250K.size(); i++) {
			multi = multi.multiply(multiplier);
		}
	}
	
	@Test
	public void test250KMultiplyAndStore() {
		Location multi = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		double multiplier = 33.3;
		
		for (int i = 0; i < locations250K.size(); i++) {
			multi.multiplyAndStore(multiplier);
		}
	}

}
