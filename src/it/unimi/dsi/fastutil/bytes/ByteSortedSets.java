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
import java.util.SortedSet;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted sets.
 *
 * @see java.util.Collections
 */
public class ByteSortedSets {
 private ByteSortedSets() {}
 /** An immutable class representing the empty sorted set and implementing a type-specific set interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted set.
	 */
 public static class EmptySet extends ByteSets.EmptySet implements ByteSortedSet , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySet() {}
  public boolean remove( byte ok ) { throw new UnsupportedOperationException(); }
  @Deprecated
  public ByteBidirectionalIterator byteIterator() { return iterator(); }
  @SuppressWarnings("unchecked")
  public ByteBidirectionalIterator iterator( byte from ) { return ByteIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ByteSortedSet subSet( byte from, byte to ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ByteSortedSet headSet( byte from ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ByteSortedSet tailSet( byte to ) { return EMPTY_SET; }
  public byte firstByte() { throw new NoSuchElementException(); }
  public byte lastByte() { throw new NoSuchElementException(); }
  public ByteComparator comparator() { return null; }
  public ByteSortedSet subSet( Byte from, Byte to ) { return EMPTY_SET; }
  public ByteSortedSet headSet( Byte from ) { return EMPTY_SET; }
  public ByteSortedSet tailSet( Byte to ) { return EMPTY_SET; }
  public Byte first() { throw new NoSuchElementException(); }
  public Byte last() { throw new NoSuchElementException(); }
  public Object clone() { return EMPTY_SET; }

        private Object readResolve() { return EMPTY_SET; }
 }


 /** An empty sorted set (immutable). It is serializable and cloneable.
	 *
	 * <P>The class of this objects represent an abstract empty set
	 * that is a subset of a (sorted) type-specific set.
	 */

 @SuppressWarnings("rawtypes")
 public static final EmptySet EMPTY_SET = new EmptySet();

 /** A class representing a singleton sorted set.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted set.
	 */

 public static class Singleton extends ByteSets.Singleton implements ByteSortedSet , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  final ByteComparator comparator;

  private Singleton( final byte element, final ByteComparator comparator ) {
   super( element );
   this.comparator = comparator;
  }

  private Singleton( final byte element ) {
   this( element, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final byte k1, final byte k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  @Deprecated
  public ByteBidirectionalIterator byteIterator() {
   return iterator();
  }

  public ByteBidirectionalIterator iterator( byte from ) {
   ByteBidirectionalIterator i = iterator();
   if ( compare( element, from ) <= 0 ) i.next();
   return i;
  }

  public ByteComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ByteSortedSet subSet( final byte from, final byte to ) { if ( compare( from, element ) <= 0 && compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public ByteSortedSet headSet( final byte to ) { if ( compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public ByteSortedSet tailSet( final byte from ) { if ( compare( from, element ) <= 0 ) return this; return EMPTY_SET; }

  public byte firstByte() { return element; }
  public byte lastByte() { return element; }


  public Byte first() { return (Byte.valueOf(element)); }
  public Byte last() { return (Byte.valueOf(element)); }


  public ByteSortedSet subSet( final Byte from, final Byte to ) { return subSet( ((from).byteValue()), ((to).byteValue()) ); }
  public ByteSortedSet headSet( final Byte to ) { return headSet( ((to).byteValue()) ); }
  public ByteSortedSet tailSet( final Byte from ) { return tailSet( ((from).byteValue()) ); }

 }


 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ByteSortedSet singleton( final byte element ) {
  return new Singleton ( element );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ByteSortedSet singleton( final byte element, final ByteComparator comparator ) {
  return new Singleton ( element, comparator );
 }



 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ByteSortedSet singleton( final Object element ) {
  return new Singleton( ((((Byte)(element)).byteValue())) );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ByteSortedSet singleton( final Object element, final ByteComparator comparator ) {
  return new Singleton( ((((Byte)(element)).byteValue())), comparator );
 }



 /** A synchronized wrapper class for sorted sets. */

 public static class SynchronizedSortedSet extends ByteSets.SynchronizedSet implements ByteSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ByteSortedSet sortedSet;

  protected SynchronizedSortedSet( final ByteSortedSet s, final Object sync ) {
   super( s, sync );
   sortedSet = s;
  }

  protected SynchronizedSortedSet( final ByteSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public ByteComparator comparator() { synchronized( sync ) { return sortedSet.comparator(); } }

  public ByteSortedSet subSet( final byte from, final byte to ) { return new SynchronizedSortedSet ( sortedSet.subSet( from, to ), sync ); }
  public ByteSortedSet headSet( final byte to ) { return new SynchronizedSortedSet ( sortedSet.headSet( to ), sync ); }
  public ByteSortedSet tailSet( final byte from ) { return new SynchronizedSortedSet ( sortedSet.tailSet( from ), sync ); }

  public ByteBidirectionalIterator iterator() { return sortedSet.iterator(); }
  public ByteBidirectionalIterator iterator( final byte from ) { return sortedSet.iterator( from ); }

  @Deprecated
  public ByteBidirectionalIterator byteIterator() { return sortedSet.iterator(); }

  public byte firstByte() { synchronized( sync ) { return sortedSet.firstByte(); } }
  public byte lastByte() { synchronized( sync ) { return sortedSet.lastByte(); } }


  public Byte first() { synchronized( sync ) { return sortedSet.first(); } }
  public Byte last() { synchronized( sync ) { return sortedSet.last(); } }

  public ByteSortedSet subSet( final Byte from, final Byte to ) { return new SynchronizedSortedSet( sortedSet.subSet( from, to ), sync ); }
  public ByteSortedSet headSet( final Byte to ) { return new SynchronizedSortedSet( sortedSet.headSet( to ), sync ); }
  public ByteSortedSet tailSet( final Byte from ) { return new SynchronizedSortedSet( sortedSet.tailSet( from ), sync ); }

 }


 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */
 public static ByteSortedSet synchronize( final ByteSortedSet s ) { return new SynchronizedSortedSet ( s ); }

 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set, using an assigned object to synchronize.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @param sync an object that will be used to synchronize the access to the sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */

 public static ByteSortedSet synchronize( final ByteSortedSet s, final Object sync ) { return new SynchronizedSortedSet ( s, sync ); }





 /** An unmodifiable wrapper class for sorted sets. */

 public static class UnmodifiableSortedSet extends ByteSets.UnmodifiableSet implements ByteSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ByteSortedSet sortedSet;

  protected UnmodifiableSortedSet( final ByteSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public ByteComparator comparator() { return sortedSet.comparator(); }

  public ByteSortedSet subSet( final byte from, final byte to ) { return new UnmodifiableSortedSet ( sortedSet.subSet( from, to ) ); }
  public ByteSortedSet headSet( final byte to ) { return new UnmodifiableSortedSet ( sortedSet.headSet( to ) ); }
  public ByteSortedSet tailSet( final byte from ) { return new UnmodifiableSortedSet ( sortedSet.tailSet( from ) ); }

  public ByteBidirectionalIterator iterator() { return ByteIterators.unmodifiable( sortedSet.iterator() ); }
  public ByteBidirectionalIterator iterator( final byte from ) { return ByteIterators.unmodifiable( sortedSet.iterator( from ) ); }

  @Deprecated
  public ByteBidirectionalIterator byteIterator() { return iterator(); }

  public byte firstByte() { return sortedSet.firstByte(); }
  public byte lastByte() { return sortedSet.lastByte(); }


  public Byte first() { return sortedSet.first(); }
  public Byte last() { return sortedSet.last(); }

  public ByteSortedSet subSet( final Byte from, final Byte to ) { return new UnmodifiableSortedSet( sortedSet.subSet( from, to ) ); }
  public ByteSortedSet headSet( final Byte to ) { return new UnmodifiableSortedSet( sortedSet.headSet( to ) ); }
  public ByteSortedSet tailSet( final Byte from ) { return new UnmodifiableSortedSet( sortedSet.tailSet( from ) ); }

 }


 /** Returns an unmodifiable type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in an unmodifiable sorted set.
	 * @return an unmodifiable view of the specified sorted set.
	 * @see java.util.Collections#unmodifiableSortedSet(SortedSet)
	 */
 public static ByteSortedSet unmodifiable( final ByteSortedSet s ) { return new UnmodifiableSortedSet ( s ); }
}
