/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.SplitDegree;
import com.cohesiva.drifter.stellar.split.WithinSpaceCriteria;

/**
 * The <code>Space</code> represents the default universe implementation.
 * 
 * @author carmel
 * 
 */
public class Space implements ISpace {
	/*
	 * The <code>DEFAULT_SPLIT_CRITERIA</code> stands for a default space split
	 * strategy.
	 */
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
	 * The <code>depth</code> stands for an origin depth.
	 */
	protected int depth;

	/**
	 * Creates the new <code>Space</code> instance.
	 * 
	 * @param stellars
	 * @param bounds
	 */
	public Space(List<IStellar> stellars, IBoundingBox bounds) {
		super();

		this.stellars = stellars;
		this.bounds = bounds;
	}

	/**
	 * Creates the new empty <code>Space</code> instance.
	 * 
	 * @param bounds
	 */
	public Space(IBoundingBox bounds) {
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
		// FIXME: memory issue (we should possibly code it more wisely)
		if (stellars == null) {
			// lazy initialize collection, when there is a memory need for
			// stellars inside
			// FIXME: consider a fast insertion collection
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
	 * @see com.cohesiva.drifter.datastruct.IComplex#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.stellar.IStellar#locate()
	 */
	@Override
	public Location locate() {
		return bounds.locate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#splitCriteria()
	 */
	@SuppressWarnings("unchecked")
	@Override
	// FIXME: unchecked conversion
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
		return ((IComplex) bounds).splitDegree();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplit(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public IComplex onSplit(ISplitContext ctx, IOffset offset) {
		// split space bounds first
		IBoundingBox subbound = (IBoundingBox) this.bounds().onSplit(ctx,
				offset);
		// create new subspace configured with splitted subbounds
		IComplex subspace = new Space(subbound);

		return subspace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplitComplete(com.cohesiva
	 * .drifter.common.Location, com.cohesiva.drifter.datastruct.IComplex[])
	 */
	@Override
	public void onSplitComplete(ISplitContext ctx, IComplex[] splittedParts) {
		// {{ move the stellars down to subspaces
		while (this.complexity() > 0) {
			IStellar stellar = stellars.remove(0);
			// determine where the stellar should belong to (which subspace)
			int stellarIndex = computeStellarSubspaceIndex(stellar);
			// add stellar to appropriate subspace
			((Space) splittedParts[stellarIndex]).addStellar(stellar);
		}
		// }}

		// free this space stellar memory
		this.empty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMerge(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IComplex)
	 */
	@Override
	public void onMerge(ISplitContext ctx, IComplex mergedWhole) {
		ISpace spaceWhole = (ISpace) mergedWhole;

		// {{ iterate through stellars
		for (Iterator<IStellar> iterator = this.stellars().iterator(); iterator
				.hasNext();) {
			IStellar stellar = iterator.next();

			if (stellar.depth() <= this.depth()) {
				// move the stellar back to its origin
				spaceWhole.addStellar(stellar);
			} else {
				// remove stellars from higher depths (generated deeper)
				iterator.remove();
			}
		}
		// }}

		// {{ free memory
		this.empty();
		this.bounds = null;
		// }}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMergeComplete(com.cohesiva
	 * .drifter.common.Location)
	 */
	@Override
	public void onMergeComplete(ISplitContext ctx) {
		// just do nothing
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
