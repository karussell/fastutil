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
package it.unimi.dsi.fastutil.bytes;
import java.util.List;
import it.unimi.dsi.fastutil.BigList;
/** A type-specific {@link BigList}; provides some additional methods that use polymorphism to avoid (un)boxing. 
 *
 * <P>Additionally, this interface strengthens {@link #iterator()}, {@link #listIterator()},
 * {@link #listIterator(long)} and {@link #subList(long,long)}.
 *
 * <P>Besides polymorphic methods, this interfaces specifies methods to copy into an array or remove contiguous
 * sublists. Although the abstract implementation of this interface provides simple, one-by-one implementations
 * of these methods, it is expected that concrete implementation override them with optimized versions.
 *
 * @see List
 */
public interface ByteBigList extends BigList<Byte>, ByteCollection , Comparable<BigList<? extends Byte>> {
 /** Returns a type-specific big-list iterator on this type-specific big list.
	 *
	 * @see List#iterator()
	 */
 ByteBigListIterator iterator();
 /** Returns a type-specific big-list iterator on this type-specific big list.
	 *
	 * @see List#listIterator()
	 */
 ByteBigListIterator listIterator();
 /** Returns a type-specific list iterator on this type-specific big list starting at a given index.
	 *
	 * @see BigList#listIterator(long)
	 */
 ByteBigListIterator listIterator( long index );

 /** Returns a type-specific view of the portion of this type-specific big list from the index <code>from</code>, inclusive, to the index <code>to</code>, exclusive.
	 *
	 * <P>Note that this specification strengthens the one given in {@link BigList#subList(long,long)}.
	 *
	 * @see BigList#subList(long,long)
	 */
 ByteBigList subList( long from, long to );

 /** Copies (hopefully quickly) elements of this type-specific big list into the given big array.
	 *
	 * @param from the start index (inclusive).
	 * @param a the destination big array.
	 * @param offset the offset into the destination big array where to store the first element copied.
	 * @param length the number of elements to be copied.
	 */
 void getElements( long from, byte a[][], long offset, long length );

 /** Removes (hopefully quickly) elements of this type-specific big list.
	 *
	 * @param from the start index (inclusive).
	 * @param to the end index (exclusive).
	 */
 void removeElements( long from, long to );

 /** Add (hopefully quickly) elements to this type-specific big list.
	 *
	 * @param index the index at which to add elements.
	 * @param a the big array containing the elements.
	 */
 void addElements( long index, byte a[][] );

 /** Add (hopefully quickly) elements to this type-specific big list.
	 *
	 * @param index the index at which to add elements.
	 * @param a the big array containing the elements.
	 * @param offset the offset of the first element to add.
	 * @param length the number of elements to add.
	 */
 void addElements( long index, byte a[][], long offset, long length );



 /**
	 * @see List#add(int,Object)
	 */
 void add( long index, byte key );

 /**
	 * @see List#addAll(int,java.util.Collection)
	 */
 boolean addAll( long index, ByteCollection c );

 /**
	 * @see List#addAll(int,java.util.Collection)
	 */
 boolean addAll( long index, ByteBigList c );

 /**
	 * @see List#addAll(int,java.util.Collection)
	 */
 boolean addAll( ByteBigList c );

 /**
	 * @see BigList#get(long)
	 */
 byte getByte( long index );

 /**
	 * @see BigList#indexOf(Object)
	 */
 long indexOf( byte k );

 /**
	 * @see BigList#lastIndexOf(Object)
	 */
 long lastIndexOf( byte k );

 /**
	 * @see BigList#remove(long)
	 */
 byte removeByte( long index );

 /**
	 * @see BigList#set(long,Object)
	 */
 byte set( long index, byte k );



}
