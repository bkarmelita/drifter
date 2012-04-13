/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.ISplitCriteria;
import com.cohesiva.drifter.split.ISplitable;

/**
 * The <code>ITreeNode</code> represents an interface for an abstract tree node.
 * 
 * @author carmel
 * 
 */
public interface ITreeNode<T extends ISplitable> {

	/**
	 * Get the depth of this node.
	 * 
	 * @return
	 */
	public int depth();

	/**
	 * Gets this node parent.
	 * 
	 * @return this node parent
	 */
	public ITreeNode<T> parent();

	/**
	 * Gets the index of this node node against the parent node.
	 * 
	 * @return node index against parent
	 */
	public int indexInParent();

	/**
	 * Gets this node index.
	 * 
	 * @return
	 */
	public long index();

	/**
	 * Gets this node children.
	 * 
	 * @return this node children
	 */
	public ITreeNode<T>[] children();

	/**
	 * Gets this node item.
	 * 
	 * @return this node item.
	 */
	public T item();

	/**
	 * Checks if this node is a leaf.
	 * 
	 * @return <code>true</code> if this node has no children; <code></code>
	 *         otherwise;
	 */
	public boolean isLeaf();

	/**
	 * Builds this node regarding the reference location.
	 * 
	 * @param referenceLocation
	 * @param threshold
	 * @param maxDepth
	 */
	public void build(Location referenceLocation, ISplitCriteria<T> splitCriteria, IPopulationStrategy<T> populationStrategy, int threshold, int maxDepth);

	/**
	 * Builds this node regarding the reference location.
	 * 
	 * @param referenceLocation
	 * @param maxDepth
	 */
	public void build(Location referenceLocation, int maxDepth);

	/**
	 * Builds this node regarding the reference location.
	 * 
	 * @param referenceLocation
	 */
	public void build(Location referenceLocation);

	/**
	 * Accept the given visitor to let him visit this node.
	 * 
	 * @param visitor
	 */
	public void accept(ITreeNodeVisitor<T> visitor);

	/**
	 * Splits this <code>ITreeNode</code> into subnodes.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 * @return subnodes of this node
	 */
	public ITreeNode<T>[] split(ISplitContext<T> ctx);

	/**
	 * Merges this <code>ITreeNode</code> reducing his children.
	 * 
	 * @param splitContext
	 *            the splitting context information (eg. player location)
	 */
	public T merge(ISplitContext<T> ctx);

}
