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
import java.util.List;
import java.util.Collection;
import java.util.Random;
/** A class providing static methods and objects that do useful things with type-specific lists.
 *
 * @see java.util.Collections
 */
public class ByteLists {
 private ByteLists() {}
 /** Shuffles the specified list using the specified pseudorandom number generator.
	 * 
	 * @param l the list to be shuffled.
	 * @param random a pseudorandom number generator (please use a <a href="http://dsiutils.dsi.unimi.it/docs/it/unimi/dsi/util/XorShiftStarRandom.html">XorShift*</a> generator).
	 * @return <code>l</code>.
	 */
 public static ByteList shuffle( final ByteList l, final Random random ) {
  for( int i = l.size(); i-- != 0; ) {
   final int p = random.nextInt( i + 1 );
   final byte t = l.getByte( i );
   l.set( i, l.getByte( p ) );
   l.set( p, t );
  }
  return l;
 }
 /** An immutable class representing an empty type-specific list.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific list.
	 */
 public static class EmptyList extends ByteCollections.EmptyCollection implements ByteList , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyList() {}
  public void add( final int index, final byte k ) { throw new UnsupportedOperationException(); }
  public boolean add( final byte k ) { throw new UnsupportedOperationException(); }
  public byte removeByte( int i ) { throw new UnsupportedOperationException(); }
  public byte set( final int index, final byte k ) { throw new UnsupportedOperationException(); }
  public int indexOf( byte k ) { return -1; }
  public int lastIndexOf( byte k ) { return -1; }
  public boolean addAll( Collection<? extends Byte> c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( int i, Collection<? extends Byte> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( Collection<?> c ) { throw new UnsupportedOperationException(); }
  public Byte get( int i ) { throw new IndexOutOfBoundsException(); }
  public boolean addAll( ByteCollection c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( ByteList c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( int i, ByteCollection c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( int i, ByteList c ) { throw new UnsupportedOperationException(); }
  public void add( final int index, final Byte k ) { throw new UnsupportedOperationException(); }
  public boolean add( final Byte k ) { throw new UnsupportedOperationException(); }
  public Byte set( final int index, final Byte k ) { throw new UnsupportedOperationException(); }
  public byte getByte( int i ) { throw new IndexOutOfBoundsException(); }
  public Byte remove( int k ) { throw new UnsupportedOperationException(); }
  public int indexOf( Object k ) { return -1; }
  public int lastIndexOf( Object k ) { return -1; }
  //@SuppressWarnings("unchecked")
  //public KEY_ITERATOR KEY_GENERIC iterator( int i ) { if ( i == 0 ) return ITERATORS.EMPTY_ITERATOR; throw new IndexOutOfBoundsException( String.valueOf( i ) ); }
  @Deprecated
  @SuppressWarnings("unchecked")
  public ByteIterator byteIterator() { return ByteIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ByteListIterator listIterator() { return ByteIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ByteListIterator iterator() { return ByteIterators.EMPTY_ITERATOR; }
  @SuppressWarnings("unchecked")
  public ByteListIterator listIterator( int i ) { if ( i == 0 ) return ByteIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException( String.valueOf( i ) ); }

  @Deprecated
  public ByteListIterator byteListIterator() { return listIterator(); }

  @Deprecated
  public ByteListIterator byteListIterator( int i ) { return listIterator( i ); }

  public ByteList subList( int from, int to ) { if ( from == 0 && to == 0 ) return this; throw new IndexOutOfBoundsException(); }

  @Deprecated
  public ByteList byteSubList( int from, int to ) { return subList( from, to ); }

  public void getElements( int from, byte[] a, int offset, int length ) { if ( from == 0 && length == 0 && offset >= 0 && offset <= a.length ) return; throw new IndexOutOfBoundsException(); }
  public void removeElements( int from, int to ) { throw new UnsupportedOperationException(); }

  public void addElements( int index, final byte a[], int offset, int length ) { throw new UnsupportedOperationException(); }
  public void addElements( int index, final byte a[] ) { throw new UnsupportedOperationException(); }

  public void size( int s ) { throw new UnsupportedOperationException(); }

  public int compareTo( final List<? extends Byte> o ) {
   if ( o == this ) return 0;
   return ((List<?>)o).isEmpty() ? 0 : -1;
  }

  private Object readResolve() { return EMPTY_LIST; }
  public Object clone() { return EMPTY_LIST; }
 }

 /** An empty list (immutable). It is serializable and cloneable. 
	 *
	 * <P>The class of this objects represent an abstract empty list
	 * that is a sublist of any type of list. Thus, {@link #EMPTY_LIST}
	 * may be assigned to a variable of any (sorted) type-specific list.
	 */

 @SuppressWarnings("rawtypes")
 public static final EmptyList EMPTY_LIST = new EmptyList();



 /** An immutable class representing a type-specific singleton list. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific list.
	 */

 public static class Singleton extends AbstractByteList implements java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  private final byte element;

  private Singleton( final byte element ) {
   this.element = element;
  }

  public byte getByte( final int i ) { if ( i == 0 ) return element; throw new IndexOutOfBoundsException(); }
  public byte removeByte( final int i ) { throw new UnsupportedOperationException(); }
  public boolean contains( final byte k ) { return ( (k) == (element) ); }

  public boolean addAll( final Collection<? extends Byte> c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final int i, final Collection <? extends Byte> c ) { throw new UnsupportedOperationException(); }
  public boolean removeAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }
  public boolean retainAll( final Collection<?> c ) { throw new UnsupportedOperationException(); }

  /* Slightly optimized w.r.t. the one in ABSTRACT_SET. */

  public byte[] toByteArray() {
   byte a[] = new byte[ 1 ];
   a[ 0 ] = element;
   return a;
  }

  @SuppressWarnings("unchecked")
  public ByteListIterator listIterator() { return ByteIterators.singleton( element ); }

  public ByteListIterator iterator() { return listIterator(); }

  public ByteListIterator listIterator( int i ) {
   if ( i > 1 || i < 0 ) throw new IndexOutOfBoundsException();
   ByteListIterator l = listIterator();
   if ( i == 1 ) l.next();
   return l;
  }

  @SuppressWarnings("unchecked")
  public ByteList subList( final int from, final int to ) {
   ensureIndex( from );
   ensureIndex( to );
   if ( from > to ) throw new IndexOutOfBoundsException( "Start index (" + from + ") is greater than end index (" + to + ")" );

   if ( from != 0 || to != 1 ) return EMPTY_LIST;
   return this;
  }

  public int size() { return 1; }
  public void size( final int size ) { throw new UnsupportedOperationException(); }
  public void clear() { throw new UnsupportedOperationException(); }

  public Object clone() { return this; }


  public boolean rem( final byte k ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final ByteCollection c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final int i, final ByteCollection c ) { throw new UnsupportedOperationException(); }




 }

 /** Returns a type-specific immutable list containing only the specified element. The returned list is serializable and cloneable.
	 *
	 * @param element the only element of the returned list.
	 * @return a type-specific immutable list containing just <code>element</code>.
	 */

 public static ByteList singleton( final byte element ) { return new Singleton ( element ); }



 /** Returns a type-specific immutable list containing only the specified element. The returned list is serializable and cloneable.
	 *
	 * @param element the only element of the returned list.
	 * @return a type-specific immutable list containing just <code>element</code>.
	 */

 public static ByteList singleton( final Object element ) { return new Singleton ( ((((Byte)(element)).byteValue())) ); }




 /** A synchronized wrapper class for lists. */

 public static class SynchronizedList extends ByteCollections.SynchronizedCollection implements ByteList , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ByteList list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.

  protected SynchronizedList( final ByteList l, final Object sync ) {
   super( l, sync );
   this.list = l;
  }

  protected SynchronizedList( final ByteList l ) {
   super( l );
   this.list = l;
  }

  public byte getByte( final int i ) { synchronized( sync ) { return list.getByte( i ); } }
  public byte set( final int i, final byte k ) { synchronized( sync ) { return list.set( i, k ); } }
  public void add( final int i, final byte k ) { synchronized( sync ) { list.add( i, k ); } }
  public byte removeByte( final int i ) { synchronized( sync ) { return list.removeByte( i ); } }

  public int indexOf( final byte k ) { synchronized( sync ) { return list.indexOf( k ); } }
  public int lastIndexOf( final byte k ) { synchronized( sync ) { return list.lastIndexOf( k ); } }

  public boolean addAll( final int index, final Collection<? extends Byte> c ) { synchronized( sync ) { return list.addAll( index, c ); } }

  public void getElements( final int from, final byte a[], final int offset, final int length ) { synchronized( sync ) { list.getElements( from, a, offset, length ); } }
  public void removeElements( final int from, final int to ) { synchronized( sync ) { list.removeElements( from, to ); } }
  public void addElements( int index, final byte a[], int offset, int length ) { synchronized( sync ) { list.addElements( index, a, offset, length ); } }
  public void addElements( int index, final byte a[] ) { synchronized( sync ) { list.addElements( index, a ); } }
  public void size( final int size ) { synchronized( sync ) { list.size( size ); } }

  public ByteListIterator iterator() { return list.listIterator(); }
  public ByteListIterator listIterator() { return list.listIterator(); }
  public ByteListIterator listIterator( final int i ) { return list.listIterator( i ); }

  @Deprecated
  public ByteListIterator byteListIterator() { return listIterator(); }

  @Deprecated
  public ByteListIterator byteListIterator( final int i ) { return listIterator( i ); }

  public ByteList subList( final int from, final int to ) { synchronized( sync ) { return synchronize( list.subList( from, to ), sync ); } }

  @Deprecated
  public ByteList byteSubList( final int from, final int to ) { return subList( from, to ); }

  public boolean equals( final Object o ) { synchronized( sync ) { return collection.equals( o ); } }
  public int hashCode() { synchronized( sync ) { return collection.hashCode(); } }


  public int compareTo( final List<? extends Byte> o ) { synchronized( sync ) { return list.compareTo( o ); } }



  public boolean addAll( final int index, final ByteCollection c ) { synchronized( sync ) { return list.addAll( index, c ); } }
  public boolean addAll( final int index, ByteList l ) { synchronized( sync ) { return list.addAll( index, l ); } }
  public boolean addAll( ByteList l ) { synchronized( sync ) { return list.addAll( l ); } }

  public Byte get( final int i ) { synchronized( sync ) { return list.get( i ); } }
  public void add( final int i, Byte k ) { synchronized( sync ) { list.add( i, k ); } }
  public Byte set( final int index, Byte k ) { synchronized( sync ) { return list.set( index, k ); } }
  public Byte remove( final int i ) { synchronized( sync ) { return list.remove( i ); } }
  public int indexOf( final Object o ) { synchronized( sync ) { return list.indexOf( o ); } }
  public int lastIndexOf( final Object o ) { synchronized( sync ) { return list.lastIndexOf( o ); } }

 }


 /** Returns a synchronized type-specific list backed by the given type-specific list.
	 *
	 * @param l the list to be wrapped in a synchronized list.
	 * @return a synchronized view of the specified list.
	 * @see java.util.Collections#synchronizedList(List)
	 */
 public static ByteList synchronize( final ByteList l ) { return new SynchronizedList ( l ); }

 /** Returns a synchronized type-specific list backed by the given type-specific list, using an assigned object to synchronize.
	 *
	 * @param l the list to be wrapped in a synchronized list.
	 * @param sync an object that will be used to synchronize the access to the list.
	 * @return a synchronized view of the specified list.
	 * @see java.util.Collections#synchronizedList(List)
	 */

 public static ByteList synchronize( final ByteList l, final Object sync ) { return new SynchronizedList ( l, sync ); }



 /** An unmodifiable wrapper class for lists. */

 public static class UnmodifiableList extends ByteCollections.UnmodifiableCollection implements ByteList , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ByteList list; // Due to the large number of methods that are not in COLLECTION, this is worth caching.

  protected UnmodifiableList( final ByteList l ) {
   super( l );
   this.list = l;
  }

  public byte getByte( final int i ) { return list.getByte( i ); }
  public byte set( final int i, final byte k ) { throw new UnsupportedOperationException(); }
  public void add( final int i, final byte k ) { throw new UnsupportedOperationException(); }
  public byte removeByte( final int i ) { throw new UnsupportedOperationException(); }

  public int indexOf( final byte k ) { return list.indexOf( k ); }
  public int lastIndexOf( final byte k ) { return list.lastIndexOf( k ); }

  public boolean addAll( final int index, final Collection<? extends Byte> c ) { throw new UnsupportedOperationException(); }

  public void getElements( final int from, final byte a[], final int offset, final int length ) { list.getElements( from, a, offset, length ); }
  public void removeElements( final int from, final int to ) { throw new UnsupportedOperationException(); }
  public void addElements( int index, final byte a[], int offset, int length ) { throw new UnsupportedOperationException(); }
  public void addElements( int index, final byte a[] ) { throw new UnsupportedOperationException(); }
  public void size( final int size ) { list.size( size ); }

  public ByteListIterator iterator() { return listIterator(); }
  public ByteListIterator listIterator() { return ByteIterators.unmodifiable( list.listIterator() ); }
  public ByteListIterator listIterator( final int i ) { return ByteIterators.unmodifiable( list.listIterator( i ) ); }

  @Deprecated
  public ByteListIterator byteListIterator() { return listIterator(); }

  @Deprecated
  public ByteListIterator byteListIterator( final int i ) { return listIterator( i ); }

  public ByteList subList( final int from, final int to ) { return unmodifiable( list.subList( from, to ) ); }

  @Deprecated
  public ByteList byteSubList( final int from, final int to ) { return subList( from, to ); }

  public boolean equals( final Object o ) { return collection.equals( o ); }
  public int hashCode() { return collection.hashCode(); }


  public int compareTo( final List<? extends Byte> o ) { return list.compareTo( o ); }



  public boolean addAll( final int index, final ByteCollection c ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final ByteList l ) { throw new UnsupportedOperationException(); }
  public boolean addAll( final int index, final ByteList l ) { throw new UnsupportedOperationException(); }
  public Byte get( final int i ) { return list.get( i ); }
  public void add( final int i, Byte k ) { throw new UnsupportedOperationException(); }
  public Byte set( final int index, Byte k ) { throw new UnsupportedOperationException(); }
  public Byte remove( final int i ) { throw new UnsupportedOperationException(); }
  public int indexOf( final Object o ) { return list.indexOf( o ); }
  public int lastIndexOf( final Object o ) { return list.lastIndexOf( o ); }

 }


 /** Returns an unmodifiable type-specific list backed by the given type-specific list.
	 *
	 * @param l the list to be wrapped in an unmodifiable list.
	 * @return an unmodifiable view of the specified list.
	 * @see java.util.Collections#unmodifiableList(List)
	 */
 public static ByteList unmodifiable( final ByteList l ) { return new UnmodifiableList ( l ); }
}
