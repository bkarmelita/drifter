/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.List;


/**
 * The <code>SpaceFactory</code> represents the default space factory.
 * 
 * @author bkarmelita
 * 
 */
public class DefaultSpaceFactory extends SpaceFactory {

	/**
	 * Creates the new <code>SpaceFactory</code> instance.
	 * 
	 */
	protected DefaultSpaceFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.stellar.SpaceFactory#produceSpace(java.util.List,
	 * com.cohesiva.drifter.test.stellar.IBoundingBox)
	 */
	@Override
	public ISpace produceSpace(List<IStellar> stellars, IBoundingBox bounds) {
		return new Space(stellars, bounds);
	}

}
