/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.test.interactive.view;

import java.awt.Graphics;

import com.cohesiva.drifter.common.Location;
import com.cohesiva.drifter.split.containers.BoundingSquare;

/**
 * The <code>AWTBoundingSquareView</code> represents
 * <code>IBoundingSquare</code> view for Abstract Window Toolkit visualization.
 * 
 * @author carmel
 * 
 */
public class AWTBoundingSquareView extends AWTAbstractView<BoundingSquare> {

	/**
	 * Creates the new <code>AWTBoundingSquareView</code> instance.
	 */
	protected AWTBoundingSquareView() {
		super();
	}

	/**
	 * Creates the new <code>AWTBoundingSquareView</code> instance.
	 * 
	 * @param graphics
	 */
	protected AWTBoundingSquareView(Graphics graphics) {
		super(graphics);
	}

	@Override
	public void render(BoundingSquare complex) {
		Location topLeft = computeTopLeft(complex);
		Location display = LocationTransform.toDisplayLocation(topLeft);
		int width = (int) (complex.radius() * 2);

		graphics.setColor(AWTAbstractView.DEFAULT_FILL);
		graphics.fillRect((int) display.x(), (int) display.y(), width, width);
		graphics.setColor(AWTAbstractView.DEFAULT_PAINT);
		graphics.drawRect((int) display.x(), (int) display.y(), width, width);
	}

	/**
	 * Computes the top left square vertex location based on its center.
	 * 
	 * @param complex
	 *            the target bounding square
	 * @return location of the top left vertex of the bounding square
	 */
	private Location computeTopLeft(BoundingSquare complex) {
		Location center = complex.center();

		int x = (int) center.x() - (int) (complex.radius());
		int y = (int) center.y() + (int) (complex.radius());

		return new Location(x, y, 0, complex.center().getUnit());
	}

}
