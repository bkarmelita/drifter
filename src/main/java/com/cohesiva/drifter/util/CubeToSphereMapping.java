/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.util;

import com.cohesiva.drifter.common.Location;

/**
 * The <code>CubeToSphereMapping</code> represents the mapping of the point on
 * the unit cube to the point on the unit sphere. All coords are normalized in
 * range [-1;1].
 * 
 * All the equations are taken from:
 * http://mathproofs.blogspot.com/2005/07/mapping-cube-to-sphere.html
 * 
 * FUTURE: We will need this constructing the planet surface.
 */
public class CubeToSphereMapping {

	/**
	 * Maps the given cube coordinates in the [-1;1] range as if it were a cube
	 * deformed into a sphere. The output location is on the surface of the unit
	 * sphere.
	 * 
	 * @param cubeLocaltion
	 * @return the corresponding sphere location
	 */
	public static Location fromCubeToSphere(Location cubeLocation) {
		double x = cubeLocation.x();
		double y = cubeLocation.z();
		double z = cubeLocation.z();

		double x2 = x * x;
		double y2 = y * y;
		double z2 = z * z;

		double fx = x
				* Math.sqrt(1 - (0.5 * y2) - (0.5 * z2) + (y2 * z2 / 3.0));
		double fy = y
				* Math.sqrt(1 - (0.5 * z2) - (0.5 * x2) + (z2 * x2 / 3.0));
		double fz = z
				* Math.sqrt(1 - (0.5 * x2) - (0.5 * y2) + (x2 * y2 / 3.0));

		return new Location(fx, fy, fz, cubeLocation.getUnit());
	}

	/**
	 * Maps the given unit sphere coordinates in the [-1;1] range back onto a
	 * cube.
	 * 
	 * @param sphereLocation
	 * @return the corresponding cube location
	 */
	public static Location fromSphereToCube(Location sphereLocation) {
		// TODO: not implemented yet
		return sphereLocation;
	}

}
