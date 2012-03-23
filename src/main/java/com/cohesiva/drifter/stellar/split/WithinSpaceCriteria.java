/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.stellar.split;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.stellar.ISpace;

/**
 * The <code>WithinBoundsCriteria</code> represents an implementation of the
 * split criteria for ISpace. The split criteria evaluates for being inside the
 * given space. An extra padding is included.
 * 
 * @author carmel
 * 
 */
public class WithinSpaceCriteria implements ISplitCriteria<ISpace> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ISplitCriteria#shouldSplit(com.cohesiva
	 * .drifter.datastruct.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(ISpace space, Location referenceLocation,
			int threshold) {
		return space.bounds().splitCriteria().evaluate(space.bounds(), referenceLocation, threshold);
	}

}
