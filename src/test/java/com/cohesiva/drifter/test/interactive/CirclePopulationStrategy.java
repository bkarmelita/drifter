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

	private static final int[] CIRCE_RADIUS_BY_DEPTH = new int[] { 50, // for
																		// level
																		// 0
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

		// {{ compute min and max coords
		int minx = (int) topLeft.x();
		int miny = (int) topLeft.y() - square.square.getWidth()
				+ CIRCE_RADIUS_BY_DEPTH[idx];
		int maxx = (int) topLeft.x() + square.square.getWidth()
				- CIRCE_RADIUS_BY_DEPTH[idx];
		int maxy = (int) topLeft.y();
		// }}

		// {{ compute location bounds
		Location minLocation = new Location(minx, miny, 0,
				DistanceUnit.LIGHT_YEAR);
		Location maxLocation = new Location(maxx, maxy, 0,
				DistanceUnit.LIGHT_YEAR);
		// }}

		// populate 1 new circle per square
		//square.circles.add(new Circle(minLocation, CIRCE_RADIUS_BY_DEPTH[idx]));
		//square.circles.add(new Circle(maxLocation, CIRCE_RADIUS_BY_DEPTH[idx]));
		Location randomLocation = new RandomLocation(minLocation,
				maxLocation, square.random);
		square.circles.add(new Circle(randomLocation,
				CIRCE_RADIUS_BY_DEPTH[idx]));
		/*for (int count = 1; count <= 1; count++) {
			Location randomLocation = new RandomLocation(minLocation,
					maxLocation, square.random);
			square.circles.add(new Circle(randomLocation,
					CIRCE_RADIUS_BY_DEPTH[idx]));
		}*/
	}

	/**
	 * Compute origin depth of the given circle.
	 * 
	 * @param circle
	 * @return circle origin depth
	 */
	public static int originDepth(Circle circle) {
		int depth = -1;

		int radius = circle.getRadius();

		for (int i = 0; i < CIRCE_RADIUS_BY_DEPTH.length; i++) {
			int radiusByDepth = CIRCE_RADIUS_BY_DEPTH[i];
			if (radiusByDepth == radius) {
				depth = i;
			}
		}

		return depth;
	}

}
