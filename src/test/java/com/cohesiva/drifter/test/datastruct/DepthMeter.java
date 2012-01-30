/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.stellar.ISpace;

/**
 * TODO: 
 * 
 * @author bkarmelita
 *
 */
public class DepthMeter implements ITreeNodeVisitor<ISpace> {
	
	protected int maxDepth = 0;

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNodeVisitor#visit(com.cohesiva.drifter.test.datastruct.ITreeNode)
	 */
	@Override
	public void visit(ITreeNode<ISpace> node) {
		if (node.depth() > maxDepth) {
			maxDepth = node.depth();
		}
	}
	
	/**
	 * Return the max node depth.
	 * 
	 * @return
	 */
	public int maxDepth() {
		return maxDepth;
	}
	
	/**
	 * Resets the meter.
	 */
	public void reset() {
		this.maxDepth = 0;
	}

}
