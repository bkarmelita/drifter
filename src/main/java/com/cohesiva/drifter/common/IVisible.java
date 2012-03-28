/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.common;

/**
 * The <code>IVisible</code> represents a visual thing. Every
 * <code>IVisible</code> can be seen and we can specify its location. It can also
 * be rendered by <code>IView</code>.
 * 
 * @author carmel
 * 
 */
public interface IVisible {

	/**
	 * Get the location of this visual.
	 * 
	 * @return
	 */
	public Location locate();

}
