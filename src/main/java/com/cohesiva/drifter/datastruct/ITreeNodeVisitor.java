/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.split.ISplitable;


/**
 * The <code>INodeVisitor</code> represents an interface for an abstract tree
 * walker. Visitor responsibility is to visit each tree node to check some
 * things and report them.
 * 
 * @author carmel
 * 
 */
public interface ITreeNodeVisitor<T extends ISplitable> {

	/**
	 * Visit the given node.
	 * 
	 * @param node
	 */
	public void visit(ITreeNode<T> node);

}
