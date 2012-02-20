/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.shapes;

import java.awt.Color;
import java.awt.Graphics;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.test.interactive.IPaintable;
import com.cohesiva.drifter.test.interactive.LocationTransform;


/**
 * The <code>Rect</code> class defines a rectangle.
 * 
 * @author bkarmelita
 * 
 */
public class Rect implements IPaintable {
	
	/**
	 * The <code>center</code> stands for a rectangle center.
	 */
	private Location center;

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
	public Rect(Location center, int width, int height) {
		this(center, width, height, DEFAULT_FILL);
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
	public Rect(Location center, int width, int height, Color color) {
		super();
		
		this.center = center;
		this.width = width;
		this.height = height;
		this.color = color;
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
	
	/**
	 * Gets the center rect location.
	 * 
	 * @return
	 */
	public Location getCenter() {
		return this.center;
	}
	
	/**
	 * Gets the top left rect corner.
	 *  
	 * @return
	 */
	public Location getTopLeft() {
		int x = (int) this.center.x() - (int) (this.width * 0.5);
		int y = (int) this.center.y() + (int) (this.height * 0.5);
		
		return new Location(x, y, 0, this.center.getUnit());
	}

	@Override
	public void paint(Graphics graph) {
		Location display = LocationTransform.toDisplayLocation(this.getTopLeft());
		
		graph.setColor(DEFAULT_FILL);
		//graph.fillRect((int) display.x(), (int) display.y(), this.width, this.height);
		graph.setColor(DEFAULT_PAINT);
		graph.drawRect((int) display.x(), (int) display.y(), this.width, this.height);
	}

}
