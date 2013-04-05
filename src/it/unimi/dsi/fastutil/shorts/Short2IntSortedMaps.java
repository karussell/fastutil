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
package it.unimi.dsi.fastutil.shorts;
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
public class Short2IntSortedMaps {
 private Short2IntSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Short, ?>> entryComparator( final ShortComparator comparator ) {
  return new Comparator<Map.Entry<Short, ?>>() {
   public int compare( Map.Entry<Short, ?> x, Map.Entry<Short, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap extends Short2IntMaps.EmptyMap implements Short2IntSortedMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public ShortComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Short2IntMap.Entry > short2IntEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Short, Integer>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ShortSortedSet keySet() { return ShortSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Short2IntSortedMap subMap( final short from, final short to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Short2IntSortedMap headMap( final short to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Short2IntSortedMap tailMap( final short from ) { return EMPTY_MAP; }
  public short firstShortKey() { throw new NoSuchElementException(); }
  public short lastShortKey() { throw new NoSuchElementException(); }
  public Short2IntSortedMap headMap( Short oto ) { return headMap( ((oto).shortValue()) ); }
  public Short2IntSortedMap tailMap( Short ofrom ) { return tailMap( ((ofrom).shortValue()) ); }
  public Short2IntSortedMap subMap( Short ofrom, Short oto ) { return subMap( ((ofrom).shortValue()), ((oto).shortValue()) ); }
  public Short firstKey() { return (Short.valueOf(firstShortKey())); }
  public Short lastKey() { return (Short.valueOf(lastShortKey())); }
 }

 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton extends Short2IntMaps.Singleton implements Short2IntSortedMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final ShortComparator comparator;

  protected Singleton( final short key, final int value, ShortComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final short key, final int value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final short k1, final short k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public ShortComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Short2IntMap.Entry )new SingletonEntry(), (Comparator<? super Short2IntMap.Entry >)entryComparator( comparator ) ); return (ObjectSortedSet<Short2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Short, Integer>> entrySet() { return (ObjectSortedSet)short2IntEntrySet(); }

  public ShortSortedSet keySet() { if ( keys == null ) keys = ShortSortedSets.singleton( key, comparator ); return (ShortSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Short2IntSortedMap subMap( final short from, final short to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Short2IntSortedMap headMap( final short to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Short2IntSortedMap tailMap( final short from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public short firstShortKey() { return key; }
  public short lastShortKey() { return key; }


  public Short2IntSortedMap headMap( Short oto ) { return headMap( ((oto).shortValue()) ); }
  public Short2IntSortedMap tailMap( Short ofrom ) { return tailMap( ((ofrom).shortValue()) ); }
  public Short2IntSortedMap subMap( Short ofrom, Short oto ) { return subMap( ((ofrom).shortValue()), ((oto).shortValue()) ); }

  public Short firstKey() { return (Short.valueOf(firstShortKey())); }
  public Short lastKey() { return (Short.valueOf(lastShortKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Short2IntSortedMap singleton( final Short key, Integer value ) {
  return new Singleton ( ((key).shortValue()), ((value).intValue()) );
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

 public static Short2IntSortedMap singleton( final Short key, Integer value, ShortComparator comparator ) {
  return new Singleton ( ((key).shortValue()), ((value).intValue()), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Short2IntSortedMap singleton( final short key, final int value ) {
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

 public static Short2IntSortedMap singleton( final short key, final int value, ShortComparator comparator ) {
  return new Singleton ( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap extends Short2IntMaps.SynchronizedMap implements Short2IntSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Short2IntSortedMap sortedMap;

  protected SynchronizedSortedMap( final Short2IntSortedMap m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Short2IntSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public ShortComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.short2IntEntrySet(), sync ); return (ObjectSortedSet<Short2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Short, Integer>> entrySet() { return (ObjectSortedSet)short2IntEntrySet(); }
  public ShortSortedSet keySet() { if ( keys == null ) keys = ShortSortedSets.synchronize( sortedMap.keySet(), sync ); return (ShortSortedSet )keys; }

  public Short2IntSortedMap subMap( final short from, final short to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Short2IntSortedMap headMap( final short to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Short2IntSortedMap tailMap( final short from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }

  public short firstShortKey() { synchronized( sync ) { return sortedMap.firstShortKey(); } }
  public short lastShortKey() { synchronized( sync ) { return sortedMap.lastShortKey(); } }


  public Short firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Short lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Short2IntSortedMap subMap( final Short from, final Short to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Short2IntSortedMap headMap( final Short to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Short2IntSortedMap tailMap( final Short from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static Short2IntSortedMap synchronize( final Short2IntSortedMap m ) { return new SynchronizedSortedMap ( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static Short2IntSortedMap synchronize( final Short2IntSortedMap m, final Object sync ) { return new SynchronizedSortedMap ( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap extends Short2IntMaps.UnmodifiableMap implements Short2IntSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Short2IntSortedMap sortedMap;

  protected UnmodifiableSortedMap( final Short2IntSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public ShortComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.short2IntEntrySet() ); return (ObjectSortedSet<Short2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Short, Integer>> entrySet() { return (ObjectSortedSet)short2IntEntrySet(); }
  public ShortSortedSet keySet() { if ( keys == null ) keys = ShortSortedSets.unmodifiable( sortedMap.keySet() ); return (ShortSortedSet )keys; }

  public Short2IntSortedMap subMap( final short from, final short to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Short2IntSortedMap headMap( final short to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Short2IntSortedMap tailMap( final short from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }

  public short firstShortKey() { return sortedMap.firstShortKey(); }
  public short lastShortKey() { return sortedMap.lastShortKey(); }


  public Short firstKey() { return sortedMap.firstKey(); }
  public Short lastKey() { return sortedMap.lastKey(); }

  public Short2IntSortedMap subMap( final Short from, final Short to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Short2IntSortedMap headMap( final Short to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Short2IntSortedMap tailMap( final Short from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static Short2IntSortedMap unmodifiable( final Short2IntSortedMap m ) { return new UnmodifiableSortedMap ( m ); }
}
