/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split.criteria;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.Container;
import com.cohesiva.drifter.split.ISplitCriteria;

/**
 * The <code>InsideContainerSplitCriteria</code> represents an implementation of the
 * split criteria for IBounding. The split criteria evaluates for being inside
 * the given bounds. An extra padding is included.
 * 
 * @author carmel
 * 
 */
public class InsideContainerSplitCriteria<T extends Container> implements ISplitCriteria<T> {

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
	public boolean evaluate(T container, Location referenceLocation,
			int threshold) {
		return container.bounds().isSurrounding(referenceLocation, padding);
	}

}
