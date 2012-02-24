/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.split;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>ISplitContext</code> defines an interface for a context of splitting.
 * The split context serves as a placeholder for information to share between ITreeNode and IComplex.
 *
 * @author bkarmelita
 *
 */
public interface ISplitContext {
	
	/**
	 * Returns the splitting index (the index of a node being split).
	 * 
	 * @return
	 */
	public long index();
	
	/**
	 * Returns the splitting reference location.
	 * 
	 * @return
	 */
	public Location referenceLocation();

}
