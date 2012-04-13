/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split.containers;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.Bounding;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitable;
import com.cohesiva.drifter.split.SplitDegree;

/**
 * The <code>BoundingSquare</code> represents a rectangular bounding area.
 * 
 * @author carmel
 * 
 */
public class BoundingSquare extends Bounding {

	/**
	 * Creates the new <code>BoundingSquare</code> instance.
	 * 
	 * @param center
	 * @param radius
	 */
	public BoundingSquare(Location center, double radius, int depth) {
		super(center, radius, depth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.split.Container#splitDegree()
	 */
	@Override
	public SplitDegree splitDegree() {
		return SplitDegree.QUARTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.split.Container#onSplit(com.cohesiva.drifter.common
	 * .Location, com.cohesiva.drifter.split.IOffset)
	 */
	@Override
	public <T extends ISplitable> ISplitable onSplit(ISplitContext<T> ctx, IOffset offset) {
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
	
	/**
	 * Gets the top left rect corner.
	 * TODO: rafactor this as an API method
	 *  
	 * @return
	 */
	public Location getTopLeft() {
		int x = (int) this.center.x() - (int) this.radius;
		int y = (int) this.center.y() + (int) this.radius;
		
		return new Location(x, y, 0, this.center.getUnit());
	}

}
