/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

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
	public Square(Location topLeft, int size) {
		this((int) topLeft.x(), (int) topLeft.y(), size, DEFAULT_FILL);
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
	public Square(int x, int y, int size) {
		super(x, y, size, size);
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
	public Square(int x, int y, int size, Color color) {
		super(x, y, size, size, color);
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

	/**
	 * Check if the given position is inside the specified square.
	 * 
	 * @param square
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isInside(Square square, int x, int y) {
		int minx = square.getLocx();
		int miny = square.getLocy();
		int maxx = square.getLocx() + square.getWidth();
		int maxy = square.getLocy() + square.getWidth();

		// {{ check location against max and min
		if (x < minx || x > maxx) {
			return false;
		}

		if (y < miny || y > maxy) {
			return false;
		}
		// }}

		return true;
	}

}
