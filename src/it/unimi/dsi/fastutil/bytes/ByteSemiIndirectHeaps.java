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
package it.unimi.dsi.fastutil.bytes;
import it.unimi.dsi.fastutil.ints.IntArrays;
/** A class providing static methods and objects that do useful things with semi-indirect heaps.
 *
 * <P>A semi-indirect heap is based on a <em>reference array</em>. Elements of
 * a semi-indirect heap are integers that index the reference array (note that
 * in an <em>indirect</em> heap you can also map elements of the reference
 * array to heap positions).  
 */
public class ByteSemiIndirectHeaps {
 private ByteSemiIndirectHeaps() {}
 /** Moves the given element down into the semi-indirect heap until it reaches the lowest possible position.
	 *
	 * @param refArray the reference array.
	 * @param heap the semi-indirect heap (starting at 0).
	 * @param size the number of elements in the heap.
	 * @param i the index in the heap of the element to be moved down.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position in the heap of the element of heap index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int downHeap( final byte[] refArray, final int[] heap, final int size, int i, final ByteComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final int e = heap[ i ];
  final byte E = refArray[ e ];
  int child;
  if ( c == null )
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && ( (refArray[ heap[ child + 1 ] ]) < (refArray[ heap[ child ] ]) ) ) child++;
    if ( ( (E) <= (refArray[ heap[ child ] ]) ) ) break;
    heap[ i ] = heap[ child ];
    i = child;
   }
  else
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && c.compare( refArray[ heap[ child + 1 ] ], refArray[ heap[ child ] ] ) < 0 ) child++;
    if ( c.compare( E, refArray[ heap[ child ] ] ) <= 0 ) break;
    heap[ i ] = heap[ child ];
    i = child;
   }
  heap[ i ] = e;
  return i;
 }
 /** Moves the given element up in the semi-indirect heap until it reaches the highest possible position.
	 *
	 * @param refArray the reference array.
	 * @param heap the semi-indirect heap (starting at 0).
	 * @param size the number of elements in the heap.
	 * @param i the index in the heap of the element to be moved up.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position in the heap of the element of heap index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int upHeap( final byte[] refArray, final int[] heap, final int size, int i, final ByteComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final int e = heap[ i ];
  int parent;
  final byte E = refArray[ e ];
  if ( c == null )
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( ( (refArray[ heap[ parent ] ]) <= (E) ) ) break;
    heap[ i ] = heap[ parent ];
    i = parent;
   }
  else
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( c.compare( refArray[ heap[ parent ] ], E ) <= 0 ) break;
    heap[ i ] = heap[ parent ];
    i = parent;
   }
  heap[ i ] = e;
  return i;
 }
 /** Creates a semi-indirect heap in the given array.
	 *
	 * @param refArray the reference array.
	 * @param offset the first element of the reference array to be put in the heap.
	 * @param length the number of elements to be put in the heap.
	 * @param heap the array where the heap is to be created.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 */
 public static void makeHeap( final byte[] refArray, final int offset, final int length, final int[] heap, final ByteComparator c ) {
  ByteArrays.ensureOffsetLength( refArray, offset, length );
  if ( heap.length < length ) throw new IllegalArgumentException( "The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")" );
  int i = length;
  while( i-- != 0 ) heap[ i ] = offset + i;
  i = length / 2;
  while( i-- != 0 ) downHeap( refArray, heap, length, i, c );
 }

 /** Creates a semi-indirect heap, allocating its heap array.
	 *
	 * @param refArray the reference array.
	 * @param offset the first element of the reference array to be put in the heap.
	 * @param length the number of elements to be put in the heap.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the heap array.
	 */

 public static int[] makeHeap( final byte[] refArray, final int offset, final int length, final ByteComparator c ) {
  int[] heap = length <= 0 ? IntArrays.EMPTY_ARRAY : new int[ length ];
  makeHeap( refArray, offset, length, heap, c );
  return heap;
 }



 /** Creates a semi-indirect heap from a given index array.
	 *
	 * @param refArray the reference array.
	 * @param heap an array containing indices into <code>refArray</code>.
	 * @param size the number of elements in the heap.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 */

