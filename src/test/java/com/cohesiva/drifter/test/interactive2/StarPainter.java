/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive2;

import java.util.ArrayList;
import java.util.List;

import com.cohesiva.drifter.common.IVisible;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.view.IView;
import com.cohesiva.drifter.view.IViewFactory;

/**
 * The <code>StarPainter</code> represents a quadtree visitor that paints stars.
 * 
 * @author carmel
 * 
 */
public class StarPainter implements ITreeNodeVisitor<IComplex> {

	/**
	 * The <code>viewFactory</code> stands for a view factory to produce views.
	 */
	private IViewFactory viewFactory;

	/**
	 * The <code>starsToPaint</code> stands for a collection of stars to be
	 * painted.
	 */
	private List<Star> starsToPaint = new ArrayList<Star>();

	/**
	 * Creates the new <code>SquareTreePainter</code> instance.
	 * 
	 * @param graph
	 */
	public StarPainter(IViewFactory viewFactory) {
		super();

		this.viewFactory = viewFactory;
	}

	@Override
	public void visit(ITreeNode<IComplex> node) {
		// TODO: implement this
	}

	/**
	 * Resets this visitor.
	 */
	public void reset() {
		this.starsToPaint.clear();
	}

	/**
	 * Paint all collected shapes.
	 */
	public void doPainting() {
		// {{ paint stars
		for (Star star : this.starsToPaint) {
			IView<IVisible> starView = viewFactory.produceView(star);
			starView.render(star);
		}
		// }}
	}

}
