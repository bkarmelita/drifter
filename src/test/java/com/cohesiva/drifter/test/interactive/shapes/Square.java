/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.shapes;

import java.awt.Color;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>Square</code> represents a square.
 * 
 * @author bkarmelita
 * 
 */
public class Square extends Rect {
	
	/**
	 * Creates the new <code>Square</code> instance.
	 * 
	 * @param locx
	 * @param locy
	 * @param width
	 * @param height
	 */
	public Square(Location center, int size) {
		this(center, size, DEFAULT_FILL);
	}

	/**
	 * Creates the new <code>Square</code> instance.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public Square(Location center, int size, Color color) {
		super(center, size, size, color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.interactive.common.Rect#setWidth(int)
	 */
	@Override
	public void setWidth(int width) {
		this.setSize(width);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.interactive.common.Rect#setHeight(int)
	 */
	@Override
	public void setHeight(int height) {
		this.setSize(height);
	}

	/**
	 * Sets the square size.
	 * 
	 * @param size
	 */
	protected void setSize(int size) {
		super.setWidth(size);
		super.setHeight(size);
	}

}
