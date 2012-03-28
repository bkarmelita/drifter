/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.IPopulable;
import com.cohesiva.drifter.common.IVisible;


/**
 * The <code>IComplex</code> represents an interface for a complex.
 * <code>IComplex</code> has some complexity (typically it consists of
 * something). It can be split into <code>IComplex</code> subentities to reduce
 * the complexity.
 * 
 * The <code>ISpace</code> and <code>IBoundingBox</code> are examples of
 * <code>IComplex</code>.
 * 
 * @author carmel
 * 
 */
public interface IComplex extends IPopulable, IVisible {

	/*
	 * The <code>DEFAULT_SPLIT_CRITERIA</code> stands for a default IComplex
	 * split criteria.
	 */
	public static final ISplitCriteria<IComplex> DEFAULT_SPLIT_CRITERIA = new ThresholdSplitCriteria<IComplex>();

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
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @param offset
	 *            the split offset
	 * 
	 * @return the splitted part
	 */
	public IComplex onSplit(ISplitContext splitContext, IOffset offset);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @param splittedParts
	 *            the splitted parts
	 */
	public void onSplitComplete(ISplitContext splitContext,
			IComplex[] splittedParts);

	/**
	 * Split lifecycle callback executed on every merge.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @param mergedWhole
	 *            the merged whole complex
	 */
	public void onMerge(ISplitContext splitContext, IComplex mergedWhole);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 */
	public void onMergeComplete(ISplitContext splitContext);

}
