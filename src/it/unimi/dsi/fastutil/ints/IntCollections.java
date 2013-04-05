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
package it.unimi.dsi.fastutil.ints;
import java.util.Collection;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
/** A class providing static methods and objects that do useful things with type-specific collections.
 *
 * @see java.util.Collections
 */
public class IntCollections {
 private IntCollections() {}
 /** An immutable class representing an empty type-specific collection.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific collection.
	 */
 public abstract static class EmptyCollection extends AbstractIntCollection {
  protected EmptyCollection() {}
  public boolean add( int k ) { throw new UnsupportedOperationException(); }
  public boolean contains( int k ) { return false; }
  public Object[] toArray() { return ObjectArrays.EMPTY_ARRAY; }
  public int[] toIntArray( int[] a ) { return a; }
  public int[] toIntArray() { return IntArrays.EMPTY_ARRAY; }
  public boolean rem( int k ) { throw new UnsupportedOperationException(); }
  public boolean addAll( IntCollection c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( IntCollection c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( IntCollection c ) { throw new UnsupportedOperationException(); }
  public boolean containsAll( IntCollection c ) { return c.isEmpty(); }
  @SuppressWarnings("unchecked")
  public IntBidirectionalIterator iterator() { return IntIterators.EMPTY_ITERATOR; }
  public int size() { return 0; }
  public void clear() {}
  public int hashCode() { return 0; }
  public boolean equals( Object o ) {
   if ( o == this ) return true;
   if ( ! ( o instanceof Collection ) ) return false;
   return ((Collection<?>)o).isEmpty();
  }
 }
 /** A synchronized wrapper class for collections. */

 public static class SynchronizedCollection implements IntCollection , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final IntCollection collection;
  protected final Object sync;

  protected SynchronizedCollection( final IntCollection c, final Object sync ) {
   if ( c == null ) throw new NullPointerException();
   this.collection = c;
   this.sync = sync;
  }

  protected SynchronizedCollection( final IntCollection c ) {
   if ( c == null ) throw new NullPointerException();
   this.collection = c;
   this.sync = this;
  }

  public int size() { synchronized( sync ) { return collection.size(); } }
  public boolean isEmpty() { synchronized( sync ) { return collection.isEmpty(); } }
  public boolean contains( final int o ) { synchronized( sync ) { return collection.contains( o ); } }

  public int[] toIntArray() { synchronized( sync ) { return collection.toIntArray(); } }


  public Object[] toArray() { synchronized( sync ) { return collection.toArray(); } }
  public int[] toIntArray( final int[] a ) { synchronized( sync ) { return collection.toIntArray( a ); } }
  public int[] toArray( final int[] a ) { synchronized( sync ) { return collection.toIntArray( a ); } }

  public boolean addAll( final IntCollection c ) { synchronized( sync ) { return collection.addAll( c ); } }
  public boolean containsAll( final IntCollection c ) { synchronized( sync ) { return collection.containsAll( c ); } }
  public boolean removeAll( final IntCollection c ) { synchronized( sync ) { return collection.removeAll( c ); } }
  public boolean retainAll( final IntCollection c ) { synchronized( sync ) { return collection.retainAll( c ); } }

  public boolean add( final Integer k ) { synchronized( sync ) { return collection.add( k ); } }
  public boolean contains( final Object k ) { synchronized( sync ) { return collection.contains( k ); } }


  public <T> T[] toArray( final T[] a ) { synchronized( sync ) { return collection.toArray( a ); } }

  public IntIterator iterator() { return collection.iterator(); }

  @Deprecated
  public IntIterator intIterator() { return iterator(); }

  public boolean add( final int k ) { synchronized( sync ) { return collection.add( k ); } }
  public boolean rem( final int k ) { synchronized( sync ) { return collection.rem( k ); } }
  public boolean remove( final Object ok ) { synchronized( sync ) { return collection.remove( ok ); } }

  public boolean addAll( final Collection<? extends Integer> c ) { synchronized( sync ) { return collection.addAll( c ); } }
  public boolean containsAll( final Collection<?> c ) { synchronized( sync ) { return collection.containsAll( c ); } }
  public boolean removeAll( final Collection<?> c ) { synchronized( sync ) { return collection.removeAll( c ); } }
  public boolean retainAll( final Collection<?> c ) { synchronized( sync ) { return collection.retainAll( c ); } }

  public void clear() { synchronized( sync ) { collection.clear(); } }
  public String toString() { synchronized( sync ) { return collection.toString(); } }
 }


