/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view;

import com.cohesiva.drifter.common.IVisible;

/**
 * The <code>IView</code> defines an interface for visualizing. Every
 * <code>IVisible</code> can have a visual representation and <code>IView</code>
 * responsibility is to render it. There can be more than one <code>IView</code>
 * implementation for the given <code>IVisible</code>.
 * 
 * @author carmel
 * 
 */
public interface IView<T extends IVisible> {

	/**
	 * Renders the given complex.
	 * 
	 * @param visible
	 *            visible to render
	 */
	public void render(T visible);

}
