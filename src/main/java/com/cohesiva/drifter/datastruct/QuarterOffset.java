/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>QuarterOffset</code> represents an offset enumeration for an quarter split degree (squares).
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
		
		/*public Square offset(Square source) {
			Square offset = new Square(source.getLocx(),
					(int) (HALF * source.getWidth()) + source.getLocy(),
					(int) (HALF * source.getWidth()), source.getColor());

			return offset;
		}*/

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
		
		/*public Square offset(Square source) {
			Square offset = new Square((int) (HALF * source.getWidth())
					+ source.getLocx(), (int) (HALF * source.getWidth())
					+ source.getLocy(), (int) (HALF * source.getWidth()),
					source.getColor());

			return offset;
		}*/
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
			return new Location(HALF, HALF, 0, unit);
		}
		
		/*public Square offset(Square source) {
			Square offset = new Square(source.getLocx(), source.getLocy(),
					(int) (HALF * source.getWidth()), source.getColor());

			return offset;
		}*/
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
		
		/*public Square offset(Square source) {
			Square offset = new Square((int) (HALF * source.getWidth())
					+ source.getLocx(), source.getLocy(),
					(int) (HALF * source.getWidth()), source.getColor());

			return offset;
		}*/
	};

	@Override
	public abstract Location offset(DistanceUnit unit);

	@Override
	public int offsetIndex() {
		return this.ordinal();
	}

}
