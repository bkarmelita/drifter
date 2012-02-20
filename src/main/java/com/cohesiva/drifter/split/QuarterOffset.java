/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>QuarterOffset</code> represents a splitting offset enumeration for
 * a quarter split degree (squares).
 * 
 * @author bkarmelita
 * 
 */
public enum QuarterOffset implements IOffset {
	
	/**
	 * The <code></code> represents a front bottom left splitting offset square.
	 * 
	 * @author bkarmelita
	 * 
	 */
	BOTTOM_LEFT() {
		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, -HALF, 0, unit);
		}
	},

	/**
	 * The <code></code> represents a bottom right splitting offset square.
	 * 
	 * @author bkarmelita
	 * 
	 */
	BOTTOM_RIGHT() {
		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, -HALF, 0, unit);
		}
	},

	/**
	 * The <code></code> represents a top left splitting offset square.
	 * 
	 * @author bkarmelita
	 * 
	 */
	TOP_LEFT() {
		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, HALF, 0, unit);
		}
	},

	/**
	 * The <code></code> represents a bottom right splitting offset square.
	 * 
	 * @author bkarmelita
	 * 
	 */
	TOP_RIGHT() {
		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, HALF, 0, unit);
		}
	};

	@Override
	public abstract Location offset(DistanceUnit unit);

	@Override
	public int offsetIndex() {
		return this.ordinal();
	}

}
