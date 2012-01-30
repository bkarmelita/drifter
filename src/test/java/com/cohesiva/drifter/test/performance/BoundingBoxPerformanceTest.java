/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.performance;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.stellar.BoundingBox;
import com.cohesiva.drifter.stellar.IStellar;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * The <code>BoundingBoxPerformanceTest</code> represents a bounds unit
 * performance test.
 * 
 * @author bkarmelita
 */
public class BoundingBoxPerformanceTest {
	
	private List<IStellar> stars100K = new LinkedList<IStellar>();
	private List<IStellar> stars50K = new LinkedList<IStellar>();
	private BoundingBox box100K;
	private BoundingBox box50K;
	private Location targetLocation;
	private Random random = new Random();

	@Before
	public void setUp() {
		targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		
		// {{ generate 100K stars
		for (int i = 0; i < 100000; i++) {
			Location location = new Location(random.nextDouble() * 100, random.nextDouble() * 100, random.nextDouble() * 100, DistanceUnit.LIGHT_YEAR);
			IStellar star = new Star(StarClass.O, location, 1);
			stars100K.add(star);
		}
		// }}
		
		// {{ generate 250K stars
		for (int i = 0; i < 5000; i++) {
			Location location = new Location(random.nextDouble() * 100, random.nextDouble() * 100, random.nextDouble() * 100, DistanceUnit.LIGHT_YEAR);
			IStellar star = new Star(StarClass.O, location, 1);
			stars50K.add(star);
		}
		// }}
		
		box100K = BoundingBox.newInstance(stars100K);
		box50K = BoundingBox.newInstance(stars50K);
	}
	
	@After
	public void tearDown() {
		stars100K.clear();
		stars50K.clear();
	}
	
	@Test
	public void test100KStarsCreation() {
		BoundingBox.newInstance(stars100K);
	}
	
	@Test
	public void test50KStarsCreation() {
		BoundingBox.newInstance(stars50K);
	}
	
	@Test
	public void test100KStarsOneSplit() {
		box100K.split(targetLocation, 0);
	}
	
	@Test
	public void test50KStarsOneSlit() {
		box50K.split(targetLocation, 0);
	}

}
