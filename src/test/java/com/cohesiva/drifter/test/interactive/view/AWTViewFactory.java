/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.cohesiva.drifter.split.containers.BoundingSquare;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.view.IView;
import com.cohesiva.drifter.view.IViewFactory;
import com.cohesiva.drifter.view.IVisible;

/**
 * The <code>AWTViewFactory</code> represents an implementation of the
 * <code>IViewFactory</code> for Abstract Window Toolkit visualization.
 * 
 * @author carmel
 * 
 */
public class AWTViewFactory implements IViewFactory {

	// FIXME: unchecked
	private static Map<Class, IView> viewMap = new HashMap<Class, IView>();

	/**
	 * The <code>graphics</code> is where we render views in AWT.
	 */
	protected Graphics graphics;

	/**
	 * Creates the new <code>AWTViewFactory</code> instance.
	 * 
	 * @param graphics
	 */
	public AWTViewFactory(Graphics graphics) {
		super();
		this.graphics = graphics;
		
		viewMap.put(Star.class, new AWTStarView(graphics));
		viewMap.put(BoundingSquare.class, new AWTBoundingSquareView(graphics));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.view.IViewFactory#produceView(com.cohesiva.drifter
	 * .stellar.IStellar)
	 */
	@Override
	public IView<IVisible> produceView(IVisible stellar) {
		// FIXME: unchecked get
		IView<IVisible> view = viewMap.get(stellar.getClass());

		return view;
	}

}
