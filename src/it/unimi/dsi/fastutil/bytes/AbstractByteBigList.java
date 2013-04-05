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
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractByteBigList extends AbstractByteCollection implements ByteBigList , ByteStack {
 protected AbstractByteBigList() {}
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
 public void add( final long index, final byte k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final byte k ) {
  add( size64(), k );
  return true;
 }
 public byte removeByte( long i ) {
  throw new UnsupportedOperationException();
 }
 public byte removeByte( int i ) {
  return removeByte( (long)i );
 }
 public byte set( final long index, final byte k ) {
  throw new UnsupportedOperationException();
 }
 public byte set( final int index, final byte k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Byte> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Byte> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Byte> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Byte> c ) {
  return addAll( size64(), c );
 }
 public ByteBigListIterator iterator() {
  return listIterator();
 }
 public ByteBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public ByteBigListIterator listIterator( final long index ) {
  return new AbstractByteBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractByteBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public byte nextByte() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractByteBigList.this.getByte( last = pos++ ); }
    public byte previousByte() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractByteBigList.this.getByte( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( byte k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractByteBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( byte k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractByteBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractByteBigList.this.removeByte( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public ByteBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final byte k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final byte k ) {
  final ByteBigListIterator i = listIterator();
  byte e;
  while( i.hasNext() ) {
   e = i.nextByte();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final byte k ) {
  ByteBigListIterator i = listIterator( size64() );
  byte e;
  while( i.hasPrevious() ) {
   e = i.previousByte();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((byte)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public ByteBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new ByteSubList ( this, from, to );
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
  ByteBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextByte();
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

 public void addElements( long index, final byte a[][], long offset, long length ) {
  ensureIndex( index );
  ByteBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, ByteBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final byte a[][] ) {
  addElements( index, a, 0, ByteBigArrays.length( a ) );
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

 public void getElements( final long from, final byte a[][], long offset, long length ) {
  ByteBigListIterator i = listIterator( from );
  ByteBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) ByteBigArrays.set( a, offset++, i.nextByte() );
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
 public int compareTo( final BigList<? extends Byte> l ) {
  if ( l == this ) return 0;

  if ( l instanceof ByteBigList ) {

   final ByteBigListIterator i1 = listIterator(), i2 = ((ByteBigList )l).listIterator();
   int r;
   byte e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextByte();
    e2 = i2.nextByte();
    if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Byte> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Byte>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  ByteIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   byte k = i.nextByte();
   h = 31 * h + (k);
  }
  return h;
 }

 public void push( byte o ) {
  add( o );
 }

 public byte popByte() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeByte( size64() - 1 );
 }

 public byte topByte() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getByte( size64() - 1 );
 }

 public byte peekByte( int i ) {
  return getByte( size64() - 1 - i );
 }



 public byte getByte( final int index ) {
  return getByte( (long)index );
 }

 public boolean rem( byte k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeByte( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final ByteCollection c ) {
  return addAll( index, (Collection<? extends Byte>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final ByteBigList l ) {
  return addAll( index, (ByteCollection)l );
 }

 public boolean addAll( final ByteCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final ByteBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Byte ok ) {
  add( index, ok.byteValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte set( final long index, final Byte ok ) {
  return (Byte.valueOf(set( index, ok.byteValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte get( final long index ) {
  return (Byte.valueOf(getByte( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Byte)(ok)).byteValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Byte)(ok)).byteValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte remove( final int index ) {
  return (Byte.valueOf(removeByte( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte remove( final long index ) {
  return (Byte.valueOf(removeByte( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Byte o ) {
  push( o.byteValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte pop() {
  return Byte.valueOf( popByte() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte top() {
  return Byte.valueOf( topByte() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Byte peek( int i ) {
  return Byte.valueOf( peekByte( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final ByteIterator i = iterator();
  long n = size64();
  byte k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextByte();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class ByteSubList extends AbstractByteBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final ByteBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public ByteSubList( final ByteBigList l, final long from, final long to ) {
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
  public boolean add( final byte k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final byte k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Byte> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public byte getByte( long index ) {
   ensureRestrictedIndex( index );
   return l.getByte( from + index );
  }
  public byte removeByte( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeByte( from + index );
  }
  public byte set( long index, byte k ) {
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
  public void getElements( final long from, final byte[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final byte a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public ByteBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractByteBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public byte nextByte() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getByte( from + ( last = pos++ ) ); }
     public byte previousByte() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getByte( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( byte k ) {
      if ( last == -1 ) throw new IllegalStateException();
      ByteSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( byte k ) {
      if ( last == -1 ) throw new IllegalStateException();
      ByteSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      ByteSubList.this.removeByte( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public ByteBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new ByteSubList ( this, from, to );
  }
  public boolean rem( byte k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeByte( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Byte)(o)).byteValue())) );
  }
  public boolean addAll( final long index, final ByteCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final ByteList l ) {
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
