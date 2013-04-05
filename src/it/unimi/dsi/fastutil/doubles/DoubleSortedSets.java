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
package it.unimi.dsi.fastutil.doubles;
import java.util.SortedSet;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted sets.
 *
 * @see java.util.Collections
 */
public class DoubleSortedSets {
 private DoubleSortedSets() {}
 /** An immutable class representing the empty sorted set and implementing a type-specific set interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted set.
	 */
 public static class EmptySet extends DoubleSets.EmptySet implements DoubleSortedSet , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySet() {}
  public boolean remove( double ok ) { throw new UnsupportedOperationException(); }
  @Deprecated
  public DoubleBidirectionalIterator doubleIterator() { return iterator(); }
  @SuppressWarnings("unchecked")
  public DoubleBidirectionalIterator iterator( double from ) { return DoubleIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public DoubleSortedSet subSet( double from, double to ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public DoubleSortedSet headSet( double from ) { return EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public DoubleSortedSet tailSet( double to ) { return EMPTY_SET; }
  public double firstDouble() { throw new NoSuchElementException(); }
  public double lastDouble() { throw new NoSuchElementException(); }
  public DoubleComparator comparator() { return null; }
  public DoubleSortedSet subSet( Double from, Double to ) { return EMPTY_SET; }
  public DoubleSortedSet headSet( Double from ) { return EMPTY_SET; }
  public DoubleSortedSet tailSet( Double to ) { return EMPTY_SET; }
  public Double first() { throw new NoSuchElementException(); }
  public Double last() { throw new NoSuchElementException(); }
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

 public static class Singleton extends DoubleSets.Singleton implements DoubleSortedSet , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  final DoubleComparator comparator;

  private Singleton( final double element, final DoubleComparator comparator ) {
   super( element );
   this.comparator = comparator;
  }

  private Singleton( final double element ) {
   this( element, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final double k1, final double k2 ) {
   return comparator == null ? ( Double.compare((k1),(k2)) ) : comparator.compare( k1, k2 );
  }

  @Deprecated
  public DoubleBidirectionalIterator doubleIterator() {
   return iterator();
  }

  public DoubleBidirectionalIterator iterator( double from ) {
   DoubleBidirectionalIterator i = iterator();
   if ( compare( element, from ) <= 0 ) i.next();
   return i;
  }

  public DoubleComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public DoubleSortedSet subSet( final double from, final double to ) { if ( compare( from, element ) <= 0 && compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public DoubleSortedSet headSet( final double to ) { if ( compare( element, to ) < 0 ) return this; return EMPTY_SET; }

  @SuppressWarnings("unchecked")
  public DoubleSortedSet tailSet( final double from ) { if ( compare( from, element ) <= 0 ) return this; return EMPTY_SET; }

  public double firstDouble() { return element; }
  public double lastDouble() { return element; }


  public Double first() { return (Double.valueOf(element)); }
  public Double last() { return (Double.valueOf(element)); }


  public DoubleSortedSet subSet( final Double from, final Double to ) { return subSet( ((from).doubleValue()), ((to).doubleValue()) ); }
  public DoubleSortedSet headSet( final Double to ) { return headSet( ((to).doubleValue()) ); }
  public DoubleSortedSet tailSet( final Double from ) { return tailSet( ((from).doubleValue()) ); }

 }


 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static DoubleSortedSet singleton( final double element ) {
  return new Singleton ( element );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static DoubleSortedSet singleton( final double element, final DoubleComparator comparator ) {
  return new Singleton ( element, comparator );
 }



 /** Returns a type-specific immutable sorted set containing only the specified element. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static DoubleSortedSet singleton( final Object element ) {
  return new Singleton( ((((Double)(element)).doubleValue())) );
 }

 /** Returns a type-specific immutable sorted set containing only the specified element, and using a specified comparator. The returned sorted set is serializable and cloneable.
	 *
	 * @param element the only element of the returned sorted set.
	 * @param comparator the comparator to use in the returned sorted set.
	 * @return a type-specific immutable sorted set containing just <code>element</code>.
	 */

 public static DoubleSortedSet singleton( final Object element, final DoubleComparator comparator ) {
  return new Singleton( ((((Double)(element)).doubleValue())), comparator );
 }



 /** A synchronized wrapper class for sorted sets. */

 public static class SynchronizedSortedSet extends DoubleSets.SynchronizedSet implements DoubleSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final DoubleSortedSet sortedSet;

  protected SynchronizedSortedSet( final DoubleSortedSet s, final Object sync ) {
   super( s, sync );
   sortedSet = s;
  }

  protected SynchronizedSortedSet( final DoubleSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public DoubleComparator comparator() { synchronized( sync ) { return sortedSet.comparator(); } }

  public DoubleSortedSet subSet( final double from, final double to ) { return new SynchronizedSortedSet ( sortedSet.subSet( from, to ), sync ); }
  public DoubleSortedSet headSet( final double to ) { return new SynchronizedSortedSet ( sortedSet.headSet( to ), sync ); }
  public DoubleSortedSet tailSet( final double from ) { return new SynchronizedSortedSet ( sortedSet.tailSet( from ), sync ); }

  public DoubleBidirectionalIterator iterator() { return sortedSet.iterator(); }
  public DoubleBidirectionalIterator iterator( final double from ) { return sortedSet.iterator( from ); }

  @Deprecated
  public DoubleBidirectionalIterator doubleIterator() { return sortedSet.iterator(); }

  public double firstDouble() { synchronized( sync ) { return sortedSet.firstDouble(); } }
  public double lastDouble() { synchronized( sync ) { return sortedSet.lastDouble(); } }


  public Double first() { synchronized( sync ) { return sortedSet.first(); } }
  public Double last() { synchronized( sync ) { return sortedSet.last(); } }

  public DoubleSortedSet subSet( final Double from, final Double to ) { return new SynchronizedSortedSet( sortedSet.subSet( from, to ), sync ); }
  public DoubleSortedSet headSet( final Double to ) { return new SynchronizedSortedSet( sortedSet.headSet( to ), sync ); }
  public DoubleSortedSet tailSet( final Double from ) { return new SynchronizedSortedSet( sortedSet.tailSet( from ), sync ); }

 }


 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */
 public static DoubleSortedSet synchronize( final DoubleSortedSet s ) { return new SynchronizedSortedSet ( s ); }

 /** Returns a synchronized type-specific sorted set backed by the given type-specific sorted set, using an assigned object to synchronize.
	 *
	 * @param s the sorted set to be wrapped in a synchronized sorted set.
	 * @param sync an object that will be used to synchronize the access to the sorted set.
	 * @return a synchronized view of the specified sorted set.
	 * @see java.util.Collections#synchronizedSortedSet(SortedSet)
	 */

 public static DoubleSortedSet synchronize( final DoubleSortedSet s, final Object sync ) { return new SynchronizedSortedSet ( s, sync ); }





 /** An unmodifiable wrapper class for sorted sets. */

 public static class UnmodifiableSortedSet extends DoubleSets.UnmodifiableSet implements DoubleSortedSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final DoubleSortedSet sortedSet;

  protected UnmodifiableSortedSet( final DoubleSortedSet s ) {
   super( s );
   sortedSet = s;
  }

  public DoubleComparator comparator() { return sortedSet.comparator(); }

  public DoubleSortedSet subSet( final double from, final double to ) { return new UnmodifiableSortedSet ( sortedSet.subSet( from, to ) ); }
  public DoubleSortedSet headSet( final double to ) { return new UnmodifiableSortedSet ( sortedSet.headSet( to ) ); }
  public DoubleSortedSet tailSet( final double from ) { return new UnmodifiableSortedSet ( sortedSet.tailSet( from ) ); }

  public DoubleBidirectionalIterator iterator() { return DoubleIterators.unmodifiable( sortedSet.iterator() ); }
  public DoubleBidirectionalIterator iterator( final double from ) { return DoubleIterators.unmodifiable( sortedSet.iterator( from ) ); }

  @Deprecated
  public DoubleBidirectionalIterator doubleIterator() { return iterator(); }

  public double firstDouble() { return sortedSet.firstDouble(); }
  public double lastDouble() { return sortedSet.lastDouble(); }


  public Double first() { return sortedSet.first(); }
  public Double last() { return sortedSet.last(); }

  public DoubleSortedSet subSet( final Double from, final Double to ) { return new UnmodifiableSortedSet( sortedSet.subSet( from, to ) ); }
  public DoubleSortedSet headSet( final Double to ) { return new UnmodifiableSortedSet( sortedSet.headSet( to ) ); }
  public DoubleSortedSet tailSet( final Double from ) { return new UnmodifiableSortedSet( sortedSet.tailSet( from ) ); }

 }


 /** Returns an unmodifiable type-specific sorted set backed by the given type-specific sorted set.
	 *
	 * @param s the sorted set to be wrapped in an unmodifiable sorted set.
	 * @return an unmodifiable view of the specified sorted set.
	 * @see java.util.Collections#unmodifiableSortedSet(SortedSet)
	 */
 public static DoubleSortedSet unmodifiable( final DoubleSortedSet s ) { return new UnmodifiableSortedSet ( s ); }
}
