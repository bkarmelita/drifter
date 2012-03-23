/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.common;

/**
 * The <code>Location</code> represents a spatial location.
 * 
 * @author carmel
 * 
 */
public class Location {

	/**
	 * The galaxy center location.
	 */
	public static Location GALAXY_ORIGIN = new Location(0, 0, 0,
			DistanceUnit.LIGHT_YEAR);

	/**
	 * The <code>x</code> stands for a distance along the x axis.
	 */
	protected double x;

	/**
	 * The <code>y</code> stands for a distance along the y axis.
	 */
	protected double y;

	/**
	 * The <code>z</code> stands for a distance along the z axis.
	 */
	protected double z;

	/**
	 * The <code>unit</code> stands for a distance unit.
	 */
	protected DistanceUnit unit;

	/**
	 * Creates the new <code>Location</code> instance.
	 * 
	 */
	protected Location() {
		super();
	}

	/**
	 * Creates the new <code>Location</code> instance.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param unit
	 */
	public Location(double x, double y, double z, DistanceUnit unit) {
		super();

		this.x = x;
		this.y = y;
		this.z = z;
		this.unit = unit;
	}

	/**
	 * Creates the new <code>Location</code> instance.
	 * 
	 * @param location
	 *            other location
	 */
	public Location(Location location) {
		this(location.x, location.y, location.z, location.unit);
	}

	/**
	 * The x distance.
	 * 
	 * @return
	 */
	public double x() {
		return x;
	}

	/**
	 * The y distance.
	 * 
	 * @return
	 */
	public double y() {
		return y;
	}

	/**
	 * The z distance.
	 * 
	 * @return
	 */
	public double z() {
		return z;
	}

	/**
	 * Sets the new walue for <code>x</code>.
	 * 
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the new walue for <code>y</code>.
	 * 
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the new walue for <code>z</code>.
	 * 
	 * @param z
	 *            the z to set
	 */
	public void setZ(double z) {
		this.z = z;
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
	 * Adds this location to the other location by adding their coordinates.
	 * 
	 * @param other
	 * @return
	 */
	public Location add(Location other) {
		double otherX = other.x();
		double otherY = other.y();
		double otherZ = other.z();

		double newX = this.x + otherX;
		double newY = this.y + otherY;
		double newZ = this.z + otherZ;

		Location newLocation = new Location(newX, newY, newZ, this.unit);

		return newLocation;
	}

	/**
	 * Adds this location to the other location by adding their coordinates and
	 * stores the result in this location.
	 * 
	 * @param other
	 */
	public void addAndStore(Location other) {
		double otherX = other.x();
		double otherY = other.y();
		double otherZ = other.z();

		double newX = this.x + otherX;
		double newY = this.y + otherY;
		double newZ = this.z + otherZ;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
	}

	/**
	 * Subtracts this location from the other location by subtracting their
	 * coordinates.
	 * 
	 * @param other
	 * @return
	 */
	public Location subtract(Location other) {
		double otherX = other.x();
		double otherY = other.y();
		double otherZ = other.z();

		double newX = this.x - otherX;
		double newY = this.y - otherY;
		double newZ = this.z - otherZ;

		Location newLocation = new Location(newX, newY, newZ, this.unit);

		return newLocation;
	}

	/**
	 * Subtracts this location from the other location by subtracting their
	 * coordinates and stores the result in this location.
	 * 
	 * @param other
	 * @return
	 */
	public void subtractAndStore(Location other) {
		double otherX = other.x();
		double otherY = other.y();
		double otherZ = other.z();

		double newX = this.x - otherX;
		double newY = this.y - otherY;
		double newZ = this.z - otherZ;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
	}

	/**
	 * Multiplies this location multiplying its coordinates by the given
	 * multiplier.
	 * 
	 * @param multiplier
	 * @return
	 */
	public Location multiply(double multiplier) {
		double newX = this.x * multiplier;
		double newY = this.y * multiplier;
		double newZ = this.z * multiplier;

		Location newLocation = new Location(newX, newY, newZ, this.unit);

		return newLocation;
	}

	/**
	 * Multiplies this location multiplying its coordinates by the given
	 * multiplier and stores the result in this location.
	 * 
	 * @param multiplier
	 * @return
	 */
	public void multiplyAndStore(double multiplier) {
		double newX = this.x * multiplier;
		double newY = this.y * multiplier;
		double newZ = this.z * multiplier;

		this.x = newX;
		this.y = newY;
		this.z = newZ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Location))
			return false;

		Location otherLocation = (Location) other;
		return otherLocation.x() == x && otherLocation.y() == y
				&& otherLocation.z() == z;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		sb.append(this.x);
		sb.append(", ");
		sb.append(this.y);
		sb.append(", ");
		sb.append(this.z);
		sb.append(")");
		
		return sb.toString();
	}

}
