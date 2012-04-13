/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.population.strategies;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.Container;

/**
 * The <code>NoPopulationStrategy</code> represents the zero population strategy
 * for complex where nothing is populated. This strategy is the default for
 * every complex.
 * 
 * @author carmel
 * 
 */
public class NoPopulationStrategy<T extends Container> implements
		IPopulationStrategy<T> {

	@Override
	public void populate(T container, Location referenceLocation) {
		// do not populate anything
	}

}
