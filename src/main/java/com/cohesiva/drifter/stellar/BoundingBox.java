/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.List;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.stellar.split.WithinBoundingBoxCriteria;

/**
 * The <code>BoundingBox</code> represents a cubic bounding volume.
 * 
 * @author carmel
 * 
 */
public class BoundingBox implements IBoundingBox {

	/*
	 * The <code>DEFAULT_SPLIT_CRITERIA</code> stands for a default bounding box
	 * split strategy.
	 */
	private static final ISplitCriteria<IBoundingBox> DEFAULT_SPLIT_CRITERIA = new WithinBoundingBoxCriteria();

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
	 * Creates the new <code>BoundingBox</code> instance.
	 * 
	 * @param center
	 * @param radius
	 */
	public BoundingBox(Location center, double radius, int depth) {
		super();

		this.center = center;
		this.radius = radius;
		this.depth = depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.spatial.IBoundingBox#center()
	 */
	@Override
	public Location center() {
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.spatial.IBoundingBox#radius()
	 */
	@Override
	public double radius() {
		return radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.IComplex#ofHighComplexity(com.cohesiva
	 * .drifter.common.Location)
	 */
	@Override
	public int complexity() {
		return 0; // bounding box has no complexity
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.datastruct.IComplex#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.stellar.IStellar#locate()
	 */
	@Override
	public Location locate() {
		return center();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#splitCriteria()
	 */
	@SuppressWarnings("unchecked")
	@Override
	// FIXME: unchecked conversion
	public ISplitCriteria<IBoundingBox> splitCriteria() {
		return DEFAULT_SPLIT_CRITERIA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.datastruct.ISplittable#splitDegree()
	 */
	@Override
	public SplitDegree splitDegree() {
		return SplitDegree.OCTANT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codestellar.drifter.spatial.IBoundingBox#surround(com.codestellar
	 * .drifter.spatial.Location)
	 */
	@Override
	public boolean isSurrounding(Location location, double padding) {
		double r = radius + radius * padding;

		// {{ determine min and max within this bounds
		Location minLocation = this.center().add(
				new Location(-r, -r, -r, this.center().getUnit()));
		Location maxLocation = this.center().add(
				new Location(r, r, r, this.center().getUnit()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplit(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public IComplex onSplit(ISplitContext ctx, IOffset offset) {
		// {{ compute the offset location relative to this as parent
		Location centerOffset = offset.offset(this.center().getUnit());
		centerOffset.multiplyAndStore(this.radius());
		// }}

		// create new bounding box for the subsequent child octant
		BoundingBox bounds = new BoundingBox(this.center.add(centerOffset),
				this.radius() * IOffset.HALF, this.depth + 1);

		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMerge(com.cohesiva.drifter
	 * .common.Location)
	 */
	@Override
	public void onMerge(ISplitContext ctx, IComplex mergedWhole) {
		// TODO: do some basic cleanup
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplitComplete(com.cohesiva
	 * .drifter.common.Location, com.cohesiva.drifter.datastruct.IComplex[])
	 */
	@Override
	public void onSplitComplete(ISplitContext ctx, IComplex[] splitted) {
		// just do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMergeComplete(com.cohesiva
	 * .drifter.common.Location)
	 */
	@Override
	public void onMergeComplete(ISplitContext ctx) {
		// just do nothing
	}

	/**
	 * @param stellars
	 * @return
	 */
	public static BoundingBox newInstance(List<IStellar> stellars) {
		// {{ determine min and max of the given spatials
		Location minLocation = new Location(stellars.get(0).locate());
		Location maxLocation = new Location(stellars.get(0).locate());

		for (IStellar stellar : stellars) {
			Location location = stellar.locate();

			// update the min location
			if (location.x() < minLocation.x()) {
				minLocation.setX(location.x());
			}

			if (location.y() < minLocation.y()) {
				minLocation.setY(location.y());
			}

			if (location.z() < minLocation.z()) {
				minLocation.setZ(location.z());
			}

			// update the max location
			if (location.x() > maxLocation.x()) {
				maxLocation.setX(location.x());
			}

			if (location.y() > maxLocation.y()) {
				maxLocation.setY(location.y());
			}

			if (location.z() > maxLocation.z()) {
				maxLocation.setZ(location.z());
			}

		}
		// }}

		// {{ compute the space center
		// alias the maxLocation to reduce object creation
		Location spaceRadius = maxLocation;
		// spaceRadius = (maxLocation - minLocation) * 0.5
		spaceRadius.subtractAndStore(minLocation);
		// spaceRadius = spaceRadius.multiply(HALF);
		spaceRadius.multiplyAndStore(IOffset.HALF);

		// // alias the minLocation to reduce object creation
		Location spaceCenter = minLocation;
		// spaceCenter = minLocation + spaceRadius
		// TODO: strange
		spaceCenter = spaceCenter.add(spaceRadius);
		// spaceCenter.addAndStore(spaceRadius);
		// }}

		// {{ get the highest radius distance as a bounding box radius
		double radius = spaceRadius.x();
		if (radius < spaceRadius.y())
			radius = spaceRadius.y();
		if (radius < spaceRadius.z())
			radius = spaceRadius.z();
		// }}

		// prepare the space bounds
		BoundingBox result = new BoundingBox(spaceCenter, radius, 1);

		return result;
	}

}
