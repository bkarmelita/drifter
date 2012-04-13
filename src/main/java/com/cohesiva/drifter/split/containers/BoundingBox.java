/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split.containers;

import java.util.List;

import com.cohesiva.drifter.common.IStellar;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.Bounding;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitable;
import com.cohesiva.drifter.split.SplitDegree;

/**
 * The <code>BoundingBox</code> represents a cubic bounding volume.
 * 
 * @author carmel
 * 
 */
public class BoundingBox extends Bounding {

	/**
	 * Creates the new <code>BoundingBox</code> instance.
	 * 
	 * @param center
	 * @param radius
	 */
	public BoundingBox(Location center, double radius, int depth) {
		super(center, radius, depth);
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
	 * com.cohesiva.drifter.datastruct.IComplex#onSplit(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public <T extends ISplitable> ISplitable onSplit(ISplitContext<T> ctx, IOffset offset) {
		// {{ compute the offset location relative to this as parent
		Location centerOffset = offset.offset(this.center().getUnit());
		centerOffset.multiplyAndStore(this.radius());
		// }}

		// create new bounding box for the subsequent child octant
		BoundingBox bounds = new BoundingBox(this.center.add(centerOffset),
				this.radius() * IOffset.HALF, this.depth + 1);

		return bounds;
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
