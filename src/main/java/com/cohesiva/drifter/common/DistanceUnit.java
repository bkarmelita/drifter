/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.common;

/**
 * The <code>DistanceUnit</code> represents distance unit types. We assume that
 * space can be measured by light years or kilometers. At the galaxy level we
 * use light years. At the star system level we use kilometers.
 * 
 * @author bkarmelita
 * 
 */
public enum DistanceUnit {

	/**
	 * Distance unit of spatial light years.
	 */
	LIGHT_YEAR,

	/**
	 * Distance unit of kilometers.
	 */
	KILOMETER;

}
