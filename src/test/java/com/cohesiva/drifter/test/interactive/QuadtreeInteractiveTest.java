/**
 * 
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

/**
 * @author bkarmelita
 * 
 */
public class QuadtreeInteractiveTest extends Applet {

	/**
	 * The <code>serialVersionUID</code> stands for a ... TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The <code>SIZE</code> stands for an octree initial size (visual).
	 */
	private static final int SIZE = 700;
	
	/**
	 * The <code>DEPTH</code> stands for an octree max depth.
	 */
	private static final int MAX_DEPTH = 2;

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

		int size = SIZE + (int) (0.1 * SIZE);
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
		// rebuild tree
		quadtree.build(refLoc, 0, MAX_DEPTH);
		// repaint all
		this.repaint();

		return true;
	}

	private void initQuadtree(Graphics graph) {
		refLoc = new Location(50, 50, 0, DistanceUnit.LIGHT_YEAR);
		Square sq = new Square(0, 0, SIZE);
		SquareWithCircles sqit = new SquareWithCircles(0, 0, sq);
		quadtree = new Tree<SquareWithCircles>(sqit);
		visitor = new SquareTreePainter(graph);
	}

}
