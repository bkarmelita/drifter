/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ISplitContext</code> defines an interface for a context of splitting.
 * The split context serves as a placeholder for information to share between ITreeNode and IComplex.
 *
 * @author carmel
 *
 */
public interface ISplitContext {
	
	/**
	 * Returns the node splitting index (the index of a node being split).
	 * 
	 * @return
	 */
	public long index();
	
	/**
	 * Returns the node subindex based on current context index and the given splitting offset.
	 * 
	 * @param offset
	 * @return
	 */
	public long subindex(IOffset offset);
	
	/**
	 * Returns the splitting reference location.
	 * 
	 * @return
	 */
	public Location referenceLocation();

}
