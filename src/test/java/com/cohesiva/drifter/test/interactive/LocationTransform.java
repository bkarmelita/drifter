package com.cohesiva.drifter.test.interactive;

import com.cohesiva.drifter.common.DistanceUnit;
import com.cohesiva.drifter.common.Location;

/**
 * The <code>LocationTransform</code> represents a location transformer.
 *
 * @author bkarmelita
 *
 */
public class LocationTransform {
	
	public static final int SCREEN_SIZE = 800;
	public static final Location CENTER = new Location(SCREEN_SIZE/2, SCREEN_SIZE/2, 0, DistanceUnit.LIGHT_YEAR);
	
	public static Location toDisplayLocation(Location source) {
		Location displayLocation = new Location(source.x(), -source.y(), 0, source.getUnit());
		displayLocation.addAndStore(CENTER);
		
		return displayLocation;
	}
	
	public static Location toRealLocation(Location source) {
		Location realLocation = new Location((source.x() - SCREEN_SIZE), -source.y(), 0, source.getUnit());
		realLocation.addAndStore(CENTER);
		
		return realLocation;
	}

}
