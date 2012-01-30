/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ITreeNode</code> represents an interface for an abstract tree node.
 * 
 * @author bkarmelita
 * 
 */
public interface ITreeNode<T extends IComplex> {

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
	 * Gets this node index in parent node.
	 * 
	 * @return node index in parent
	 */
	public int indexInParent();
	
	/**
	 * TODO: 
	 * 
	 * @return
	 */
	public int index();

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
	public void build(Location referenceLocation, int threshold, int maxDepth);
	
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
	 * @param referenceLocation
	 *            the reference location being considered while splitting
	 *            (player location)
	 * @return subnodes of this node
	 */
	public ITreeNode<T>[] split(Location referenceLocation);

	/**
	 * Merges this <code>ITreeNode</code> reducing his children.
	 * 
	 * @param referenceLocation
	 *            the reference location being considered while merging (player
	 *            location)
	 */
	public T merge(Location referenceLocation);

}
