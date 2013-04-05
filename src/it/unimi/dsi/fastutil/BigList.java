package it.unimi.dsi.fastutil;

/*		 
 * Copyright (C) 2010-2013 Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */


import java.util.Collection;
import java.util.List;

/** A list with big (i.e., 64-bit) indices.
 *
 * <P>Instances of this class implement the same semantics as that of {@link List}: however,
 * setters methods use long indices, getters return long values, and returned iterators are actually
 * of type {@link BigListIterator}.
 */

public interface BigList<K> extends Collection<K>, Size64 {

	/** Returns the element at the specified position.
	 * 
	 * @param index a position in the big list.
	 * @return the element at the specified position.
	 * @see List#get(int)
	 */
	public K get( long index );

	/** Removes the element at the specified position.
	 * 
	 * @param index a position in the big list.
	 * @return the element previously at the specified position.
	 * @see List#remove(int)
	 */
	public K remove( long index );

	/** Replaces the element at the specified position in this big list with the specified element (optional operation). 
	 * 
	 * @param index a position in the big list.
	 * @param element the element to be stored at the specified position.
	 * @return the element previously at the specified positions.
	 * @see List#set(int,Object)
	 */
	public K set( long index, K element );

	/** Inserts the specified element at the specified position in this big list (optional operation).
	 * 
	 * @param index a position in the big list.
	 * @param element an element to be inserted.
	 * @see List#add(int,Object)
	 */
	public void add( long index, K element );
	
	/** Sets the size of this big list.
	 *
	 * <P>If the specified size is smaller than the current size, the last elements are
	 * discarded. Otherwise, they are filled with 0/<code>null</code>/<code>false</code>.
	 *
	 * @param size the new size.
	 */

	void size( long size );

	/** Inserts all of the elements in the specified collection into this big list at the specified position (optional operation).
	 * 
	 * @param index index at which to insert the first element from the specified collection.
	 * @param c collection containing elements to be added to this big list.
	 * @return <code>true</code> if this big list changed as a result of the call
	 * @see List#addAll(int, Collection) 
	 */	
	public boolean addAll( long index, Collection<? extends K> c );

	/** Returns the index of the first occurrence of the specified element in this big list, or -1 if this big list does not contain the element.
	 * 
	 * @param o the object to search for.
	 * @return the index of the first occurrence of the specified element in this big list, or -1 if this big list does not contain the element.
	 * @see List#indexOf(Object)
	 */
	public long indexOf( Object o );
	
	/** Returns the index of the last occurrence of the specified element in this big list, or -1 if this big list does not contain the element. 
	 * 
	 * @param o the object to search for.
	 * @return the index of the last occurrence of the specified element in this big list, or -1 if this big list does not contain the element.
 	 * @see List#lastIndexOf(Object)
	 */
	public long lastIndexOf( Object o );
	
	/** Returns a big-list iterator over the elements in this big list.
	 * 
	 * @return a big-list iterator over the elements in this big list.
	 * @see List#listIterator()
	 */

	public BigListIterator<K> listIterator();

	/** Returns a big-list iterator of the elements in this big list, starting at the specified position in this big list.
	 * 
	 * @param index index of first element to be returned from the big-list iterator.
	 * @return a big-list iterator of the elements in this big list, starting at the specified position in
	 * this big list.
	 * @see List#listIterator(int)
	 */
	public BigListIterator<K> listIterator( long index );

	/** Returns a big sublist view of this big list.
	 * 
	 * @param from the starting element (inclusive).
	 * @param to the ending element (exclusive).
	 * @return a big sublist view of this big list.
	 * @see List#subList(int, int)
	 */
	public BigList<K> subList( long from, long to );
}
