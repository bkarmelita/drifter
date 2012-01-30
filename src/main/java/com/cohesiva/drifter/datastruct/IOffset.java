/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>IOffset</code> represents ... TODO
 *
 * @author bkarmelita
 *
 */
public interface IOffset {
	
	/*
	 * The <code>HALF</code> stands for a splitting factor.
	 */
	public static final double HALF = 0.5;
	
	/**
	 * Gives an offset location of the given source location
	 * 
	 * @param source 
	 * @return
	 */
	public Location offset(DistanceUnit unit);
	
	/**
	 * Gives an offset index.
	 * 
	 * @return the offset index
	 */
	public int offsetIndex();

}
