/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.view;

import java.awt.Color;
import java.awt.Graphics;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.stellar.Star;

/**
 * The <code>AWTStarView</code> represents <code>Star</code> view for Abstract
 * Window Toolkit visualization.
 * 
 * @author carmel
 * 
 */
public class AWTStarView extends AWTAbstractView<Star> {

	private static final int[] RADIUS_BY_CLASS = new int[] { 50, // for class O
			30, // for class B
			20, // for class A
			10, // for class F
			5, // for class G
			2, // for class K
			1, // for class M
	};

	private static final Color[] COLOR_BY_CLASS = new Color[] { Color.BLUE, // for
																			// class
																			// O
			Color.CYAN, // for class B
			Color.LIGHT_GRAY, // for class A
			Color.GRAY, // for class F
			Color.YELLOW, // for class G
			Color.ORANGE, // for class K
			Color.RED, // for class M
	};

	/**
	 * Creates the new <code>AWTStarView</code> instance.
	 */
	protected AWTStarView() {
		super();
	}

	/**
	 * Creates the new <code>AWTStarView</code> instance.
	 * 
	 * @param graphics
	 */
	protected AWTStarView(Graphics graphics) {
		super(graphics);
	}

	@Override
	public void render(Star star) {
		Location display = LocationTransform.toDisplayLocation(star.locate());

		graphics.setColor(COLOR_BY_CLASS[star.getStarClass().ordinal()]);
		graphics.drawOval((int) display.x(), (int) display.y(),
				RADIUS_BY_CLASS[star.getStarClass().ordinal()],
				RADIUS_BY_CLASS[star.getStarClass().ordinal()]);
	}

}
