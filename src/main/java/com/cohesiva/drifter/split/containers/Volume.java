/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split.containers;

import java.util.List;
import java.util.Random;

import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.Container;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitable;

/**
 * The <code>Volume</code> represents a 3 dimensional container.
 * 
 * @author carmel
 * 
 */
public class Volume extends Container {

	/**
	 * Creates the new empty <code>Volume</code> instance.
	 * 
	 * @param bounds
	 */
	public Volume(BoundingBox bounds, int depth, Random randomizer) {
		super(bounds, depth, randomizer);
	}

	/**
	 * Creates the new <code>Volume</code> instance.
	 * 
	 * @param stellars
	 * @param bounds
	 */
	public Volume(List<IStellar> stellars, BoundingBox bounds, int depth,
			Random randomizer) {
		super(stellars, bounds, depth, randomizer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplit(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public <T extends ISplitable> ISplitable onSplit(ISplitContext<T> ctx,
			IOffset offset) {
		// split space bounds first
		BoundingBox subbound = (BoundingBox) this.bounds().onSplit(ctx, offset);
		// create new subvolume configured with splitted bounding box
		ISplitable subvolume = new Volume(subbound, this.depth + 1, new Random(
				ctx.subindex(offset)));
		
		ctx.populationStrategy().populate((T) subvolume, ctx.referenceLocation());

		return subvolume;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#computeEntityHolderIndex(com.cohesiva
	 * .drifter.common.IEntity)
	 */
	@Override
	protected int computeContainerIndexAfterSplit(IStellar element) {
		int index = 0;

		Location center = this.bounds().center();
		Location stellarLocation = element.locate();

		if (stellarLocation.x() > center.x())
			index |= 1;
		if (stellarLocation.y() > center.y())
			index |= 2;
		if (stellarLocation.z() > center.z())
			index |= 4;

		return index;
	}

}
