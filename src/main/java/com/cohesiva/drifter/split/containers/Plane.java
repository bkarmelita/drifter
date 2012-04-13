/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split.containers;

import java.util.Random;

import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.Container;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitable;

/**
 * The <code>Plane</code> represents a 2 dimensional container.
 * 
 * @author carmel
 * 
 */
public class Plane extends Container {

	/**
	 * Creates the new empty <code>Plane</code> instance.
	 * 
	 * @param bounds
	 */
	public Plane(BoundingSquare bounds, int depth, Random randomizer) {
		super(bounds, depth, randomizer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#onSplit(com.cohesiva.drifter.split
	 * .ISplitContext, com.cohesiva.drifter.split.IOffset)
	 */
	@Override
	public <T extends ISplitable> ISplitable onSplit(ISplitContext<T> ctx,
			IOffset offset) {
		// split space bounds first
		BoundingSquare subbound = (BoundingSquare) this.bounds().onSplit(ctx,
				offset);
		// create new subplane configured with splitted subbounds
		Plane subplane = new Plane(subbound, this.depth + 1, new Random(
				ctx.subindex(offset)));

		ctx.populationStrategy().populate((T) subplane, ctx.referenceLocation());

		return subplane;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.containers.Container#
	 * computeContainerIndexAfterSplit(com.cohesiva.drifter.common.IStellar)
	 */
	@Override
	protected int computeContainerIndexAfterSplit(IStellar element) {
		int index = 0;

		Location planeCenter = this.bounds().center();
		Location planarLocation = element.locate();

		if (planarLocation.x() > planeCenter.x())
			index |= 1;
		if (planarLocation.y() > planeCenter.y())
			index |= 2;

		return index;
	}

}
