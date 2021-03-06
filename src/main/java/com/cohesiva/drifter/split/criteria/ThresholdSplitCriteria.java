/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split.criteria;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.ISplitable;

/**
 * The <code>ThresholdSplitCriteria</code> represents the default split criteria
 * for every complex. The split criteria evaluates not to exceed the complexity
 * over the given threshold.
 * 
 * @author carmel
 * 
 */
public class ThresholdSplitCriteria<T extends ISplitable> implements
		ISplitCriteria<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ISplitCriteria#shouldSplit(com.cohesiva
	 * .drifter.datastruct.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(T complex, Location referenceLocation, int threshold) {
		return complex.complexity() >= threshold;
	}

}
