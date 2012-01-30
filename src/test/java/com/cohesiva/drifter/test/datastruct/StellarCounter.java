/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import java.util.HashMap;
import java.util.Map;

import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.stellar.ISpace;

/**
 * The <code>StellarCounter</code> represents an example tree node visitor
 * counting stellars.
 * 
 * @author bkarmelita
 * 
 */
public class StellarCounter implements ITreeNodeVisitor<ISpace> {

	/**
	 * The <code>counter</code> stands for the stellar counter.
	 */
	protected Map<Integer, Integer> stellarCounter = new HashMap<Integer, Integer>();
	
	/**
	 * The <code>counter</code> stands for the stellar counter.
	 */
	protected Map<Integer, Integer> nodeCounter = new HashMap<Integer, Integer>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.ITreeNodeVisitor#visit(com.cohesiva.drifter
	 * .datastruct.ITreeNode)
	 */
	@Override
	public void visit(ITreeNode<ISpace> node) {
		if (! stellarCounter.containsKey(node.depth())) {
			stellarCounter.put(node.depth(), 0);
		}
		
		if (! nodeCounter.containsKey(node.depth())) {
			nodeCounter.put(node.depth(), 0);
		}
		
		if (node.item().complexity() > 0) {			
			int depthStellarCounter = stellarCounter.get(node.depth());			
			depthStellarCounter += node.item().complexity();
			stellarCounter.put(node.depth(), depthStellarCounter);
			
			int depthNodeCounter = nodeCounter.get(node.depth());
			nodeCounter.put(node.depth(), depthNodeCounter + 1);
		}
	}

	/**
	 * Gets the count of stellars on the given depth.
	 * 
	 * @return the stellar count
	 */
	public int stellarCount(int depth) {
		int result = 0;
		
		if (stellarCounter.containsKey(depth)) {
			result = stellarCounter.get(depth);
		}
		
		return result;
	}
	
	/**
	 * Gets the count of non empty nodes on the given depth.
	 * 
	 * @param depth
	 * @return
	 */
	public int nodeCount(int depth) {
		int result = 0;
		
		if (nodeCounter.containsKey(depth)) {
			result = nodeCounter.get(depth);
		}
		
		return result;
	}

	/**
	 * Resets the counter.
	 */
	public void reset() {
		this.stellarCounter.clear();
		this.nodeCounter.clear();
	}
	
	/**
	 * TODO: implement this
	 * 
	 * @return
	 */
	public String report() {
		return "";
	}

}
