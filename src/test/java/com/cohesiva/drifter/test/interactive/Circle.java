/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.awt.Graphics;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>Circle</code> defines a circle.
 * 
 * @author bkarmelita
 * 
 */
public class Circle implements IPaintable {

	/**
	 * The <code>locx</code> stands for a center x coord.
	 */
	private int locx;

	/**
	 * The <code>locy</code> stands for a center y coord.
	 */
	private int locy;

	/**
	 * The <code>radius</code> stands for a circle radius.
	 */
	private int radius;

	/**
	 * Creates the new <code>Circle</code> instance.
	 * 
	 */
	public Circle(Location center, int radius) {
		this((int) center.x(), (int) center.y(), radius);
	}

	/**
	 * Creates the new <code>Circle</code> instance.
	 * 
	 */
	public Circle(int locx, int locy, int radius) {
		super();

		this.locx = locx;
		this.locy = locy;
		this.radius = radius;
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
	 * Gets the current value of <code>radius</code>.
	 * 
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the new value for <code>radius</code>.
	 * 
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.interactive.IPaintable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics graph) {
		graph.setColor(DEFAULT_PAINT);
		graph.drawOval(locx, locy, radius, radius);
	}

}
