/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view.awt;

import java.awt.Graphics;

import com.cohesiva.drifter.stellar.Star;

/**
 * The <code>AWTStarView</code> represents <code>Star</code> view for Abstract
 * Window Toolkit visualization
 * 
 * @author carmel
 * 
 */
public class AWTStarView extends AWTAbstractView<Star> {

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
		// TODO Auto-generated method stub
	}

}
