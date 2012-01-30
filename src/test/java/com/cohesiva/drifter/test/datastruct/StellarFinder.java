/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.test.datastruct;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.ITreeNode;
import com.cohesiva.drifter.datastruct.ITreeNodeVisitor;
import com.cohesiva.drifter.stellar.ISpace;
import com.cohesiva.drifter.stellar.IStellar;

/**
 * The <code>StellarFinder</code> represents an example Octree node visitor.
 * 
 * @author bkarmelita
 * 
 */
public class StellarFinder implements ITreeNodeVisitor<ISpace> {

	/**
	 * The <code>templateLocation</code> stands for a template location to be
	 * found in an octree.
	 */
	private Location templateLocation;

	private int resultDepth;

	private String resultIndex = "";

	/**
	 * Creates the new <code>SpatialFinder</code> instance.
	 * 
	 * @param templateLocation
	 */
	public StellarFinder(Location templateLocation) {
		super();
		this.templateLocation = templateLocation;
	}

	/**
	 * Gets the current value of <code>resultDepth</code>.
	 * 
	 * @return the resultDepth
	 */
	public int getResultDepth() {
		return resultDepth;
	}

	/**
	 * Gets the current value of <code>resultIndex</code>.
	 * 
	 * @return the resultIndex
	 */
	public String getResultIndex() {
		return resultIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codestellar.drifter.datastruct.IOctreeNodeVisitor#visit(com.codestellar
	 * .drifter.datastruct.ITreeNode)
	 */
	@Override
	public void visit(ITreeNode<ISpace> node) {
		for (IStellar nextStellar : node.item().stellars()) {
			Location stellarLocation = nextStellar.locate();
			if (stellarLocation.equals(templateLocation)) {
				resultDepth = node.depth();

				ITreeNode<ISpace> indexer = node;

				do {
					resultIndex = indexer.indexInParent() + resultIndex;
					indexer = indexer.parent();
				} while (indexer != null);

				break;
			}
		}
	}
}
