/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view.awt;

import java.awt.Color;
import java.awt.Graphics;

import com.cohesiva.drifter.common.IVisible;
import com.cohesiva.drifter.view.IView;

/**
 * The <code>AWTAbstractView</code> represents a base AWT <code>IView</code>.
 * 
 * @author carmel
 * 
 */
public abstract class AWTAbstractView<T extends IVisible> implements IView<T> {

	public static final Color DEFAULT_PAINT = Color.BLACK;
	public static final Color DEFAULT_FILL = Color.WHITE;

	/**
	 * The <code>graphics</code> is where we render views in AWT.
	 */
	protected Graphics graphics;

	/**
	 * Creates the new <code>AWTAbstractView</code> instance.
	 */
	protected AWTAbstractView() {
		super();
	}

	/**
	 * Creates the new <code>AWTAbstractView</code> instance.
	 * 
	 * @param graphics
	 */
	protected AWTAbstractView(Graphics graphics) {
		super();
		this.graphics = graphics;
	}

	/**
	 * Sets up the graphics for rendering.
	 * 
	 * @param graphics
	 */
	protected void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

}
