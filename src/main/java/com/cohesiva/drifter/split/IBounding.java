/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.view.IVisible;

/**
 * The <code>IBounding</code> defines an interface for an abstract boundary.
 * 
 * @author carmel
 * 
 */
public interface IBounding extends ISplitable, IVisible {

	/**
	 * Get the bounding center location.
	 * 
	 * @return center location
	 */
	public Location center();

	/**
	 * Get the bounding radius.
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
