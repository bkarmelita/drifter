/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

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
	 * Gives the configured split strategy for this complex.
	 * 
	 * @return the current split strategy
	 */
	public ISplitCriteria splitCriteria();

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
	 * Split this <code>IComplex</code> into smaller parts.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while splitting
	 *            (player location)
	 * @return broken parts of this whole
	 */
	public IComplex[] split(Location referenceLocation);

	/**
	 * Merges the other complex.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while merging (player
	 *            location)
	 */
	public void merge(IComplex other);

}
