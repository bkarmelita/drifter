/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.terrain;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;

/**
 * The <code>IBoundingSquare</code> defines an interface for the rectangular
 * bounding area.
 * 
 * @author cohesiva
 * 
 */
public interface IBoundingSquare extends IComplex {
	
	/**
	 * Get the bounding box center location.
	 * 
	 * @return center location
	 */
	public Location center();

	/**
	 * Get the bounding box volume radius.
	 * 
	 * @return radius
	 */
	public double radius();

	/**
	 * Checks if the given location is surrounded by this bounds.
	 * 
	 * @param location
	 *            to check
	 * @return <code>true</code> if surrounded; <code>false</code> otherwise;
	 */
	public boolean isSurrounding(Location location, double padding);

}
