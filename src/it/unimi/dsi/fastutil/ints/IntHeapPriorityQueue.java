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
package it.unimi.dsi.fastutil.ints;
import java.util.NoSuchElementException;
/** A type-specific heap-based priority queue.
 *
 * <P>Instances of this class represent a priority queue using a heap. The heap is enlarged as needed, but
 * it is never shrunk. Use the {@link #trim()} method to reduce its size, if necessary.
 */
public class IntHeapPriorityQueue extends AbstractIntPriorityQueue {
 /** The heap array. */
 @SuppressWarnings("unchecked")
 protected int[] heap = IntArrays.EMPTY_ARRAY;
 /** The number of elements in this queue. */
 protected int size;
 /** The type-specific comparator used in this queue. */
 protected IntComparator c;
 /** Creates a new empty queue with a given capacity and comparator.
	 *
	 * @param capacity the initial capacity of this queue.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 @SuppressWarnings("unchecked")
 public IntHeapPriorityQueue( int capacity, IntComparator c ) {
  if ( capacity > 0 ) this.heap = new int[ capacity ];
  this.c = c;
 }
 /** Creates a new empty queue with a given capacity and using the natural order.
	 *
	 * @param capacity the initial capacity of this queue.
	 */
 public IntHeapPriorityQueue( int capacity ) {
  this( capacity, null );
 }
 /** Creates a new empty queue with a given comparator.
	 *
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public IntHeapPriorityQueue( IntComparator c ) {
  this( 0, c );
 }
 /** Creates a new empty queue using the natural order. 
	 */
 public IntHeapPriorityQueue() {
  this( 0, null );
 }
 /** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 * The first <code>size</code> element of the array will be rearranged so to form a heap (this is
	 * more efficient than enqueing the elements of <code>a</code> one by one).
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public IntHeapPriorityQueue( final int[] a, int size, final IntComparator c ) {
  this( c );
  this.heap = a;
  this.size = size;
  IntHeaps.makeHeap( a, size, c );
 }
 /** Wraps a given array in a queue using a given comparator.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 * The elements of the array will be rearranged so to form a heap (this is
	 * more efficient than enqueing the elements of <code>a</code> one by one).
	 *
	 * @param a an array.
	 * @param c the comparator used in this queue, or <code>null</code> for the natural order.
	 */
 public IntHeapPriorityQueue( final int[] a, final IntComparator c ) {
  this( a, a.length, c );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 * The first <code>size</code> element of the array will be rearranged so to form a heap (this is
	 * more efficient than enqueing the elements of <code>a</code> one by one).
	 *
	 * @param a an array.
	 * @param size the number of elements to be included in the queue.
	 */
 public IntHeapPriorityQueue( final int[] a, int size ) {
  this( a, size, null );
 }
 /** Wraps a given array in a queue using the natural order.
	 *
	 * <P>The queue returned by this method will be backed by the given array.
	 * The elements of the array will be rearranged so to form a heap (this is
	 * more efficient than enqueing the elements of <code>a</code> one by one).
	 *
	 * @param a an array.
	 */
 public IntHeapPriorityQueue( final int[] a ) {
  this( a, a.length );
 }
 @SuppressWarnings("unchecked")
 public void enqueue( int x ) {
  if ( size == heap.length ) heap = IntArrays.grow( heap, size + 1 );

  heap[ size++ ] = x;
  IntHeaps.upHeap( heap, size, size - 1, c );
 }

 public int dequeueInt() {
  if ( size == 0 ) throw new NoSuchElementException();

  final int result = heap[ 0 ];
  heap[ 0 ] = heap[ --size ];



  if ( size != 0 ) IntHeaps.downHeap( heap, size, 0, c );
  return result;
 }

 public int firstInt() {
  if ( size == 0 ) throw new NoSuchElementException();
  return heap[ 0 ];
 }

 public void changed() {
  IntHeaps.downHeap( heap, size, 0, c );
 }

 public int size() { return size; }

 public void clear() {



  size = 0;
 }

 /** Trims the underlying heap array so that it has exactly {@link #size()} elements.
	 */

 public void trim() {
  heap = IntArrays.trim( heap, size );
 }

 public IntComparator comparator() { return c; }
}