 /** Returns a synchronized collection backed by the specified collection.
	 *
	 * @param c the collection to be wrapped in a synchronized collection.
	 * @return a synchronized view of the specified collection.
	 * @see java.util.Collections#synchronizedCollection(Collection)
	 */
 public static IntCollection synchronize( final IntCollection c ) { return new SynchronizedCollection ( c ); }

 /** Returns a synchronized collection backed by the specified collection, using an assigned object to synchronize.
	 *
	 * @param c the collection to be wrapped in a synchronized collection.
	 * @param sync an object that will be used to synchronize the list access.
	 * @return a synchronized view of the specified collection.
	 * @see java.util.Collections#synchronizedCollection(Collection)
	 */

 public static IntCollection synchronize( final IntCollection c, final Object sync ) { return new SynchronizedCollection ( c, sync ); }


 /** An unmodifiable wrapper class for collections. */

 public static class UnmodifiableCollection implements IntCollection , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final IntCollection collection;

  protected UnmodifiableCollection( final IntCollection c ) {
   if ( c == null ) throw new NullPointerException();
   this.collection = c;
  }

  public int size() { return collection.size(); }
  public boolean isEmpty() { return collection.isEmpty(); }
  public boolean contains( final int o ) { return collection.contains( o ); }

  public IntIterator iterator() { return IntIterators.unmodifiable( collection.iterator() ); }

  @Deprecated
  public IntIterator intIterator() { return iterator(); }

  public boolean add( final int k ) { throw new UnsupportedOperationException(); }
  public boolean remove( final Object ok ) { throw new UnsupportedOperationException(); }

  public boolean addAll( final Collection<? extends Integer> c ) { throw new UnsupportedOperationException(); }
  public boolean containsAll( final Collection<?> c ) { return collection.containsAll( c ); }
  public boolean removeAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return collection.toString(); }

  public <T> T[] toArray( final T[] a ) { return collection.toArray( a ); }

  public Object[] toArray() { return collection.toArray(); }


  public int[] toIntArray() { return collection.toIntArray(); }
  public int[] toIntArray( final int[] a ) { return collection.toIntArray( a ); }
  public int[] toArray( final int[] a ) { return collection.toArray( a ); }
  public boolean rem( final int k ) { throw new UnsupportedOperationException(); }

  public boolean addAll( final IntCollection c ) { throw new UnsupportedOperationException(); }
  public boolean containsAll( final IntCollection c ) { return collection.containsAll( c ); }
  public boolean removeAll( final IntCollection c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final IntCollection c ) { throw new UnsupportedOperationException(); }

  public boolean add( final Integer k ) { throw new UnsupportedOperationException(); }
  public boolean contains( final Object k ) { return collection.contains( k ); }

 }


 /** Returns an unmodifiable collection backed by the specified collection.
	 *
	 * @param c the collection to be wrapped in an unmodifiable collection.
	 * @return an unmodifiable view of the specified collection.
	 * @see java.util.Collections#unmodifiableCollection(Collection)
	 */
 public static IntCollection unmodifiable( final IntCollection c ) { return new UnmodifiableCollection ( c ); }

 /** A collection wrapper class for iterables. */

 public static class IterableCollection extends AbstractIntCollection implements java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final IntIterable iterable;

  protected IterableCollection( final IntIterable iterable ) {
   if ( iterable == null ) throw new NullPointerException();
   this.iterable = iterable;
  }

  public int size() {
   int c = 0;
   final IntIterator iterator = iterator();
   while( iterator.hasNext() ) {
    iterator.next();
    c++;
   }

   return c;
  }

  public boolean isEmpty() { return iterable.iterator().hasNext(); }
  public IntIterator iterator() { return iterable.iterator(); }

  @Deprecated
  public IntIterator intIterator() { return iterator(); }
 }


 /** Returns an unmodifiable collection backed by the specified iterable.
	 *
	 * @param iterable the iterable object to be wrapped in an unmodifiable collection.
	 * @return an unmodifiable collection view of the specified iterable.
	 */
 @SuppressWarnings("unchecked")
 public static IntCollection asCollection( final IntIterable iterable ) {
  if ( iterable instanceof IntCollection ) return (IntCollection )iterable;
  return new IterableCollection ( iterable );
 }

}
