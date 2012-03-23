/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.IComplex;
import com.cohesiva.drifter.split.IOffset;
import com.cohesiva.drifter.split.ISplitContext;
import com.cohesiva.drifter.terrain.BoundingSquare;
import com.cohesiva.drifter.terrain.IBoundingSquare;
import com.cohesiva.drifter.test.interactive.shapes.Circle;
import com.cohesiva.drifter.test.interactive.shapes.Square;

/**
 * The <code>SquareWithCircles</code> represents an example
 * <code>IComplex</code>. This complex consists of Square and few Circles. The
 * circles are being populated while split.
 * 
 * @author carmel
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
	public SquareWithCircles(long idx, int depth, Square square) {
		super(square.getCenter(), square.getWidth() / 2, depth);
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
	public SquareWithCircles(long idx, int depth, IBoundingSquare bounds) {
		super(bounds.center(), bounds.radius(), depth);
		this.square = new Square(bounds.center(), (int) (2 * bounds.radius()));
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
	public IComplex onSplit(ISplitContext ctx, IOffset offset) {
		// split space bounds first
		IBoundingSquare subbound = (IBoundingSquare) super.onSplit(ctx, offset);
		// than split holder
		SquareWithCircles holder = new SquareWithCircles(ctx.subindex(offset), this.depth + 1,
				subbound);
		
		// populate new circles
		POP_STRATEGY.populate(holder, ctx.referenceLocation());

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
	public void onSplitComplete(ISplitContext ctx, IComplex[] splittedParts) {
		// {{ distribute all circles to splitted complexes
		for (Iterator<Circle> iter = this.circles.iterator(); iter.hasNext(); ) {
			Circle circle = iter.next();
			
			for (IOffset offset : this.splitDegree().offsets()) {
				SquareWithCircles holder = (SquareWithCircles) splittedParts[offset
						.offsetIndex()];
				if (holder.isSurrounding(ctx.referenceLocation(), 0)) {
					holder.circles.add(circle);
					iter.remove();
					
					break;
				}

			}
		}
		// }}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMerge(com.cohesiva.drifter
	 * .common.Location, com.cohesiva.drifter.datastruct.IComplex)
	 */
	@Override
	public void onMerge(ISplitContext ctx, IComplex mergedWhole) {
		SquareWithCircles wholeComplex = ((SquareWithCircles) mergedWhole);
		
		// {{ give the circles back to merged complex
		for (Iterator<Circle> iter = this.circles.iterator(); iter.hasNext(); ) {
			Circle circle = iter.next();
			int originDepth = CirclePopulationStrategy.originDepth(circle);
			
			if (originDepth <= wholeComplex.depth()) {
				wholeComplex.circles.add(circle);
				iter.remove();
			}
			
		}

		this.circles.clear();
		// }}
		
		// reset random
		wholeComplex.random = new Random(ctx.index());
		
		// {{ mark for garbage collection
		this.square = null;
		this.random = null;
		this.circles = null;
		// }}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.IComplex#onMergeComplete(com.cohesiva
	 * .drifter.common.Location)
	 */
	@Override
	public void onMergeComplete(ISplitContext ctx) {
		// do nothing
	}

}
