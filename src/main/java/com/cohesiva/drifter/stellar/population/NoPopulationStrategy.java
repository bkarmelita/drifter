/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.stellar.population;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.IComplex;
import com.cohesiva.drifter.datastruct.IPopulationStrategy;

/**
 * The <code>NoPopulationStrategy</code> represents the zero population strategy
 * for complex where nothing is populated. This strategy is the default for
 * every complex.
 * 
 * @author bkarmelita
 * 
 */
public class NoPopulationStrategy implements IPopulationStrategy<IComplex> {

	@Override
	public void populate(IComplex complex, Location referenceLocation) {
		// do not populate anything
	}

}
