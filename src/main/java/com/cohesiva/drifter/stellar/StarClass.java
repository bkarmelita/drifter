/**
 * Copyright 2011 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

/**
 * The <code>StarClass</code> represents the star class enumeration in terms of
 * Harvard spectral classification:
 * http://en.wikipedia.org/wiki/Stellar_classification
 * 
 * @author bkarmelita
 * 
 */
public enum StarClass {

	/**
	 * The <code>O</code> stands for an O class star. They are blue. These are
	 * the most massive and the hottest stars known.
	 * 
	 * There are at most 120 of them.
	 */
	O(150 * IStellarConstants.SUN_MASS, 650 * IStellarConstants.SUN_DIAMETER,
			StarColor.BLUE, 0.00003/100),

	/**
	 * The <code>B</code> stands for a B class star. They are blue white. These
	 * are ... TODO:
	 * 
	 * There are ~ 520.000 of them.
	 */
	B(16 * IStellarConstants.SUN_MASS, 6.6 * IStellarConstants.SUN_DIAMETER,
			StarColor.BLUE_WHITE, 0.13/100),

	/**
	 * The <code>A</code> stands for an A class star. They are white. These are
	 * ... TODO:
	 * 
	 * There are ~ 2.400.000 of them.
	 */
	A(2.1 * IStellarConstants.SUN_MASS, 1.8 * IStellarConstants.SUN_DIAMETER,
			StarColor.WHITE, 0.6/100),

	/**
	 * The <code>F</code> stands for an F class star. They are yellow white.
	 * These are ... TODO:
	 */
	F(1.4 * IStellarConstants.SUN_MASS, 1.4 * IStellarConstants.SUN_DIAMETER,
			StarColor.YELLOW_WHITE, 3.0/100),

	/**
	 * The <code>G</code> stands for a G class star. They are yellow. These are
	 * ... TODO:
	 */
	G(1.04 * IStellarConstants.SUN_MASS, 1.15 * IStellarConstants.SUN_DIAMETER,
			StarColor.YELLOW, 7.6/100),

	/**
	 * The <code>K</code> stands for a K class star. They are orange. These are
	 * ... TODO:
	 */
	K(0.8 * IStellarConstants.SUN_MASS, 0.96 * IStellarConstants.SUN_DIAMETER,
			StarColor.ORANGE, 12.1/100),

	/**
	 * The <code>M</code> stands for an M class star. They are red dwarves.
	 * These are very old, dying stars ... TODO:
	 */
	M(0.45 * IStellarConstants.SUN_MASS, 0.7 * IStellarConstants.SUN_DIAMETER,
			StarColor.RED, 76.45/100);

	/**
	 * The <code>maximumMass</code> stands for a maximum star class mass.
	 * 
	 */
	private double maximumMass;

	/**
	 * The <code>maximumDiameter</code> stands for a maximum star class
	 * diameter.
	 */
	private double maximumDiameter;

	/**
	 * The <code>color</code> stands for a star class color.
	 */
	private StarColor color;
	
	/**
	 * The <code>fraction</code> stands for a percent of all star population.
	 */
	private double fraction;

	/**
	 * Creates the new <code>StarClass</code> instance.
	 * 
	 * @param maximumMass
	 * @param maximumRadius
	 */
	private StarClass(double maximumMass, double maximumDiameter,
			StarColor color, double fraction) {
		this.maximumMass = maximumMass;
		this.maximumDiameter = maximumDiameter;
		this.color = color;
		this.fraction = fraction;
	}

	/**
	 * Gets the current value of <code>maximumMass</code>.
	 * 
	 * @return the maximumMass
	 */
	public double getMaximumMass() {
		return maximumMass;
	}

	/**
	 * Gets the current value of <code>maximumRadius</code>.
	 * 
	 * @return the maximumRadius
	 */
	public double getMaximumDiameter() {
		return maximumDiameter;
	}

	/**
	 * Gets the current value of <code>color</code>.
	 * 
	 * @return the color
	 */
	public StarColor getColor() {
		return color;
	}

	/**
	 * Gets the current value of <code>fraction</code>.
	 * 
	 * @return the fraction
	 */
	public double getFraction() {
		return fraction;
	}

}
