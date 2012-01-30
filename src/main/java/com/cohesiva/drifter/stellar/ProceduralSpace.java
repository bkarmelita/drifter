/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.LinkedList;
import java.util.List;

/**
 * The <code>ProceduralSpace</code> represents the procedural universe
 * implementation. The space is populated with stars during the split. The count
 * and configuration of stars depends on the population strategy.
 * 
 * @author bkarmelita
 * 
 */
public class ProceduralSpace extends Space {

	/**
	 * The <code>populationStrategy</code> stands for a space population
	 * strategy.
	 */
	protected ISpacePopulationStrategy populationStrategy;
	
	protected boolean populated = false;

	/**
	 * Creates the new <code>ProceduralSpace</code> instance.
	 * 
	 * @param spatials
	 * @param bounds
	 */
	protected ProceduralSpace(List<IStellar> spatials, IBoundingBox bounds,
			ISpacePopulationStrategy populationStrategy) {
		super(spatials, bounds);

		this.populationStrategy = populationStrategy;
	}

	/**
	 * Creates the new <code>ProceduralSpace</code> instance.
	 * 
	 * @param bounds
	 */
	protected ProceduralSpace(IBoundingBox bounds,
			ISpacePopulationStrategy populationStrategy) {
		this(null, bounds, populationStrategy);
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.test.stellar.Space#stellars()
	 */
	@Override
	public List<IStellar> stellars() {
		if (stellars == null) {
			stellars = new LinkedList<IStellar>();
		}
		
		return super.stellars();
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.test.stellar.Space#contentSize()
	 */
	@Override
	public int complexity() {
		return this.stellars().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.stellar.Space#split()
	 */
	/*@Override
	public ISpace[] split(Location targetLocation) {
		// populate space procedurally
		populationStrategy.populate(this);

		// split into subspaces
		return super.split(targetLocation);
	}*/

}
