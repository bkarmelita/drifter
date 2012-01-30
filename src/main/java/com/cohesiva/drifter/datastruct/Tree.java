/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>Tree</code> represents the default implementation of the tree.
 * 
 * @author bkarmelita
 * 
 */
public class Tree<T extends IComplex> implements ITreeNode<T> {

	/*
	 * The <code>DEFAULT_THRESHOLD</code> stands for a default treshold.
	 */
	private static final int DEFAULT_THRESHOLD = 1000;

	/*
	 * The <code>DEFAULT_MAX_DEPTH</code> stands for a default max depth.
	 */
	private static final int DEFAULT_MAX_DEPTH = 6;

	/**
	 * The <code>parent</code> stands for a node parent.
	 */
	protected ITreeNode<T> parent;

	/**
	 * The <code>indexInParent</code> stands for a node index in parent.
	 */
	protected int indexInParent = 0;

	/**
	 * The <code>children</code> stands for a node children.
	 */
	protected ITreeNode<T>[] children;

	/**
	 * The <code>container</code> stands for a node complex.
	 */
	protected T complex;

	/**
	 * The <code>depth</code> stands for a node depth.
	 */
	protected int depth = 0;

	/**
	 * Creates the new <code>Tree</code> instance.
	 * 
	 */
	public Tree(T complex) {
		super();

		this.complex = complex;
	}

	/**
	 * Creates the new <code>Tree</code> instance.
	 * 
	 * @param depth
	 *            this tree node depth
	 * 
	 */
	protected Tree(T complex, int indexInParent, int depth) {
		super();

		this.complex = complex;
		this.indexInParent = indexInParent;
		this.depth = depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ITreeNode#build(com.cohesiva.drifter.
	 * common.Location, int)
	 */
	@Override
	public void build(Location referenceLocation, int maxDepth) {
		this.build(referenceLocation, DEFAULT_THRESHOLD, maxDepth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ITreeNode#build(com.cohesiva.drifter.
	 * common.Location)
	 */
	@Override
	public void build(Location referenceLocation) {
		this.build(referenceLocation, DEFAULT_THRESHOLD, DEFAULT_MAX_DEPTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.ITreeNode#build(com.cohesiva.drifter
	 * . common.Location, int, int)
	 */
	@Override
	public void build(Location referenceLocation, int threshold, int maxDepth) {
		// check if we shall go into deep (split / merge)
		if (complex.splitCriteria().evaluate(complex, referenceLocation,
				threshold)
				&& this.depth() < maxDepth) {
			// we are of high complexity so we try to split ...
			this.split(referenceLocation);

			// {{ .. than recursively build every child
			for (int i = 0; i < this.children.length; i++) {
				ITreeNode<T> child = this.children[i];
				child.build(referenceLocation, threshold, maxDepth);
			}
			// }}
		} else {
			if (this.depth() > 0) {
				this.merge(referenceLocation);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#item()
	 */
	@Override
	public T item() {
		return complex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#children()
	 */
	@Override
	public ITreeNode<T>[] children() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return children == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#parent()
	 */
	@Override
	public ITreeNode<T> parent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.test.datastruct.ITreeNode#indexInParent()
	 */
	@Override
	public int indexInParent() {
		return indexInParent;
	}

	/* (non-Javadoc)
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#index()
	 */
	@Override
	public int index() {
		int result = 0;
		
		int localIndex = this.indexInParent();
		localIndex = (localIndex << (this.complex.splitDegree().value() * this.depth()));
		
		result += localIndex;
		
		if (this.parent() != null) {
			result += this.parent.index();
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.ITreeNode#accept(com.cohesiva.drifter
	 * .datastruct.ITreeNodeVisitor)
	 */
	@Override
	public void accept(ITreeNodeVisitor<T> visitor) {
		visitor.visit(this);

		if (!this.isLeaf()) {
			for (ITreeNode<T> child : this.children()) {
				child.accept(visitor);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.ITreeNode#split(com.cohesiva.drifter
	 * . common.Location)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ITreeNode<T>[] split(Location referenceLocation) {
		if (this.isLeaf()) {
			// split into subcontents
			IComplex[] subcontents = complex.split(referenceLocation, this.index());

			// prepare memory for children
			this.children = new Tree[complex.splitDegree().value()];

			// {{ process each child
			for (int i = 0; i < this.children.length; i++) {
				Tree childNode = new Tree(subcontents[i], i, this.depth() + 1);
				childNode.parent = this;
				children[i] = childNode;
			}
			// }}
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.test.datastruct.ITreeNode#merge(com.cohesiva.drifter
	 * . common.Location)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T merge(Location referenceLocation) {
		T result = this.complex;

		if (!this.isLeaf()) {
			// {{ merge each child
			for (int i = 0; i < this.children.length; i++) {
				ITreeNode<T> childNode = this.children[i];
				// merge node
				T complexPart = childNode.merge(referenceLocation);
				// merge the merged node content
				this.complex.merge(complexPart);
			}
			// }}

			// {{ free memory for child nodes
			for (int i = 0; i < this.children.length; i++) {
				((Tree) this.children[i]).complex = null;
				((Tree) this.children[i]).parent = null;
				this.children[i] = null;
			}
			this.children = null;
			// }}
		}

		return result;
	}

}
