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
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.datastruct.Tree;
import com.cohesiva.drifter.test.interactive.shapes.Square;

/**
 * The <code>QuadtreeInteractiveTest</code> represents an interactive test of a
 * quadtree implementation.
 * 
 * @author bkarmelita
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
	ITreeNodeVisitor<SquareWithCircles> visitor;

	/**
	 * The <code>refLoc</code> stands for a reference location (mouse).
	 */
	private Location refLoc;

	@Override
	public void init() {
		super.init();

		int size = LocationTransform.SCREEN_SIZE;
		this.setBackground(Color.WHITE);
		this.setSize(size, size);

		this.offscreenImage = this.createImage(size, size);
		offscreen = this.offscreenImage.getGraphics();

		initQuadtree(offscreen);
	}

	@Override
	public void update(Graphics graph) {
		this.paint(graph);
	}

	@Override
	public void paint(Graphics graph) {
		// run visitor
		quadtree.accept(visitor);
		// draw offscreen
		graph.drawImage(offscreenImage, 0, 0, this);
	}

	@Override
	public boolean mouseMove(Event e, int x, int y) {
		// save reference location
		refLoc = new Location(x, y, 0, DistanceUnit.LIGHT_YEAR);
		Location realLoc = LocationTransform.toRealLocation(refLoc);

		// rebuild tree
		quadtree.build(realLoc, 0, MAX_DEPTH);
		// repaint all
		this.repaint();

		return true;
	}

	private void initQuadtree(Graphics graph) {
		refLoc = new Location(50, 50, 0, DistanceUnit.LIGHT_YEAR);
		Location centerLoc = new Location(0, 0, 0, DistanceUnit.LIGHT_YEAR);
		Square sq = new Square(centerLoc, LocationTransform.SCREEN_SIZE);
		SquareWithCircles sqit = new SquareWithCircles(0, 0, sq);
		quadtree = new Tree<SquareWithCircles>(sqit);
		visitor = new SquareTreePainter(graph);
	}

}
