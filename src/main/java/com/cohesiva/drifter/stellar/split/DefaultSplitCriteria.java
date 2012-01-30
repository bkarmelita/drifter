/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.stellar.split;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.IComplex;
import com.cohesiva.drifter.datastruct.ISplitCriteria;

/**
 * The <code>DefaultSplitStrategy</code> represents the default split criteria
 * for every complex. The split criteria evaluates for not exceeding the
 * complexity over the given threshold.
 * 
 * @author bkarmelita
 * 
 */
public class DefaultSplitCriteria implements ISplitCriteria<IComplex> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ISplitCriteria#shouldSplit(com.cohesiva
	 * .drifter.datastruct.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(IComplex complex, Location referenceLocation,
			int threshold) {
		return complex.complexity() >= threshold;
	}

}
