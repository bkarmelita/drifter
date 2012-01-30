/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>IPopulationStrategy</code> defines an interface for the population
 * strategy.
 * 
 * @author bkarmelita
 * 
 */
public interface IPopulationStrategy<T extends IComplex> {

	/**
	 * Populates the given complex.
	 * 
	 * @param complex
	 * @param referenceLocation
	 */
	public void populate(T complex, Location referenceLocation);

}
