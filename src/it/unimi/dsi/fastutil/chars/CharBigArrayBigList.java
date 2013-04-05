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
package it.unimi.dsi.fastutil.chars;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.BigArrays;
/** A type-specific big list based on a big array; provides some additional methods that use polymorphism to avoid (un)boxing. 
 *
 * <P>This class implements a lightweight, fast, open, optimized,
 * reuse-oriented version of big-array-based big lists. Instances of this class
 * represent a big list with a big array that is enlarged as needed when new entries
 * are created (by doubling the current length), but is
 * <em>never</em> made smaller (even on a {@link #clear()}). A family of
 * {@linkplain #trim() trimming methods} lets you control the size of the
 * backing big array; this is particularly useful if you reuse instances of this class.
 * Range checks are equivalent to those of {@link java.util}'s classes, but
 * they are delayed as much as possible. The backing big array is exposed by the
 * {@link #elements()} method.
 *
 * <p>This class implements the bulk methods <code>removeElements()</code>,
 * <code>addElements()</code> and <code>getElements()</code> using
 * high-performance system calls (e.g., {@link
 * System#arraycopy(Object,int,Object,int,int) System.arraycopy()} instead of
 * expensive loops.
 *
 * @see java.util.ArrayList
 */
public class CharBigArrayBigList extends AbstractCharBigList implements RandomAccess, Cloneable, java.io.Serializable {
 private static final long serialVersionUID = -7046029254386353130L;
 /** The initial default capacity of a big-array big list. */
 public final static int DEFAULT_INITIAL_CAPACITY = 16;
 /** The backing big array. */
 protected transient char a[][];
 /** The current actual size of the big list (never greater than the backing-array length). */
 protected long size;
 private static final boolean ASSERTS = false;
 /** Creates a new big-array big list using a given array.
	 *
	 * <P>This constructor is only meant to be used by the wrapping methods.
	 *
	 * @param a the big array that will be used to back this big-array big list.
	 */
 @SuppressWarnings("unused")
 protected CharBigArrayBigList( final char a[][], boolean dummy ) {
  this.a = a;
 }
 /** Creates a new big-array big list with given capacity.
	 *
	 * @param capacity the initial capacity of the array list (may be 0).
	 */
 @SuppressWarnings("unchecked")
 public CharBigArrayBigList( final long capacity ) {
  if ( capacity < 0 ) throw new IllegalArgumentException( "Initial capacity (" + capacity + ") is negative" );
  a = CharBigArrays.newBigArray( capacity );
 }
 /** Creates a new big-array big list with {@link #DEFAULT_INITIAL_CAPACITY} capacity.
	 */
 public CharBigArrayBigList() {
  this( DEFAULT_INITIAL_CAPACITY );
 }
 /** Creates a new big-array big list and fills it with a given type-specific collection.
	 *
	 * @param c a type-specific collection that will be used to fill the array list.
	 */
 public CharBigArrayBigList( final CharCollection c ) {
  this( c.size() );
  for( CharIterator i = c.iterator(); i.hasNext(); ) add( i.nextChar() );
 }
 /** Creates a new big-array big list and fills it with a given type-specific list.
	 *
	 * @param l a type-specific list that will be used to fill the array list.
	 */
 public CharBigArrayBigList( final CharBigList l ) {
  this( l.size64() );
  l.getElements( 0, a, 0, size = l.size64() );
 }
 /** Creates a new big-array big list and fills it with the elements of a given big array.
	 *
	 * <p>Note that this constructor makes it easy to build big lists from literal arrays
	 * declared as <code><var>type</var>[][] {{ <var>init_values</var> }}</code>.
	 * The only constraint is that the number of initialisation values is
	 * below {@link it.unimi.dsi.fastutil.BigArrays#SEGMENT_SIZE}.
	 *
	 * @param a a big array whose elements will be used to fill the array list.
	 */
 public CharBigArrayBigList( final char a[][] ) {
  this( a, 0, CharBigArrays.length( a ) );
 }
 /** Creates a new big-array big list and fills it with the elements of a given big array.
	 *
	 * <p>Note that this constructor makes it easy to build big lists from literal arrays
	 * declared as <code><var>type</var>[][] {{ <var>init_values</var> }}</code>.
	 * The only constraint is that the number of initialisation values is
	 * below {@link it.unimi.dsi.fastutil.BigArrays#SEGMENT_SIZE}.
	 *
	 * @param a a big array whose elements will be used to fill the array list.
	 * @param offset the first element to use.
	 * @param length the number of elements to use.
	 */
 public CharBigArrayBigList( final char a[][], final long offset, final long length ) {
  this( length );
  CharBigArrays.copy( a, offset, this.a, 0, length );
  size = length;
 }
 /** Creates a new big-array big list and fills it with the elements returned by an iterator..
	 *
	 * @param i an iterator whose returned elements will fill the array list.
	 */
 public CharBigArrayBigList( final Iterator<? extends Character> i ) {
  this();
  while( i.hasNext() ) this.add( i.next() );
 }
 /** Creates a new big-array big list and fills it with the elements returned by a type-specific iterator..
	 *
	 * @param i a type-specific iterator whose returned elements will fill the array list.
	 */
 public CharBigArrayBigList( final CharIterator i ) {
  this();
  while( i.hasNext() ) this.add( i.nextChar() );
 }
 /** Returns the backing big array of this big list.
	 *
	 * @return the backing big array.
	 */
 public char[][] elements() {
  return a;
 }
 /** Wraps a given big array into a big-array list of given size.
	 *
	 * @param a a big array to wrap.
	 * @param length the length of the resulting big-array list.
	 * @return a new big-array list of the given size, wrapping the given big array.
	 */
 public static CharBigArrayBigList wrap( final char a[][], final long length ) {
  if ( length > CharBigArrays.length( a ) ) throw new IllegalArgumentException( "The specified length (" + length + ") is greater than the array size (" + CharBigArrays.length( a ) + ")" );
  final CharBigArrayBigList l = new CharBigArrayBigList ( a, false );
  l.size = length;
  return l;
 }
 /** Wraps a given big array into a big-array big list.
	 *
	 * @param a a big array to wrap.
	 * @return a new big-array big list wrapping the given array.
	 */
 public static CharBigArrayBigList wrap( final char a[][] ) {
  return wrap( a, CharBigArrays.length( a ) );
 }
 /** Ensures that this big-array big list can contain the given number of entries without resizing.
	 *
	 * @param capacity the new minimum capacity for this big-array big list.
	 */
 @SuppressWarnings("unchecked")
 public void ensureCapacity( final long capacity ) {
  a = CharBigArrays.ensureCapacity( a, capacity, size );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
 }
 /** Grows this big-array big list, ensuring that it can contain the given number of entries without resizing,
	 * and in case enlarging it at least by a factor of two.
	 *
	 * @param capacity the new minimum capacity for this big-array big list.
	 */
 @SuppressWarnings("unchecked")
 private void grow( final long capacity ) {
  a = CharBigArrays.grow( a, capacity, size );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
 }
 public void add( final long index, final char k ) {
  ensureIndex( index );
  grow( size + 1 );
  if ( index != size ) CharBigArrays.copy( a, index, a, index + 1, size - index );
  CharBigArrays.set( a, index, k );
  size++;
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
 }
 public boolean add( final char k ) {
  grow( size + 1 );
  CharBigArrays.set( a, size++, k );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
  return true;
 }
 public char getChar( final long index ) {
  if ( index >= size ) throw new IndexOutOfBoundsException( "Index (" + index + ") is greater than or equal to list size (" + size + ")" );
  return CharBigArrays.get( a, index );
 }
 public long indexOf( final char k ) {
  for( long i = 0; i < size; i++ ) if ( ( (k) == (CharBigArrays.get( a, i )) ) ) return i;
  return -1;
 }
 public long lastIndexOf( final char k ) {
  for( long i = size; i-- != 0; ) if ( ( (k) == (CharBigArrays.get( a, i )) ) ) return i;
  return -1;
 }
 public char removeChar( final long index ) {
  if ( index >= size ) throw new IndexOutOfBoundsException( "Index (" + index + ") is greater than or equal to list size (" + size + ")" );
  final char old = CharBigArrays.get( a, index );
  size--;
  if ( index != size ) CharBigArrays.copy( a, index + 1, a, index, size - index );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
  return old;
 }
 public boolean rem( final char k ) {
  final long index = indexOf( k );
  if ( index == -1 ) return false;
  removeChar( index );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
  return true;
 }
 public char set( final long index, final char k ) {
  if ( index >= size ) throw new IndexOutOfBoundsException( "Index (" + index + ") is greater than or equal to list size (" + size + ")" );
  char old = CharBigArrays.get( a, index );
  CharBigArrays.set( a, index, k );
  return old;
 }
 public void clear() {
  size = 0;
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
 }
 public long size64() {
  return size;
 }
 public void size( final long size ) {
  if ( size > CharBigArrays.length( a ) ) ensureCapacity( size );
  if ( size > this.size ) CharBigArrays.fill( a, this.size, size, ((char)0) );
  this.size = size;
 }
 public boolean isEmpty() {
  return size == 0;
 }
 /** Trims this big-array big list so that the capacity is equal to the size. 
	 *
	 * @see java.util.ArrayList#trimToSize()
	 */
 public void trim() {
  trim( 0 );
 }
 /** Trims the backing big array if it is too large.
	 * 
	 * If the current big array length is smaller than or equal to
	 * <code>n</code>, this method does nothing. Otherwise, it trims the
	 * big-array length to the maximum between <code>n</code> and {@link #size64()}.
	 *
	 * <P>This method is useful when reusing big lists.  {@linkplain #clear() Clearing a
	 * big list} leaves the big-array length untouched. If you are reusing a big list
	 * many times, you can call this method with a typical
	 * size to avoid keeping around a very large big array just
	 * because of a few large transient big lists.
	 *
	 * @param n the threshold for the trimming.
	 */
 @SuppressWarnings("unchecked")
 public void trim( final long n ) {
  final long arrayLength = CharBigArrays.length( a );
  if ( n >= arrayLength || size == arrayLength ) return;
  a = CharBigArrays.trim( a, Math.max( n, size ) );
  if ( ASSERTS ) assert size <= CharBigArrays.length( a );
 }
    /** Copies element of this type-specific list into the given big array using optimized system calls.
	 *
	 * @param from the start index (inclusive).
	 * @param a the destination big array.
	 * @param offset the offset into the destination array where to store the first element copied.
	 * @param length the number of elements to be copied.
	 */
 public void getElements( final int from, final char[][] a, final long offset, final long length ) {
  CharBigArrays.copy( this.a, from, a, offset, length );
 }
 /** Removes elements of this type-specific list using optimized system calls.
	 *
	 * @param from the start index (inclusive).
	 * @param to the end index (exclusive).
	 */
 public void removeElements( final int from, final int to ) {
  BigArrays.ensureFromTo( size, from, to );
  CharBigArrays.copy( a, to, a, from, size - to );
  size -= ( to - from );
 }
 /** Adds elements to this type-specific list using optimized system calls.
	 *
	 * @param index the index at which to add elements.
	 * @param a the big array containing the elements.
	 * @param offset the offset of the first element to add.
	 * @param length the number of elements to add.
	 */
 public void addElements( final int index, final char a[][], final long offset, final long length ) {
  ensureIndex( index );
  CharBigArrays.ensureOffsetLength( a, offset, length );
  grow( size + length );
  CharBigArrays.copy( this.a, index, this.a, index + length, size - index );
  CharBigArrays.copy( a, offset, this.a, index, length );
  size += length;
 }
 public CharBigListIterator listIterator( final int index ) {
  ensureIndex( index );
  return new AbstractCharBigListIterator () {
    int pos = index, last = -1;
    public boolean hasNext() { return pos < size; }
    public boolean hasPrevious() { return pos > 0; }
    public char nextChar() { if ( ! hasNext() ) throw new NoSuchElementException(); return CharBigArrays.get( a, last = pos++ ); }
    public char previousChar() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return CharBigArrays.get( a, last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( char k ) {
     if ( last == -1 ) throw new IllegalStateException();
     CharBigArrayBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( char k ) {
     if ( last == -1 ) throw new IllegalStateException();
     CharBigArrayBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     CharBigArrayBigList.this.removeChar( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }
 @SuppressWarnings("unchecked")
 public CharBigArrayBigList clone() {
  CharBigArrayBigList c = new CharBigArrayBigList ( size );
  CharBigArrays.copy( a, 0, c.a, 0, size );
  c.size = size;
  return c;
 }
    /** Compares this type-specific big-array list to another one.
	 *
	 * <P>This method exists only for sake of efficiency. The implementation
	 * inherited from the abstract implementation would already work.
	 *
	 * @param l a type-specific big-array list.
     * @return true if the argument contains the same elements of this type-specific big-array list.
	 */
 public boolean equals( final CharBigArrayBigList l ) {
  if ( l == this ) return true;
  long s = size64();
  if ( s != l.size64() ) return false;
  final char[][] a1 = a;
  final char[][] a2 = l.a;
  while( s-- != 0 ) if ( CharBigArrays.get( a1, s ) != CharBigArrays.get( a2, s ) ) return false;
  return true;
 }
    /** Compares this big list to another big list.
     *
	 * <P>This method exists only for sake of efficiency. The implementation
	 * inherited from the abstract implementation would already work.
	 *
     * @param l a big list.
     * @return a negative integer,
     * zero, or a positive integer as this big list is lexicographically less than, equal
     * to, or greater than the argument.
     */
 @SuppressWarnings("unchecked")
 public int compareTo( final CharBigArrayBigList l ) {
  final long s1 = size64(), s2 = l.size64();
  final char a1[][] = a, a2[][] = l.a;
  char e1, e2;
  int r, i;
  for( i = 0; i < s1 && i < s2; i++ ) {
   e1 = CharBigArrays.get( a1, i );
   e2 = CharBigArrays.get( a2, i );
   if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
  }
  return i < s2 ? -1 : ( i < s1 ? 1 : 0 );
 }
 private void writeObject( java.io.ObjectOutputStream s ) throws java.io.IOException {
  s.defaultWriteObject();
  for( int i = 0; i < size; i++ ) s.writeChar( CharBigArrays.get( a, i ) );
 }
 @SuppressWarnings("unchecked")
 private void readObject( java.io.ObjectInputStream s ) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  a = CharBigArrays.newBigArray( size );
  for( int i = 0; i < size; i++ ) CharBigArrays.set( a, i, s.readChar() );
 }
}
