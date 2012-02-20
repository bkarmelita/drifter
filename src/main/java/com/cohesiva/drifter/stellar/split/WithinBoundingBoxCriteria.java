/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.stellar.split;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.stellar.IBoundingBox;

/**
 * The <code>WithinBoundsCriteria</code> represents an implementation of the
 * split criteria for IBoundingBox. The split criteria evaluates for being
 * inside the given bounds. An extra padding is included.
 * 
 * @author bkarmelita
 * 
 */
public class WithinBoundingBoxCriteria implements ISplitCriteria<IBoundingBox> {

	/**
	 * The <code>padding</code> stands for a padding.
	 */
	private double padding = 0.1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ISplitCriteria#shouldSplit(com.cohesiva
	 * .drifter.datastruct.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(IBoundingBox bounds, Location referenceLocation,
			int threshold) {
		return bounds.isSurrounding(referenceLocation, padding);
	}
}
