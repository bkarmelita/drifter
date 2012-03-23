/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.population;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;

/**
 * The <code>IPopulationStrategy</code> defines an interface for the population
 * strategy.
 * 
 * @author carmel
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
