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
import it.unimi.dsi.fastutil.BigList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
/** A class providing static methods and objects that do useful things with type-specific big lists.
 *
 * @see java.util.Collections
 * @see it.unimi.dsi.fastutil.BigList
 */
public class ObjectBigLists {
 private ObjectBigLists() {}
 /** Shuffles the specified big list using the specified pseudorandom number generator.
	 * 
	 * @param l the big list to be shuffled.
	 * @param random a pseudorandom number generator (please use a <a href="http://dsiutils.dsi.unimi.it/docs/it/unimi/dsi/util/XorShiftStarRandom.html">XorShift*</a> generator).
	 * @return <code>l</code>.
	 */
 public static <K> ObjectBigList <K> shuffle( final ObjectBigList <K> l, final Random random ) {
  for( long i = l.size64(); i-- != 0; ) {
   final long p = ( random.nextLong() & 0x7FFFFFFFFFFFFFFFL ) % ( i + 1 );
   final K t = l.get( i );
   l.set( i, l.get( p ) );
   l.set( p, t );
  }
  return l;
 }
 /** An immutable class representing an empty type-specific big list.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific list.
	 */
 public static class EmptyBigList <K> extends ObjectCollections.EmptyCollection <K> implements ObjectBigList <K>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyBigList() {}
  public void add( final long index, final K k ) { throw new UnsupportedOperationException(); }
  public boolean add( final K k ) { throw new UnsupportedOperationException(); }
  public K remove( long i ) { throw new UnsupportedOperationException(); }
  public K set( final long index, final K k ) { throw new UnsupportedOperationException(); }
  public long indexOf( Object k ) { return -1; }
  public long lastIndexOf( Object k ) { return -1; }
  public boolean addAll( Collection<? extends K> c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( long i, Collection<? extends K> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( Collection<?> c ) { throw new UnsupportedOperationException(); }
  public K get( long i ) { throw new IndexOutOfBoundsException(); }
  public boolean remove( Object k ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectBigListIterator <K> listIterator() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ObjectBigListIterator <K> iterator() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ObjectBigListIterator <K> listIterator( long i ) { if ( i == 0 ) return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException( String.valueOf( i ) ); }
  public ObjectBigList <K> subList( long from, long to ) { if ( from == 0 && to == 0 ) return this; throw new IndexOutOfBoundsException(); }
  public void getElements( long from, Object[][] a, long offset, long length ) { ObjectBigArrays.ensureOffsetLength( a, offset, length ); if ( from != 0 ) throw new IndexOutOfBoundsException(); }
  public void removeElements( long from, long to ) { throw new UnsupportedOperationException(); }
  public void addElements( long index, final K a[][], long offset, long length ) { throw new UnsupportedOperationException(); }
  public void addElements( long index, final K a[][] ) { throw new UnsupportedOperationException(); }
  public void size( long s ) { throw new UnsupportedOperationException(); }
  public long size64() { return 0; }
  public int compareTo( final BigList<? extends K> o ) {
   if ( o == this ) return 0;
   return ((BigList<?>)o).isEmpty() ? 0 : -1;
  }
  private Object readResolve() { return EMPTY_BIG_LIST; }
  public Object clone() { return EMPTY_BIG_LIST; }
 }
 /** An empty big list (immutable). It is serializable and cloneable. 
	 *
	 * <P>The class of this objects represent an abstract empty list
	 * that is a sublist of any type of list. Thus, {@link #EMPTY_BIG_LIST}
	 * may be assigned to a variable of any (sorted) type-specific list.
	 */
 @SuppressWarnings("rawtypes")
 public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
 /** An immutable class representing a type-specific singleton big list. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific big list.
	 */
 public static class Singleton <K> extends AbstractObjectBigList <K> implements java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  private final K element;
  private Singleton( final K element ) {
   this.element = element;
  }
  public K get( final long i ) { if ( i == 0 ) return element; throw new IndexOutOfBoundsException(); }
  public K remove( final long i ) { throw new UnsupportedOperationException(); }
  public boolean contains( final Object k ) { return ( (k) == null ? (element) == null : (k).equals(element) ); }
  public boolean addAll( final Collection<? extends K> c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final long i, final Collection <? extends K> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  /* Slightly optimized w.r.t. the one in ABSTRACT_SET. */
  public Object[] toArray() {
   Object a[] = new Object[ 1 ];
   a[ 0 ] = element;
   return a;
  }
  @SuppressWarnings("unchecked")
  public ObjectBigListIterator <K> listIterator() { return ObjectBigListIterators.singleton( element ); }
  public ObjectBigListIterator <K> iterator() { return listIterator(); }
  public ObjectBigListIterator <K> listIterator( long i ) {
   if ( i > 1 || i < 0 ) throw new IndexOutOfBoundsException();
   ObjectBigListIterator <K> l = listIterator();
   if ( i == 1 ) l.next();
   return l;
  }
  @SuppressWarnings("unchecked")
  public ObjectBigList <K> subList( final long from, final long to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );
   if ( from != 0 || to != 1 ) return EMPTY_BIG_LIST;
   return this;
  }
  @Deprecated
  public int size() { return 1; }
  public long size64() { return 1; }
  public void size( final long size ) { throw new UnsupportedOperationException(); }
  public void clear() { throw new UnsupportedOperationException(); }
  public Object clone() { return this; }
  public boolean remove( Object k ) { throw new UnsupportedOperationException(); }
 }
 /** Returns a type-specific immutable big list containing only the specified element. The returned big list is serializable and cloneable.
	 *
	 * @param element the only element of the returned big list.
	 * @return a type-specific immutable big list containing just <code>element</code>.
	 */
 public static <K> ObjectBigList <K> singleton( final K element ) { return new Singleton <K>( element ); }
 /** A synchronized wrapper class for big lists. */
 public static class SynchronizedBigList <K> extends ObjectCollections.SynchronizedCollection <K> implements ObjectBigList <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final ObjectBigList <K> list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.
  protected SynchronizedBigList( final ObjectBigList <K> l, final Object sync ) {
   super( l, sync );
   this.list = l;
  }
  protected SynchronizedBigList( final ObjectBigList <K> l ) {
   super( l );
   this.list = l;
  }
  public K get( final long i ) { synchronized( sync ) { return list.get( i ); } }
  public K set( final long i, final K k ) { synchronized( sync ) { return list.set( i, k ); } }
  public void add( final long i, final K k ) { synchronized( sync ) { list.add( i, k ); } }
  public K remove( final long i ) { synchronized( sync ) { return list.remove( i ); } }
  public long indexOf( final Object k ) { synchronized( sync ) { return list.indexOf( k ); } }
  public long lastIndexOf( final Object k ) { synchronized( sync ) { return list.lastIndexOf( k ); } }
  public boolean addAll( final long index, final Collection<? extends K> c ) { synchronized( sync ) { return list.addAll( index, c ); } }
  public void getElements( final long from, final Object a[][], final long offset, final long length ) { synchronized( sync ) { list.getElements( from, a, offset, length ); } }
  public void removeElements( final long from, final long to ) { synchronized( sync ) { list.removeElements( from, to ); } }
  public void addElements( long index, final K a[][], long offset, long length ) { synchronized( sync ) { list.addElements( index, a, offset, length ); } }
  public void addElements( long index, final K a[][] ) { synchronized( sync ) { list.addElements( index, a ); } }
  public void size( final long size ) { synchronized( sync ) { list.size( size ); } }
  public long size64() { synchronized( sync ) { return list.size64(); } }
  public ObjectBigListIterator <K> iterator() { return list.listIterator(); }
  public ObjectBigListIterator <K> listIterator() { return list.listIterator(); }
  public ObjectBigListIterator <K> listIterator( final long i ) { return list.listIterator( i ); }
  public ObjectBigList <K> subList( final long from, final long to ) { synchronized( sync ) { return synchronize( list.subList( from, to ), sync ); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return list.equals( o ); } }
  public int hashCode() { synchronized( sync ) { return list.hashCode(); } }
  public int compareTo( final BigList<? extends K> o ) { synchronized( sync ) { return list.compareTo( o ); } }
 }
 /** Returns a synchronized type-specific big list backed by the given type-specific big list.
	 *
	 * @param l the big list to be wrapped in a synchronized big list.
	 * @return a synchronized view of the specified big list.
	 * @see java.util.Collections#synchronizedList(List)
	 */
 public static <K> ObjectBigList <K> synchronize( final ObjectBigList <K> l ) { return new SynchronizedBigList <K>( l ); }
 /** Returns a synchronized type-specific big list backed by the given type-specific big list, using an assigned object to synchronize.
	 *
	 * @param l the big list to be wrapped in a synchronized big list.
	 * @param sync an object that will be used to synchronize the access to the big list.
	 * @return a synchronized view of the specified big list.
	 * @see java.util.Collections#synchronizedList(List)
	 */
 public static <K> ObjectBigList <K> synchronize( final ObjectBigList <K> l, final Object sync ) { return new SynchronizedBigList <K>( l, sync ); }
 /** An unmodifiable wrapper class for big lists. */
 public static class UnmodifiableBigList <K> extends ObjectCollections.UnmodifiableCollection <K> implements ObjectBigList <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final ObjectBigList <K> list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.
  protected UnmodifiableBigList( final ObjectBigList <K> l ) {
   super( l );
   this.list = l;
  }
  public K get( final long i ) { return list.get( i ); }
  public K set( final long i, final K k ) { throw new UnsupportedOperationException(); }
  public void add( final long i, final K k ) { throw new UnsupportedOperationException(); }
  public K remove( final long i ) { throw new UnsupportedOperationException(); }
  public long indexOf( final Object k ) { return list.indexOf( k ); }
  public long lastIndexOf( final Object k ) { return list.lastIndexOf( k ); }
  public boolean addAll( final long index, final Collection<? extends K> c ) { throw new UnsupportedOperationException(); }
  public void getElements( final long from, final Object a[][], final long offset, final long length ) { list.getElements( from, a, offset, length ); }
  public void removeElements( final long from, final long to ) { throw new UnsupportedOperationException(); }
  public void addElements( long index, final K a[][], long offset, long length ) { throw new UnsupportedOperationException(); }
  public void addElements( long index, final K a[][] ) { throw new UnsupportedOperationException(); }
  public void size( final long size ) { list.size( size ); }
  public long size64() { return list.size64(); }
  public ObjectBigListIterator <K> iterator() { return listIterator(); }
  public ObjectBigListIterator <K> listIterator() { return ObjectBigListIterators.unmodifiable( list.listIterator() ); }
  public ObjectBigListIterator <K> listIterator( final long i ) { return ObjectBigListIterators.unmodifiable( list.listIterator( i ) ); }
  public ObjectBigList <K> subList( final long from, final long to ) { return unmodifiable( list.subList( from, to ) ); }
  public boolean equals( final Object o ) { return list.equals( o ); }
  public int hashCode() { return list.hashCode(); }
  public int compareTo( final BigList<? extends K> o ) { return list.compareTo( o ); }
 }
 /** Returns an unmodifiable type-specific big list backed by the given type-specific big list.
	 *
	 * @param l the big list to be wrapped in an unmodifiable big list.
	 * @return an unmodifiable view of the specified big list.
	 * @see java.util.Collections#unmodifiableList(List)
	 */
 public static <K> ObjectBigList <K> unmodifiable( final ObjectBigList <K> l ) { return new UnmodifiableBigList <K>( l ); }
 /** A class exposing a list as a big list. */
 public static class ListBigList <K> extends AbstractObjectBigList <K> implements java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  private final ObjectList <K> list;
  protected ListBigList( final ObjectList <K> list ) {
   this.list = list;
  }
  private int intIndex( long index ) {
   if ( index >= Integer.MAX_VALUE ) throw new IndexOutOfBoundsException( "This big list is restricted to 32-bit indices" );
   return (int)index;
  }
  public long size64() { return list.size(); }
  @Deprecated
  public int size() { return list.size(); }
  public void size( final long size ) { list.size( intIndex( size ) ); }
  public ObjectBigListIterator <K> iterator() { return ObjectBigListIterators.asBigListIterator( list.iterator() ); }
  public ObjectBigListIterator <K> listIterator() { return ObjectBigListIterators.asBigListIterator( list.listIterator() ); }
  public boolean addAll( final long index, final Collection<? extends K> c ) { return list.addAll( intIndex( index ), c ); }
  public ObjectBigListIterator <K> listIterator( final long index ) { return ObjectBigListIterators.asBigListIterator( list.listIterator( intIndex( index ) ) ); }
  public ObjectBigList <K> subList( long from, long to ) { return new ListBigList <K>( list.subList( intIndex( from ), intIndex( to ) ) ); }
  public boolean contains( final Object key ) { return list.contains( key ); }
  public Object[] toArray() { return list.toArray(); }
  public void removeElements( final long from, final long to ) { list.removeElements( intIndex( from ), intIndex( to ) ); }
  public void add( long index, K key ) { list.add( intIndex( index ), key ); }
  public boolean addAll( long index, ObjectCollection <K> c ) { return list.addAll( intIndex( index ), c ); }
  public boolean addAll( long index, ObjectBigList <K> c ) { return list.addAll( intIndex( index ), c ); }
  public boolean add( K key ) { return list.add( key ); }
  public boolean addAll( ObjectBigList <K> c ) { return list.addAll( c ); }
  public K get( long index ) { return list.get( intIndex( index ) ); }
  public long indexOf( Object k ) { return list.indexOf( k ); }
  public long lastIndexOf( Object k ) { return list.lastIndexOf( k ); }
  public K remove( long index ) { return list.remove( intIndex( index ) ); }
  public K set( long index, K k ) { return list.set( intIndex( index ), k ); }
  public boolean addAll( ObjectCollection <K> c ) { return list.addAll( c ); }
  public boolean containsAll( ObjectCollection <K> c ) { return list.containsAll( c ); }
  public boolean removeAll( ObjectCollection <K> c ) { return list.removeAll( c ); }
  public boolean retainAll( ObjectCollection <K> c ) { return list.retainAll( c ); }
  public boolean isEmpty() { return list.isEmpty(); }
  public <T> T[] toArray( T[] a ) { return list.toArray( a ); }
  public boolean containsAll( Collection<?> c ) { return list.containsAll( c ); }
  public boolean addAll( Collection<? extends K> c ) { return list.addAll( c ); }
  public boolean removeAll( Collection<?> c ) { return list.removeAll( c ); }
  public boolean retainAll( Collection<?> c ) { return list.retainAll( c ); }
  public void clear() { list.clear(); }
  public int hashCode() { return list.hashCode(); }
 }
 /** Returns a big list backed by the specified list.
	*
	* @param list a list.
	* @return a big list backed by the specified list.
	*/
 public static <K> ObjectBigList <K> asBigList( final ObjectList <K> list ) { return new ListBigList <K>( list ); }
}
