/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>OctantOffset</code> represents an offset enumeration for an octant split degree (cubes).
 *
 * @author bkarmelita
 *
 */
public enum OctantOffset implements IOffset {
	
	/**
	 * The <code></code> represents a front bottom left splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	FRONT_BOTTOM_LEFT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, -HALF, -HALF, unit);
		}
	},

	/**
	 * The <code></code> represents a front bottom rigth splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	FRONT_BOTTOM_RIGHT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, -HALF, -HALF, unit);
		}
	},

	/**
	 * The <code></code> represents a front top left splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	FRONT_TOP_LEFT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, HALF, -HALF, unit);
		}
	},

	/**
	 * The <code></code> represents a front top right splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	FRONT_TOP_RIGHT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, HALF, -HALF, unit);
		}
		
		
	},

	/**
	 * The <code></code> represents a rear bottom left splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	REAR_BOTTOM_LEFT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, -HALF, -HALF, unit);
		}
		
		
	},

	/**
	 * The <code></code> represents a rear bottom right splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	REAR_BOTTOM_RIGHT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, -HALF, HALF, unit);
		}
		
		
	},

	/**
	 * The <code></code> represents a rear top left splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	REAR_TOP_LEFT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(-HALF, HALF, HALF, unit);
		}
	},

	/**
	 * The <code></code> represents a rear top right splitting offset
	 * location.
	 * 
	 * @author bkarmelita
	 * 
	 */
	REAR_TOP_RIGHT() {

		@Override
		public Location offset(DistanceUnit unit) {
			return new Location(HALF, HALF, HALF, unit);
		}
	};

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.datastruct.IOffset#offsetIndex()
	 */
	@Override
	public int offsetIndex() {
		return this.ordinal();
	}

}
