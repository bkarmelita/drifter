/**
 * Copyright 2011 cohesiva.com.
 */
package com.cohesiva.drifter.common;

import java.util.Random;

/**
 * The <code>RandomLocation</code> represents the random spatial location. 
 *
 * @author carmel
 *
 */
public class RandomLocation extends Location {

	/**
	 * Creates the new <code>RandomLocation</code> instance.
	 *
	 * @param location
	 */
	public RandomLocation(Location minLocation, Location maxLocation, Random random) {
		super();
		
		double min = minLocation.x();
		double max = maxLocation.x();
		this.x = randomizeRange(min, max, random);
		
		min = minLocation.y();
		max = maxLocation.y();
		this.y = randomizeRange(min, max, random);
		
		min = minLocation.z();
		max = maxLocation.z();
		this.z = randomizeRange(min, max, random);
		
		this.unit = minLocation.getUnit();
	}
	
	/**
	 * Randomizes the value within a given range.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	private double randomizeRange(double min, double max, Random random) {
		double rand = min + random.nextDouble() * (max - min);
		
		return rand;
	}

}
