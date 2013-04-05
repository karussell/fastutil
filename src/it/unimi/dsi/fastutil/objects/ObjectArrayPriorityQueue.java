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
package it.unimi.dsi.fastutil.objects;
import java.util.Comparator;
import it.unimi.dsi.fastutil.AbstractPriorityQueue;
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
public class ObjectArrayPriorityQueue <K> extends AbstractPriorityQueue <K> {
 /** The backing array. */
 @SuppressWarnings("unchecked")
 protected K array[] = (K[]) ObjectArrays.EMPTY_ARRAY;
 /** The number of elements in this queue. */
 protected int size;
 /** The type-specific comparator used in this queue. */
 protected Comparator <? super K> c;
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
 public ObjectArrayPriorityQueue( int capacity, Comparator <? super K> c ) {
  if ( capacity > 0 ) this.array = (K[]) new Object[ capacity ];
  this.c = c;
 }
 /** Creates a new empty queue with a given capacity and using the natural order.
	 *
	 * @param capacity the initial capacity of this queue.
	 */
 public ObjectArrayPriorityQueue( int capacity ) {
  this( capacity, null );
 }
 /** Creates a new empty queue with a given comparator.
	 *
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public ObjectArrayPriorityQueue( Comparator <? super K> c ) {
  this( 0, c );
 }
 /** Creates a new empty queue using the natural order. 
	 */
 public ObjectArrayPriorityQueue() {
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
 public ObjectArrayPriorityQueue( final K[] a, int size, final Comparator <? super K> c ) {
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
 public ObjectArrayPriorityQueue( final K[] a, final Comparator <? super K> c ) {
  this( a, a.length, c );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 */
 public ObjectArrayPriorityQueue( final K[] a, int size ) {
  this( a, size, null );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 *
	 * @param a an array.
	 */
 public ObjectArrayPriorityQueue( final K[] a ) {
  this( a, a.length );
 }

 /** Returns the index of the smallest element. */

 @SuppressWarnings("unchecked")
 private int findFirst() {
  if ( firstIndexValid ) return this.firstIndex;
  firstIndexValid = true;
  int i = size;
  int firstIndex = --i;
  K first = array[ firstIndex ];

  if ( c == null ) { while( i-- != 0 ) if ( ( ((Comparable<K>)(array[ i ])).compareTo(first) < 0 ) ) first = array[ firstIndex = i ]; }
  else while( i-- != 0 ) { if ( c.compare( array[ i ], first ) < 0 ) first = array[ firstIndex = i ]; }

  return this.firstIndex = firstIndex;
 }

 private void ensureNonEmpty() {
  if ( size == 0 ) throw new NoSuchElementException();
 }

 @SuppressWarnings("unchecked")
 public void enqueue( K x ) {
  if ( size == array.length ) array = ObjectArrays.grow( array, size + 1 );
  if ( firstIndexValid ) {
   if ( c == null ) { if ( ( ((Comparable<K>)(x)).compareTo(array[ firstIndex ]) < 0 ) ) firstIndex = size; }
   else if ( c.compare( x, array[ firstIndex ] ) < 0 ) firstIndex = size;
  }
  else firstIndexValid = false;
  array[ size++ ] = x;
 }

 public K dequeue() {
  ensureNonEmpty();
  final int first = findFirst();
  final K result = array[ first ];
  System.arraycopy( array, first + 1, array, first, --size - first );

  array[ size ] = null;

  firstIndexValid = false;
  return result;
 }

 public K first() {
  ensureNonEmpty();
  return array[ findFirst() ];
 }

 public void changed() {
  ensureNonEmpty();
  firstIndexValid = false;
 }

 public int size() { return size; }

 public void clear() {

  ObjectArrays.fill( array, 0, size, null );

  size = 0;
  firstIndexValid = false;
 }

 /** Trims the underlying array so that it has exactly {@link #size()} elements.
	 */

 public void trim() {
  array = ObjectArrays.trim( array, size );
 }

 public Comparator <? super K> comparator() { return c; }
}
