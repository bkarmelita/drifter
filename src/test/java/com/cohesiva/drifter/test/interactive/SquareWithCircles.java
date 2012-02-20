/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.terrain.BoundingSquare;
import com.cohesiva.drifter.terrain.IBoundingSquare;
import com.cohesiva.drifter.test.interactive.shapes.Circle;
import com.cohesiva.drifter.test.interactive.shapes.Square;

/**
 * The <code>SquareWithCircles</code> represents an example
 * <code>IComplex</code>. This complex consists of Square and few Circles. The
 * circles are being populated while split.
 * 
 * @author bkarmelita
 * 
 */
public class SquareWithCircles extends BoundingSquare {

	private static final IPopulationStrategy<SquareWithCircles> POP_STRATEGY = new CirclePopulationStrategy();

	/**
	 * The <code>square</code> stands for a
	 */
	protected Square square;

	/**
	 * The <code>circles</code> stands for circes of this square.
	 */
	protected List<Circle> circles;

	/**
	 * The <code>random</code> stands for a randomizer.
	 */
	protected Random random;

	/**
	 * Creates the new <code>SquareWithCircles</code> instance.
	 * 
	 * @param depth
	 * @param square
	 */
	public SquareWithCircles(int idx, int depth, Square square) {
		super(square.getCenter(), square.getWidth()/2, depth);
		this.square = square;
		this.circles = new ArrayList<Circle>(2);
		this.random = new Random(idx);
	}
	
	/**
	 * Creates the new <code>SquareWithCircles</code> instance.
	 *
	 * @param idx
	 * @param depth
	 * @param bounds
	 */
	public SquareWithCircles(int idx, int depth, IBoundingSquare bounds) {
		super(bounds.center(), bounds.radius(), depth);
		this.square = new Square(bounds.center(), (int) (2*bounds.radius()));
		this.circles = new ArrayList<Circle>(2);
		this.random = new Random(idx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#complexity()
	 */
	@Override
	public int complexity() {
		return (circles != null ? circles.size() : 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplit(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IOffset)
	 */
	@Override
	public IComplex onSplit(Location referenceLocation, IOffset offset) {
		// split space bounds first
		IBoundingSquare subbound = (IBoundingSquare) super.onSplit(referenceLocation, offset);
		// than split holder
		SquareWithCircles holder = new SquareWithCircles(1, this.depth + 1, subbound);
		// populate
		POP_STRATEGY.populate(holder, referenceLocation);

		return holder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onSplitComplete(com.cohesiva
	 * .drifter.common.Location, com.cohesiva.drifter.datastruct.IComplex[])
	 */
	@Override
	public void onSplitComplete(Location referenceLocation,
			IComplex[] splittedParts) {
		for (Circle circle : this.circles) {
			for (IOffset offset : this.splitDegree().offsets()) {
				SquareWithCircles holder = (SquareWithCircles) splittedParts[offset
						.offsetIndex()];
				if (holder.isSurrounding(referenceLocation, 0)) {
					((SquareWithCircles) splittedParts[offset.offsetIndex()]).circles
							.add(circle);
					break;
				}

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMerge(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IComplex)
	 */
	@Override
	public void onMerge(Location referenceLocation, IComplex mergedWhole) {
		this.square = null;
		this.random = null;
		this.circles.clear();
		this.circles = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMergeComplete(com.cohesiva
	 * .drifter.common.Location)
	 */
	@Override
	public void onMergeComplete(Location referenceLocation) {
		// do nothing
	}

}
