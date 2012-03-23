/**
 * 
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>StarJumpProbabilityGives</code> defines an interface of function
 * that computes the probability of a successful jump to the star. Star mass and
 * distance to the star are essential factors of computation.
 * 
 * @author carmel
 * 
 */
public interface IStarJumpProbability {

	/**
	 * Computes the probability of the successful jump to the given potential
	 * star. The probability is a function of star mass and distance to that
	 * star. Massive stars are accessible even if they are very far. Small stars
	 * are potentially accessible (of higher jump probability) if we are very
	 * close to them. If the probability is very small (less than lets say 20%)
	 * than the star should never be visible nor in memory.
	 * 
	 * @param relativeLocaton
	 *            the location the distance to the star is taken from
	 * @param starLocation
	 *            the target potential star location
	 * @param starMass
	 *            the target potential star mass
	 * @param starClass
	 *            the target potential star class
	 * 
	 * @return the probability how probable the jump is
	 */
	public int compute(Location relativeLocaton, Location starLocation,
			double starMass, StarClass starClass);

}
