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
package it.unimi.dsi.fastutil.floats;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractFloatBigList extends AbstractFloatCollection implements FloatBigList , FloatStack {
 protected AbstractFloatBigList() {}
 /** Ensures that the given index is nonnegative and not greater than this big-list size.
	 *
	 * @param index an index.
	 * @throws IndexOutOfBoundsException if the given index is negative or greater than this big-list size.
	 */
 protected void ensureIndex( final long index ) {
  if ( index < 0 ) throw new IndexOutOfBoundsException( "Index (" + index + ") is negative" );
  if ( index > size64() ) throw new IndexOutOfBoundsException( "Index (" + index + ") is greater than list size (" + ( size64() ) + ")" );
 }
 /** Ensures that the given index is nonnegative and smaller than this big-list size.
	 *
	 * @param index an index.
	 * @throws IndexOutOfBoundsException if the given index is negative or not smaller than this big-list size.
	 */
 protected void ensureRestrictedIndex( final long index ) {
  if ( index < 0 ) throw new IndexOutOfBoundsException( "Index (" + index + ") is negative" );
  if ( index >= size64() ) throw new IndexOutOfBoundsException( "Index (" + index + ") is greater than or equal to list size (" + ( size64() ) + ")" );
 }
 public void add( final long index, final float k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final float k ) {
  add( size64(), k );
  return true;
 }
 public float removeFloat( long i ) {
  throw new UnsupportedOperationException();
 }
 public float removeFloat( int i ) {
  return removeFloat( (long)i );
 }
 public float set( final long index, final float k ) {
  throw new UnsupportedOperationException();
 }
 public float set( final int index, final float k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Float> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Float> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Float> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Float> c ) {
  return addAll( size64(), c );
 }
 public FloatBigListIterator iterator() {
  return listIterator();
 }
 public FloatBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public FloatBigListIterator listIterator( final long index ) {
  return new AbstractFloatBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractFloatBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public float nextFloat() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractFloatBigList.this.getFloat( last = pos++ ); }
    public float previousFloat() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractFloatBigList.this.getFloat( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( float k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractFloatBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( float k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractFloatBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractFloatBigList.this.removeFloat( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public FloatBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final float k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final float k ) {
  final FloatBigListIterator i = listIterator();
  float e;
  while( i.hasNext() ) {
   e = i.nextFloat();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final float k ) {
  FloatBigListIterator i = listIterator( size64() );
  float e;
  while( i.hasPrevious() ) {
   e = i.previousFloat();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((float)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public FloatBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new FloatSubList ( this, from, to );
 }

 /** Removes elements of this type-specific big list one-by-one. 
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 *
	 * @param from the start index (inclusive).
	 * @param to the end index (exclusive).
	 */

 public void removeElements( final long from, final long to ) {
  ensureIndex( to );
  FloatBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextFloat();
   i.remove();
  }
 }

 /** Adds elements to this type-specific big list one-by-one. 
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 *
	 * @param index the index at which to add elements.
	 * @param a the big array containing the elements.
	 * @param offset the offset of the first element to add.
	 * @param length the number of elements to add.
	 */

 public void addElements( long index, final float a[][], long offset, long length ) {
  ensureIndex( index );
  FloatBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, FloatBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final float a[][] ) {
  addElements( index, a, 0, FloatBigArrays.length( a ) );
 }

 /** Copies element of this type-specific big list into the given big array one-by-one.
	 *
	 * <P>This is a trivial iterator-based implementation. It is expected that
	 * implementations will override this method with a more optimized version.
	 *
	 * @param from the start index (inclusive).
	 * @param a the destination big array.
	 * @param offset the offset into the destination big array where to store the first element copied.
	 * @param length the number of elements to be copied.
	 */

 public void getElements( final long from, final float a[][], long offset, long length ) {
  FloatBigListIterator i = listIterator( from );
  FloatBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) FloatBigArrays.set( a, offset++, i.nextFloat() );
 }

 @Deprecated
 public int size() {
  return (int)Math.min( Integer.MAX_VALUE, size64() );
 }


 private boolean valEquals( final Object a, final Object b ) {
  return a == null ? b == null : a.equals( b );
 }


 @SuppressWarnings("unchecked")
 public boolean equals( final Object o ) {
  if ( o == this ) return true;
  if ( ! ( o instanceof BigList ) ) return false;
  final BigList<?> l = (BigList<?>)o;
  long s = size64();
  if ( s != l.size64() ) return false;

  final BigListIterator<?> i1 = listIterator(), i2 = l.listIterator();




  while( s-- != 0 ) if ( ! valEquals( i1.next(), i2.next() ) ) return false;

  return true;
 }


    /** Compares this big list to another object. If the
     * argument is a {@link BigList}, this method performs a lexicographical comparison; otherwise,
     * it throws a <code>ClassCastException</code>.
     *
     * @param l a big list.
     * @return if the argument is a {@link BigList}, a negative integer,
     * zero, or a positive integer as this list is lexicographically less than, equal
     * to, or greater than the argument.
     * @throws ClassCastException if the argument is not a big list.
     */

 @SuppressWarnings("unchecked")
 public int compareTo( final BigList<? extends Float> l ) {
  if ( l == this ) return 0;

  if ( l instanceof FloatBigList ) {

   final FloatBigListIterator i1 = listIterator(), i2 = ((FloatBigList )l).listIterator();
   int r;
   float e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextFloat();
    e2 = i2.nextFloat();
    if ( ( r = ( Float.compare((e1),(e2)) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Float> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Float>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  FloatIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   float k = i.nextFloat();
   h = 31 * h + it.unimi.dsi.fastutil.HashCommon.float2int(k);
  }
  return h;
 }

 public void push( float o ) {
  add( o );
 }

 public float popFloat() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeFloat( size64() - 1 );
 }

 public float topFloat() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getFloat( size64() - 1 );
 }

 public float peekFloat( int i ) {
  return getFloat( size64() - 1 - i );
 }



 public float getFloat( final int index ) {
  return getFloat( (long)index );
 }

 public boolean rem( float k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeFloat( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final FloatCollection c ) {
  return addAll( index, (Collection<? extends Float>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final FloatBigList l ) {
  return addAll( index, (FloatCollection)l );
 }

 public boolean addAll( final FloatCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final FloatBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Float ok ) {
  add( index, ok.floatValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Float set( final long index, final Float ok ) {
  return (Float.valueOf(set( index, ok.floatValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Float get( final long index ) {
  return (Float.valueOf(getFloat( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Float)(ok)).floatValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Float)(ok)).floatValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Float remove( final int index ) {
  return (Float.valueOf(removeFloat( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Float remove( final long index ) {
  return (Float.valueOf(removeFloat( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Float o ) {
  push( o.floatValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Float pop() {
  return Float.valueOf( popFloat() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Float top() {
  return Float.valueOf( topFloat() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Float peek( int i ) {
  return Float.valueOf( peekFloat( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final FloatIterator i = iterator();
  long n = size64();
  float k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextFloat();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class FloatSubList extends AbstractFloatBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final FloatBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public FloatSubList( final FloatBigList l, final long from, final long to ) {
   this.l = l;
   this.from = from;
   this.to = to;
  }
  private void assertRange() {
   if ( ASSERTS ) {
    assert from <= l.size64();
    assert to <= l.size64();
    assert to >= from;
   }
  }
  public boolean add( final float k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final float k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Float> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public float getFloat( long index ) {
   ensureRestrictedIndex( index );
   return l.getFloat( from + index );
  }
  public float removeFloat( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeFloat( from + index );
  }
  public float set( long index, float k ) {
   ensureRestrictedIndex( index );
   return l.set( from + index, k );
  }
  public void clear() {
   removeElements( 0, size64() );
   if ( ASSERTS ) assertRange();
  }
  public long size64() {
   return to - from;
  }
  public void getElements( final long from, final float[][] a, final long offset, final long length ) {
   ensureIndex( from );
   if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + from + length + ") is greater than list size (" + size64() + ")" );
   l.getElements( this.from + from, a, offset, length );
  }
  public void removeElements( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   l.removeElements( this.from + from, this.from + to );
   this.to -= ( to - from );
   if ( ASSERTS ) assertRange();
  }
  public void addElements( final long index, final float a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public FloatBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractFloatBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public float nextFloat() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getFloat( from + ( last = pos++ ) ); }
     public float previousFloat() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getFloat( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( float k ) {
      if ( last == -1 ) throw new IllegalStateException();
      FloatSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( float k ) {
      if ( last == -1 ) throw new IllegalStateException();
      FloatSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      FloatSubList.this.removeFloat( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public FloatBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new FloatSubList ( this, from, to );
  }
  public boolean rem( float k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeFloat( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Float)(o)).floatValue())) );
  }
  public boolean addAll( final long index, final FloatCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final FloatList l ) {
   ensureIndex( index );
   to += l.size();
   if ( ASSERTS ) {
    boolean retVal = this.l.addAll( from + index, l );
    assertRange();
    return retVal;
   }
   return this.l.addAll( from + index, l );
  }
 }
}
