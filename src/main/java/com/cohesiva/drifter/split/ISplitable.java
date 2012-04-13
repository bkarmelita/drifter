/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

/**
 * The <code>ISplitable</code> represents an interface for a complex.
 * <code>ISplitable</code> has some complexity (typically it consists of
 * something). It can be split into <code>ISplitable</code> subentities to
 * reduce the complexity.
 * 
 * The <code>ISpace</code> and <code>IBoundingBox</code> are examples of
 * <code>ISplittables</code>.
 * 
 * @author carmel
 * 
 */
public interface ISplitable {

	/**
	 * Get the population origin depth.
	 */
	public int depth();

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
	 * Gets the split degree
	 * 
	 * @return the split degree
	 */
	public SplitDegree splitDegree();

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
	public <T extends ISplitable> ISplitable onSplit(
			ISplitContext<T> splitContext, IOffset offset);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @param splittedParts
	 *            the splitted parts
	 */
	public <T extends ISplitable> void onSplitComplete(
			ISplitContext<T> splitContext, ISplitable[] splittedParts);

	/**
	 * Split lifecycle callback executed on every merge.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @param mergedWhole
	 *            the merged whole complex
	 */
	public <T extends ISplitable> void onMerge(ISplitContext<T> splitContext,
			ISplitable mergedWhole);

	/**
	 * Split lifecycle callback executed on split completion.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 */
	public <T extends ISplitable> void onMergeComplete(
			ISplitContext<T> splitContext);

}
