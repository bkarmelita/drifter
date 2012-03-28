/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.common;

/**
 * The <code>IPopulable</code> represents a populable thing.
 * Every <code>IPopulable</code> can populate in the process of split.
 *
 * @author carmel
 *
 */
public interface IPopulable {
	
	/**
	 * Get the population origin depth.
	 */
	public int depth();

}
