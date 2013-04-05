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
 * Copyright (C) 2009-2013 Sebastiano Vigna 
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
 *
 *
 *
 * Copyright (C) 1999 CERN - European Organization for Nuclear Research.
 *
 *   Permission to use, copy, modify, distribute and sell this software and
 *   its documentation for any purpose is hereby granted without fee,
 *   provided that the above copyright notice appear in all copies and that
 *   both that copyright notice and this permission notice appear in
 *   supporting documentation. CERN makes no representations about the
 *   suitability of this software for any purpose. It is provided "as is"
 *   without expressed or implied warranty. 
 */
package it.unimi.dsi.fastutil.longs;
import java.util.Arrays;
import java.util.Random;
import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.Hash;
import static it.unimi.dsi.fastutil.BigArrays.start;
import static it.unimi.dsi.fastutil.BigArrays.segment;
import static it.unimi.dsi.fastutil.BigArrays.displacement;
import static it.unimi.dsi.fastutil.BigArrays.SEGMENT_MASK;
import static it.unimi.dsi.fastutil.BigArrays.SEGMENT_SIZE;
import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/** A class providing static methods and objects that do useful things with {@linkplain BigArrays big arrays}.
 *
 * <p>In particular, the <code>ensureCapacity()</code>, <code>grow()</code>,
 * <code>trim()</code> and <code>setLength()</code> methods allow to handle
 * big arrays much like array lists.
 *
 * <P>Note that {@link it.unimi.dsi.fastutil.io.BinIO} and {@link it.unimi.dsi.fastutil.io.TextIO}
 * contain several methods that make it possible to load and save big arrays of primitive types as sequences
 * of elements in {@link java.io.DataInput} format (i.e., not as objects) or as sequences of lines of text.
 *
 * @see BigArrays
 */
