/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.awt.Graphics;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.test.interactive.shapes.Circle;

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
		node.item().square.paint(graph);
		for (Circle circle : node.item().circles) {
			circle.paint(graph);
		}
	}

}
