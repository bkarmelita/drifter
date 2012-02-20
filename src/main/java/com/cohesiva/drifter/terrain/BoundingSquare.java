/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.terrain;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.terrain.split.WithinBoundingSquareCriteria;

/**
 * The <code>BoundingSquare</code> represents a rectangular bounding area.
 * 
 * @author bkarmelita
 * 
 */
public class BoundingSquare implements IBoundingSquare {

	/*
	 * The <code>DEFAULT_SPLIT_CRITERIA</code> stands for a default bounding
	 * square split strategy.
	 */
	private static final ISplitCriteria<IBoundingSquare> DEFAULT_SPLIT_CRITERIA = new WithinBoundingSquareCriteria();

	/**
	 * The <code>center</code> stands for a center of the cubic bounding volume.
	 */
	private Location center;

	/**
	 * The <code>radius</code> stands for an radius of the cubic bounding
	 * volume.
	 */
	private double radius;

	/**
	 * The <code>depth</code> stands for an origin depth.
	 */
	protected int depth;

	/**
	 * Creates the new <code>BoundingSquare</code> instance.
	 * 
	 * @param center
	 * @param radius
	 */
	public BoundingSquare(Location center, double radius, int depth) {
		super();

		this.center = center;
		this.radius = radius;
		this.depth = depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.terrain.IBoundingSquare#center()
	 */
	@Override
	public Location center() {
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.terrain.IBoundingSquare#radius()
	 */
	@Override
	public double radius() {
		return radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.IComplex#complexity()
	 */
	@Override
	public int complexity() {
		return 0; // bounding square has no complexity
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.IComplex#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.IComplex#splitDegree()
	 */
	@Override
	public SplitDegree splitDegree() {
		return SplitDegree.QUARTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.IComplex#splitCriteria()
	 */
	@SuppressWarnings("unchecked")
	@Override
	// FIXME: unchecked conversion
	public ISplitCriteria<IBoundingSquare> splitCriteria() {
		return DEFAULT_SPLIT_CRITERIA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.IComplex#onSplit(com.cohesiva.drifter.common
	 * .Location, com.cohesiva.drifter.split.IOffset)
	 */
	@Override
	public IComplex onSplit(Location referenceLocation, IOffset offset) {
		// {{ compute the offset location relative to this as parent
		Location centerOffset = offset.offset(this.center().getUnit());
		centerOffset.multiplyAndStore(this.radius());
		// }}

		// create new bounding square for the subsequent child quad
		BoundingSquare bounds = new BoundingSquare(
				this.center.add(centerOffset), this.radius() * IOffset.HALF,
				this.depth + 1);

		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.IComplex#onSplitComplete(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.split.IComplex[])
	 */
	@Override
	public void onSplitComplete(Location referenceLocation,
			IComplex[] splittedParts) {
		// just do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.IComplex#onMerge(com.cohesiva.drifter.common
	 * .Location, com.cohesiva.drifter.split.IComplex)
	 */
	@Override
	public void onMerge(Location referenceLocation, IComplex mergedWhole) {
		// TODO: do some basic cleanup
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.IComplex#onMergeComplete(com.cohesiva.drifter
	 * .common.Location)
	 */
	@Override
	public void onMergeComplete(Location referenceLocation) {
		// just do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.terrain.IBoundingSquare#isSurrounding(com.cohesiva
	 * .drifter.common.Location, double)
	 */
	@Override
	public boolean isSurrounding(Location location, double padding) {
		double r = radius + radius * padding;

		// {{ determine min and max within this bounds
		// ignore z coordinate
		Location minLocation = this.center().add(
				new Location(-r, -r, 0, this.center().getUnit()));
		Location maxLocation = this.center().add(
				new Location(r, r, 0, this.center().getUnit()));
		// }}

		// {{ check location against max and min
		if (location.x() < minLocation.x() || location.x() > maxLocation.x()) {
			return false;
		}

		if (location.y() < minLocation.y() || location.y() > maxLocation.y()) {
			return false;
		}
		// }}

		return true;
	}

}
