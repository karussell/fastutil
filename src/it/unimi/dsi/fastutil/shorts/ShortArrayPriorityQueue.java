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
 * Copyright (C) 2003-2013 Paolo Boldi and Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.shorts;
import java.util.NoSuchElementException;
/** A type-specific array-based priority queue.
 *
 * <P>Instances of this class represent a priority queue using a backing
 * array&mdash;all operations are performed directly on the array. The array is
 * enlarged as needed, but it is never shrunk. Use the {@link #trim()} method
 * to reduce its size, if necessary.
 *
 * <P>This implementation is extremely inefficient, but it is difficult to beat
 * when the size of the queue is very small.
 */
public class ShortArrayPriorityQueue extends AbstractShortPriorityQueue {
 /** The backing array. */
 @SuppressWarnings("unchecked")
 protected short array[] = ShortArrays.EMPTY_ARRAY;
 /** The number of elements in this queue. */
 protected int size;
 /** The type-specific comparator used in this queue. */
 protected ShortComparator c;
 /** The first index, cached, if {@link #firstIndexValid} is true. */
 protected int firstIndex;
 /** Whether {@link #firstIndex} contains a valid value. */
 protected boolean firstIndexValid;
 /** Creates a new empty queue with a given capacity and comparator.
	 *
	 * @param capacity the initial capacity of this queue.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 @SuppressWarnings("unchecked")
 public ShortArrayPriorityQueue( int capacity, ShortComparator c ) {
  if ( capacity > 0 ) this.array = new short[ capacity ];
  this.c = c;
 }
 /** Creates a new empty queue with a given capacity and using the natural order.
	 *
	 * @param capacity the initial capacity of this queue.
	 */
 public ShortArrayPriorityQueue( int capacity ) {
  this( capacity, null );
 }
 /** Creates a new empty queue with a given comparator.
	 *
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public ShortArrayPriorityQueue( ShortComparator c ) {
  this( 0, c );
 }
 /** Creates a new empty queue using the natural order. 
	 */
 public ShortArrayPriorityQueue() {
  this( 0, null );
 }
 /** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public ShortArrayPriorityQueue( final short[] a, int size, final ShortComparator c ) {
  this( c );
  this.array = a;
  this.size = size;
 }
 /** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public ShortArrayPriorityQueue( final short[] a, final ShortComparator c ) {
  this( a, a.length, c );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 */
 public ShortArrayPriorityQueue( final short[] a, int size ) {
  this( a, size, null );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 */
 public ShortArrayPriorityQueue( final short[] a ) {
  this( a, a.length );
 }


 /** Returns the index of the smallest element. */

 @SuppressWarnings("unchecked")
 private int findFirst() {
  if ( firstIndexValid ) return this.firstIndex;
  firstIndexValid = true;
  int i = size;
  int firstIndex = --i;
  short first = array[ firstIndex ];

  if ( c == null ) { while( i-- != 0 ) if ( ( (array[ i ]) < (first) ) ) first = array[ firstIndex = i ]; }
  else while( i-- != 0 ) { if ( c.compare( array[ i ], first ) < 0 ) first = array[ firstIndex = i ]; }

  return this.firstIndex = firstIndex;
 }

 private void ensureNonEmpty() {
  if ( size == 0 ) throw new NoSuchElementException();
 }

 @SuppressWarnings("unchecked")
 public void enqueue( short x ) {
  if ( size == array.length ) array = ShortArrays.grow( array, size + 1 );
  if ( firstIndexValid ) {
   if ( c == null ) { if ( ( (x) < (array[ firstIndex ]) ) ) firstIndex = size; }
   else if ( c.compare( x, array[ firstIndex ] ) < 0 ) firstIndex = size;
  }
  else firstIndexValid = false;
  array[ size++ ] = x;
 }

 public short dequeueShort() {
  ensureNonEmpty();
  final int first = findFirst();
  final short result = array[ first ];
  System.arraycopy( array, first + 1, array, first, --size - first );



  firstIndexValid = false;
  return result;
 }

 public short firstShort() {
  ensureNonEmpty();
  return array[ findFirst() ];
 }

 public void changed() {
  ensureNonEmpty();
  firstIndexValid = false;
 }

 public int size() { return size; }

 public void clear() {



  size = 0;
  firstIndexValid = false;
 }

 /** Trims the underlying array so that it has exactly {@link #size()} elements.
	 */

 public void trim() {
  array = ShortArrays.trim( array, size );
 }

 public ShortComparator comparator() { return c; }
}
