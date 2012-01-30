/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Location;


/**
 * The <code>ISpacePopulationStrategy</code> defines an interface for the
 * procedural population strategy for filling the splitting space.
 * 
 * @author simpatico
 * 
 */
public interface ISpacePopulationStrategy {

	/**
	 * Gets the required population for the specified space depth.
	 * 
	 * @param depth
	 *            the target depth to calculate population for
	 * @return the space population for given depth
	 */
	public int populationAt(int depth);

	/**
	 * Populate the given space considering the reference location.
	 * 
	 * @param space
	 * @param referenceLocation
	 */
	public void populate(ISpace space, Location referenceLocation);

}
