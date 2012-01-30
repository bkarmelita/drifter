/**
 * Copyright 2011 ohesiva.com
 */
package com.cohesiva.drifter.stellar;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>Start</code> represents the star.
 * 
 * @author bkarmelita
 * 
 */
public class Star implements IStellar {

	/**
	 * The <code>starClass</code> stands for a star class.
	 */
	protected StarClass starClass;

	/**
	 * The <code>location</code> stands for a star location in space.
	 */
	protected Location location;

	/**
	 * The <code>depth</code> stands for a birth depth.
	 */
	protected int depth;

	/**
	 * Creates the new <code>Start</code> instance.
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
