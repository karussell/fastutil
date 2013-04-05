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
package it.unimi.dsi.fastutil.booleans;
import java.util.Collection;
import java.util.Set;
/** A class providing static methods and objects that do useful things with type-specific sets.
 *
 * @see java.util.Collections
 */
public class BooleanSets {
 private BooleanSets() {}
 /** An immutable class representing the empty set and implementing a type-specific set interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific set.
	 */
 public static class EmptySet extends BooleanCollections.EmptyCollection implements BooleanSet , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySet() {}
  public boolean remove( boolean ok ) { throw new UnsupportedOperationException(); }
  public Object clone() { return EMPTY_SET; }
        private Object readResolve() { return EMPTY_SET; }
 }
 /** An empty set (immutable). It is serializable and cloneable.
	 *
	 * <P>The class of this objects represent an abstract empty set
	 * that is a subset of a (sorted) type-specific set.
	 */
 @SuppressWarnings("rawtypes")
 public static final EmptySet EMPTY_SET = new EmptySet();
 /** An immutable class representing a type-specific singleton set.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific set.	 */
 public static class Singleton extends AbstractBooleanSet implements java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final boolean element;
  protected Singleton( final boolean element ) {
   this.element = element;
  }
  public boolean add( final boolean k ) { throw new UnsupportedOperationException(); }
  public boolean contains( final boolean k ) { return ( (k) == (element) ); }
  public boolean addAll( final Collection<? extends Boolean> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  /* Slightly optimized w.r.t. the one in ABSTRACT_SET. */
  public boolean[] toBooleanArray() {
   boolean a[] = new boolean[ 1 ];
   a[ 0 ] = element;
   return a;
  }
  public boolean addAll( final BooleanCollection c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( final BooleanCollection c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final BooleanCollection c ) { throw new UnsupportedOperationException(); }

  @SuppressWarnings("unchecked")
  public BooleanListIterator iterator() { return BooleanIterators.singleton( element ); }

  public int size() { return 1; }

  public Object clone() { return this; }
 }



 /** Returns a type-specific immutable set containing only the specified element. The returned set is serializable and cloneable.
	 *
	 * @param element the only element of the returned set.
	 * @return a type-specific immutable set containing just <code>element</code>.
	 */

 public static BooleanSet singleton( final boolean element ) {
  return new Singleton ( element );
 }





 /** Returns a type-specific immutable set containing only the specified element. The returned set is serializable and cloneable.
	 *
	 * @param element the only element of the returned set.
	 * @return a type-specific immutable set containing just <code>element</code>.
	 */

 public static BooleanSet singleton( final Boolean element ) {
  return new Singleton ( ((element).booleanValue()) );
 }



 /** A synchronized wrapper class for sets. */

 public static class SynchronizedSet extends BooleanCollections.SynchronizedCollection implements BooleanSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected SynchronizedSet( final BooleanSet s, final Object sync ) {
   super( s, sync );
  }

  protected SynchronizedSet( final BooleanSet s ) {
   super( s );
  }

  public boolean remove( final boolean k ) { synchronized( sync ) { return collection.remove( (Boolean.valueOf(k)) ); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return collection.equals( o ); } }
  public int hashCode() { synchronized( sync ) { return collection.hashCode(); } }
 }


 /** Returns a synchronized type-specific set backed by the given type-specific set.
	 *
	 * @param s the set to be wrapped in a synchronized set.
	 * @return a synchronized view of the specified set.
	 * @see java.util.Collections#synchronizedSet(Set)
	 */
 public static BooleanSet synchronize( final BooleanSet s ) { return new SynchronizedSet ( s ); }

 /** Returns a synchronized type-specific set backed by the given type-specific set, using an assigned object to synchronize.
	 *
	 * @param s the set to be wrapped in a synchronized set.
	 * @param sync an object that will be used to synchronize the access to the set.
	 * @return a synchronized view of the specified set.
	 * @see java.util.Collections#synchronizedSet(Set)
	 */

 public static BooleanSet synchronize( final BooleanSet s, final Object sync ) { return new SynchronizedSet ( s, sync ); }



 /** An unmodifiable wrapper class for sets. */

 public static class UnmodifiableSet extends BooleanCollections.UnmodifiableCollection implements BooleanSet , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected UnmodifiableSet( final BooleanSet s ) {
   super( s );
  }

  public boolean remove( final boolean k ) { throw new UnsupportedOperationException(); }
  public boolean equals( final Object o ) { return collection.equals( o ); }
  public int hashCode() { return collection.hashCode(); }
 }


 /** Returns an unmodifiable type-specific set backed by the given type-specific set.
	 *
	 * @param s the set to be wrapped in an unmodifiable set.
	 * @return an unmodifiable view of the specified set.
	 * @see java.util.Collections#unmodifiableSet(Set)
	 */
 public static BooleanSet unmodifiable( final BooleanSet s ) { return new UnmodifiableSet ( s ); }
}