public class LongBigArrays {
 private LongBigArrays() {}
 /** A static, final, empty big array. */
 public final static long[][] EMPTY_BIG_ARRAY = {};
 /** Returns the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 * @return the element of the big array at the specified position.
	 */
 public static long get( final long[][] array, final long index ) {
  return array[ segment( index ) ][ displacement( index ) ];
 }
 /** Sets the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 */
 public static void set( final long[][] array, final long index, long value ) {
  array[ segment( index ) ][ displacement( index ) ] = value;
 }
 /** Swaps the element of the given big array of specified indices.
	 * 
	 * @param array a big array.
	 * @param first a position in the big array.
	 * @param second a position in the big array.
	 */
 public static void swap( final long[][] array, final long first, final long second ) {
  final long t = array[ segment( first ) ][ displacement( first ) ];
  array[ segment( first ) ][ displacement( first ) ] = array[ segment( second ) ][ displacement( second ) ];
  array[ segment( second ) ][ displacement( second ) ] = t;
 }
 /** Adds the specified increment the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 * @param incr the increment
	 */
 public static void add( final long[][] array, final long index, long incr ) {
  array[ segment( index ) ][ displacement( index ) ] += incr;
 }
 /** Multiplies by the specified factor the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 * @param factor the factor
	 */
 public static void mul( final long[][] array, final long index, long factor ) {
  array[ segment( index ) ][ displacement( index ) ] *= factor;
 }
 /** Increments the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 */
 public static void incr( final long[][] array, final long index ) {
  array[ segment( index ) ][ displacement( index ) ]++;
 }
 /** Decrements the element of the given big array of specified index.
	 * 
	 * @param array a big array.
	 * @param index a position in the big array.
	 */
 public static void decr( final long[][] array, final long index ) {
  array[ segment( index ) ][ displacement( index ) ]--;
 }
 /** Returns the length of the given big array.
	 * 
	 * @param array a big array.
	 * @return the length of the given big array.
	 */
 public static long length( final long[][] array ) {
  final int length = array.length;
  return length == 0 ? 0 : start( length - 1 ) + array[ length - 1 ].length;
 }
 /** Copies a big array from the specified source big array, beginning at the specified position, to the specified position of the destination big array.
	 * Handles correctly overlapping regions of the same big array. 
	 * 
	 * @param srcArray the source big array.
	 * @param srcPos the starting position in the source big array.
	 * @param destArray the destination big array.
	 * @param destPos the starting position in the destination data.
	 * @param length the number of elements to be copied.
	 */
 public static void copy( final long[][] srcArray, final long srcPos, final long[][] destArray, final long destPos, long length ) {
  if ( destPos <= srcPos ) {
   int srcSegment = segment( srcPos );
   int destSegment = segment( destPos );
   int srcDispl = displacement( srcPos );
   int destDispl = displacement( destPos );
   int l;
   while( length > 0 ) {
    l = (int)Math.min( length, Math.min( srcArray[ srcSegment ].length - srcDispl, destArray[ destSegment ].length - destDispl ) );
    System.arraycopy( srcArray[ srcSegment ], srcDispl, destArray[ destSegment ], destDispl, l );
    if ( ( srcDispl += l ) == SEGMENT_SIZE ) {
     srcDispl = 0;
     srcSegment++;
    }
    if ( ( destDispl += l ) == SEGMENT_SIZE ) {
     destDispl = 0;
     destSegment++;
    }
    length -= l;
   }
  }
  else {
   int srcSegment = segment( srcPos + length );
   int destSegment = segment( destPos + length );
   int srcDispl = displacement( srcPos + length );
   int destDispl = displacement( destPos + length );
   int l;
   while( length > 0 ) {
    if ( srcDispl == 0 ) {
     srcDispl = SEGMENT_SIZE;
     srcSegment--;
    }
    if ( destDispl == 0 ) {
     destDispl = SEGMENT_SIZE;
     destSegment--;
    }
    l = (int)Math.min( length, Math.min( srcDispl, destDispl ) );
    System.arraycopy( srcArray[ srcSegment ], srcDispl - l, destArray[ destSegment ], destDispl - l, l );
    srcDispl -= l;
    destDispl -= l;
    length -= l;
   }
  }
 }
 /** Copies a big array from the specified source big array, beginning at the specified position, to the specified position of the destination array.
	 * 
	 * @param srcArray the source big array.
	 * @param srcPos the starting position in the source big array.
	 * @param destArray the destination array.
	 * @param destPos the starting position in the destination data.
	 * @param length the number of elements to be copied.
	 */
 public static void copyFromBig( final long[][] srcArray, final long srcPos, final long[] destArray, int destPos, int length ) {
  int srcSegment = segment( srcPos );
  int srcDispl = displacement( srcPos );
  int l;
  while( length > 0 ) {
   l = Math.min( srcArray[ srcSegment ].length - srcDispl, length );
   System.arraycopy( srcArray[ srcSegment ], srcDispl, destArray, destPos, l );
   if ( ( srcDispl += l ) == SEGMENT_SIZE ) {
    srcDispl = 0;
    srcSegment++;
   }
   destPos += l;
   length -= l;
  }
 }
 /** Copies an array from the specified source array, beginning at the specified position, to the specified position of the destination big array.
	 * 
	 * @param srcArray the source array.
	 * @param srcPos the starting position in the source array.
	 * @param destArray the destination big array.
	 * @param destPos the starting position in the destination data.
	 * @param length the number of elements to be copied.
	 */
 public static void copyToBig( final long[] srcArray, int srcPos, final long[][] destArray, final long destPos, long length ) {
  int destSegment = segment( destPos );
  int destDispl = displacement( destPos );
  int l;
  while( length > 0 ) {
   l = (int)Math.min( destArray[ destSegment ].length - destDispl, length );
   System.arraycopy( srcArray, srcPos, destArray[ destSegment ], destDispl, l );
   if ( ( destDispl += l ) == SEGMENT_SIZE ) {
    destDispl = 0;
    destSegment++;
   }
   srcPos += l;
   length -= l;
  }
 }
 /** Creates a new big array.
	 *
	 * @param length the length of the new big array.
	 * @return a new big array of given length.
	 */
 public static long[][] newBigArray( final long length ) {
  if ( length == 0 ) return EMPTY_BIG_ARRAY;
  final int baseLength = (int)((length + SEGMENT_MASK) / SEGMENT_SIZE);
  long[][] base = new long[ baseLength ][];
  final int residual = (int)(length & SEGMENT_MASK);
  if ( residual != 0 ) {
   for( int i = 0; i < baseLength - 1; i++ ) base[ i ] = new long[ SEGMENT_SIZE ];
   base[ baseLength - 1 ] = new long[ residual ];
  }
  else for( int i = 0; i < baseLength; i++ ) base[ i ] = new long[ SEGMENT_SIZE ];
  return base;
 }
 /** Turns a standard array into a big array.
	 *
	 * <P>Note that the returned big array might contain as a segment the original array.
	 *
	 * @param array an array.
	 * @return a new big array with the same length and content of <code>array</code>.
	 */
 public static long[][] wrap( final long[] array ) {
  if ( array.length == 0 ) return EMPTY_BIG_ARRAY;
  if ( array.length <= SEGMENT_SIZE ) return new long[][] { array };
  final long[][] bigArray = newBigArray( array.length );
  for( int i = 0; i < bigArray.length; i++ ) System.arraycopy( array, (int)start( i ), bigArray[ i ], 0, bigArray[ i ].length );
  return bigArray;
 }
 /** Ensures that a big array can contain the given number of entries.
	 *
	 * <P>If you cannot foresee whether this big array will need again to be
	 * enlarged, you should probably use <code>grow()</code> instead.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new minimum length for this big array.
	 * @return <code>array</code>, if it contains <code>length</code> entries or more; otherwise,
	 * a big array with <code>length</code> entries whose first <code>length(array)</code>
	 * entries are the same as those of <code>array</code>.
	 */
 public static long[][] ensureCapacity( final long[][] array, final long length ) {
  return ensureCapacity( array, length, length( array ) );
 }
 /** Ensures that a big array can contain the given number of entries, preserving just a part of the big array.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new minimum length for this big array.
	 * @param preserve the number of elements of the big array that must be preserved in case a new allocation is necessary.
	 * @return <code>array</code>, if it can contain <code>length</code> entries or more; otherwise,
	 * a big array with <code>length</code> entries whose first <code>preserve</code>
	 * entries are the same as those of <code>array</code>.
	 */
 public static long[][] ensureCapacity( final long[][] array, final long length, final long preserve ) {
  final long oldLength = length( array );
  if ( length > oldLength ) {
   final int valid = array.length - ( array.length == 0 || array.length > 0 && array[ array.length - 1 ].length == SEGMENT_SIZE ? 0 : 1 );
   final int baseLength = (int)((length + SEGMENT_MASK) / SEGMENT_SIZE);
   final long[][] base = Arrays.copyOf( array, baseLength );
   final int residual = (int)(length & SEGMENT_MASK);
   if ( residual != 0 ) {
    for( int i = valid; i < baseLength - 1; i++ ) base[ i ] = new long[ SEGMENT_SIZE ];
    base[ baseLength - 1 ] = new long[ residual ];
   }
   else for( int i = valid; i < baseLength; i++ ) base[ i ] = new long[ SEGMENT_SIZE ];
   if ( preserve - ( valid * (long)SEGMENT_SIZE ) > 0 ) copy( array, valid * (long)SEGMENT_SIZE, base, valid * (long)SEGMENT_SIZE, preserve - ( valid * (long)SEGMENT_SIZE ) );
   return base;
  }
  return array;
 }
 /** Grows the given big array to the maximum between the given length and
	 * the current length multiplied by two, provided that the given
	 * length is larger than the current length.
	 *
	 * <P>If you want complete control on the big array growth, you
	 * should probably use <code>ensureCapacity()</code> instead.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new minimum length for this big array.
	 * @return <code>array</code>, if it can contain <code>length</code>
	 * entries; otherwise, a big array with
	 * max(<code>length</code>,<code>length(array)</code>/&phi;) entries whose first
	 * <code>length(array)</code> entries are the same as those of <code>array</code>.
	 * */
 public static long[][] grow( final long[][] array, final long length ) {
  final long oldLength = length( array );
  return length > oldLength ? grow( array, length, oldLength ) : array;
 }
 /** Grows the given big array to the maximum between the given length and
	 * the current length multiplied by two, provided that the given
	 * length is larger than the current length, preserving just a part of the big array.
	 *
	 * <P>If you want complete control on the big array growth, you
	 * should probably use <code>ensureCapacity()</code> instead.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new minimum length for this big array.
	 * @param preserve the number of elements of the big array that must be preserved in case a new allocation is necessary.
	 * @return <code>array</code>, if it can contain <code>length</code>
	 * entries; otherwise, a big array with
	 * max(<code>length</code>,<code>length(array)</code>/&phi;) entries whose first
	 * <code>preserve</code> entries are the same as those of <code>array</code>.
	 * */
 public static long[][] grow( final long[][] array, final long length, final long preserve ) {
  final long oldLength = length( array );
  return length > oldLength ? ensureCapacity( array, Math.max( 2 * oldLength, length ), preserve ) : array;
 }
 /** Trims the given big array to the given length.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new maximum length for the big array.
	 * @return <code>array</code>, if it contains <code>length</code>
	 * entries or less; otherwise, a big array with
	 * <code>length</code> entries whose entries are the same as
	 * the first <code>length</code> entries of <code>array</code>.
	 * 
	 */
 public static long[][] trim( final long[][] array, final long length ) {
  final long oldLength = length( array );
  if ( length >= oldLength ) return array;
  final int baseLength = (int)((length + SEGMENT_MASK) / SEGMENT_SIZE);
  final long[][] base = Arrays.copyOf( array, baseLength );
  final int residual = (int)(length & SEGMENT_MASK);
  if ( residual != 0 ) base[ baseLength - 1 ] = LongArrays.trim( base[ baseLength - 1 ], residual );
  return base;
 }
 /** Sets the length of the given big array.
	 *
	 * <p><strong>Warning:</strong> the returned array might use part of the segments of the original
	 * array, which must be considered read-only after calling this method.
	 *
	 * @param array a big array.
	 * @param length the new length for the big array.
	 * @return <code>array</code>, if it contains exactly <code>length</code>
	 * entries; otherwise, if it contains <em>more</em> than
	 * <code>length</code> entries, a big array with <code>length</code> entries
	 * whose entries are the same as the first <code>length</code> entries of
	 * <code>array</code>; otherwise, a big array with <code>length</code> entries
	 * whose first <code>length(array)</code> entries are the same as those of
	 * <code>array</code>.
	 * 
	 */
 public static long[][] setLength( final long[][] array, final long length ) {
  final long oldLength = length( array );
  if ( length == oldLength ) return array;
  if ( length < oldLength ) return trim( array, length );
  return ensureCapacity( array, length );
 }
 /** Returns a copy of a portion of a big array.
	 *
	 * @param array a big array.
	 * @param offset the first element to copy.
	 * @param length the number of elements to copy.
	 * @return a new big array containing <code>length</code> elements of <code>array</code> starting at <code>offset</code>.
	 */
 public static long[][] copy( final long[][] array, final long offset, final long length ) {
  ensureOffsetLength( array, offset, length );
  final long[][] a =
   newBigArray( length );
  copy( array, offset, a, 0, length );
  return a;
 }
 /** Returns a copy of a big array.
	 *
	 * @param array a big array.
	 * @return a copy of <code>array</code>.
	 */
 public static long[][] copy( final long[][] array ) {
  final long[][] base = array.clone();
  for( int i = base.length; i-- != 0; ) base[ i ] = array[ i ].clone();
  return base;
 }
 /** Fills the given big array with the given value.
	 *
	 * <P>This method uses a backward loop. It is significantly faster than the corresponding
	 * method in {@link java.util.Arrays}.
	 *
	 * @param array a big array.
	 * @param value the new value for all elements of the big array.
	 */
 public static void fill( final long[][] array, final long value ) {
  for( int i = array.length; i-- != 0; ) LongArrays.fill( array[ i ], value );
 }
 /** Fills a portion of the given big array with the given value.
	 *
	 * <P>If possible (i.e., <code>from</code> is 0) this method uses a
	 * backward loop. In this case, it is significantly faster than the
	 * corresponding method in {@link java.util.Arrays}.
	 *
	 * @param array a big array.
	 * @param from the starting index of the portion to fill.
	 * @param to the end index of the portion to fill.
	 * @param value the new value for all elements of the specified portion of the big array.
	 */
 public static void fill( final long[][] array, final long from, long to, final long value ) {
  final long length = length( array );
  BigArrays.ensureFromTo( length, from, to );
  int fromSegment = segment( from );
  int toSegment = segment( to );
  int fromDispl = displacement( from );
  int toDispl = displacement( to );
  if ( fromSegment == toSegment ) {
   LongArrays.fill( array[ fromSegment ], fromDispl, toDispl, value );
   return;
  }
  if ( toDispl != 0 ) LongArrays.fill( array[ toSegment ], 0, toDispl, value );
  while( --toSegment > fromSegment ) LongArrays.fill( array[ toSegment ], value );
  LongArrays.fill( array[ fromSegment ], fromDispl, SEGMENT_SIZE, value );
 }
 /** Returns true if the two big arrays are elementwise equal.
	 *
	 * <P>This method uses a backward loop. It is significantly faster than the corresponding
	 * method in {@link java.util.Arrays}.
	 *
	 * @param a1 a big array.
	 * @param a2 another big array.
	 * @return true if the two big arrays are of the same length, and their elements are equal.
	 */
 public static boolean equals( final long[][] a1, final long a2[][] ) {
  if ( length( a1 ) != length( a2 ) ) return false;
  int i = a1.length, j;
  long[] t, u;
  while( i-- != 0 ) {
   t = a1[ i ];
   u = a2[ i ];
   j = t.length;
   while( j-- != 0 ) if (! ( (t[ j ]) == (u[ j ]) ) ) return false;
  }
  return true;
 }
 /* Returns a string representation of the contents of the specified big array. 
	 *
	 * The string representation consists of a list of the big array's elements, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (a comma followed by a space). Returns "null" if <code>a</code> is null.
	 * @param a the big array whose string representation to return.
	 * @return the string representation of <code>a</code>.
	 */
 public static String toString( final long[][] a ) {
  if ( a == null ) return "null";
  final long last = length( a ) - 1;
  if ( last == - 1 ) return "[]";
  final StringBuilder b = new StringBuilder();
  b.append('[');
  for ( long i = 0; ; i++ ) {
   b.append( String.valueOf( get( a, i ) ) );
   if ( i == last ) return b.append(']').toString();
   b.append(", ");
        }
 }
 /** Ensures that a range given by its first (inclusive) and last (exclusive) elements fits a big array.
	 *
	 * <P>This method may be used whenever a big array range check is needed.
	 *
	 * @param a a big array.
	 * @param from a start index (inclusive).
	 * @param to an end index (inclusive).
	 * @throws IllegalArgumentException if <code>from</code> is greater than <code>to</code>.
	 * @throws ArrayIndexOutOfBoundsException if <code>from</code> or <code>to</code> are greater than the big array length or negative.
	 */
 public static void ensureFromTo( final long[][] a, final long from, final long to ) {
  BigArrays.ensureFromTo( length( a ), from, to );
 }
 /** Ensures that a range given by an offset and a length fits a big array.
	 *
	 * <P>This method may be used whenever a big array range check is needed.
	 *
	 * @param a a big array.
	 * @param offset a start index.
	 * @param length a length (the number of elements in the range).
	 * @throws IllegalArgumentException if <code>length</code> is negative.
	 * @throws ArrayIndexOutOfBoundsException if <code>offset</code> is negative or <code>offset</code>+<code>length</code> is greater than the big array length.
	 */
 public static void ensureOffsetLength( final long[][] a, final long offset, final long length ) {
  BigArrays.ensureOffsetLength( length( a ), offset, length );
 }
 /** A type-specific content-based hash strategy for big arrays. */
 private static final class BigArrayHashStrategy implements Hash.Strategy<long[][]>, java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  public int hashCode( final long[][] o ) {
   return java.util.Arrays.deepHashCode( o );
  }
  public boolean equals( final long[][] a, final long[][] b ) {
   return LongBigArrays.equals( a, b );
  }
 }
 /** A type-specific content-based hash strategy for big arrays.
	 *
	 * <P>This hash strategy may be used in custom hash collections whenever keys are
	 * big arrays, and they must be considered equal by content. This strategy
	 * will handle <code>null</code> correctly, and it is serializable.
	 */
 @SuppressWarnings({"unchecked", "rawtypes"})
 public final static Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy();
 private static final int SMALL = 7;
 private static final int MEDIUM = 40;
 private static void vecSwap( final long[][] x, long a, long b, final long n ) {
  for( int i = 0; i < n; i++, a++, b++ ) swap( x, a, b );
 }
 private static long med3( final long x[][], final long a, final long b, final long c, LongComparator comp ) {
  int ab = comp.compare( get( x, a ), get( x, b ) );
  int ac = comp.compare( get( x, a ), get( x, c ) );
  int bc = comp.compare( get( x, b ), get( x, c ) );
  return ( ab < 0 ?
   ( bc < 0 ? b : ac < 0 ? c : a ) :
   ( bc > 0 ? b : ac > 0 ? c : a ) );
 }
 private static void selectionSort( final long[][] a, final long from, final long to, final LongComparator comp ) {
  for( long i = from; i < to - 1; i++ ) {
   long m = i;
   for( long j = i + 1; j < to; j++ ) if ( comp.compare( LongBigArrays.get( a, j ), LongBigArrays.get( a, m ) ) < 0 ) m = j;
   if ( m != i ) swap( a, i, m );
  }
 }
 /** Sorts the specified range of elements according to the order induced by the specified
	 * comparator using quicksort. 
	 * 
	 * <p>The sorting algorithm is a tuned quicksort adapted from Jon L. Bentley and M. Douglas
	 * McIlroy, &ldquo;Engineering a Sort Function&rdquo;, <i>Software: Practice and Experience</i>, 23(11), pages
	 * 1249&minus;1265, 1993.
	 * 
	 * @param x the big array to be sorted.
	 * @param from the index of the first element (inclusive) to be sorted.
	 * @param to the index of the last element (exclusive) to be sorted.
	 * @param comp the comparator to determine the sorting order.
	 */
 public static void quickSort( final long[][] x, final long from, final long to, final LongComparator comp ) {
  final long len = to - from;
  // Selection sort on smallest arrays
  if ( len < SMALL ) {
   selectionSort( x, from, to, comp );
   return;
  }
  // Choose a partition element, v
  long m = from + len / 2; // Small arrays, middle element
  if ( len > SMALL ) {
   long l = from;
   long n = to - 1;
   if ( len > MEDIUM ) { // Big arrays, pseudomedian of 9
    long s = len / 8;
    l = med3( x, l, l + s, l + 2 * s, comp );
    m = med3( x, m - s, m, m + s, comp );
    n = med3( x, n - 2 * s, n - s, n, comp );
   }
   m = med3( x, l, m, n, comp ); // Mid-size, med of 3
  }
  final long v = get( x, m );
  // Establish Invariant: v* (<v)* (>v)* v*
  long a = from, b = a, c = to - 1, d = c;
  while(true) {
   int comparison;
   while ( b <= c && ( comparison = comp.compare( get( x, b ), v ) ) <= 0 ) {
    if ( comparison == 0 ) swap( x, a++, b );
    b++;
   }
   while (c >= b && ( comparison = comp.compare( get( x, c ), v ) ) >=0 ) {
    if ( comparison == 0 ) swap( x, c, d-- );
    c--;
   }
   if ( b > c ) break;
   swap( x, b++, c-- );
  }
  // Swap partition elements back to middle
  long s, n = to;
  s = Math.min( a - from, b - a );
  vecSwap( x, from, b - s, s );
  s = Math.min( d - c, n - d- 1 );
  vecSwap( x, b, n - s, s );
  // Recursively sort non-partition-elements
  if ( ( s = b - a ) > 1 ) quickSort( x, from, from + s, comp );
  if ( ( s = d - c ) > 1 ) quickSort( x, n - s, n, comp );
 }
 @SuppressWarnings("unchecked")
 private static long med3( final long x[][], final long a, final long b, final long c ) {
  int ab = ( (get( x, a )) < (get( x, b )) ? -1 : ( (get( x, a )) == (get( x, b )) ? 0 : 1 ) );
  int ac = ( (get( x, a )) < (get( x, c )) ? -1 : ( (get( x, a )) == (get( x, c )) ? 0 : 1 ) );
  int bc = ( (get( x, b )) < (get( x, c )) ? -1 : ( (get( x, b )) == (get( x, c )) ? 0 : 1 ) );
  return ( ab < 0 ?
   ( bc < 0 ? b : ac < 0 ? c : a ) :
   ( bc > 0 ? b : ac > 0 ? c : a ) );
 }
 @SuppressWarnings("unchecked")
 private static void selectionSort( final long[][] a, final long from, final long to ) {
  for( long i = from; i < to - 1; i++ ) {
   long m = i;
   for( long j = i + 1; j < to; j++ ) if ( ( (LongBigArrays.get( a, j )) < (LongBigArrays.get( a, m )) ) ) m = j;
   if ( m != i ) swap( a, i, m );
  }
 }
 /** Sorts the specified big array according to the order induced by the specified
	 * comparator using quicksort. 
	 * 
	 * <p>The sorting algorithm is a tuned quicksort adapted from Jon L. Bentley and M. Douglas
	 * McIlroy, &ldquo;Engineering a Sort Function&rdquo;, <i>Software: Practice and Experience</i>, 23(11), pages
	 * 1249&minus;1265, 1993.
	 * 
	 * @param x the big array to be sorted.
	 * @param comp the comparator to determine the sorting order.
	 * 
	 */
 public static void quickSort( final long[][] x, final LongComparator comp ) {
  quickSort( x, 0, LongBigArrays.length( x ), comp );
 }
 /** Sorts the specified range of elements according to the natural ascending order using quicksort.
	 * 
	 * <p>The sorting algorithm is a tuned quicksort adapted from Jon L. Bentley and M. Douglas
	 * McIlroy, &ldquo;Engineering a Sort Function&rdquo;, <i>Software: Practice and Experience</i>, 23(11), pages
	 * 1249&minus;1265, 1993.
	 * 
	 * @param x the big array to be sorted.
	 * @param from the index of the first element (inclusive) to be sorted.
	 * @param to the index of the last element (exclusive) to be sorted.
	 */
 @SuppressWarnings("unchecked")
 public static void quickSort( final long[][] x, final long from, final long to ) {
  final long len = to - from;
  // Selection sort on smallest arrays
  if ( len < SMALL ) {
   selectionSort( x, from, to );
   return;
  }
  // Choose a partition element, v
  long m = from + len / 2; // Small arrays, middle element
  if ( len > SMALL ) {
   long l = from;
   long n = to - 1;
   if ( len > MEDIUM ) { // Big arrays, pseudomedian of 9
    long s = len / 8;
    l = med3( x, l, l + s, l + 2 * s );
    m = med3( x, m - s, m, m + s );
    n = med3( x, n - 2 * s, n - s, n );
   }
   m = med3( x, l, m, n ); // Mid-size, med of 3
  }
  final long v = get( x, m );
  // Establish Invariant: v* (<v)* (>v)* v*
  long a = from, b = a, c = to - 1, d = c;
  while(true) {
   int comparison;
   while ( b <= c && ( comparison = ( (get( x, b )) < (v) ? -1 : ( (get( x, b )) == (v) ? 0 : 1 ) ) ) <= 0 ) {
    if ( comparison == 0 ) swap( x, a++, b );
    b++;
   }
   while (c >= b && ( comparison = ( (get( x, c )) < (v) ? -1 : ( (get( x, c )) == (v) ? 0 : 1 ) ) ) >=0 ) {
    if ( comparison == 0 ) swap( x, c, d-- );
    c--;
   }
   if ( b > c ) break;
   swap( x, b++, c-- );
  }
  // Swap partition elements back to middle
  long s, n = to;
  s = Math.min( a - from, b - a );
  vecSwap( x, from, b - s, s );
  s = Math.min( d - c, n - d- 1 );
  vecSwap( x, b, n - s, s );
  // Recursively sort non-partition-elements
  if ( ( s = b - a ) > 1 ) quickSort( x, from, from + s );
  if ( ( s = d - c ) > 1 ) quickSort( x, n - s, n );
 }
 /** Sorts the specified big array according to the natural ascending order using quicksort.
	 * 
	 * <p>The sorting algorithm is a tuned quicksort adapted from Jon L. Bentley and M. Douglas
	 * McIlroy, &ldquo;Engineering a Sort Function&rdquo;, <i>Software: Practice and Experience</i>, 23(11), pages
	 * 1249&minus;1265, 1993.
	 * 
	 * @param x the big array to be sorted.
	 */
 @SuppressWarnings("unchecked")
 public static void quickSort( final long[][] x ) {
  quickSort( x, 0, LongBigArrays.length( x ) );
 }
 /**
	 * Searches a range of the specified big array for the specified value using 
	 * the binary search algorithm. The range must be sorted prior to making this call. 
	 * If it is not sorted, the results are undefined. If the range contains multiple elements with 
	 * the specified value, there is no guarantee which one will be found.
	 *
	 * @param a the big array to be searched.
	 * @param from  the index of the first element (inclusive) to be searched.
	 * @param to  the index of the last element (exclusive) to be searched.
	 * @param key the value to be searched for.
	 * @return index of the search key, if it is contained in the big array;
	 *             otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The <i>insertion
	 *             point</i> is defined as the the point at which the value would
	 *             be inserted into the big array: the index of the first
	 *             element greater than the key, or the length of the big array, if all
	 *             elements in the big array are less than the specified key.  Note
	 *             that this guarantees that the return value will be &gt;= 0 if
	 *             and only if the key is found.
	 * @see java.util.Arrays
	 */
 @SuppressWarnings({"unchecked","rawtypes"})
 public static long binarySearch( final long[][] a, long from, long to, final long key ) {
  long midVal;
  to--;
  while (from <= to) {
   final long mid = (from + to) >>> 1;
   midVal = get( a, mid );
   if (midVal < key) from = mid + 1;
   else if (midVal > key) to = mid - 1;
   else return mid;
        }
  return -( from + 1 );
 }
 /**
	 * Searches a big array for the specified value using 
	 * the binary search algorithm. The range must be sorted prior to making this call. 
	 * If it is not sorted, the results are undefined. If the range contains multiple elements with 
	 * the specified value, there is no guarantee which one will be found.
	 *
	 * @param a the big array to be searched.
	 * @param key the value to be searched for.
	 * @return index of the search key, if it is contained in the big array;
	 *             otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The <i>insertion
	 *             point</i> is defined as the the point at which the value would
	 *             be inserted into the big array: the index of the first
	 *             element greater than the key, or the length of the big array, if all
	 *             elements in the big array are less than the specified key.  Note
	 *             that this guarantees that the return value will be &gt;= 0 if
	 *             and only if the key is found.
	 * @see java.util.Arrays
	 */
 public static long binarySearch( final long[][] a, final long key ) {
  return binarySearch( a, 0, LongBigArrays.length( a ), key );
 }
 /**
	 * Searches a range of the specified big array for the specified value using 
	 * the binary search algorithm and a specified comparator. The range must be sorted following the comparator prior to making this call. 
	 * If it is not sorted, the results are undefined. If the range contains multiple elements with 
	 * the specified value, there is no guarantee which one will be found.
	 *
	 * @param a the big array to be searched.
	 * @param from  the index of the first element (inclusive) to be searched.
	 * @param to  the index of the last element (exclusive) to be searched.
	 * @param key the value to be searched for.
	 * @param c a comparator.
	 * @return index of the search key, if it is contained in the big array;
	 *             otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The <i>insertion
	 *             point</i> is defined as the the point at which the value would
	 *             be inserted into the big array: the index of the first
	 *             element greater than the key, or the length of the big array, if all
	 *             elements in the big array are less than the specified key.  Note
	 *             that this guarantees that the return value will be &gt;= 0 if
	 *             and only if the key is found.
	 * @see java.util.Arrays
	 */
 public static long binarySearch( final long[][] a, long from, long to, final long key, final LongComparator c ) {
  long midVal;
  to--;
  while (from <= to) {
   final long mid = (from + to) >>> 1;
   midVal = get( a, mid );
   final int cmp = c.compare( midVal, key );
   if ( cmp < 0 ) from = mid + 1;
   else if (cmp > 0) to = mid - 1;
   else return mid; // key found
  }
  return -( from + 1 );
 }
 /**
	 * Searches a big array for the specified value using 
	 * the binary search algorithm and a specified comparator. The range must be sorted following the comparator prior to making this call. 
	 * If it is not sorted, the results are undefined. If the range contains multiple elements with 
	 * the specified value, there is no guarantee which one will be found.
	 *
	 * @param a the big array to be searched.
	 * @param key the value to be searched for.
	 * @param c a comparator.
	 * @return index of the search key, if it is contained in the big array;
	 *             otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The <i>insertion
	 *             point</i> is defined as the the point at which the value would
	 *             be inserted into the big array: the index of the first
	 *             element greater than the key, or the length of the big array, if all
	 *             elements in the big array are less than the specified key.  Note
	 *             that this guarantees that the return value will be &gt;= 0 if
	 *             and only if the key is found.
	 * @see java.util.Arrays
	 */
 public static long binarySearch( final long[][] a, final long key, final LongComparator c ) {
  return binarySearch( a, 0, LongBigArrays.length( a ), key, c );
 }
 /** The size of a digit used during radix sort (must be a power of 2). */
 private static final int DIGIT_BITS = 8;
 /** The mask to extract a digit of {@link #DIGIT_BITS} bits. */
 private static final int DIGIT_MASK = ( 1 << DIGIT_BITS ) - 1;
 /** The number of digits per element. */
 private static final int DIGITS_PER_ELEMENT = Long.SIZE / DIGIT_BITS;
 /** This method fixes negative numbers so that the combination exponent/significand is lexicographically sorted. */
 /** Sorts the specified big array using radix sort.
	 * 
	 * <p>The sorting algorithm is a tuned radix sort adapted from Peter M. McIlroy, Keith Bostic and M. Douglas
	 * McIlroy, &ldquo;Engineering radix sort&rdquo;, <i>Computing Systems</i>, 6(1), pages 5&minus;27 (1993),
	 * and further improved using the digit-oracle idea described by
	 * Juha K&auml;rkk&auml;inen and Tommi Rantala in &ldquo;Engineering radix sort for strings&rdquo;,
	 * <i>String Processing and Information Retrieval, 15th International Symposium</i>, volume 5280 of
	 * Lecture Notes in Computer Science, pages 3&minus;14, Springer (2008).
	 *
	 * <p>This implementation is significantly faster than quicksort 
	 * already at small sizes (say, more than 10000 elements), but it can only
	 * sort in ascending order. 
	 * It will allocate a support array of bytes with the same number of elements as the array to be sorted.
	 * 
	 * @param a the big array to be sorted.
	 */
 public static void radixSort( final long[][] a ) {
  radixSort( a, 0, LongBigArrays.length( a ) );
 }
 /** Sorts the specified big array using radix sort.
	 * 
	 * <p>The sorting algorithm is a tuned radix sort adapted from Peter M. McIlroy, Keith Bostic and M. Douglas
	 * McIlroy, &ldquo;Engineering radix sort&rdquo;, <i>Computing Systems</i>, 6(1), pages 5&minus;27 (1993),
	 * and further improved using the digit-oracle idea described by
	 * Juha K&auml;rkk&auml;inen and Tommi Rantala in &ldquo;Engineering radix sort for strings&rdquo;,
	 * <i>String Processing and Information Retrieval, 15th International Symposium</i>, volume 5280 of
	 * Lecture Notes in Computer Science, pages 3&minus;14, Springer (2008).
	 *
	 * <p>This implementation is significantly faster than quicksort 
	 * already at small sizes (say, more than 10000 elements), but it can only
	 * sort in ascending order. 
	 * It will allocate a support array of bytes with the same number of elements as the array to be sorted.
	 * 
	 * @param a the big array to be sorted.
	 * @param from the index of the first element (inclusive) to be sorted.
	 * @param to the index of the last element (exclusive) to be sorted.
	 */
 public static void radixSort( final long[][] a, final long from, final long to ) {
  final int maxLevel = DIGITS_PER_ELEMENT - 1;
  final int stackSize = ( ( 1 << DIGIT_BITS ) - 1 ) * ( DIGITS_PER_ELEMENT - 1 ) + 1;
  final long[] offsetStack = new long[ stackSize ];
  int offsetPos = 0;
  final long[] lengthStack = new long[ stackSize ];
  int lengthPos = 0;
  final int[] levelStack = new int[ stackSize ];
  int levelPos = 0;
  offsetStack[ offsetPos++ ] = from;
  lengthStack[ lengthPos++ ] = to - from;
  levelStack[ levelPos++ ] = 0;
  final long[] count = new long[ 1 << DIGIT_BITS ];
  final long[] pos = new long[ 1 << DIGIT_BITS ];
  final byte[][] digit = ByteBigArrays.newBigArray( to - from );
  while( offsetPos > 0 ) {
   final long first = offsetStack[ --offsetPos ];
   final long length = lengthStack[ --lengthPos ];
   final int level = levelStack[ --levelPos ];
   final int signMask = level % DIGITS_PER_ELEMENT == 0 ? 1 << DIGIT_BITS - 1 : 0;
   if ( length < MEDIUM ) {
    selectionSort( a, first, first + length );
    continue;
   }
   final int shift = ( DIGITS_PER_ELEMENT - 1 - level % DIGITS_PER_ELEMENT ) * DIGIT_BITS; // This is the shift that extract the right byte from a key
   // Count keys.
   for( long i = length; i-- != 0; ) ByteBigArrays.set( digit, i, (byte)( ( ( (LongBigArrays.get( a, first + i )) >>> shift ) & DIGIT_MASK ) ^ signMask ));
   for( long i = length; i-- != 0; ) count[ ByteBigArrays.get( digit, i ) & 0xFF ]++;
   // Compute cumulative distribution and push non-singleton keys on stack.
   int lastUsed = -1;
   long p = 0;
   for( int i = 0; i < 1 << DIGIT_BITS; i++ ) {
    if ( count[ i ] != 0 ) {
     lastUsed = i;
     if ( level < maxLevel && count[ i ] > 1 ){
      //System.err.println( " Pushing " + new StackEntry( first + pos[ i - 1 ], first + pos[ i ], level + 1 ) );
      offsetStack[ offsetPos++ ] = p + first;
      lengthStack[ lengthPos++ ] = count[ i ];
      levelStack[ levelPos++ ] = level + 1;
     }
    }
    pos[ i ] = ( p += count[ i ] );
   }
   // When all slots are OK, the last slot is necessarily OK.
   final long end = length - count[ lastUsed ];
   count[ lastUsed ] = 0;
   // i moves through the start of each block
   int c = -1;
   for( long i = 0, d; i < end; i += count[ c ], count[ c ] = 0 ) {
    long t = LongBigArrays.get( a, i +first );
    c = ByteBigArrays.get( digit, i ) & 0xFF;
    while( ( d = --pos[ c ] ) > i ) {
     final long z = t;
     final int zz = c;
     t = LongBigArrays.get( a, d + first );
     c = ByteBigArrays.get( digit, d ) & 0xFF;
     LongBigArrays.set( a, d + first, z );
     ByteBigArrays.set( digit, d, (byte)zz );
    }
    LongBigArrays.set( a, i + first, t );
   }
  }
 }
 private static void selectionSort( final long[][] a, final long[][] b, final long from, final long to ) {
  for( long i = from; i < to - 1; i++ ) {
   long m = i;
   for( long j = i + 1; j < to; j++ )
    if ( ( (LongBigArrays.get( a, j )) < (LongBigArrays.get( a, m )) ) || ( (LongBigArrays.get( a, j )) == (LongBigArrays.get( a, m )) ) && ( (LongBigArrays.get( b, j )) < (LongBigArrays.get( b, m )) ) ) m = j;
   if ( m != i ) {
    long t = LongBigArrays.get( a, i );
    LongBigArrays.set( a, i, LongBigArrays.get( a, m ) );
    LongBigArrays.set( a, m, t );
    t = LongBigArrays.get( b, i );
    LongBigArrays.set( b, i, LongBigArrays.get( b, m ) );
    LongBigArrays.set( b, m, t );
   }
  }
 }
 /** Sorts the specified pair of big arrays lexicographically using radix sort.
	 * <p>The sorting algorithm is a tuned radix sort adapted from Peter M. McIlroy, Keith Bostic and M. Douglas
	 * McIlroy, &ldquo;Engineering radix sort&rdquo;, <i>Computing Systems</i>, 6(1), pages 5&minus;27 (1993),
	 * and further improved using the digit-oracle idea described by
	 * Juha K&auml;rkk&auml;inen and Tommi Rantala in &ldquo;Engineering radix sort for strings&rdquo;,
	 * <i>String Processing and Information Retrieval, 15th International Symposium</i>, volume 5280 of
	 * Lecture Notes in Computer Science, pages 3&minus;14, Springer (2008).
	 *
	 * <p>This method implements a <em>lexicographical</em> sorting of the arguments. Pairs of elements
	 * in the same position in the two provided arrays will be considered a single key, and permuted
	 * accordingly. In the end, either <code>a[ i ] < a[ i + 1 ]</code> or <code>a[ i ] == a[ i + 1 ]</code> and <code>b[ i ] <= b[ i + 1 ]</code>.
	 *
	 * <p>This implementation is significantly faster than quicksort 
	 * already at small sizes (say, more than 10000 elements), but it can only
	 * sort in ascending order. It will allocate a support array of bytes with the same number of elements as the arrays to be sorted.
	 * 
	 * @param a the first big array to be sorted.
	 * @param b the second big array to be sorted.
	 */
 public static void radixSort( final long[][] a, final long[][] b ) {
  radixSort( a, b, 0, LongBigArrays.length( a ) );
 }
 /** Sorts the specified pair of big arrays lexicographically using radix sort.
	 * 
	 * <p>The sorting algorithm is a tuned radix sort adapted from Peter M. McIlroy, Keith Bostic and M. Douglas
	 * McIlroy, &ldquo;Engineering radix sort&rdquo;, <i>Computing Systems</i>, 6(1), pages 5&minus;27 (1993),
	 * and further improved using the digit-oracle idea described by
	 * Juha K&auml;rkk&auml;inen and Tommi Rantala in &ldquo;Engineering radix sort for strings&rdquo;,
	 * <i>String Processing and Information Retrieval, 15th International Symposium</i>, volume 5280 of
	 * Lecture Notes in Computer Science, pages 3&minus;14, Springer (2008).
	 *
	 * <p>This method implements a <em>lexicographical</em> sorting of the arguments. Pairs of elements
	 * in the same position in the two provided arrays will be considered a single key, and permuted
	 * accordingly. In the end, either <code>a[ i ] < a[ i + 1 ]</code> or <code>a[ i ] == a[ i + 1 ]</code> and <code>b[ i ] <= b[ i + 1 ]</code>.
	 *
	 * <p>This implementation is significantly faster than quicksort 
	 * already at small sizes (say, more than 10000 elements), but it can only
	 * sort in ascending order. It will allocate a support array of bytes with the same number of elements as the arrays to be sorted.
	 * 
	 * @param a the first big array to be sorted.
	 * @param b the second big array to be sorted.
	 * @param from the index of the first element (inclusive) to be sorted.
	 * @param to the index of the last element (exclusive) to be sorted.
	 */
 public static void radixSort( final long[][] a, final long[][] b, final long from, final long to ) {
  final int layers = 2;
  if ( LongBigArrays.length( a ) != LongBigArrays.length( b ) ) throw new IllegalArgumentException( "Array size mismatch." );
  final int maxLevel = DIGITS_PER_ELEMENT * layers - 1;
  final int stackSize = ( ( 1 << DIGIT_BITS ) - 1 ) * ( layers * DIGITS_PER_ELEMENT - 1 ) + 1;
  final long[] offsetStack = new long[ stackSize ];
  int offsetPos = 0;
  final long[] lengthStack = new long[ stackSize ];
  int lengthPos = 0;
  final int[] levelStack = new int[ stackSize ];
  int levelPos = 0;
  offsetStack[ offsetPos++ ] = from;
  lengthStack[ lengthPos++ ] = to - from;
  levelStack[ levelPos++ ] = 0;
  final long[] count = new long[ 1 << DIGIT_BITS ];
  final long[] pos = new long[ 1 << DIGIT_BITS ];
  final byte[][] digit = ByteBigArrays.newBigArray( to - from );
  while( offsetPos > 0 ) {
   final long first = offsetStack[ --offsetPos ];
   final long length = lengthStack[ --lengthPos ];
   final int level = levelStack[ --levelPos ];
   final int signMask = level % DIGITS_PER_ELEMENT == 0 ? 1 << DIGIT_BITS - 1 : 0;
   if ( length < MEDIUM ) {
    selectionSort( a, b, first, first + length );
    continue;
   }
   final long[][] k = level < DIGITS_PER_ELEMENT ? a : b; // This is the key array
   final int shift = ( DIGITS_PER_ELEMENT - 1 - level % DIGITS_PER_ELEMENT ) * DIGIT_BITS; // This is the shift that extract the right byte from a key
   // Count keys.
   for( long i = length; i-- != 0; ) ByteBigArrays.set( digit, i, (byte)( ( ( (LongBigArrays.get( k, first + i )) >>> shift ) & DIGIT_MASK ) ^ signMask ) );
   for( long i = length; i-- != 0; ) count[ ByteBigArrays.get( digit, i ) & 0xFF ]++;
   // Compute cumulative distribution and push non-singleton keys on stack.
   int lastUsed = -1;
   long p = 0;
   for( int i = 0; i < 1 << DIGIT_BITS; i++ ) {
    if ( count[ i ] != 0 ) {
     lastUsed = i;
     if ( level < maxLevel && count[ i ] > 1 ){
      offsetStack[ offsetPos++ ] = p + first;
      lengthStack[ lengthPos++ ] = count[ i ];
      levelStack[ levelPos++ ] = level + 1;
     }
    }
    pos[ i ] = ( p += count[ i ] );
   }
   // When all slots are OK, the last slot is necessarily OK.
   final long end = length - count[ lastUsed ];
   count[ lastUsed ] = 0;
   // i moves through the start of each block
   int c = -1;
   for( long i = 0, d; i < end; i += count[ c ], count[ c ] = 0 ) {
    long t = LongBigArrays.get( a, i + first );
    long u = LongBigArrays.get( b, i + first );
    c = ByteBigArrays.get( digit, i ) & 0xFF;
    while( ( d = --pos[ c ] ) > i ) {
     long z = t;
     final int zz = c;
     t = LongBigArrays.get( a, d + first );
     LongBigArrays.set( a, d + first, z );
     z = u;
     u = LongBigArrays.get( b, d + first );
     LongBigArrays.set( b, d + first, z );
     c = ByteBigArrays.get( digit, d ) & 0xFF;
     ByteBigArrays.set( digit, d, (byte)zz );
    }
    LongBigArrays.set( a, i + first, t );
    LongBigArrays.set( b, i + first, u );
   }
  }
 }
 /** Shuffles the specified big array fragment using the specified pseudorandom number generator.
	 * 
	 * @param a the big array to be shuffled.
	 * @param from the index of the first element (inclusive) to be shuffled.
	 * @param to the index of the last element (exclusive) to be shuffled.
	 * @param random a pseudorandom number generator (please use a <a href="http://dsiutils.dsi.unimi.it/docs/it/unimi/dsi/util/XorShiftStarRandom.html">XorShift*</a> generator).
	 * @return <code>a</code>.
	 */
 public static long[][] shuffle( final long[][] a, final long from, final long to, final Random random ) {
  for( long i = to - from; i-- != 0; ) {
   final long p = ( random.nextLong() & 0x7FFFFFFFFFFFFFFFL ) % ( i + 1 );
   final long t = get( a, from + i );
   set( a, from + i, get( a, from + p ) );
   set( a, from + p, t );
  }
  return a;
 }
 /** Shuffles the specified big array using the specified pseudorandom number generator.
	 * 
	 * @param a the big array to be shuffled.
	 * @param random a pseudorandom number generator (please use a <a href="http://dsiutils.dsi.unimi.it/docs/it/unimi/dsi/util/XorShiftStarRandom.html">XorShift*</a> generator).
	 * @return <code>a</code>.
	 */
 public static long[][] shuffle( final long[][] a, final Random random ) {
  for( long i = length( a ); i-- != 0; ) {
   final long p = ( random.nextLong() & 0x7FFFFFFFFFFFFFFFL ) % ( i + 1 );
   final long t = get( a, i );
   set( a, i, get( a, p ) );
   set( a, p, t );
  }
  return a;
 }
}
