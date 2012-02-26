/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.stellar.IBoundingBox;
import com.cohesiva.drifter.stellar.IStellarConstants;
import com.cohesiva.drifter.stellar.Space;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * The <code>OctreeTest</code> represents octree test.
 * 
 * @author bkarmelita
 * 
 */
public class OctreeTest {
	private double galaxyRadius = IStellarConstants.GALAXY_RADIUS.value(); 
	private Location targetLocation = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
	private Space space;
	private IBoundingBox box;

	@Before
	public void setUp() {
		box = IStellarConstants.GALAXY_BOUNDS;
		space = new Space(box);
		
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
		ITreeNode<Space> octree = new Tree<Space>(space);
		octree.build(targetLocation, 1);

		DepthMeter meter = new DepthMeter();
		octree.accept(meter);
		
		int depth = meter.maxDepth();
		assertTrue(depth <= 1);
	}
	
	@Test
	public void test10StarCount() {
		ITreeNode<Space> octree = new Tree<Space>(space);
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
	
	@Test
	public void testIndex() {
		ITreeNode<Space> octree = new Tree<Space>(space);
		octree.build(targetLocation, 2);

		TreeIndexVerifier idxVerifier = new TreeIndexVerifier();
		octree.accept(idxVerifier);
	}

	@Test
	@Ignore
	public void testOctree() {
		ITreeNode<Space> octree = new Tree<Space>(space);
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
