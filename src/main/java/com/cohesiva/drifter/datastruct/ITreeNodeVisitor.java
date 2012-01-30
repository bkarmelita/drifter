/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;


/**
 * The <code>INodeVisitor</code> represents an interface for an abstract tree
 * walker. Visitor responsibility is to visit each tree node to check some
 * things and report them.
 * 
 * @author bkarmelita
 * 
 */
public interface ITreeNodeVisitor<T extends IComplex> {

	/**
	 * Visit the given node.
	 * 
	 * @param node
	 */
	public void visit(ITreeNode<T> node);

}
