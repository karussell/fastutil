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
package it.unimi.dsi.fastutil.longs;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractLongBigList extends AbstractLongCollection implements LongBigList , LongStack {
 protected AbstractLongBigList() {}
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
 public void add( final long index, final long k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final long k ) {
  add( size64(), k );
  return true;
 }
 public long removeLong( long i ) {
  throw new UnsupportedOperationException();
 }
 public long removeLong( int i ) {
  return removeLong( (long)i );
 }
 public long set( final long index, final long k ) {
  throw new UnsupportedOperationException();
 }
 public long set( final int index, final long k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Long> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Long> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Long> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Long> c ) {
  return addAll( size64(), c );
 }
 public LongBigListIterator iterator() {
  return listIterator();
 }
 public LongBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public LongBigListIterator listIterator( final long index ) {
  return new AbstractLongBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractLongBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public long nextLong() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractLongBigList.this.getLong( last = pos++ ); }
    public long previousLong() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractLongBigList.this.getLong( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( long k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractLongBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( long k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractLongBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractLongBigList.this.removeLong( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public LongBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final long k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final long k ) {
  final LongBigListIterator i = listIterator();
  long e;
  while( i.hasNext() ) {
   e = i.nextLong();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final long k ) {
  LongBigListIterator i = listIterator( size64() );
  long e;
  while( i.hasPrevious() ) {
   e = i.previousLong();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((long)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public LongBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new LongSubList ( this, from, to );
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
  LongBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextLong();
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

 public void addElements( long index, final long a[][], long offset, long length ) {
  ensureIndex( index );
  LongBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, LongBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final long a[][] ) {
  addElements( index, a, 0, LongBigArrays.length( a ) );
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

 public void getElements( final long from, final long a[][], long offset, long length ) {
  LongBigListIterator i = listIterator( from );
  LongBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) LongBigArrays.set( a, offset++, i.nextLong() );
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
 public int compareTo( final BigList<? extends Long> l ) {
  if ( l == this ) return 0;

  if ( l instanceof LongBigList ) {

   final LongBigListIterator i1 = listIterator(), i2 = ((LongBigList )l).listIterator();
   int r;
   long e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextLong();
    e2 = i2.nextLong();
    if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Long> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Long>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  LongIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   long k = i.nextLong();
   h = 31 * h + it.unimi.dsi.fastutil.HashCommon.long2int(k);
  }
  return h;
 }

 public void push( long o ) {
  add( o );
 }

 public long popLong() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeLong( size64() - 1 );
 }

 public long topLong() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getLong( size64() - 1 );
 }

 public long peekLong( int i ) {
  return getLong( size64() - 1 - i );
 }



 public long getLong( final int index ) {
  return getLong( (long)index );
 }

 public boolean rem( long k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeLong( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final LongCollection c ) {
  return addAll( index, (Collection<? extends Long>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final LongBigList l ) {
  return addAll( index, (LongCollection)l );
 }

 public boolean addAll( final LongCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final LongBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Long ok ) {
  add( index, ok.longValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Long set( final long index, final Long ok ) {
  return (Long.valueOf(set( index, ok.longValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Long get( final long index ) {
  return (Long.valueOf(getLong( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Long)(ok)).longValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Long)(ok)).longValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Long remove( final int index ) {
  return (Long.valueOf(removeLong( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Long remove( final long index ) {
  return (Long.valueOf(removeLong( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Long o ) {
  push( o.longValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Long pop() {
  return Long.valueOf( popLong() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Long top() {
  return Long.valueOf( topLong() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Long peek( int i ) {
  return Long.valueOf( peekLong( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final LongIterator i = iterator();
  long n = size64();
  long k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextLong();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class LongSubList extends AbstractLongBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final LongBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public LongSubList( final LongBigList l, final long from, final long to ) {
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
  public boolean add( final long k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final long k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Long> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public long getLong( long index ) {
   ensureRestrictedIndex( index );
   return l.getLong( from + index );
  }
  public long removeLong( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeLong( from + index );
  }
  public long set( long index, long k ) {
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
  public void getElements( final long from, final long[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final long a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public LongBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractLongBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public long nextLong() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getLong( from + ( last = pos++ ) ); }
     public long previousLong() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getLong( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( long k ) {
      if ( last == -1 ) throw new IllegalStateException();
      LongSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( long k ) {
      if ( last == -1 ) throw new IllegalStateException();
      LongSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      LongSubList.this.removeLong( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public LongBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new LongSubList ( this, from, to );
  }
  public boolean rem( long k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeLong( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Long)(o)).longValue())) );
  }
  public boolean addAll( final long index, final LongCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final LongList l ) {
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
