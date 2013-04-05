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
package it.unimi.dsi.fastutil.booleans;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractBooleanBigList extends AbstractBooleanCollection implements BooleanBigList , BooleanStack {
 protected AbstractBooleanBigList() {}
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
 public void add( final long index, final boolean k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final boolean k ) {
  add( size64(), k );
  return true;
 }
 public boolean removeBoolean( long i ) {
  throw new UnsupportedOperationException();
 }
 public boolean removeBoolean( int i ) {
  return removeBoolean( (long)i );
 }
 public boolean set( final long index, final boolean k ) {
  throw new UnsupportedOperationException();
 }
 public boolean set( final int index, final boolean k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Boolean> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Boolean> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Boolean> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Boolean> c ) {
  return addAll( size64(), c );
 }
 public BooleanBigListIterator iterator() {
  return listIterator();
 }
 public BooleanBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public BooleanBigListIterator listIterator( final long index ) {
  return new AbstractBooleanBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractBooleanBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public boolean nextBoolean() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractBooleanBigList.this.getBoolean( last = pos++ ); }
    public boolean previousBoolean() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractBooleanBigList.this.getBoolean( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( boolean k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractBooleanBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( boolean k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractBooleanBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractBooleanBigList.this.removeBoolean( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public BooleanBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final boolean k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final boolean k ) {
  final BooleanBigListIterator i = listIterator();
  boolean e;
  while( i.hasNext() ) {
   e = i.nextBoolean();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final boolean k ) {
  BooleanBigListIterator i = listIterator( size64() );
  boolean e;
  while( i.hasPrevious() ) {
   e = i.previousBoolean();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( (false) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public BooleanBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new BooleanSubList ( this, from, to );
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
  BooleanBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextBoolean();
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

 public void addElements( long index, final boolean a[][], long offset, long length ) {
  ensureIndex( index );
  BooleanBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, BooleanBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final boolean a[][] ) {
  addElements( index, a, 0, BooleanBigArrays.length( a ) );
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

 public void getElements( final long from, final boolean a[][], long offset, long length ) {
  BooleanBigListIterator i = listIterator( from );
  BooleanBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) BooleanBigArrays.set( a, offset++, i.nextBoolean() );
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
 public int compareTo( final BigList<? extends Boolean> l ) {
  if ( l == this ) return 0;

  if ( l instanceof BooleanBigList ) {

   final BooleanBigListIterator i1 = listIterator(), i2 = ((BooleanBigList )l).listIterator();
   int r;
   boolean e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextBoolean();
    e2 = i2.nextBoolean();
    if ( ( r = ( !(e1) && (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Boolean> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Boolean>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  BooleanIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   boolean k = i.nextBoolean();
   h = 31 * h + ((k) ? 1231 : 1237);
  }
  return h;
 }

 public void push( boolean o ) {
  add( o );
 }

 public boolean popBoolean() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeBoolean( size64() - 1 );
 }

 public boolean topBoolean() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getBoolean( size64() - 1 );
 }

 public boolean peekBoolean( int i ) {
  return getBoolean( size64() - 1 - i );
 }



 public boolean getBoolean( final int index ) {
  return getBoolean( (long)index );
 }

 public boolean rem( boolean k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeBoolean( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final BooleanCollection c ) {
  return addAll( index, (Collection<? extends Boolean>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final BooleanBigList l ) {
  return addAll( index, (BooleanCollection)l );
 }

 public boolean addAll( final BooleanCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final BooleanBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Boolean ok ) {
  add( index, ok.booleanValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean set( final long index, final Boolean ok ) {
  return (Boolean.valueOf(set( index, ok.booleanValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean get( final long index ) {
  return (Boolean.valueOf(getBoolean( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Boolean)(ok)).booleanValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Boolean)(ok)).booleanValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean remove( final int index ) {
  return (Boolean.valueOf(removeBoolean( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean remove( final long index ) {
  return (Boolean.valueOf(removeBoolean( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Boolean o ) {
  push( o.booleanValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean pop() {
  return Boolean.valueOf( popBoolean() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean top() {
  return Boolean.valueOf( topBoolean() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Boolean peek( int i ) {
  return Boolean.valueOf( peekBoolean( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final BooleanIterator i = iterator();
  long n = size64();
  boolean k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextBoolean();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class BooleanSubList extends AbstractBooleanBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final BooleanBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public BooleanSubList( final BooleanBigList l, final long from, final long to ) {
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
  public boolean add( final boolean k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final boolean k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Boolean> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean getBoolean( long index ) {
   ensureRestrictedIndex( index );
   return l.getBoolean( from + index );
  }
  public boolean removeBoolean( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeBoolean( from + index );
  }
  public boolean set( long index, boolean k ) {
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
  public void getElements( final long from, final boolean[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final boolean a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public BooleanBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractBooleanBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public boolean nextBoolean() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getBoolean( from + ( last = pos++ ) ); }
     public boolean previousBoolean() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getBoolean( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( boolean k ) {
      if ( last == -1 ) throw new IllegalStateException();
      BooleanSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( boolean k ) {
      if ( last == -1 ) throw new IllegalStateException();
      BooleanSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      BooleanSubList.this.removeBoolean( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public BooleanBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new BooleanSubList ( this, from, to );
  }
  public boolean rem( boolean k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeBoolean( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Boolean)(o)).booleanValue())) );
  }
  public boolean addAll( final long index, final BooleanCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final BooleanList l ) {
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
