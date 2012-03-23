/**
 * Copyright 2012 cohesiva.com
 */
package com.cohesiva.drifter.view;

import com.cohesiva.drifter.stellar.IStellar;

/**
 * The <code>IViewFactory</code> defines an interface for the abstract factory
 * producing views for visualization of <code>IComplex</code> implementations.
 * 
 * @author carmel
 * 
 */
public interface IViewFactory {

	/**
	 * Produces the view implementation for a given <code>IStellar</code>.
	 * 
	 * @param stellar
	 *            the stellar entity for which the view is requested
	 * @return the view of a given stellar
	 */
	public IView<IStellar> produceView(IStellar stellar);

}
