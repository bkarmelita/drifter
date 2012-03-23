/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The <code>IPaintable</code> represents a paintable shape.
 * 
 * @author carmel
 */
public interface IPaintable {
	
	public static final Color DEFAULT_PAINT = Color.BLACK;
	public static final Color DEFAULT_FILL = Color.WHITE;

	/**
	 * Paints this paintable shape.
	 * 
	 * @param graph
	 */
	public void paint(Graphics graph);

}
