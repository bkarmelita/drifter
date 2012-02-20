/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.terrain.split;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.terrain.IBoundingSquare;

/**
 * The <code>WithinBoundingSquareCriteria</code> represents an implementation of
 * the split criteria for IBoundingSquare. The split criteria evaluates for
 * being inside the given bounds. An extra padding is included.
 * 
 * @author bkarmelita
 * 
 */
public class WithinBoundingSquareCriteria implements
		ISplitCriteria<IBoundingSquare> {

	/**
	 * The <code>padding</code> stands for a padding.
	 */
	private double padding = 0.1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.ISplitCriteria#evaluate(com.cohesiva.drifter
	 * .split.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(IBoundingSquare bounds, Location referenceLocation,
			int threshold) {
		return bounds.isSurrounding(referenceLocation, padding);
	}

}
