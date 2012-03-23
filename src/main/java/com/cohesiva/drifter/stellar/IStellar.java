/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ISpatial</code> represents any stellar entity (star) with its
 * spatial location.
 * 
 * @author carmel
 * 
 */
public interface IStellar {

	/**
	 * Get the location of this stellar entity.
	 * 
	 * @return
	 */
	public Location locate();

	/**
	 * Get the depth this entity was born at.
	 */
	public int depth();

}
