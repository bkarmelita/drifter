/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>Bounding</code> represents a base for <code>IBounding</code>.
 * 
 * @author carmel
 * 
 */
public abstract class Bounding implements IBounding {

	/**
	 * The <code>center</code> stands for a center of the cubic bounding volume.
	 */
	protected Location center;

	/**
	 * The <code>radius</code> stands for an radius of the cubic bounding
	 * volume.
	 */
	protected double radius;

	/**
	 * The <code>depth</code> stands for an origin depth.
	 */
	protected int depth;

	/**
	 * Creates the new <code>Bounding</code> instance.
	 * 
	 * @param center
	 * @param radius
	 */
	public Bounding(Location center, double radius, int depth) {
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
	 * @see com.cohesiva.drifter.split.Container#complexity()
	 */
	@Override
	public int complexity() {
		return 0; // bounding has no complexity
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.Container#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.stellar.IStellar#locate()
	 */
	@Override
	public Location locate() {
		return center();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#onSplitComplete(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.split.Container[])
	 */
	@Override
	public <T extends ISplitable> void onSplitComplete(ISplitContext<T> ctx, ISplitable[] splittedParts) {
		// just do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#onMerge(com.cohesiva.drifter.common
	 * .Location, com.cohesiva.drifter.split.Container)
	 */
	@Override
	public <T extends ISplitable> void onMerge(ISplitContext<T> ctx, ISplitable mergedWhole) {
		// TODO: do some basic cleanup
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#onMergeComplete(com.cohesiva.drifter
	 * .common.Location)
	 */
	@Override
	public <T extends ISplitable> void onMergeComplete(ISplitContext<T> ctx) {
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

		if (location.z() < minLocation.z() || location.z() > maxLocation.z()) {
			return false;
		}
		// }}

		return true;
	}

}
