/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.common;

/**
 * The <code>Distance</code> represents a spatial distance.
 * 
 * @author bkarmelita
 */
public class Distance {

	/**
	 * The <code>value</code> stands for a distance value.
	 */
	protected double value;

	/**
	 * The <code>unit</code> stands for a distance unit.
	 */
	protected DistanceUnit unit;

	/**
	 * Creates the new <code>Distance</code> instance.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param unit
	 */
	public Distance(double value, DistanceUnit unit) {
		super();

		this.value = value;
		this.unit = unit;
	}

	/**
	 * Gets the distance value.
	 * 
	 * @return
	 */
	public double value() {
		return this.value;
	}

	/**
	 * Gets the current value of <code>unit</code>.
	 * 
	 * @return the unit
	 */
	public DistanceUnit getUnit() {
		return unit;
	}

	/**
	 * Adds this distance to the other distance.
	 * 
	 * @param other
	 * @return
	 */
	public Distance add(Distance other) {
		double newValue = this.value + other.value();
		Distance newDistance = new Distance(newValue, this.unit);

		return newDistance;
	}

	/**
	 * Adds this distance to the other distance and stores the result in this
	 * distance.
	 * 
	 * @param other
	 */
	public void addAndStore(Distance other) {
		// TODO
	}

	/**
	 * Subtracts this distance from the other distance and stores the result in
	 * this distance.
	 * 
	 * @param other
	 * @return
	 */
	public void subtractAndStore(Distance other) {
		// TODO
	}

	/**
	 * Multiplies this distance by the given multiplier and stores the result in
	 * this distance.
	 * 
	 * @param multiplier
	 * @return
	 */
	public void multiplyAndStore(double multiplier) {
		// TODO
	}

}
