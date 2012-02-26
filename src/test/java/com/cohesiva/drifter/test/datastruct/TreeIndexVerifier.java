/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.stellar.Space;

/**
 * The <code>TreeIndexVerifier</code> represents ... TODO
 * 
 * @author bkarmelita
 * 
 */
public class TreeIndexVerifier implements ITreeNodeVisitor<Space> {

	@Override
	public void visit(ITreeNode<Space> node) {
		long idx = node.index();
		System.out.println("depth: " + node.depth() + ", indexInParent: " + node.indexInParent() + ", index: " + idx + ", binary: " + Long.toBinaryString(idx));
		// TODO: not implemented yet
	}

}
