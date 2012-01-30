/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.stellar.BoundingBox;
import com.cohesiva.drifter.stellar.ISpace;
import com.cohesiva.drifter.stellar.ProceduralSpace;
import com.cohesiva.drifter.stellar.Space;
import com.cohesiva.drifter.stellar.SpaceFactory;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * The <code>OctreeTest</code> represents octree test.
 * 
 * @author bkarmelita
 * 
 */
public class OctreeTest {
	private double galaxyRadius = 50000; 
	private Random random = new Random();
	private Location targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
	private SpaceFactory spaceFactory = SpaceFactory.getInstance(Space.class);
	private ISpace space;

	@Before
	public void setUp() {
		space = spaceFactory.produceSpace();
		
		space.addStellar(new Star(StarClass.O, new Location(-galaxyRadius * 2, -galaxyRadius * 2, -galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(galaxyRadius * 2, -galaxyRadius * 2, -galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(-galaxyRadius * 2, galaxyRadius * 2, -galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(galaxyRadius * 2, galaxyRadius * 2, -galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		
		space.addStellar(new Star(StarClass.O, new Location(-galaxyRadius * 2, -galaxyRadius * 2, galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(galaxyRadius * 2, -galaxyRadius * 2, galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(-galaxyRadius * 2, galaxyRadius * 2, galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
		space.addStellar(new Star(StarClass.O, new Location(galaxyRadius * 2, galaxyRadius * 2, galaxyRadius * 2, DistanceUnit.LIGHT_YEAR), 0));
	}
	
	@Test
	public void testDepth() {
		ITreeNode<ISpace> octree = new Tree<ISpace>(space);
		octree.build(targetLocation, 1);

		DepthMeter meter = new DepthMeter();
		octree.accept(meter);
		
		int depth = meter.maxDepth();
		assertTrue(depth <= 1);
	}
	
	@Test
	public void test10StarCount() {
		ITreeNode<ISpace> octree = new Tree<ISpace>(space);
		octree.build(targetLocation, 2);

		StellarCounter counter = new StellarCounter();
		octree.accept(counter);
		
		// stellar count at the top level should be 0
		assertEquals(0, counter.stellarCount(0));
		// stellar count at the 1 level should be 8
		assertEquals(0, counter.stellarCount(1));
		// there should be no star at the 2 level
		assertEquals(8, counter.stellarCount(2));
	}
	
	/**
	 * 100K stars distributed in the 2-depth octree. All stars in the corners.
	 * Should be distributed at 2-th depth level in 16 nodes (diagonally). 
	 */
	@Test
	public void test100KStarCount() {
		int stars = 100000;
		int maxDepth = 6;
		
		// {{ generate 100K stars
		for (int i = 0; i < 8; i++) {
			Location offsetCenter = BoundingBox.Offset.offset(space.bounds(), i);
			Location unitLocation = new Location(offsetCenter.x()/Math.abs(offsetCenter.x()), offsetCenter.y()/Math.abs(offsetCenter.y()), offsetCenter.z()/Math.abs(offsetCenter.z()), DistanceUnit.LIGHT_YEAR);
			
			for (int j = 0; j < stars/16; j++) {
				Location location = offsetCenter.add(new Location(random.nextDouble() * galaxyRadius * unitLocation.x(), random.nextDouble() * galaxyRadius * unitLocation.y(), random.nextDouble() * galaxyRadius * unitLocation.z(), DistanceUnit.LIGHT_YEAR));
				space.addStellar(new Star(StarClass.O, location, 0));
				
				location = offsetCenter.add(new Location(-random.nextDouble() * galaxyRadius * unitLocation.x(), -random.nextDouble() * galaxyRadius * unitLocation.y(), -random.nextDouble() * galaxyRadius * unitLocation.z(), DistanceUnit.LIGHT_YEAR));
				space.addStellar(new Star(StarClass.O, location, 0));
			}
		}
		// }}
		
		ITreeNode<ISpace> octree = new Tree<ISpace>(space);
		octree.build(targetLocation, maxDepth);

		StellarCounter counter = new StellarCounter();
		octree.accept(counter);
		
		int totalStellarCount = 0;
		int totalNodeCount = 0;
		for (int i = 0; i <= maxDepth; i++) {
			int stellarCount = counter.stellarCount(i);
			int nodeCount = counter.nodeCount(i);
			int maxNodes = (int) Math.pow(8, i);
			
			totalStellarCount += stellarCount;
			totalNodeCount += nodeCount;
			
			assertTrue(nodeCount <= maxNodes);
			System.out.println("Depth " + i + "(" + maxNodes + ")" + ": stars " + stellarCount + ", nodes " + nodeCount);
		}
		
		System.out.println("-----------");
		System.out.println("Total stars " + totalStellarCount + ", Total nodes " + totalNodeCount);
	}

	@Test
	@Ignore
	public void testOctree() {
		Location targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		SpaceFactory spaceFactory = SpaceFactory.getInstance(ProceduralSpace.class);
		
		ISpace space = spaceFactory.produceSpace();
		ITreeNode<ISpace> octree = new Tree<ISpace>(space);
		octree.build(targetLocation, 1);

		DepthMeter meter = new DepthMeter();
		octree.accept(meter);
		
		int depth = meter.maxDepth();
		assertTrue(depth <= 1);
		
		StellarCounter counter = new StellarCounter();
		octree.accept(counter);
		
		int totalStellarCount = 0;
		int totalNodeCount = 0;
		for (int i = 0; i <= depth; i++) {
			int stellarCount = counter.stellarCount(i);
			int nodeCount = counter.nodeCount(i);
			int maxNodes = (int) Math.pow(8, i);
			
			totalStellarCount += stellarCount;
			totalNodeCount += nodeCount;
			
			assertTrue(nodeCount <= maxNodes);
			System.out.println("Depth " + i + "(" + maxNodes + ")" + ": stars " + stellarCount + ", nodes " + nodeCount);
		}
		
		System.out.println("-----------");
		System.out.println("Total stars " + totalStellarCount + ", Total nodes " + totalNodeCount);
	}

}
