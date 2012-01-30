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
 * The <code>DefaultPopulationStrategy</code> represents the default population
 * strategy for space where extra stars are populated during the space split.
 * 
 * TODO: This class is under construction ...
 * 
 * @author bkarmelita
 * 
 */
public class DefaultPopulationStrategy implements ISpacePopulationStrategy {
	
	private static final Random random = new Random(255); // its nonsense ofcourse
	
	private static int[] virtualPopulation = new int[] {
		50000000, // for level 0 (50.000)
		5000000,  // for level 1 (5.000)
		500000,   // for level 2 ()
		25000, 	  // for level 3
		12000,    // for level 4
		8000,     // for level 5
		4000,     // for level 6
		2400,     // for level 7
		1500,     // for level 8
		1000,     // for level 9
		600,      // for level 10
		350,      // for level 11
		200       // for level 12
};
	
	private static double[] densityFactor = new double[] {
			0.1, // for level 0
			0.1, // for level 1
			0.1, // for level 2
			1, // for level 3
			0.1, // for level 4
			1, // for level 5
			10, // for level 6
			10, // for level 7
			20, // for level 8
			5, // for level 9
			5, // for level 10
			5, // for level 11
			5 // for level 12		
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
			Star star = new Star(StarClass.O, location, depth);
			space.addStellar(star);
		}
		// }}
	}

	@Override
	public int populationAt(int depth) {
		int population = (int) (virtualPopulation[depth] * densityFactor[depth] / 100);
		
		return population;
	}
	
	public static int populationAtt(int depth) {
		int population = (int) (virtualPopulation[depth] * densityFactor[depth] / 100);
		
		return population;
	}
	
	public static void main(String... args) {
		for (int i = 0; i <= 12; i++) {
			System.out.println(i + ":" + populationAtt(i));
		}
		
		long sum = 0;
		for (int i = 0; i <= 12; i++) {
			sum += virtualPopulation[i];
		}
		System.out.println("population:" + sum);
	}

}
