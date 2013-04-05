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
package it.unimi.dsi.fastutil.shorts;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractShortBigList extends AbstractShortCollection implements ShortBigList , ShortStack {
 protected AbstractShortBigList() {}
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
 public void add( final long index, final short k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final short k ) {
  add( size64(), k );
  return true;
 }
 public short removeShort( long i ) {
  throw new UnsupportedOperationException();
 }
 public short removeShort( int i ) {
  return removeShort( (long)i );
 }
 public short set( final long index, final short k ) {
  throw new UnsupportedOperationException();
 }
 public short set( final int index, final short k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Short> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Short> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Short> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Short> c ) {
  return addAll( size64(), c );
 }
 public ShortBigListIterator iterator() {
  return listIterator();
 }
 public ShortBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public ShortBigListIterator listIterator( final long index ) {
  return new AbstractShortBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractShortBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public short nextShort() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractShortBigList.this.getShort( last = pos++ ); }
    public short previousShort() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractShortBigList.this.getShort( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( short k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractShortBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( short k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractShortBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractShortBigList.this.removeShort( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public ShortBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final short k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final short k ) {
  final ShortBigListIterator i = listIterator();
  short e;
  while( i.hasNext() ) {
   e = i.nextShort();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final short k ) {
  ShortBigListIterator i = listIterator( size64() );
  short e;
  while( i.hasPrevious() ) {
   e = i.previousShort();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((short)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public ShortBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new ShortSubList ( this, from, to );
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
  ShortBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextShort();
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

 public void addElements( long index, final short a[][], long offset, long length ) {
  ensureIndex( index );
  ShortBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, ShortBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final short a[][] ) {
  addElements( index, a, 0, ShortBigArrays.length( a ) );
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

 public void getElements( final long from, final short a[][], long offset, long length ) {
  ShortBigListIterator i = listIterator( from );
  ShortBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) ShortBigArrays.set( a, offset++, i.nextShort() );
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
 public int compareTo( final BigList<? extends Short> l ) {
  if ( l == this ) return 0;

  if ( l instanceof ShortBigList ) {

   final ShortBigListIterator i1 = listIterator(), i2 = ((ShortBigList )l).listIterator();
   int r;
   short e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextShort();
    e2 = i2.nextShort();
    if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Short> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Short>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  ShortIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   short k = i.nextShort();
   h = 31 * h + (k);
  }
  return h;
 }

 public void push( short o ) {
  add( o );
 }

 public short popShort() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeShort( size64() - 1 );
 }

 public short topShort() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getShort( size64() - 1 );
 }

 public short peekShort( int i ) {
  return getShort( size64() - 1 - i );
 }



 public short getShort( final int index ) {
  return getShort( (long)index );
 }

 public boolean rem( short k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeShort( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final ShortCollection c ) {
  return addAll( index, (Collection<? extends Short>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final ShortBigList l ) {
  return addAll( index, (ShortCollection)l );
 }

 public boolean addAll( final ShortCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final ShortBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Short ok ) {
  add( index, ok.shortValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Short set( final long index, final Short ok ) {
  return (Short.valueOf(set( index, ok.shortValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Short get( final long index ) {
  return (Short.valueOf(getShort( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Short)(ok)).shortValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Short)(ok)).shortValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Short remove( final int index ) {
  return (Short.valueOf(removeShort( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Short remove( final long index ) {
  return (Short.valueOf(removeShort( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Short o ) {
  push( o.shortValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Short pop() {
  return Short.valueOf( popShort() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Short top() {
  return Short.valueOf( topShort() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Short peek( int i ) {
  return Short.valueOf( peekShort( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final ShortIterator i = iterator();
  long n = size64();
  short k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextShort();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class ShortSubList extends AbstractShortBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final ShortBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public ShortSubList( final ShortBigList l, final long from, final long to ) {
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
  public boolean add( final short k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final short k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Short> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public short getShort( long index ) {
   ensureRestrictedIndex( index );
   return l.getShort( from + index );
  }
  public short removeShort( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeShort( from + index );
  }
  public short set( long index, short k ) {
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
  public void getElements( final long from, final short[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final short a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public ShortBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractShortBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public short nextShort() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getShort( from + ( last = pos++ ) ); }
     public short previousShort() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getShort( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( short k ) {
      if ( last == -1 ) throw new IllegalStateException();
      ShortSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( short k ) {
      if ( last == -1 ) throw new IllegalStateException();
      ShortSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      ShortSubList.this.removeShort( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public ShortBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new ShortSubList ( this, from, to );
  }
  public boolean rem( short k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeShort( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Short)(o)).shortValue())) );
  }
  public boolean addAll( final long index, final ShortCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final ShortList l ) {
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
