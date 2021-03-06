/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.split;



/**
 * The <code>SplitDegree</code> represents a degree of splitting. We use octant
 * split degree in an octree structure modeling the galaxy. Quarter split degree
 * will be used in a quadtree when modeling surface.
 * 
 * @author carmel
 * 
 */
public enum SplitDegree {

	/**
	 * Being able to split into four (4) equal pieces.
	 */
	QUARTER(4) {
		
		@Override
		public IOffset[] offsets() {
			return QuarterOffset.values();
		}

		@Override
		public int dimension() {
			return 2;
		}

	},

	/**
	 * Being able to split into eight (2^3) equal pieces.
	 */
	OCTANT(8) {
		
		@Override
		public IOffset[] offsets() {
			return OctantOffset.values();
		}
		
		@Override
		public int dimension() {
			return 3;
		}
	};

	/**
	 * The <code>degree</code> stands for a split degree.
	 */
	private int degree;

	/**
	 * 
	 * @param degree
	 */
	private SplitDegree(int degree) {
		this.degree = degree;
	}

	/**
	 * Gets the split degree.
	 * 
	 * @return split degree
	 */
	public int value() {
		return this.degree;
	}

	/**
	 * Gets an indexed offset implementation for this split degree.
	 * 
	 * @param index
	 *            offset index
	 * @return
	 */
	public abstract IOffset[] offsets();
	
	/**
	 * Gets the dimension of this split degree (2D | 3D).
	 * 
	 * @return
	 */
	public abstract int dimension();

}
