/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.IComplex;
import com.cohesiva.drifter.datastruct.IOffset;
import com.cohesiva.drifter.datastruct.ISplitCriteria;
import com.cohesiva.drifter.datastruct.SplitDegree;
import com.cohesiva.drifter.stellar.split.WithinSpaceCriteria;

/**
 * The <code>Space</code> represents the default universe implementation.
 * 
 * @author bkarmelita
 * 
 */
public class Space implements ISpace {
	
	private static final ISplitCriteria<ISpace> DEFAULT_SPLIT_CRITERIA = new WithinSpaceCriteria();

	/**
	 * The <code>stellars</code> stands for a list of elements in the universe.
	 */
	protected List<IStellar> stellars;

	/**
	 * The <code>bounds</code> stands for a bounding box
	 */
	protected IBoundingBox bounds;

	/**
	 * Creates the new <code>Space</code> instance.
	 * 
	 * @param stellars
	 * @param bounds
	 */
	protected Space(List<IStellar> stellars, IBoundingBox bounds) {
		super();

		this.stellars = stellars;
		this.bounds = bounds;
	}

	/**
	 * Creates the new empty <code>Space</code> instance.
	 * 
	 * @param bounds
	 */
	protected Space(IBoundingBox bounds) {
		this(null, bounds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.stellar.ISpace#bounds()
	 */
	@Override
	public IBoundingBox bounds() {
		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.stellar.ISpace#stellars()
	 */
	@Override
	public List<IStellar> stellars() {
		return stellars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.stellar.ISpace#addStellar(com.cohesiva.drifter
	 * .test.stellar .IStellar)
	 */
	@Override
	public void addStellar(IStellar stellar) {
		// TODO: memory issue (we should possibly code it more wisely)
		if (stellars == null) {
			// lazy initialize collection, when there is a memory need for
			// stellars inside
			// TODO: consider a fast insertion collection
			stellars = new LinkedList<IStellar>();
		}

		stellars.add(stellar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.IComplex#complexity()
	 */
	@Override
	public int complexity() {
		return this.stellars != null ? this.stellars.size() : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#splitCriteria()
	 */
	@Override
	public ISplitCriteria<ISpace> splitCriteria() {
		return DEFAULT_SPLIT_CRITERIA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.datastruct.ISplittable#splitDegree()
	 */
	@Override
	public SplitDegree splitDegree() {
		return bounds.splitDegree();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.common.ISplittable#depth()
	 */
	@Override
	public int depth() {
		return bounds.depth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.common.ISplittable#split(com.cohesiva.drifter.common
	 * .Location)
	 */
	/*public ISpace[] splitt(Location targetLocation, int index) {
		ISpace[] subspaces = new ISpace[bounds.splitDegree().value()];

		// split space bounds into eight subbounds
		IBoundingBox[] subbounds = (IBoundingBox[]) bounds
				.split(targetLocation, index);

		// {{ prepare placeholders for splitted space stellars
		for (int i = 0; i < subspaces.length; i++) {
			subspaces[i] = SpaceFactory.getInstance(this.getClass())
					.produceSpace(null, subbounds[i]);
		}
		// }}

		// {{ split space into subspaces
		while (this.complexity() > 0) {
			IStellar stellar = stellars.remove(0);
			// determine where the stellar should belong to (which subspace)
			int stellarIndex = computeStellarSubspaceIndex(stellar);
			// add stellar to appropriate subspace
			((Space) subspaces[stellarIndex]).addStellar(stellar);
		}
		// }}

		// free space stellar memory
		this.empty();

		return subspaces;
	}*/
	
	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.datastruct.IComplex#split(com.cohesiva.drifter.common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public IComplex split(Location referenceLocation, IOffset offset) {
		// split space bounds into subbounds
		IBoundingBox subbounds = (IBoundingBox) bounds.split(referenceLocation, offset);
		
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.IComplex#merge(com.cohesiva.drifter
	 * .datastruct.IComplex)
	 */
	@Override
	public void merge(IComplex part) {
		Space spacePart = (Space) part;

		for (Iterator<IStellar> iterator = spacePart.stellars().iterator(); iterator
				.hasNext();) {
			IStellar stellar = iterator.next();

			if (stellar.depth() <= this.depth()) {
				// merge stellars down to this depth (generated at this level or
				// upper)
				this.addStellar(stellar);
			} else {
				// remove stellars from higher depths (generated deeper)
				iterator.remove();
			}
		}

		spacePart.empty();
		spacePart.bounds = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codestellar.drifter.stellar.ISpace#empty()
	 */
	@Override
	public void empty() {
		if (this.complexity() > 0) {
			stellars.clear();
		}
		stellars = null;
	}

	/**
	 * Compute the stellar subspace index to determine the stellar subspace
	 * adhesion.
	 * 
	 * @param stellar
	 * @return
	 */
	protected int computeStellarSubspaceIndex(IStellar stellar) {
		int index = 0;

		Location spaceCenter = this.bounds().center();
		Location stellarLocation = stellar.locate();

		if (stellarLocation.x() > spaceCenter.x())
			index |= 1;
		if (stellarLocation.y() > spaceCenter.y())
			index |= 2;
		if (stellarLocation.z() > spaceCenter.z())
			index |= 4;

		return index;
	}

}
