/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view.awt;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.cohesiva.drifter.stellar.IStellar;
import com.cohesiva.drifter.view.IView;
import com.cohesiva.drifter.view.IViewFactory;

/**
 * The <code>AWTViewFactory</code> represents an implementation of the
 * <code>IViewFactory</code> for Abstract Window Toolkit visualization.
 * 
 * @author carmel
 * 
 */
public class AWTViewFactory implements IViewFactory {

	// FIXME: unchecked put
	private static Map<String, IView> viewMap = new HashMap<String, IView>() {
		{
			put(AWTBoundingSquareView.class.getName(),
					new AWTBoundingSquareView());
			put(AWTStarView.class.getName(), new AWTStarView());
		}
	};

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.view.IViewFactory#produceView(com.cohesiva.drifter
	 * .stellar.IStellar)
	 */
	@Override
	public IView<IStellar> produceView(IStellar stellar) {
		// FIXME: unchecked get
		IView<IStellar> view = viewMap.get(stellar.getClass().getName());

		return view;
	}

}
