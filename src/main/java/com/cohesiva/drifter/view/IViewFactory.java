/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view;


/**
 * The <code>IViewFactory</code> defines an interface for the abstract factory
 * producing views for visualization of <code>IVisible</code> implementations.
 * 
 * @author carmel
 * 
 */
public interface IViewFactory {

	/**
	 * Produces the view implementation for a given <code>IVisible</code>.
	 * 
	 * @param visible
	 *            the visible for which the view is requested
	 * @return the view of a given visible
	 */
	public IView<IVisible> produceView(IVisible visible);

}
