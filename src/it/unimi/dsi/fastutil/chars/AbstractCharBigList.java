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
package it.unimi.dsi.fastutil.chars;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.BigList;
import it.unimi.dsi.fastutil.BigListIterator;
/**  An abstract class providing basic methods for big lists implementing a type-specific big list interface. */
public abstract class AbstractCharBigList extends AbstractCharCollection implements CharBigList , CharStack {
 protected AbstractCharBigList() {}
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
 public void add( final long index, final char k ) {
  throw new UnsupportedOperationException();
 }
 public boolean add( final char k ) {
  add( size64(), k );
  return true;
 }
 public char removeChar( long i ) {
  throw new UnsupportedOperationException();
 }
 public char removeChar( int i ) {
  return removeChar( (long)i );
 }
 public char set( final long index, final char k ) {
  throw new UnsupportedOperationException();
 }
 public char set( final int index, final char k ) {
  return set( (long)index, k );
 }
 public boolean addAll( long index, final Collection<? extends Character> c ) {
  ensureIndex( index );
  int n = c.size();
  if ( n == 0 ) return false;
  Iterator<? extends Character> i = c.iterator();
  while( n-- != 0 ) add( index++, i.next() );
  return true;
 }
 public boolean addAll( int index, final Collection<? extends Character> c ) {
  return addAll( (long)index, c );
 }
 /** Delegates to a more generic method. */
 public boolean addAll( final Collection<? extends Character> c ) {
  return addAll( size64(), c );
 }
 public CharBigListIterator iterator() {
  return listIterator();
 }
 public CharBigListIterator listIterator() {
  return listIterator( 0L );
 }
 public CharBigListIterator listIterator( final long index ) {
  return new AbstractCharBigListIterator () {
    long pos = index, last = -1;
    public boolean hasNext() { return pos < AbstractCharBigList.this.size64(); }
    public boolean hasPrevious() { return pos > 0; }
    public char nextChar() { if ( ! hasNext() ) throw new NoSuchElementException(); return AbstractCharBigList.this.getChar( last = pos++ ); }
    public char previousChar() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return AbstractCharBigList.this.getChar( last = --pos ); }
    public long nextIndex() { return pos; }
    public long previousIndex() { return pos - 1; }
    public void add( char k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractCharBigList.this.add( pos++, k );
     last = -1;
    }
    public void set( char k ) {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractCharBigList.this.set( last, k );
    }
    public void remove() {
     if ( last == -1 ) throw new IllegalStateException();
     AbstractCharBigList.this.removeChar( last );
     /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
     if ( last < pos ) pos--;
     last = -1;
    }
   };
 }


 public CharBigListIterator listIterator( final int index ) {
  return listIterator( (long)index );
 }


 public boolean contains( final char k ) {
  return indexOf( k ) >= 0;
 }

 public long indexOf( final char k ) {
  final CharBigListIterator i = listIterator();
  char e;
  while( i.hasNext() ) {
   e = i.nextChar();
   if ( ( (k) == (e) ) ) return i.previousIndex();
  }
  return -1;
 }

 public long lastIndexOf( final char k ) {
  CharBigListIterator i = listIterator( size64() );
  char e;
  while( i.hasPrevious() ) {
   e = i.previousChar();
   if ( ( (k) == (e) ) ) return i.nextIndex();
  }
  return -1;
 }

 public void size( final long size ) {
  long i = size64();
  if ( size > i ) while( i++ < size ) add( ((char)0) );
  else while( i-- != size ) remove( i );
 }

 public void size( final int size ) {
  size( (long)size );
 }

 public CharBigList subList( final long from, final long to ) {
  ensureIndex( from );
  ensureIndex( to );
  if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

  return new CharSubList ( this, from, to );
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
  CharBigListIterator i = listIterator( from );
  long n = to - from;
  if ( n < 0 ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
  while( n-- != 0 ) {
   i.nextChar();
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

 public void addElements( long index, final char a[][], long offset, long length ) {
  ensureIndex( index );
  CharBigArrays.ensureOffsetLength( a, offset, length );
  while( length-- != 0 ) add( index++, CharBigArrays.get( a, offset++ ) );
 }

 public void addElements( final long index, final char a[][] ) {
  addElements( index, a, 0, CharBigArrays.length( a ) );
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

 public void getElements( final long from, final char a[][], long offset, long length ) {
  CharBigListIterator i = listIterator( from );
  CharBigArrays.ensureOffsetLength( a, offset, length );
  if ( from + length > size64() ) throw new IndexOutOfBoundsException( "End index (" + ( from + length ) + ") is greater than list size (" + size64() + ")" );
  while( length-- != 0 ) CharBigArrays.set( a, offset++, i.nextChar() );
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
 public int compareTo( final BigList<? extends Character> l ) {
  if ( l == this ) return 0;

  if ( l instanceof CharBigList ) {

   final CharBigListIterator i1 = listIterator(), i2 = ((CharBigList )l).listIterator();
   int r;
   char e1, e2;

   while( i1.hasNext() && i2.hasNext() ) {
    e1 = i1.nextChar();
    e2 = i2.nextChar();
    if ( ( r = ( (e1) < (e2) ? -1 : ( (e1) == (e2) ? 0 : 1 ) ) ) != 0 ) return r;
   }
   return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
  }

  BigListIterator<? extends Character> i1 = listIterator(), i2 = l.listIterator();
  int r;

  while( i1.hasNext() && i2.hasNext() ) {
   if ( ( r = ((Comparable<? super Character>)i1.next()).compareTo( i2.next() ) ) != 0 ) return r;
  }
  return i2.hasNext() ? -1 : ( i1.hasNext() ? 1 : 0 );
 }


 /** Returns the hash code for this big list, which is identical to {@link java.util.List#hashCode()}.
	 *
	 * @return the hash code for this big list.
	 */
 public int hashCode() {
  CharIterator i = iterator();
  int h = 1;
  long s = size64();
  while ( s-- != 0 ) {
   char k = i.nextChar();
   h = 31 * h + (k);
  }
  return h;
 }

 public void push( char o ) {
  add( o );
 }

 public char popChar() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return removeChar( size64() - 1 );
 }

 public char topChar() {
  if ( isEmpty() ) throw new NoSuchElementException();
  return getChar( size64() - 1 );
 }

 public char peekChar( int i ) {
  return getChar( size64() - 1 - i );
 }



 public char getChar( final int index ) {
  return getChar( (long)index );
 }

 public boolean rem( char k ) {
  long index = indexOf( k );
  if ( index == -1 ) return false;
  removeChar( index );
  return true;
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final CharCollection c ) {
  return addAll( index, (Collection<? extends Character>)c );
 }

 /** Delegates to a more generic method. */
 public boolean addAll( final long index, final CharBigList l ) {
  return addAll( index, (CharCollection)l );
 }

 public boolean addAll( final CharCollection c ) {
  return addAll( size64(), c );
 }

 public boolean addAll( final CharBigList l ) {
  return addAll( size64(), l );
 }

 /** Delegates to the corresponding type-specific method. */
 public void add( final long index, final Character ok ) {
  add( index, ok.charValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Character set( final long index, final Character ok ) {
  return (Character.valueOf(set( index, ok.charValue() )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Character get( final long index ) {
  return (Character.valueOf(getChar( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public long indexOf( final Object ok ) {
  return indexOf( ((((Character)(ok)).charValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public long lastIndexOf( final Object ok ) {
  return lastIndexOf( ((((Character)(ok)).charValue())) );
 }

 /** Delegates to the corresponding type-specific method. */
 public Character remove( final int index ) {
  return (Character.valueOf(removeChar( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public Character remove( final long index ) {
  return (Character.valueOf(removeChar( index )));
 }

 /** Delegates to the corresponding type-specific method. */
 public void push( Character o ) {
  push( o.charValue() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Character pop() {
  return Character.valueOf( popChar() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Character top() {
  return Character.valueOf( topChar() );
 }

 /** Delegates to the corresponding type-specific method. */
 public Character peek( int i ) {
  return Character.valueOf( peekChar( i ) );
 }
 public String toString() {
  final StringBuilder s = new StringBuilder();
  final CharIterator i = iterator();
  long n = size64();
  char k;
  boolean first = true;
  s.append("[");
  while( n-- != 0 ) {
   if (first) first = false;
   else s.append(", ");
   k = i.nextChar();
    s.append( String.valueOf( k ) );
  }
  s.append("]");
  return s.toString();
 }
 public static class CharSubList extends AbstractCharBigList implements java.io.Serializable {
     private static final long serialVersionUID = -7046029254386353129L;
  /** The list this sublist restricts. */
  protected final CharBigList l;
  /** Initial (inclusive) index of this sublist. */
  protected final long from;
  /** Final (exclusive) index of this sublist. */
  protected long to;
  private static final boolean ASSERTS = false;
  public CharSubList( final CharBigList l, final long from, final long to ) {
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
  public boolean add( final char k ) {
   l.add( to, k );
   to++;
   if ( ASSERTS ) assertRange();
   return true;
  }
  public void add( final long index, final char k ) {
   ensureIndex( index );
   l.add( from + index, k );
   to++;
   if ( ASSERTS ) assertRange();
  }
  public boolean addAll( final long index, final Collection<? extends Character> c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public char getChar( long index ) {
   ensureRestrictedIndex( index );
   return l.getChar( from + index );
  }
  public char removeChar( long index ) {
   ensureRestrictedIndex( index );
   to--;
   return l.removeChar( from + index );
  }
  public char set( long index, char k ) {
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
  public void getElements( final long from, final char[][] a, final long offset, final long length ) {
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
  public void addElements( final long index, final char a[][], long offset, long length ) {
   ensureIndex( index );
   l.addElements( this.from + index, a, offset, length );
   this.to += length;
   if ( ASSERTS ) assertRange();
  }
  public CharBigListIterator listIterator( final long index ) {
   ensureIndex( index );
   return new AbstractCharBigListIterator () {
     long pos = index, last = -1;
     public boolean hasNext() { return pos < size64(); }
     public boolean hasPrevious() { return pos > 0; }
     public char nextChar() { if ( ! hasNext() ) throw new NoSuchElementException(); return l.getChar( from + ( last = pos++ ) ); }
     public char previousChar() { if ( ! hasPrevious() ) throw new NoSuchElementException(); return l.getChar( from + ( last = --pos ) ); }
     public long nextIndex() { return pos; }
     public long previousIndex() { return pos - 1; }
     public void add( char k ) {
      if ( last == -1 ) throw new IllegalStateException();
      CharSubList.this.add( pos++, k );
      last = -1;
      if ( ASSERTS ) assertRange();
     }
     public void set( char k ) {
      if ( last == -1 ) throw new IllegalStateException();
      CharSubList.this.set( last, k );
     }
     public void remove() {
      if ( last == -1 ) throw new IllegalStateException();
      CharSubList.this.removeChar( last );
      /* If the last operation was a next(), we are removing an element *before* us, and we must decrease pos correspondingly. */
      if ( last < pos ) pos--;
      last = -1;
      if ( ASSERTS ) assertRange();
     }
    };
  }
  public CharBigList subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IllegalArgumentException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   return new CharSubList ( this, from, to );
  }
  public boolean rem( char k ) {
   long index = indexOf( k );
   if ( index == -1 ) return false;
   to--;
   l.removeChar( from + index );
   if ( ASSERTS ) assertRange();
   return true;
  }
  public boolean remove( final Object o ) {
   return rem( ((((Character)(o)).charValue())) );
  }
  public boolean addAll( final long index, final CharCollection c ) {
   ensureIndex( index );
   to += c.size();
   if ( ASSERTS ) {
    boolean retVal = l.addAll( from + index, c );
    assertRange();
    return retVal;
   }
   return l.addAll( from + index, c );
  }
  public boolean addAll( final long index, final CharList l ) {
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
