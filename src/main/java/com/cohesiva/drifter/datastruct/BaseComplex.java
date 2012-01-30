/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>BaseComplex</code> represents a base implementation for the
 * <code>IComplex</code> interface.
 * 
 * @author bkarmelita
 * 
 */
public abstract class BaseComplex implements IComplex {

	/**
	 * The <code>depth</code> stands for a birth depth.
	 */
	protected int depth;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#splitCriteria()
	 */
	/*@Override
	public ISplitCriteria<IComplex> splitCriteria() {
		return IComplex.DEFAULT_SPLIT_CRITERIA;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#split(com.cohesiva.drifter.common
	 * .Location)
	 */
	@Override
	public IComplex[] split(Location referenceLocation) {
		// prepare bounding box placeholders for child octants
		IComplex[] splitted = new IComplex[this.splitDegree().value()];

		// {{ iterate through offsets
		for (IOffset offset : this.splitDegree().offsets()) {
			// create new bounding box for the subsequent child octant
			IComplex bounds = this.splitOne(referenceLocation, offset);
			// store bounding box in placeholder
			splitted[offset.offsetIndex()] = bounds;
		}
		// }}

		return splitted;
	}

	/**
	 * TODO:
	 * 
	 * @param referenceLocation
	 * @param offset
	 * @return
	 */
	protected abstract IComplex splitOne(Location referenceLocation,
			IOffset offset);

}
