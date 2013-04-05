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
package it.unimi.dsi.fastutil.bytes;
import java.util.Iterator;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific iterators.
 *
 * @see Iterator
 */
public class ByteBigListIterators {
 private ByteBigListIterators() {}
 /** A class returning no elements and a type-specific big list iterator interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific iterator.
	 */
 public static class EmptyBigListIterator extends AbstractByteBigListIterator implements java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyBigListIterator() {}
  public boolean hasNext() { return false; }
  public boolean hasPrevious() { return false; }
  public byte nextByte() { throw new NoSuchElementException(); }
  public byte previousByte() { throw new NoSuchElementException(); }
  public long nextIndex() { return 0; }
  public long previousIndex() { return -1; }
  public long skip( long n ) { return 0; };
  public long back( long n ) { return 0; };
  public Object clone() { return EMPTY_BIG_LIST_ITERATOR; }
        private Object readResolve() { return EMPTY_BIG_LIST_ITERATOR; }
 }
 /** An empty iterator (immutable). It is serializable and cloneable.
	 *
	 * <P>The class of this objects represent an abstract empty iterator
	 * that can iterate as a type-specific (list) iterator.
	 */
 @SuppressWarnings("rawtypes")
 public final static EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
 /** An iterator returning a single element. */
 private static class SingletonBigListIterator extends AbstractByteBigListIterator {
  private final byte element;
  private int curr;
  public SingletonBigListIterator( final byte element ) {
   this.element = element;
  }
  public boolean hasNext() { return curr == 0; }
  public boolean hasPrevious() { return curr == 1; }
  public byte nextByte() {
   if ( ! hasNext() ) throw new NoSuchElementException();
   curr = 1;
   return element;
  }
  public byte previousByte() {
   if ( ! hasPrevious() ) throw new NoSuchElementException();
   curr = 0;
   return element;
  }
  public long nextIndex() {
   return curr;
  }
  public long previousIndex() {
   return curr - 1;
  }
 }
 /** Returns an iterator that iterates just over the given element.
	 *
	 * @param element the only element to be returned by a type-specific list iterator.
	 * @return  an iterator that iterates just over <code>element</code>.
	 */
 public static ByteBigListIterator singleton( final byte element ) {
  return new SingletonBigListIterator ( element );
 }
   /** An unmodifiable wrapper class for big list iterators. */

 public static class UnmodifiableBigListIterator extends AbstractByteBigListIterator {
  final protected ByteBigListIterator i;

  @SuppressWarnings("unchecked")
  public UnmodifiableBigListIterator( final ByteBigListIterator i ) {
   this.i = i;
  }

  public boolean hasNext() { return i.hasNext(); }
  public boolean hasPrevious() { return i.hasPrevious(); }
  public byte nextByte() { return i.nextByte(); }
  public byte previousByte() { return i.previousByte(); }
  public long nextIndex() { return i.nextIndex(); }
  public long previousIndex() { return i.previousIndex(); }

  public Byte next() { return i.next(); }
  public Byte previous() { return i.previous(); }

 }

 /** Returns an unmodifiable list iterator backed by the specified list iterator.
	 *
	 * @param i the list iterator to be wrapped in an unmodifiable list iterator.
	 * @return an unmodifiable view of the specified list iterator.
	 */
 public static ByteBigListIterator unmodifiable( final ByteBigListIterator i ) { return new UnmodifiableBigListIterator ( i ); }


 /** A class exposing a list iterator as a big-list iterator.. */

 public static class BigListIteratorListIterator extends AbstractByteBigListIterator {
  protected final ByteListIterator i;

  protected BigListIteratorListIterator( final ByteListIterator i ) {
   this.i = i;
  }

  private int intDisplacement( long n ) {
   if ( n < Integer.MIN_VALUE || n > Integer.MAX_VALUE ) throw new IndexOutOfBoundsException( "This big iterator is restricted to 32-bit displacements" );
   return (int)n;
  }

  public void set( byte ok ) { i.set( ok ); }
  public void add( byte ok ) { i.add( ok ); }
  public int back( int n ) { return i.back( n ); }
  public long back( long n ) { return i.back( intDisplacement( n ) ); }
  public void remove() { i.remove(); }
  public int skip( int n ) { return i.skip( n ); }
  public long skip( long n ) { return i.skip( intDisplacement( n ) ); }
  public boolean hasNext() { return i.hasNext(); }
  public boolean hasPrevious() { return i.hasPrevious(); }
  public byte nextByte() { return i.nextByte(); }
  public byte previousByte() { return i.previousByte(); }
  public long nextIndex() { return i.nextIndex(); }
  public long previousIndex() { return i.previousIndex(); }
 }

  /** Returns a big-list iterator backed by the specified list iterator.
	  *
	  * @param i the list iterator to adapted to the big-list-iterator interface.
	  * @return a big-list iterator backed by the specified list iterator.
	  */
 public static ByteBigListIterator asBigListIterator( final ByteListIterator i ) { return new BigListIteratorListIterator ( i ); }
}
