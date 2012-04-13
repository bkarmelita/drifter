/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.population;

import com.cohesiva.drifter.split.ISplitable;


/**
 * The <code>IPopulable</code> represents a populable thing.
 * Every <code>IPopulable</code> can populate in the process of split.
 *
 * @author carmel
 *
 */
public interface IPopulable {
	
	/**
	 * Gives the configured population strategy for this complex.
	 * 
	 * @return the current population strategy
	 */
	public <T extends ISplitable> IPopulationStrategy<T> populationStrategy();

}
