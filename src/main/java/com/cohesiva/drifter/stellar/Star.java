/**
 * Copyright 2011 ohesiva.com
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.IEntity;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>Start</code> represents the star.
 * 
 * @author carmel
 * 
 */
public class Star implements IEntity {

	/**
	 * The <code>starClass</code> stands for a star class.
	 */
	protected StarClass starClass;

	/**
	 * The <code>location</code> stands for a star location in space.
	 */
	protected Location location;

	/**
	 * The <code>diameter</code> stands for a star diameter.
	 */
	protected double diameter;

	/**
	 * The <code>mass</code> stands for a star mass.
	 */
	protected double mass;

	/**
	 * The <code>depth</code> stands for a birth depth.
	 */
	protected int depth;

	/**
	 * Creates the new <code>Start</code> instance.
	 * TODO: refactor. Additional params required (mass, diameter).
	 * 
	 * @param location
	 */
	public Star(StarClass starClass, Location location, int depth) {
		this.starClass = starClass;
		this.location = location;
		this.depth = depth;
	}

	/**
	 * Get the star class.
	 * 
	 * @return the star class.
	 */
	public StarClass getStarClass() {
		return starClass;
	}

	/**
	 * Get the star color.
	 * 
	 * @return the star color.
	 */
	public StarColor getColor() {
		return starClass.getColor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.common.ISpatial#locate()
	 */
	@Override
	public Location locate() {
		return location;
	}

	/**
	 * Gets the current value of <code>diameter</code>.
	 * 
	 * @return the diameter
	 */
	public double getDiameter() {
		return diameter;
	}

	/**
	 * Sets the new value for <code>diameter</code>.
	 * 
	 * @param diameter
	 *            the diameter to set
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	/**
	 * Gets the current value of <code>mass</code>.
	 * 
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Sets the new value for <code>mass</code>.
	 * 
	 * @param mass
	 *            the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.stellar.IStellar#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

}
