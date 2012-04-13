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
import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.split.containers.BoundingBox;
import com.cohesiva.drifter.split.containers.Volume;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * The <code>OctreeStarBuildTest</code> represents octree StarBuild test.
 * 
 * @author carmel
 * 
 */
public class OctreePerformanceTest {

	private List<IStellar> stars100K = new LinkedList<IStellar>();
	private List<IStellar> stars50K = new LinkedList<IStellar>();
	private Location targetLocation = new Location(0, 0, 0,
			DistanceUnit.LIGHT_YEAR);
	private Random random = new Random();

	@Before
	public void setUp() {
		// {{ generate 100K stars
		for (int i = 0; i < 100000; i++) {
			Location location = new Location(random.nextDouble() * 100,
					random.nextDouble() * 100, random.nextDouble() * 100,
					DistanceUnit.LIGHT_YEAR);
			IStellar star = new Star(StarClass.O, location, 1);
			stars100K.add(star);
		}
		// }}

		// {{ generate 50K stars
		for (int i = 0; i < 50000; i++) {
			Location location = new Location(random.nextDouble() * 100,
					random.nextDouble() * 100, random.nextDouble() * 100,
					DistanceUnit.LIGHT_YEAR);
			IStellar star = new Star(StarClass.O, location, 1);
			stars50K.add(star);
		}
		// }}
	}

	@Test
	public void test100KStarBuildDepth3() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 3);
	}

	@Test
	public void test100KStarBuildDepth7() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 7);
	}

	@Test
	public void test100KStarBuildDepth12() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 12);
	}

	@Test
	public void test100KStarBuildThreshold10() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 3);
	}

	@Test
	public void test100KStarBuildThreshold100() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 7);
	}

	@Test
	public void test100KStarBuildThreshold1000() {
		BoundingBox bounds = BoundingBox.newInstance(stars100K);
		Volume volume = new Volume(stars100K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 12);
	}

	@Test
	public void test50KStarBuildDepth3() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 3);
	}

	@Test
	public void test50KStarBuildDepth7() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 7);
	}

	@Test
	public void test50KStarBuildDepth12() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 12);
	}

	@Test
	public void test50KStarBuildThreshold10() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 3);
	}

	@Test
	public void test50KStarBuildThreshold100() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 7);
	}

	@Test
	public void test50KStarBuildThreshold1000() {
		BoundingBox bounds = BoundingBox.newInstance(stars50K);
		Volume volume = new Volume(stars50K, bounds, 0, new Random(0));
		ITreeNode<Volume> octree = new Tree<Volume>(volume);
		octree.build(targetLocation, 12);
	}

}
