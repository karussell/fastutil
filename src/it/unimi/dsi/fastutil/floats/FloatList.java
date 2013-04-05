/* Generic definitions */




/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/*		 
 * Copyright (C) 2002-2013 Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.floats;
import java.util.List;
/** A type-specific {@link List}; provides some additional methods that use polymorphism to avoid (un)boxing. 
 *
 * <P>Note that this type-specific interface extends {@link Comparable}: it is expected that implementing
 * classes perform a lexicographical comparison using the standard operator "less then" for primitive types,
 * and the usual {@link Comparable#compareTo(Object) compareTo()} method for objects.
 *
 * <P>Additionally, this interface strengthens {@link #listIterator()},
 * {@link #listIterator(int)} and {@link #subList(int,int)}.
 *
 * <P>Besides polymorphic methods, this interfaces specifies methods to copy into an array or remove contiguous
 * sublists. Although the abstract implementation of this interface provides simple, one-by-one implementations
 * of these methods, it is expected that concrete implementation override them with optimized versions.
 *
 * @see List
 */
public interface FloatList extends List<Float>, Comparable<List<? extends Float>>, FloatCollection {
 /** Returns a type-specific iterator on the elements of this list (in proper sequence).
	 *
	 * Note that this specification strengthens the one given in {@link List#iterator()}.
	 * It would not be normally necessary, but {@link java.lang.Iterable#iterator()} is bizarrily re-specified
	 * in {@link List}.
	 *
	 * @return an iterator on the elements of this list (in proper sequence).
	 */
 FloatListIterator iterator();
 /** Returns a type-specific list iterator on the list.
	 *
	 * @see #listIterator()
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator()}.
	 */
 @Deprecated
 FloatListIterator floatListIterator();

 /** Returns a type-specific list iterator on the list starting at a given index.
	 *
	 * @see #listIterator(int)
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #listIterator(int)}.
	 */
 @Deprecated
 FloatListIterator floatListIterator( int index );

 /** Returns a type-specific list iterator on the list.
	 *
	 * @see List#listIterator()
	 */
 FloatListIterator listIterator();

 /** Returns a type-specific list iterator on the list starting at a given index.
	 *
	 * @see List#listIterator(int)
	 */
 FloatListIterator listIterator( int index );

 /** Returns a type-specific view of the portion of this list from the index <code>from</code>, inclusive, to the index <code>to</code>, exclusive.
	 * @see List#subList(int,int)
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #subList(int,int)}.
	 */
 @Deprecated
 FloatList floatSubList( int from, int to );

 /** Returns a type-specific view of the portion of this list from the index <code>from</code>, inclusive, to the index <code>to</code>, exclusive.
	 *
	 * <P>Note that this specification strengthens the one given in {@link List#subList(int,int)}.
	 *
	 * @see List#subList(int,int)
	 */
 FloatList subList(int from, int to);


 /** Sets the size of this list.
	 *
	 * <P>If the specified size is smaller than the current size, the last elements are
	 * discarded. Otherwise, they are filled with 0/<code>null</code>/<code>false</code>.
	 *
	 * @param size the new size.
	 */

 void size( int size );

 /** Copies (hopefully quickly) elements of this type-specific list into the given array.
	 *
	 * @param from the start index (inclusive).
	 * @param a the destination array.
	 * @param offset the offset into the destination array where to store the first element copied.
	 * @param length the number of elements to be copied.
	 */
 void getElements( int from, float a[], int offset, int length );

 /** Removes (hopefully quickly) elements of this type-specific list.
	 *
	 * @param from the start index (inclusive).
	 * @param to the end index (exclusive).
	 */
 void removeElements( int from, int to );

 /** Add (hopefully quickly) elements to this type-specific list.
	 *
	 * @param index the index at which to add elements.
	 * @param a the array containing the elements.
	 */
 void addElements( int index, float a[] );

 /** Add (hopefully quickly) elements to this type-specific list.
	 *
	 * @param index the index at which to add elements.
	 * @param a the array containing the elements.
	 * @param offset the offset of the first element to add.
	 * @param length the number of elements to add.
	 */
 void addElements( int index, float a[], int offset, int length );



 /**
	 * @see List#add(Object)
	 */
 boolean add( float key );

 /**
	 * @see List#add(int,Object)
	 */
 void add( int index, float key );

 /**
	 * @see List#add(int,Object)
	 */
 boolean addAll( int index, FloatCollection c );

 /**
	 * @see List#add(int,Object)
	 */
 boolean addAll( int index, FloatList c );

 /**
	 * @see List#add(int,Object)
	 */
 boolean addAll( FloatList c );

 /**
	 * @see List#get(int)
	 */
 float getFloat( int index );

 /**
	 * @see List#indexOf(Object)
	 */
 int indexOf( float k );

 /**
	 * @see List#lastIndexOf(Object)
	 */
 int lastIndexOf( float k );

 /**
	 * @see List#remove(int)
	 */
 float removeFloat( int index );

 /**
	 * @see List#set(int,Object)
	 */
 float set( int index, float k );




}
