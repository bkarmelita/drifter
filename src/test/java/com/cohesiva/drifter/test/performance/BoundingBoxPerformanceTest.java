/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.performance;

import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.stellar.BoundingBox;
import com.cohesiva.drifter.stellar.IBoundingBox;
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
	private IBoundingBox box100K;
	private IBoundingBox box50K;
	private ISplitContext ctx;
	private Random random = new Random();

	@Before
	public void setUp() {
		// mock the split context since BoundingBox does not make much use of it
		ctx = mock(ISplitContext.class);
		
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
		SplitDegree splitDegree = box100K.splitDegree();
		for (IOffset offset : splitDegree.offsets()) {
			box100K.onSplit(ctx, offset);
		}
	}
	
	@Test
	public void test50KStarsOneSlit() {
		SplitDegree splitDegree = box100K.splitDegree();
		for (IOffset offset : splitDegree.offsets()) {
			box50K.onSplit(ctx, offset);
		}
	}
	
	

}
