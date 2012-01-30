/**
 * Copyright 2010 Simpatico.
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>IBoundingBox</code> defines an interface for the cubic bounding
 * volume.
 * 
 * @author bkarmelita
 * 
 */
public interface IBoundingBox {

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
	 * @param spatial
	 *            to check
	 * @return <code>true</code> if surrounded; <code>false</code> otherwise;
	 */
	public boolean isSurrounding(Location location, double padding);

}
