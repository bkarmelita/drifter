/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.awt.Color;
import java.awt.Graphics;

import com.cohesiva.drifter.common.Location;


/**
 * The <code>Rect</code> class defines a rectangle.
 * 
 * @author bkarmelita
 * 
 */
public class Rect implements IPaintable {

	/**
	 * The <code>locx</code> stands for a to left x.
	 */
	private int locx;

	/**
	 * The <code>locy</code> stands for a top left y.
	 */
	private int locy;

	/**
	 * The rectangle width;
	 */
	private int width;

	/**
	 * The rectangle height.
	 */
	private int height;

	/**
	 * The rectangle color.
	 */
	private Color color = DEFAULT_FILL;
	
	/**
	 * Creates the new <code>Rect</code> instance.
	 * 
	 * @param locx
	 * @param locy
	 * @param width
	 * @param height
	 */
	public Rect(Location topLeft, int width, int height) {
		this((int) topLeft.x(), (int) topLeft.y(), width, height, DEFAULT_FILL);
	}

	/**
	 * Creates the new <code>Rect</code> instance.
	 * 
	 * @param locx
	 * @param locy
	 * @param width
	 * @param height
	 */
	public Rect(int locx, int locy, int width, int height) {
		this(locx, locy, width, height, DEFAULT_FILL);
	}

	/**
	 * Creates this <code>Rect</code>.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public Rect(int x, int y, int width, int height, Color color) {
		super();
		this.locx = x;
		this.locy = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	/**
	 * Gets the current value of <code>locx</code>.
	 * 
	 * @return the locx
	 */
	public int getLocx() {
		return locx;
	}

	/**
	 * Sets the new value for <code>locx</code>.
	 * 
	 * @param locx
	 *            the locx to set
	 */
	public void setLocx(int locx) {
		this.locx = locx;
	}

	/**
	 * Gets the current value of <code>locy</code>.
	 * 
	 * @return the locy
	 */
	public int getLocy() {
		return locy;
	}

	/**
	 * Sets the new value for <code>locy</code>.
	 * 
	 * @param locy
	 *            the locy to set
	 */
	public void setLocy(int locy) {
		this.locy = locy;
	}

	/**
	 * Gets the current value of <code>width</code>.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the new value for <code>width</code>.
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the current value of <code>height</code>.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the new value for <code>height</code>.
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the current value of <code>color</code>.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the new value for <code>color</code>.
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void paint(Graphics graph) {
		graph.setColor(this.color);
		graph.fillRect(this.locx, this.locy, this.width, this.height);
		graph.setColor(DEFAULT_PAINT);
		graph.drawRect(this.locx, this.locy, this.width, this.height);
	}

}
