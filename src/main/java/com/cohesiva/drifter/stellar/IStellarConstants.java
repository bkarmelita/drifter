package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Distance;
import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;


/**
 * The <code>IStellarConstants</code> encapsulates globally used stellar constants.
 * 
 * @author bkarmelita
 *
 */
public interface IStellarConstants {
	
	/**
	 * The unit of star mass.
	 */
	public static final int SUN_MASS = 1;
	
	/**
	 * The unit of star diameter.
	 */
	public static final int SUN_DIAMETER = 1;
	
	/**
	 * The maximum known star mass.
	 * One of the most massive stars known is Eta Carinae, with 100–150 times as
	 * much mass as the Sun.
	 */
	public static final int MAXIMUM_KNOWN_STAR_MASS = 150 * SUN_MASS ;
	
	/**
	 * The maximum known star diameter.
	 * Betelgeuse in the Orion constellation has a diameter approximately 650
	 * times larger than the Sun Mass.
	 */
	public static final int MAXIMUM_KNOWN_STAR_DIAMETER = 650 * SUN_DIAMETER;
	
	/**
	 * The galaxy size in light years.
	 */
	public static final Distance GALAXY_RADIUS = new Distance(50000,
			DistanceUnit.LIGHT_YEAR);
	
	/**
	 * The galaxy bounds.
	 */
	public static final IBoundingBox GALAXY_BOUNDS = new BoundingBox(
			Location.GALAXY_ORIGIN, GALAXY_RADIUS.value(), 0);

}
