/**
 * Copyright 2010 cohesiva.com
 */
package com.cohesiva.drifter.stellar;

import java.util.List;

import com.cohesiva.drifter.datastruct.IComplex;

/**
 * The <code>ISpace</code> defines the interface for the target universe or its
 * part. The <code>ISpace</code> consists of <code>IStellar</code> entities. The
 * <code>ISpace</code> is surrounded with the <code>IBoundingBox</code> that
 * assings its bounds.
 * 
 * @author bkarmelita
 * 
 */
public interface ISpace extends IComplex {

	/**
	 * Gets the <code>IStellar</code> entities inside this <code>ISpace</code>.
	 * 
	 * @return this <code>ISpace</code> <code>IStellar</code> entities
	 */
	public List<IStellar> stellars();

	/**
	 * Gets the bounds of this <code>ISpace</code>.
	 * 
	 * @return this <code>ISpace</code> bounds.
	 */
	public IBoundingBox bounds();

	/**
	 * Remove all stellars from this space.
	 */
	public void empty();

	/**
	 * Adds an additional stellar to this space.
	 * 
	 * @param stellar
	 *            stellar to add
	 */
	public void addStellar(IStellar stellar);

}
