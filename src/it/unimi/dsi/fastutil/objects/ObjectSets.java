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
package it.unimi.dsi.fastutil.objects;
import java.util.Collection;
import java.util.Set;
/** A class providing static methods and objects that do useful things with type-specific sets.
 *
 * @see java.util.Collections
 */
public class ObjectSets {
 private ObjectSets() {}
 /** An immutable class representing the empty set and implementing a type-specific set interface.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific set.
	 */
 public static class EmptySet <K> extends ObjectCollections.EmptyCollection <K> implements ObjectSet <K>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySet() {}
  public boolean remove( Object ok ) { throw new UnsupportedOperationException(); }
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
 public static class Singleton <K> extends AbstractObjectSet <K> implements java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final K element;
  protected Singleton( final K element ) {
   this.element = element;
  }
  public boolean add( final K k ) { throw new UnsupportedOperationException(); }
  public boolean contains( final Object k ) { return ( (k) == null ? (element) == null : (k).equals(element) ); }
  public boolean addAll( final Collection<? extends K> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectListIterator <K> iterator() { return ObjectIterators.singleton( element ); }
  public int size() { return 1; }
  public Object clone() { return this; }
 }
 /** Returns a type-specific immutable set containing only the specified element. The returned set is serializable and cloneable.
	 *
	 * @param element the only element of the returned set.
	 * @return a type-specific immutable set containing just <code>element</code>.
	 */
 public static <K> ObjectSet <K> singleton( final K element ) {
  return new Singleton <K>( element );
 }
 /** A synchronized wrapper class for sets. */
 public static class SynchronizedSet <K> extends ObjectCollections.SynchronizedCollection <K> implements ObjectSet <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected SynchronizedSet( final ObjectSet <K> s, final Object sync ) {
   super( s, sync );
  }
  protected SynchronizedSet( final ObjectSet <K> s ) {
   super( s );
  }
  public boolean remove( final Object k ) { synchronized( sync ) { return collection.remove( (k) ); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return collection.equals( o ); } }
  public int hashCode() { synchronized( sync ) { return collection.hashCode(); } }
 }
 /** Returns a synchronized type-specific set backed by the given type-specific set.
	 *
	 * @param s the set to be wrapped in a synchronized set.
	 * @return a synchronized view of the specified set.
	 * @see java.util.Collections#synchronizedSet(Set)
	 */
 public static <K> ObjectSet <K> synchronize( final ObjectSet <K> s ) { return new SynchronizedSet <K>( s ); }
 /** Returns a synchronized type-specific set backed by the given type-specific set, using an assigned object to synchronize.
	 *
	 * @param s the set to be wrapped in a synchronized set.
	 * @param sync an object that will be used to synchronize the access to the set.
	 * @return a synchronized view of the specified set.
	 * @see java.util.Collections#synchronizedSet(Set)
	 */
 public static <K> ObjectSet <K> synchronize( final ObjectSet <K> s, final Object sync ) { return new SynchronizedSet <K>( s, sync ); }
 /** An unmodifiable wrapper class for sets. */
 public static class UnmodifiableSet <K> extends ObjectCollections.UnmodifiableCollection <K> implements ObjectSet <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected UnmodifiableSet( final ObjectSet <K> s ) {
   super( s );
  }
  public boolean remove( final Object k ) { throw new UnsupportedOperationException(); }
  public boolean equals( final Object o ) { return collection.equals( o ); }
  public int hashCode() { return collection.hashCode(); }
 }
 /** Returns an unmodifiable type-specific set backed by the given type-specific set.
	 *
	 * @param s the set to be wrapped in an unmodifiable set.
	 * @return an unmodifiable view of the specified set.
	 * @see java.util.Collections#unmodifiableSet(Set)
	 */
 public static <K> ObjectSet <K> unmodifiable( final ObjectSet <K> s ) { return new UnmodifiableSet <K>( s ); }
}
