/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view;

import com.cohesiva.drifter.stellar.IStellar;

/**
 * The <code>IView</code> defines an interface for <code>IStellar</code> view.
 * Every <code>IStellar</code> can have a visual representation and
 * <code>IView</code> responsibility is to display it. There can be more than
 * one <code>IView</code> implementation for the given <code>IStellar</code>.
 * 
 * @author carmel
 * 
 */
public interface IView<T extends IStellar> {

	/**
	 * Renders the given complex.
	 * 
	 * @param complex
	 *            complex to render
	 */
	public void render(T complex);

}
