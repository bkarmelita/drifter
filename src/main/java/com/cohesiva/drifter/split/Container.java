/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.cohesiva.drifter.common.IStellar;

/**
 * The <code>Container</code> represents a base container implementation. Every
 * <code>Container</code> can hold some elements inside. It has also a boundary.
 * The <code>Volume</code> and <code>Plane</code> are examples of
 * <code>Containers</code>.
 * 
 * @author carmel
 * 
 */
public abstract class Container implements ISplitable {

	/**
	 * The <code>contents</code> stands for a list of elements of this complex.
	 */
	protected List<IStellar> contents;

	/**
	 * The <code>depth</code> stands for an origin depth.
	 */
	protected int depth;

	/**
	 * The <code>bounds</code> stands for a boundary of this complex.
	 */
	protected IBounding bounds;

	/**
	 * The <code>random</code> stands for a randomizer.
	 */
	public Random randomizer;

	/**
	 * Creates the new empty <code>Container</code> instance.
	 * 
	 * @param bounds
	 */
	public Container(IBounding bounds, int depth) {
		this(bounds, depth, new Random(0));
	}

	/**
	 * Creates the new empty <code>Container</code> instance.
	 * 
	 * @param bounds
	 */
	public Container(IBounding bounds, int depth, Random randomizer) {
		super();

		this.bounds = bounds;
		this.depth = depth;
		this.randomizer = randomizer;
	}

	/**
	 * Creates the new empty <code>Container</code> instance.
	 * 
	 * @param bounds
	 */
	public Container(List<IStellar> stellars, IBounding bounds, int depth,
			Random randomizer) {
		this(bounds, depth, randomizer);

		for (IStellar stellar : stellars) {
			this.addContent(stellar);
		}
	}

	/**
	 * Gets the enclosed elements inside this <code>Container</code>.
	 * 
	 * @return this <code>IStellar</code> elements
	 */
	public List<IStellar> contents() {
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ISplitable#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/**
	 * Gets the bounds of this <code>Container</code>.
	 * 
	 * @return this <code>ISpace</code> bounds.
	 */
	public IBounding bounds() {
		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ISplitable#complexity()
	 */
	@Override
	public int complexity() {
		return this.contents != null ? this.contents.size() : 0;
	}

	/**
	 * Adds an additional entity to this space.
	 * 
	 * @param stellar
	 *            stellar to add
	 */
	public void addContent(IStellar stellar) {
		// FIXME: memory issue (we should possibly code it more wisely)
		if (contents == null) {
			// lazy initialize collection, when there is a memory need for
			// elements inside
			// FIXME: consider a fast insertion collection
			contents = new LinkedList<IStellar>();
		}

		contents.add(stellar);
	}

	/**
	 * Remove all elements from this container.
	 */
	public void empty() {
		if (this.complexity() > 0) {
			contents.clear();
		}
		contents = null;
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
	 * @see
	 * com.cohesiva.drifter.common.ISplitable#onSplitComplete(com.cohesiva.drifter
	 * .split.ISplitContext, com.cohesiva.drifter.common.ISplitable[])
	 */
	@Override
	public <T extends ISplitable> void onSplitComplete(ISplitContext<T> ctx, ISplitable[] splittedParts) {
		// {{ distribute the elements down
		while (this.complexity() > 0) {
			IStellar element = contents.remove(0);
			// determine where the element should belong to after split
			int stellarIndex = computeContainerIndexAfterSplit(element);
			// add element to appropriate subcontainer
			((Container) splittedParts[stellarIndex]).addContent(element);
		}
		// }}

		// free this container memory
		this.empty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.common.ISplitable#onMerge(com.cohesiva.drifter.split
	 * .ISplitContext, com.cohesiva.drifter.common.ISplitable)
	 */
	@Override
	public <T extends ISplitable> void onMerge(ISplitContext<T> ctx, ISplitable mergedWhole) {
		// {{ return the elements back to its origin
		if (this.complexity() > 0) {
			for (Iterator<IStellar> iterator = this.contents().iterator(); iterator
					.hasNext();) {
				IStellar element = iterator.next();

				if (element.depth() <= mergedWhole.depth()) {
					((Container) mergedWhole).addContent(element);
					iterator.remove();
				}
			}
		}
		// }}

		// reset randomizer
		this.randomizer = new Random(ctx.index());

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
	public <T extends ISplitable> void onMergeComplete(ISplitContext<T> ctx) {
		// just do nothing
	}

	/**
	 * Compute the index of splitted subcomplex where the given element should
	 * belong to after split.
	 * 
	 * @param stellar
	 * @return
	 */
	protected abstract int computeContainerIndexAfterSplit(IStellar element);

}
