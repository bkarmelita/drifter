/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.test.interactive.shapes.Square;
import com.cohesiva.drifter.view.awt.LocationTransform;

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
	private Tree<SquareWithCircles> quadtree;

	/**
	 * The <code>visitor</code> stands for a quadtree visitor.
	 */
	private SquareTreePainter visitor;

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
		// {{ save reference location and transofrm it
		refLoc = new Location(x, y, 0, DistanceUnit.LIGHT_YEAR);
		Location realLoc = LocationTransform.toRealLocation(refLoc);
		// }}
		
		// rebuild tree
		quadtree.build(realLoc, 0, MAX_DEPTH);
		
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
		// the first root square is of screen size
		Square sq = new Square(centerLoc, LocationTransform.SCREEN_SIZE);
		// the first root complex has idx 0 and depth 0
		SquareWithCircles sqit = new SquareWithCircles(0, 0, sq);
		// the quad tree is created
		quadtree = new Tree<SquareWithCircles>(sqit);
		// painter is prepared
		visitor = new SquareTreePainter(graph);
	}

}
