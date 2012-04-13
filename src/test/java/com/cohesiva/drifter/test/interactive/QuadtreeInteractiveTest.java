/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.datastruct.visitor.TreePainter;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.containers.BoundingSquare;
import com.cohesiva.drifter.split.containers.Plane;
import com.cohesiva.drifter.split.criteria.InsideContainerSplitCriteria;
import com.cohesiva.drifter.test.interactive.population.StarPopulationStrategy;
import com.cohesiva.drifter.test.interactive.view.AWTViewFactory;
import com.cohesiva.drifter.test.interactive.view.LocationTransform;

/**
 * The <code>QuadtreeInteractiveTest</code> represents an interactive test of a
 * quadtree implementation.
 * 
 * @author carmel
 * 
 */
public class QuadtreeInteractiveTest extends Applet {

	/**
	 * The <code>serialVersionUID</code> stands for a class serial UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The <code>DEPTH</code> stands for an octree max depth.
	 */
	private static final int MAX_DEPTH = 6;

	/**
	 * The <code>image</code> stands for a painting buffer.
	 */
	private Image offscreenImage;

	/**
	 * The <code>offscreen</code> stands for an offscreen graphics.
	 */
	private Graphics offscreen;

	/**
	 * The <code>quadtree</code> stands for an internal quadtree.
	 */
	private Tree<Plane> quadtree;
	
	/**
	 * The <code>splitCriteria</code> stands for a ... TODO
	 */
	private ISplitCriteria<Plane> splitCriteria = new InsideContainerSplitCriteria<Plane>();
	
	/**
	 * The <code>populationStrategy</code> stands for a ... TODO
	 */
	private IPopulationStrategy<Plane> populationStrategy = new StarPopulationStrategy();

	/**
	 * The <code>visitor</code> stands for a quadtree visitor.
	 */
	private TreePainter<Plane> visitor;

	/**
	 * The <code>refLoc</code> stands for a reference location (mouse).
	 */
	private Location refLoc;

	@Override
	public void init() {
		super.init();
		
		// {{ set size and color of screen
		this.setBackground(Color.WHITE);
		this.setSize(LocationTransform.SCREEN_SIZE, LocationTransform.SCREEN_SIZE);
		// }}

		// {{ prepare offscreen image for performance
		this.offscreenImage = this.createImage(LocationTransform.SCREEN_SIZE, LocationTransform.SCREEN_SIZE);
		offscreen = this.offscreenImage.getGraphics();
		// }}

		// initialize quadtree stuff
		initQuadtree(offscreen);
	}

	@Override
	public void update(Graphics graph) {
		this.paint(graph);
	}

	@Override
	public void paint(Graphics graph) {
		// {{ activate visitor
		visitor.reset();
		quadtree.accept(visitor);
		visitor.doPainting();
		// }}
		
		// than draw offscreen
		graph.drawImage(offscreenImage, 0, 0, this);
	}

	@Override
	public boolean mouseMove(Event e, int x, int y) {
		// {{ save reference location and transform it
		refLoc = new Location(x, y, 0, DistanceUnit.LIGHT_YEAR);
		Location realLoc = LocationTransform.toRealLocation(refLoc);
		// }}
		
		// rebuild tree
		quadtree.build(realLoc, splitCriteria, populationStrategy, 0, MAX_DEPTH);
		
		// repaint all
		this.repaint();

		return true;
	}

	/**
	 * Initialize all stuff for quadtree
	 * 
	 * @param graph
	 */
	private void initQuadtree(Graphics graph) {
		// we arbitrarily start at 50,50
		refLoc = new Location(50, 50, 0, DistanceUnit.LIGHT_YEAR);
		// center is at 0,0 obviously
		Location centerLoc = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		// the first root bounding is of screen size
		BoundingSquare sq = new BoundingSquare(centerLoc, LocationTransform.SCREEN_SIZE/2, 0);
		// the first root complex has idx 0 and depth 0
		Plane root = new Plane(sq, 0, new Random(0));
		// the quad tree is created
		quadtree = new Tree<Plane>(root);
		// painter is prepared
		AWTViewFactory vf = new AWTViewFactory(graph);
		visitor = new TreePainter<Plane>(vf);
	}

}
