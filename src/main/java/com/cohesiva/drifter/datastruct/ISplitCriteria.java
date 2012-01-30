/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ISplitCriteria</code> defines an interface for an abstract split
 * criteria.
 * 
 * @author bkarmelita
 * 
 * @param <T>
 */
public interface ISplitCriteria<T extends IComplex> {

	/**
	 * Determines if the given context should be split into parts.
	 * 
	 * @param complex
	 * @param referenceLocation
	 * @param threshold
	 * @return
	 */
	public boolean evaluate(T complex, Location referenceLocation,
			int threshold);

}
