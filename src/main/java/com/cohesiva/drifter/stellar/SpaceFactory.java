/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.List;

/**
 * The <code>ISpaceFactory</code> represents the factory for <code>ISpace</code>
 * instances.
 * 
 * It refers to the Abstract Factory pattern.
 * 
 * @author bkarmelita
 * 
 */
public abstract class SpaceFactory {

	private static SpaceFactory spaceFactory = new DefaultSpaceFactory();
	private static SpaceFactory proceduralSpaceFactory = new ProceduralSpaceFactory();

	/**
	 * Produces the new <code>ISpace</code> instance surrounded by given bounds
	 * and filled with stellars
	 * 
	 * @param clazz
	 * @param bounds
	 * @return
	 */
	public abstract ISpace produceSpace(List<IStellar> stellars,
			IBoundingBox bounds);

	/**
	 * TODO: 
	 * @return
	 */
	public ISpace produceSpace() {
		return this.produceSpace(null, IStellarConstants.GALAXY_BOUNDS);
	}

	/**
	 * Get the space factory instance.
	 * 
	 * @param spaceClass
	 *            the class of space to prepare factory for. Space |
	 *            ProceduralSpace classes are possible
	 * @return the space factory instance
	 */
	@SuppressWarnings("rawtypes")
	public static SpaceFactory getInstance(Class spaceClass) {
		SpaceFactory result = spaceFactory;

		if (ProceduralSpace.class.isAssignableFrom(spaceClass)) {
			result = proceduralSpaceFactory;
		}

		return result;
	}

}
