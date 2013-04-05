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
package it.unimi.dsi.fastutil.shorts;
import java.util.SortedSet;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted sets.
 *
 * @see java.util.Collections
 */
public class ShortSortedSets {
 private ShortSortedSets() {}
 /** An immutable class representing the empty sorted set and implementing a type-specific set interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted set.
	 */
 public static class EmptySet extends ShortSets.EmptySet implements ShortSortedSet , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySet() {}
  public boolean remove( short ok ) { throw new UnsupportedOperationException(); }
  @Deprecated
  public ShortBidirectionalIterator shortIterator() { return iterator(); }
  @SuppressWarnings("unchecked")
  public ShortBidirectionalIterator iterator( short from ) { return ShortIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ShortSortedSet subSet( short from, short to ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ShortSortedSet headSet( short from ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ShortSortedSet tailSet( short to ) { return EMPTY_SET; }
  public short firstShort() { throw new NoSuchElementException(); }
  public short lastShort() { throw new NoSuchElementException(); }
  public ShortComparator comparator() { return null; }
  public ShortSortedSet subSet( Short from, Short to ) { return EMPTY_SET; }
  public ShortSortedSet headSet( Short from ) { return EMPTY_SET; }
  public ShortSortedSet tailSet( Short to ) { return EMPTY_SET; }
  public Short first() { throw new NoSuchElementException(); }
  public Short last() { throw new NoSuchElementException(); }
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

 public static class Singleton extends ShortSets.Singleton implements ShortSortedSet , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  final ShortComparator comparator;

  private Singleton( final short element, final ShortComparator comparator ) {
   super( element );
   this.comparator = comparator;
  }

  private Singleton( final short element ) {
   this( element, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final short k1, final short k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  @Deprecated
  public ShortBidirectionalIterator shortIterator() {
   return iterator();
  }

  public ShortBidirectionalIterator iterator( short from ) {
   ShortBidirectionalIterator i = iterator();
   if ( compare( element, from ) <= 0 ) i.next();
   return i;
  }

  public ShortComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ShortSortedSet subSet( final short from, final short to ) { if ( compare( from, element ) <= 0 && compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public ShortSortedSet headSet( final short to ) { if ( compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public ShortSortedSet tailSet( final short from ) { if ( compare( from, element ) <= 0 ) return this; return EMPTY_SET; }

  public short firstShort() { return element; }
  public short lastShort() { return element; }


  public Short first() { return (Short.valueOf(element)); }
  public Short last() { return (Short.valueOf(element)); }


  public ShortSortedSet subSet( final Short from, final Short to ) { return subSet( ((from).shortValue()), ((to).shortValue()) ); }
  public ShortSortedSet headSet( final Short to ) { return headSet( ((to).shortValue()) ); }
  public ShortSortedSet tailSet( final Short from ) { return tailSet( ((from).shortValue()) ); }

 }


 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ShortSortedSet singleton( final short element ) {
  return new Singleton ( element );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ShortSortedSet singleton( final short element, final ShortComparator comparator ) {
  return new Singleton ( element, comparator );
 }



 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ShortSortedSet singleton( final Object element ) {
  return new Singleton( ((((Short)(element)).shortValue())) );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static ShortSortedSet singleton( final Object element, final ShortComparator comparator ) {
  return new Singleton( ((((Short)(element)).shortValue())), comparator );
 }



 /** A synchronized wrapper class for sorted sets. */

 public static class SynchronizedSortedSet extends ShortSets.SynchronizedSet implements ShortSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ShortSortedSet sortedSet;

  protected SynchronizedSortedSet( final ShortSortedSet s, final Object sync ) {
   super( s, sync );
   sortedSet = s;
  }

  protected SynchronizedSortedSet( final ShortSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public ShortComparator comparator() { synchronized( sync ) { return sortedSet.comparator(); } }

  public ShortSortedSet subSet( final short from, final short to ) { return new SynchronizedSortedSet ( sortedSet.subSet( from, to ), sync ); }
  public ShortSortedSet headSet( final short to ) { return new SynchronizedSortedSet ( sortedSet.headSet( to ), sync ); }
  public ShortSortedSet tailSet( final short from ) { return new SynchronizedSortedSet ( sortedSet.tailSet( from ), sync ); }

  public ShortBidirectionalIterator iterator() { return sortedSet.iterator(); }
  public ShortBidirectionalIterator iterator( final short from ) { return sortedSet.iterator( from ); }

  @Deprecated
  public ShortBidirectionalIterator shortIterator() { return sortedSet.iterator(); }

  public short firstShort() { synchronized( sync ) { return sortedSet.firstShort(); } }
  public short lastShort() { synchronized( sync ) { return sortedSet.lastShort(); } }


  public Short first() { synchronized( sync ) { return sortedSet.first(); } }
  public Short last() { synchronized( sync ) { return sortedSet.last(); } }

  public ShortSortedSet subSet( final Short from, final Short to ) { return new SynchronizedSortedSet( sortedSet.subSet( from, to ), sync ); }
  public ShortSortedSet headSet( final Short to ) { return new SynchronizedSortedSet( sortedSet.headSet( to ), sync ); }
  public ShortSortedSet tailSet( final Short from ) { return new SynchronizedSortedSet( sortedSet.tailSet( from ), sync ); }

 }


 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */
 public static ShortSortedSet synchronize( final ShortSortedSet s ) { return new SynchronizedSortedSet ( s ); }

 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set, using an assigned object to synchronize.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @param sync an object that will be used to synchronize the access to the sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */

 public static ShortSortedSet synchronize( final ShortSortedSet s, final Object sync ) { return new SynchronizedSortedSet ( s, sync ); }





 /** An unmodifiable wrapper class for sorted sets. */

 public static class UnmodifiableSortedSet extends ShortSets.UnmodifiableSet implements ShortSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ShortSortedSet sortedSet;

  protected UnmodifiableSortedSet( final ShortSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public ShortComparator comparator() { return sortedSet.comparator(); }

  public ShortSortedSet subSet( final short from, final short to ) { return new UnmodifiableSortedSet ( sortedSet.subSet( from, to ) ); }
  public ShortSortedSet headSet( final short to ) { return new UnmodifiableSortedSet ( sortedSet.headSet( to ) ); }
  public ShortSortedSet tailSet( final short from ) { return new UnmodifiableSortedSet ( sortedSet.tailSet( from ) ); }

  public ShortBidirectionalIterator iterator() { return ShortIterators.unmodifiable( sortedSet.iterator() ); }
  public ShortBidirectionalIterator iterator( final short from ) { return ShortIterators.unmodifiable( sortedSet.iterator( from ) ); }

  @Deprecated
  public ShortBidirectionalIterator shortIterator() { return iterator(); }

  public short firstShort() { return sortedSet.firstShort(); }
  public short lastShort() { return sortedSet.lastShort(); }


  public Short first() { return sortedSet.first(); }
  public Short last() { return sortedSet.last(); }

  public ShortSortedSet subSet( final Short from, final Short to ) { return new UnmodifiableSortedSet( sortedSet.subSet( from, to ) ); }
  public ShortSortedSet headSet( final Short to ) { return new UnmodifiableSortedSet( sortedSet.headSet( to ) ); }
  public ShortSortedSet tailSet( final Short from ) { return new UnmodifiableSortedSet( sortedSet.tailSet( from ) ); }

 }


 /** Returns an unmodifiable type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in an unmodifiable sorted set.
	 * @return an unmodifiable view of the specified sorted set.
	 * @see java.util.Collections#unmodifiableSortedSet(SortedSet)
	 */
 public static ShortSortedSet unmodifiable( final ShortSortedSet s ) { return new UnmodifiableSortedSet ( s ); }
}
