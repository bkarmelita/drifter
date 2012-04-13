/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.split.containers.Volume;

/**
 * The <code>TreeIndexVerifier</code> represents ... TODO
 * 
 * @author carmel
 * 
 */
public class TreeIndexVerifier implements ITreeNodeVisitor<Volume> {

	@Override
	public void visit(ITreeNode<Volume> node) {
		long idx = node.index();
		System.out.println("depth: " + node.depth() + ", indexInParent: " + node.indexInParent() + ", index: " + idx + ", binary: " + Long.toBinaryString(idx));
		// TODO: not implemented yet
	}

}
