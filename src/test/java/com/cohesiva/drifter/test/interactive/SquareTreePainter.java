/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.test.interactive.shapes.Circle;
import com.cohesiva.drifter.test.interactive.shapes.Square;

/**
 * The <code>SquareTreePainter</code> represents a square quadtree painter
 * visitor.
 * 
 * @author bkarmelita
 * 
 */
public class SquareTreePainter implements ITreeNodeVisitor<SquareWithCircles> {

	/**
	 * The <code>graph</code> stands for a graphics.
	 */
	private Graphics graph;
	
	/**
	 * The <code>squaresToPaint</code> stands for a collection of squares to be painted.
	 */
	private List<Square> squaresToPaint = new ArrayList<Square>();
	
	/**
	 * The <code>circlesToPaint</code> stands for a collection of circles to be painted.
	 */
	private List<Circle> circlesToPaint = new ArrayList<Circle>();

	/**
	 * Creates the new <code>SquareTreePainter</code> instance.
	 * 
	 * @param graph
	 */
	public SquareTreePainter(Graphics graph) {
		super();
		this.graph = graph;
	}

	@Override
	public void visit(ITreeNode<SquareWithCircles> node) {
		this.squaresToPaint.add(node.item().square);
		this.circlesToPaint.addAll(node.item().circles);
	}
	
	/**
	 * Resets this visitor.
	 */
	public void reset() {
		this.squaresToPaint.clear();
		this.circlesToPaint.clear();
	}
	
	/**
	 * Paint all collected shapes.
	 */
	public void doPainting() {
		// {{ paint squares first
		for (Square square : this.squaresToPaint) {
			square.paint(graph);
		}
		// }}
		
		// {{ than paint circles
		for (Circle circle : this.circlesToPaint) {
			circle.paint(graph);
		}
		// }}
	}

}
