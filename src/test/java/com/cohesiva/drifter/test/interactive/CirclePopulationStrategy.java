/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.common.RandomLocation;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.test.interactive.shapes.Circle;

/**
 * The <code>CirclePopulationStrategy</code> represents an example of simple
 * population strategy.
 * 
 * @author bkarmelita
 * 
 */
public class CirclePopulationStrategy implements
		IPopulationStrategy<SquareWithCircles> {

	private static int[] circeRadiusByDepth = new int[] {
			50, // for level 0
			30, // for level 1
			20, // for level 2
			10, // for level 3
			5, // for level 4
			2, // for level 5
			1, // for level 6
	};

	@Override
	public void populate(SquareWithCircles square, Location referenceLocation) {
		// set up fuse
		int idx = square.depth() <= 6 ? square.depth() : 6;
		Location topLeft = square.square.getTopLeft();
		
		// {{ compute min and max
		int minx = (int) topLeft.x() + circeRadiusByDepth[idx];
		int miny = (int) topLeft.y() + circeRadiusByDepth[idx];
		int maxx = (int) topLeft.x() + square.square.getWidth() - circeRadiusByDepth[idx];
		int maxy = (int) topLeft.y() + square.square.getWidth() - circeRadiusByDepth[idx];
		// }}
		
		// {{ compute location bounds
		Location minLocation = new Location(minx, miny, 0, DistanceUnit.LIGHT_YEAR);
		Location maxLocation = new Location(maxx, maxy, 0, DistanceUnit.LIGHT_YEAR);
		// }}
		
		// populate 2 new circles
		square.circles.add(new Circle(new RandomLocation(minLocation, maxLocation, square.random), circeRadiusByDepth[idx]));
		//square.circles.add(new Circle(new RandomLocation(minLocation, maxLocation, square.random), circeRadiusByDepth[idx]));
	}

}