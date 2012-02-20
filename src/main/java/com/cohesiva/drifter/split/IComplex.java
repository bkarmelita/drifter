/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>IComplex</code> represents an interface for an abstract complex
 * element. <code>IComplex</code> has some complexity (typically it consists of
 * something). It can be split into <code>IComplex</code> subelements to reduce
 * the complexity.
 * 
 * The <code>ISpace</code> and <code>IBoundingBox</code> are examples of
 * <code>IComplex</code>.
 * 
 * @author bkarmelita
 * 
 */
public interface IComplex {

	/*
	 * The <code>DEFAULT_SPLIT_CRITERIA</code> stands for a default IComplex
	 * split criteria.
	 */
	public static final ISplitCriteria<IComplex> DEFAULT_SPLIT_CRITERIA = new ThresholdSplitCriteria<IComplex>();

	/**
	 * Get the origin depth of this complex.
	 * 
	 * @return
	 */
	public int depth();

	/**
	 * Gets the split degree
	 * 
	 * @return the split degree
	 */
	public SplitDegree splitDegree();

	/**
	 * Gives the configured split criteria for this complex.
	 * 
	 * @return the current split strategy
	 */
	public <T extends IComplex> ISplitCriteria<T> splitCriteria();

	/**
	 * Gets the complexity of this complex.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while computing
	 *            complexity
	 * 
	 * @return this element complexity
	 */
	public int complexity();

	/**
	 * Split lifecycle callback executed on every single split.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while splitting
	 *            (player location)
	 * @param offset
	 *            the split offset
	 * 
	 * @return the splitted part
	 */
	public IComplex onSplit(Location referenceLocation, IOffset offset);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while splitting
	 *            (player location)
	 * @param splittedParts
	 *            the splitted parts
	 */
	public void onSplitComplete(Location referenceLocation,
			IComplex[] splittedParts);

	/**
	 * Split lifecycle callback executed on every merge.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while merging (player
	 *            location)
	 * @param mergedWhole
	 *            the merged whole complex
	 */
	public void onMerge(Location referenceLocation, IComplex mergedWhole);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while splitting
	 *            (player location)
	 */
	public void onMergeComplete(Location referenceLocation);

}