 public static void makeHeap( final byte[] refArray, final int[] heap, final int size, final ByteComparator c ) {
  int i = size / 2;
  while( i-- != 0 ) downHeap( refArray, heap, size, i, c );
 }

 /** Retrieves the front of a heap in a given array.
	 *
	 * <p>The <em>front</em> of a semi-indirect heap is the set of indices whose associated elements in the reference array 
	 * are equal to the element associated to the first index.
	 *
	 * <p>In several circumstances you need to know the front, and scanning linearly the entire heap is not
	 * the best strategy. This method simulates (using a partial linear scan) a breadth-first visit that 
	 * terminates when all visited nodes are larger than the element associated
	 * to the top index, which implies that no elements of the front can be found later. 
	 * In most cases this trick yields a significant improvement.
	 * 
	 * @param refArray the reference array.
	 * @param heap an array containing indices into <code>refArray</code>.
	 * @param size the number of elements in the heap.
	 * @param a an array large enough to hold the front (e.g., at least long as <code>refArray</code>).
	 * @return the number of elements actually written (starting from the first position of <code>a</code>).
	 */
 @SuppressWarnings("unchecked")
 public static int front( final byte[] refArray, final int[] heap, final int size, final int[] a ) {
  final byte top = refArray[ heap[ 0 ] ];
  int j = 0, // The current position in a
   l = 0, // The first position to visit in the next level (inclusive)
   r = 1, // The last position to visit in the next level (exclusive)
   f = 0; // The first position (in the heap array) of the next level
  for( int i = 0; i < r; i++ ) {
   if ( i == f ) { // New level
    if ( l >= r ) break; // If we are crossing the two bounds, we're over
    f = (f << 1) + 1; // Update the first position of the next level...
    i = l; // ...and jump directly to position l
    l = -1; // Invalidate l
   }
   if ( ( (top) == (refArray[ heap[ i ] ]) ) ) {
    a[ j++ ] = heap[ i ];
    if ( l == -1 ) l = i * 2 + 1; // If this is the first time in this level, set l
    r = Math.min( size, i * 2 + 3 ); // Update r, but do not go beyond size
   }
  }

  return j;
 }

 /** Retrieves the front of a heap in a given array using a given comparator.
	 *
	 * <p>The <em>front</em> of a semi-indirect heap is the set of indices whose associated elements in the reference array 
	 * are equal to the element associated to the first index.
	 *
	 * <p>In several circumstances you need to know the front, and scanning linearly the entire heap is not
	 * the best strategy. This method simulates (using a partial linear scan) a breadth-first visit that 
	 * terminates when all visited nodes are larger than the element associated
	 * to the top index, which implies that no elements of the front can be found later. 
	 * In most cases this trick yields a significant improvement.
	 * 
	 * @param refArray the reference array.
	 * @param heap an array containing indices into <code>refArray</code>.
	 * @param size the number of elements in the heap.
	 * @param a an array large enough to hold the front (e.g., at least long as <code>refArray</code>).
	 * @param c a type-specific comparator.
	 * @return the number of elements actually written (starting from the first position of <code>a</code>).
	 */
 public static int front( final byte[] refArray, final int[] heap, final int size, final int[] a, final ByteComparator c ) {
  final byte top = refArray[ heap[ 0 ] ];
  int j = 0, // The current position in a
   l = 0, // The first position to visit in the next level (inclusive)
   r = 1, // The last position to visit in the next level (exclusive)
   f = 0; // The first position (in the heap array) of the next level
  for( int i = 0; i < r; i++ ) {
   if ( i == f ) { // New level
    if ( l >= r ) break; // If we are crossing the two bounds, we're over
    f = (f << 1) + 1; // Update the first position of the next level...
    i = l; // ...and jump directly to position l
    l = -1; // Invalidate l
   }
   if ( c.compare( top, refArray[ heap[ i ] ] ) == 0 ) {
    a[ j++ ] = heap[ i ];
    if ( l == -1 ) l = i * 2 + 1; // If this is the first time in this level, set l
    r = Math.min( size, i * 2 + 3 ); // Update r, but do not go beyond size
   }
  }

  return j;
 }
}
