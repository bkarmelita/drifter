/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.IComplex;
import com.cohesiva.drifter.datastruct.IPopulationStrategy;
import com.cohesiva.drifter.datastruct.ISplitCriteria;
import com.cohesiva.drifter.datastruct.SplitDegree;

/**
 * The <code>SquareWithCircles</code> represents an example
 * <code>IComplex</code>. This complex consists of Square and few Circles. The
 * circles are being populated while split.
 * 
 * @author bkarmelita
 * 
 */
public class SquareWithCircles implements IComplex {

	private static final ISplitCriteria<SquareWithCircles> SPLIT_CRITERIA = new InsideSquareCriteria();
	private static final IPopulationStrategy<SquareWithCircles> POP_STRATEGY = new CirclePopulationStrategy();
	
	/**
	 * The <code>depth</code> stands for a depth origin.
	 */
	protected int depth;

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
		super();
		this.depth = depth;
		this.square = square;
		this.circles = new ArrayList<Circle>(2);
		this.random = new Random(idx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#depth()
	 */
	@Override
	public int depth() {
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#splitDegree()
	 */
	@Override
	public SplitDegree splitDegree() {
		return SplitDegree.QUARTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#defaultSplitStrategy()
	 */
	@Override
	public ISplitCriteria<SquareWithCircles> splitCriteria() {
		return SPLIT_CRITERIA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#complexity()
	 */
	@Override
	public int complexity() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#split(com.cohesiva.drifter.common
	 * .Location)
	 */
	@Override
	public IComplex[] split(Location referenceLocation, int parentIndex) {
		SquareWithCircles[] splitted = new SquareWithCircles[this.splitDegree()
				.value()];

		// {{ iterate through splitted holders
		for (Offset offset : Offset.values()) {
			Square offsetSquare = offset.offset(this.square);
			SquareWithCircles holder = new SquareWithCircles(parentIndex, this.depth + 1,
					offsetSquare);
			POP_STRATEGY.populate(holder, referenceLocation);
			splitted[offset.ordinal()] = holder;
		}
		// }}

		for (Circle circle : this.circles) {
			for (Offset offset : Offset.values()) {
				SquareWithCircles holder = splitted[offset.ordinal()];
				if (Square.isInside(holder.square, circle.getLocx(),
						circle.getLocy())) {
					splitted[offset.ordinal()].circles.add(circle);
					break;
				}
			}
		}
		return splitted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cohesiva.drifter.datastruct.IComplex#merge(com.cohesiva.drifter.
	 * datastruct.IComplex)
	 */
	@Override
	public void merge(IComplex other) {
		SquareWithCircles sq = (SquareWithCircles) other;
		sq.square = null;
		sq.random = null;
	}

}
