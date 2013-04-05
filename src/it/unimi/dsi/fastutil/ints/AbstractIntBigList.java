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
package it.unimi.dsi.fastutil.ints;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractIntBigList extends AbstractIntCollection implements IntBigList , IntStack {
 protected AbstractIntBigList() {}
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
 public void add( final long index, final int k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final int k ) {
  add( size64(), k );
  return true;
 }
 public int removeInt( long i ) {
  throw new UnsupportedOperationException();
 }
 public int removeInt( int i ) {
  return removeInt( (long)i );
 }
 public int set( final long index, final int k ) {
  throw new UnsupportedOperationException();
 }
 public int set( final int index, final int k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Integer> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Integer> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Integer> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Integer> c ) {
  return addAll( size64(), c );
 }
 public IntBigListIterator iterator() {
  return listIterator();
 }
 public IntBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public IntBigListIterator listIterator( final long index ) {
  return new AbstractIntBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractIntBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public int nextInt() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractIntBigList.this.getInt( last = pos++ ); }
    public int previousInt() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractIntBigList.this.getInt( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( int k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractIntBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( int k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractIntBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractIntBigList.this.removeInt( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public IntBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final int k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final int k ) {
  final IntBigListIterator i = listIterator();
  int e;
  while( i.hasNext() ) {
   e = i.nextInt();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final int k ) {
  IntBigListIterator i = listIterator( size64() );
  int e;
  while( i.hasPrevious() ) {
   e = i.previousInt();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((int)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public IntBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new IntSubList ( this, from, to );
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
  IntBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextInt();
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

 public void addElements( long index, final int a[][], long offset, long length ) {
  ensureIndex( index );
  IntBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, IntBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final int a[][] ) {
  addElements( index, a, 0, IntBigArrays.length( a ) );
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

 public void getElements( final long from, final int a[][], long offset, long length ) {
  IntBigListIterator i = listIterator( from );
  IntBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) IntBigArrays.set( a, offset++, i.nextInt() );
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
 public int compareTo( final BigList<? extends Integer> l ) {
  if ( l == this ) return 0;

  if ( l instanceof IntBigList ) {

   final IntBigListIterator i1 = listIterator(), i2 = ((IntBigList )l).listIterator();
   int r;
   int e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextInt();
    e2 = i2.nextInt();
    if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Integer> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Integer>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  IntIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   int k = i.nextInt();
   h = 31 * h + (k);
  }
  return h;
 }

 public void push( int o ) {
  add( o );
 }

 public int popInt() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeInt( size64() - 1 );
 }

 public int topInt() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getInt( size64() - 1 );
 }

 public int peekInt( int i ) {
  return getInt( size64() - 1 - i );
 }



 public int getInt( final int index ) {
  return getInt( (long)index );
 }

 public boolean rem( int k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeInt( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final IntCollection c ) {
  return addAll( index, (Collection<? extends Integer>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final IntBigList l ) {
  return addAll( index, (IntCollection)l );
 }

 public boolean addAll( final IntCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final IntBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Integer ok ) {
  add( index, ok.intValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer set( final long index, final Integer ok ) {
  return (Integer.valueOf(set( index, ok.intValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer get( final long index ) {
  return (Integer.valueOf(getInt( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Integer)(ok)).intValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Integer)(ok)).intValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer remove( final int index ) {
  return (Integer.valueOf(removeInt( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer remove( final long index ) {
  return (Integer.valueOf(removeInt( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Integer o ) {
  push( o.intValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer pop() {
  return Integer.valueOf( popInt() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer top() {
  return Integer.valueOf( topInt() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Integer peek( int i ) {
  return Integer.valueOf( peekInt( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final IntIterator i = iterator();
  long n = size64();
  int k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextInt();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class IntSubList extends AbstractIntBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final IntBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public IntSubList( final IntBigList l, final long from, final long to ) {
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
  public boolean add( final int k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final int k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Integer> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public int getInt( long index ) {
   ensureRestrictedIndex( index );
   return l.getInt( from + index );
  }
  public int removeInt( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeInt( from + index );
  }
  public int set( long index, int k ) {
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
  public void getElements( final long from, final int[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final int a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public IntBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractIntBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public int nextInt() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getInt( from + ( last = pos++ ) ); }
     public int previousInt() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getInt( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( int k ) {
      if ( last == -1 ) throw new IllegalStateException();
      IntSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( int k ) {
      if ( last == -1 ) throw new IllegalStateException();
      IntSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      IntSubList.this.removeInt( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public IntBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new IntSubList ( this, from, to );
  }
  public boolean rem( int k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeInt( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Integer)(o)).intValue())) );
  }
  public boolean addAll( final long index, final IntCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final IntList l ) {
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
