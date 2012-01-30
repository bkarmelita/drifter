/**
 * Copyright 2011 cohesiva.com.
 */
package com.cohesiva.drifter.stellar;

import java.util.List;

import com.cohesiva.drifter.stellar.population.DefaultPopulationStrategy;

/**
 * The <code>ProceduralSpaceFactory</code> represents the procedural space
 * factory.
 * 
 * @author bkarmelita
 * 
 */
public class ProceduralSpaceFactory extends SpaceFactory {

	/**
	 * The <code>populationStrategy</code> stands for a space population
	 * strategy. There is one strategy for all produced spaces.
	 */
	//private static ISpacePopulationStrategy populationStrategy = new NoPopulationStrategy();
	private static ISpacePopulationStrategy populationStrategy = new DefaultPopulationStrategy();

	/**
	 * Creates the new <code>ProceduralSpaceFactory</code> instance.
	 * 
	 */
	protected ProceduralSpaceFactory() {
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
		return new ProceduralSpace(stellars, bounds, populationStrategy);
	}

}
