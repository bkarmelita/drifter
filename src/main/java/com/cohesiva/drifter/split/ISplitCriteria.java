/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ISplitCriteria</code> defines an interface for an abstract split
 * criteria.
 * 
 * @author carmel
 */
public interface ISplitCriteria<T extends ISplitable> {

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
