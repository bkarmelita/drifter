/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.population;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.common.RandomLocation;
import com.cohesiva.drifter.population.IPopulationStrategy;
import com.cohesiva.drifter.split.containers.BoundingSquare;
import com.cohesiva.drifter.split.containers.Plane;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * The <code>StarPopulationStrategy</code> represents an example of simple
 * population strategy.
 * 
 * @author carmel
 * 
 */
public class StarPopulationStrategy implements
		IPopulationStrategy<Plane> {

	private static final int[] CIRCE_RADIUS_BY_DEPTH = new int[] {
			50, // for level 0
			30, // for level 1
			20, // for level 2
			10, // for level 3
			5,  // for level 4
			2,  // for level 5
			1,  // for level 6
	};

	@Override
	public void populate(Plane square, Location referenceLocation) {
		// set up fuse
		int idx = square.depth() <= 6 ? square.depth() : 6;
		Location topLeft = ((BoundingSquare) square.bounds()).getTopLeft();

		// {{ compute min and max coords
		int minx = (int) topLeft.x();
		int miny = (int) topLeft.y() - (int) (square.bounds().radius() * 2)
				+ CIRCE_RADIUS_BY_DEPTH[idx];
		int maxx = (int) topLeft.x() + (int) (square.bounds().radius() * 2)
				- CIRCE_RADIUS_BY_DEPTH[idx];
		int maxy = (int) topLeft.y();
		// }}

		// {{ compute location bounds
		Location minLocation = new Location(minx, miny, 0,
				DistanceUnit.LIGHT_YEAR);
		Location maxLocation = new Location(maxx, maxy, 0,
				DistanceUnit.LIGHT_YEAR);
		
		for (int i = 1; i <= 2; i++) {
			Location randomLocation = new RandomLocation(minLocation,
				maxLocation, square.randomizer);
			// }}

			// populate 1 new star
			square.addContent(new Star(StarClass.values()[idx], randomLocation, square.depth()));
		}
	}

}
