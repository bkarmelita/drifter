/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.datastruct;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.split.SplitDegree;

/**
 * The <code>Tree</code> represents the default implementation of the tree.
 * 
 * @author carmel
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
	 * com.cohesiva.drifter.datastruct.ITreeNode#build(com.cohesiva.drifter.
	 * common.Location, int, int)
	 */
	@Override
	public void build(Location referenceLocation, int threshold, int maxDepth) {
		// prepare split context
		ISplitContext ctx = this.new TreeSplitContext(referenceLocation);
		// check if we shall go into deep (split / merge)
		if (complex.splitCriteria().evaluate(complex, referenceLocation,
				threshold)
				&& this.depth() < maxDepth) {
			// we are of high complexity so we try to split ...
			this.split(ctx);

			// {{ .. than recursively build every child
			for (int i = 0; i < this.children.length; i++) {
				ITreeNode<T> child = this.children[i];
				child.build(referenceLocation, threshold, maxDepth);
			}
			// }}
		} else {
			if (this.depth() > 0) {
				this.merge(ctx);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#item()
	 */
	@Override
	public T item() {
		return complex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#children()
	 */
	@Override
	public ITreeNode<T>[] children() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return children == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#parent()
	 */
	@Override
	public ITreeNode<T> parent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#indexInParent()
	 */
	@Override
	public int indexInParent() {
		return indexInParent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.ITreeNode#index()
	 */
	// TODO: check it in JUnit
	@Override
	public long index() {
		long result = this.indexInParent();
		SplitDegree splitDegree = this.complex.splitDegree();

		if (this.parent() != null) {
			int bitsToShift = (this.depth() - 1) * splitDegree.dimension();
			result = result << bitsToShift;
			result = result + this.parent().index();
		}

		return result;
	}
	
	/**
	 * TODO: experimental, check it in JUnit
	 * @param offset
	 * @return
	 */
	protected long subindex(IOffset offset) {
		long result = this.index();
		SplitDegree splitDegree = this.complex.splitDegree();
		
		int bitsToShift = (this.depth() + 1) * splitDegree.dimension();
		int offsetIndex = offset.offsetIndex() << bitsToShift;
		result += offsetIndex;
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ITreeNode#accept(com.cohesiva.drifter
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
	 * com.cohesiva.drifter.datastruct.ITreeNode#split(com.cohesiva.drifter.
	 * common.Location)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ITreeNode<T>[] split(ISplitContext ctx) {
		if (this.isLeaf()) {
			// get the split degree from complex
			SplitDegree splitDegree = complex.splitDegree();
			// prepare bounding box placeholders for child octants
			IComplex[] subcontents = new IComplex[splitDegree.value()];

			// {{ iterate through offsets to create subcontents
			for (IOffset offset : splitDegree.offsets()) {
				// call split/merge lifecycle to split into new subcomplex
				IComplex subcomplex = complex.onSplit(ctx, offset);
				// store subcomplex
				subcontents[offset.offsetIndex()] = subcomplex;
			}
			// }}

			// call split/merge lifecycle to process on complete split
			complex.onSplitComplete(ctx, subcontents);

			// prepare memory for tree children
			this.children = new Tree[complex.splitDegree().value()];

			// {{ create each tree child and store subcomplex inside
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
	 * com.cohesiva.drifter.datastruct.ITreeNode#merge(com.cohesiva.drifter.
	 * common.Location)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T merge(ISplitContext ctx) {
		T result = this.complex;

		if (!this.isLeaf()) {
			// {{ merge each child
			for (int i = 0; i < this.children.length; i++) {
				ITreeNode<T> childNode = this.children[i];
				// merge node
				T complexPart = childNode.merge(ctx);
				// call split/merge lifecycle to merge to parent complex
				complexPart.onMerge(ctx, this.complex);
			}
			// }}

			// call split/merge lifecycle to process on merge completion
			this.complex.onMergeComplete(ctx);

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

	/**
	 * The <code>TreeSplitContext</code> represents the default
	 * <code>ISplitCOntext</code> implementation fro tree.
	 * 
	 * @author carmel
	 * 
	 */
	private class TreeSplitContext implements ISplitContext {

		/**
		 * The <code>referenceLocation</code> stands for a split location.
		 */
		private Location referenceLocation;

		/**
		 * Creates the new <code>TreeSplitContext</code> instance.
		 * 
		 * @param index
		 * @param offset
		 * @param referenceLocation
		 */
		private TreeSplitContext(Location referenceLocation) {
			super();

			this.referenceLocation = referenceLocation;
		}

		@Override
		public long index() {
			return Tree.this.index();
		}

		@Override
		public long subindex(IOffset offset) {
			return Tree.this.subindex(offset);
		}

		@Override
		public Location referenceLocation() {
			return referenceLocation;
		}

	}

}
