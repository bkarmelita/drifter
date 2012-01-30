/**
r * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.stellar.population;

import java.util.Random;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.common.RandomLocation;
import com.cohesiva.drifter.stellar.ISpace;
import com.cohesiva.drifter.stellar.ISpacePopulationStrategy;
import com.cohesiva.drifter.stellar.Star;
import com.cohesiva.drifter.stellar.StarClass;

/**
 * TODO: This class is experimental ...
 * 
 * @author bkarmelita
 * @deprecated
 */
public class XPopulationStrategy implements ISpacePopulationStrategy {
	
	private static final Random random = new Random(255); // its nonsense ofcourse
	
	private static int[] virtualPopulationByDepth = new int[] {
		400000000, // for level 0 (100.000)
		 50000000, // for level 1 ( 50.000)
		  6250000, // for level 2 ( 25.000)
		   781250, // for level 3 ( 12.500)
		    97656, // for level 4 (  6.250)
		    12000, // for level 5 (  3.125)
		     1500, // for level 6 (  1.562)
		      180 // for level 7 (    780)
		       //24, // for level 8 (    390)
		       // 8, // for level 9 (    200)
		       // 1, // for level 10(    100)
	};
	
	private static StarClass[] starClassByDepth = new StarClass[] {
		StarClass.O, // for level 0
		StarClass.B, // for level 1
		StarClass.A, // for level 2
		StarClass.F, // for level 3
		StarClass.G, // for level 4
		StarClass.A, // for level 5
		StarClass.K, // for level 6
		StarClass.M, // for level 7
		//StarClass.G, // for level 8
		//StarClass.K, // for level 9
		//StarClass.M, // for level 10
	};

	@Override
	public void populate(ISpace space, Location referenceLocation) {
		int depth = space.bounds().depth();
		double radius = space.bounds().radius();
		Location center = space.bounds().center();

		// {{ determine min and max within this bounds
		Location minLocation = center.add(new Location(-radius, -radius,
				-radius, center.getUnit()));
		Location maxLocation = center.add(new Location(radius, radius, radius,
				center.getUnit()));
		// }}

		// {{ generate random location
		int population = this.populationAt(depth);
		
		for (int i = 1; i <= population; i++) {
			Location location = new RandomLocation(minLocation, maxLocation, random);
			Star star = new Star(starClassByDepth[depth], location, depth);
			space.addStellar(star);
		}
		// }}
	}

	@Override
	public int populationAt(int depth) {
		int population = (int) (virtualPopulationByDepth[depth] * starClassByDepth[depth].getFraction());
		
		return population;
	}

}
