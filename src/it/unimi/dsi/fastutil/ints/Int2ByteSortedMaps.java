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
/* Primitive-type-only definitions (values) */
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
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted maps.
 *
 * @see java.util.Collections
 */
public class Int2ByteSortedMaps {
 private Int2ByteSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Integer, ?>> entryComparator( final IntComparator comparator ) {
  return new Comparator<Map.Entry<Integer, ?>>() {
   public int compare( Map.Entry<Integer, ?> x, Map.Entry<Integer, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap extends Int2ByteMaps.EmptyMap implements Int2ByteSortedMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public IntComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Int2ByteMap.Entry > int2ByteEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public IntSortedSet keySet() { return IntSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap subMap( final int from, final int to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap headMap( final int to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap tailMap( final int from ) { return EMPTY_MAP; }
  public int firstIntKey() { throw new NoSuchElementException(); }
  public int lastIntKey() { throw new NoSuchElementException(); }
  public Int2ByteSortedMap headMap( Integer oto ) { return headMap( ((oto).intValue()) ); }
  public Int2ByteSortedMap tailMap( Integer ofrom ) { return tailMap( ((ofrom).intValue()) ); }
  public Int2ByteSortedMap subMap( Integer ofrom, Integer oto ) { return subMap( ((ofrom).intValue()), ((oto).intValue()) ); }
  public Integer firstKey() { return (Integer.valueOf(firstIntKey())); }
  public Integer lastKey() { return (Integer.valueOf(lastIntKey())); }
 }

 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton extends Int2ByteMaps.Singleton implements Int2ByteSortedMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final IntComparator comparator;

  protected Singleton( final int key, final byte value, IntComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final int key, final byte value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final int k1, final int k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public IntComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Int2ByteMap.Entry > int2ByteEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Int2ByteMap.Entry )new SingletonEntry(), (Comparator<? super Int2ByteMap.Entry >)entryComparator( comparator ) ); return (ObjectSortedSet<Int2ByteMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet() { return (ObjectSortedSet)int2ByteEntrySet(); }

  public IntSortedSet keySet() { if ( keys == null ) keys = IntSortedSets.singleton( key, comparator ); return (IntSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap subMap( final int from, final int to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap headMap( final int to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Int2ByteSortedMap tailMap( final int from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public int firstIntKey() { return key; }
  public int lastIntKey() { return key; }


  public Int2ByteSortedMap headMap( Integer oto ) { return headMap( ((oto).intValue()) ); }
  public Int2ByteSortedMap tailMap( Integer ofrom ) { return tailMap( ((ofrom).intValue()) ); }
  public Int2ByteSortedMap subMap( Integer ofrom, Integer oto ) { return subMap( ((ofrom).intValue()), ((oto).intValue()) ); }

  public Integer firstKey() { return (Integer.valueOf(firstIntKey())); }
  public Integer lastKey() { return (Integer.valueOf(lastIntKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Int2ByteSortedMap singleton( final Integer key, Byte value ) {
  return new Singleton ( ((key).intValue()), ((value).byteValue()) );
 }

 /** RETURNS a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Int2ByteSortedMap singleton( final Integer key, Byte value, IntComparator comparator ) {
  return new Singleton ( ((key).intValue()), ((value).byteValue()), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Int2ByteSortedMap singleton( final int key, final byte value ) {
  return new Singleton ( key, value );
 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Int2ByteSortedMap singleton( final int key, final byte value, IntComparator comparator ) {
  return new Singleton ( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap extends Int2ByteMaps.SynchronizedMap implements Int2ByteSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Int2ByteSortedMap sortedMap;

  protected SynchronizedSortedMap( final Int2ByteSortedMap m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Int2ByteSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public IntComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Int2ByteMap.Entry > int2ByteEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.int2ByteEntrySet(), sync ); return (ObjectSortedSet<Int2ByteMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet() { return (ObjectSortedSet)int2ByteEntrySet(); }
  public IntSortedSet keySet() { if ( keys == null ) keys = IntSortedSets.synchronize( sortedMap.keySet(), sync ); return (IntSortedSet )keys; }

  public Int2ByteSortedMap subMap( final int from, final int to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Int2ByteSortedMap headMap( final int to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Int2ByteSortedMap tailMap( final int from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }

  public int firstIntKey() { synchronized( sync ) { return sortedMap.firstIntKey(); } }
  public int lastIntKey() { synchronized( sync ) { return sortedMap.lastIntKey(); } }


  public Integer firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Integer lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Int2ByteSortedMap subMap( final Integer from, final Integer to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Int2ByteSortedMap headMap( final Integer to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Int2ByteSortedMap tailMap( final Integer from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static Int2ByteSortedMap synchronize( final Int2ByteSortedMap m ) { return new SynchronizedSortedMap ( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static Int2ByteSortedMap synchronize( final Int2ByteSortedMap m, final Object sync ) { return new SynchronizedSortedMap ( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap extends Int2ByteMaps.UnmodifiableMap implements Int2ByteSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Int2ByteSortedMap sortedMap;

  protected UnmodifiableSortedMap( final Int2ByteSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public IntComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Int2ByteMap.Entry > int2ByteEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.int2ByteEntrySet() ); return (ObjectSortedSet<Int2ByteMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet() { return (ObjectSortedSet)int2ByteEntrySet(); }
  public IntSortedSet keySet() { if ( keys == null ) keys = IntSortedSets.unmodifiable( sortedMap.keySet() ); return (IntSortedSet )keys; }

  public Int2ByteSortedMap subMap( final int from, final int to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Int2ByteSortedMap headMap( final int to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Int2ByteSortedMap tailMap( final int from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }

  public int firstIntKey() { return sortedMap.firstIntKey(); }
  public int lastIntKey() { return sortedMap.lastIntKey(); }


  public Integer firstKey() { return sortedMap.firstKey(); }
  public Integer lastKey() { return sortedMap.lastKey(); }

  public Int2ByteSortedMap subMap( final Integer from, final Integer to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Int2ByteSortedMap headMap( final Integer to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Int2ByteSortedMap tailMap( final Integer from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static Int2ByteSortedMap unmodifiable( final Int2ByteSortedMap m ) { return new UnmodifiableSortedMap ( m ); }
}
