/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct.visitor;

import java.util.LinkedList;
import java.util.List;

import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.split.Container;
import com.cohesiva.drifter.split.IBounding;
import com.cohesiva.drifter.view.IView;
import com.cohesiva.drifter.view.IViewFactory;
import com.cohesiva.drifter.view.IVisible;

/**
 * The <code>TreePainter</code> represents a painting tree visitor.
 * 
 * @author carmel
 * 
 */
public class TreePainter<T extends Container> implements ITreeNodeVisitor<T> {

	/**
	 * The <code>viewFactory</code> stands for a view factory to produce views.
	 */
	private IViewFactory viewFactory;

	/**
	 * The <code>squaresToPaint</code> stands for a collection of bounds to be
	 * painted.
	 */
	private List<IBounding> boundsToPaint = new LinkedList<IBounding>();

	/**
	 * The <code>starsToPaint</code> stands for a collection of entities to be
	 * painted.
	 */
	private List<IStellar> entitiesToPaint = new LinkedList<IStellar>();

	/**
	 * Creates the new <code>TreePainter</code> instance.
	 * 
	 * @param graph
	 */
	public TreePainter(IViewFactory viewFactory) {
		super();

		this.viewFactory = viewFactory;
	}

	@Override
	public void visit(ITreeNode<T> node) {
		this.boundsToPaint.add(node.item().bounds());

		if (node.item().complexity() > 0) {
			this.entitiesToPaint.addAll(node.item().contents());
		}
	}

	/**
	 * Resets this visitor.
	 */
	public void reset() {
		this.boundsToPaint.clear();
		this.entitiesToPaint.clear();
	}

	/**
	 * Paint all collected shapes.
	 */
	public void doPainting() {
		// {{ paint bounds first
		for (IBounding bounds : this.boundsToPaint) {
			IView<IVisible> boundsView = viewFactory.produceView(bounds);
			boundsView.render(bounds);
		}
		// }}

		// {{ than paint entities
		for (IStellar stellar : this.entitiesToPaint) {
			IView<IVisible> entityView = viewFactory.produceView(stellar);
			entityView.render(stellar);
		}
		// }}
	}

}
