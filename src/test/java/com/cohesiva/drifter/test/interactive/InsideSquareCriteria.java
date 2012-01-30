/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.datastruct.ISplitCriteria;

/**
 * The <code>InsideSquareCriteria</code> represents ... TODO
 * 
 * @author bkarmelita
 * 
 */
public class InsideSquareCriteria implements ISplitCriteria<SquareWithCircles> {

	/**
	 * The <code>padding</code> stands for a padding.
	 */
	private double padding = 0.1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cohesiva.drifter.datastruct.ISplitCriteria#evaluate(com.cohesiva.
	 * drifter.datastruct.IComplex, com.cohesiva.drifter.common.Location, int)
	 */
	@Override
	public boolean evaluate(SquareWithCircles complex,
			Location referenceLocation, int threshold) {
		int widthPad = (int) (complex.square.getWidth() * padding);
		int paddedWidth = complex.square.getWidth() + widthPad;

		int minx = complex.square.getLocx() - widthPad;
		int miny = complex.square.getLocy() - widthPad;
		int maxx = complex.square.getLocx() + paddedWidth;
		int maxy = complex.square.getLocy() + paddedWidth;

		// {{ check location against max and min
		if (referenceLocation.x() < minx || referenceLocation.x() > maxx) {
			return false;
		}

		if (referenceLocation.y() < miny || referenceLocation.y() > maxy) {
			return false;
		}
		// }}

		return true;
	}

}
