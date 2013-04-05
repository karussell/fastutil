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
package it.unimi.dsi.fastutil.longs;
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
public class Long2ReferenceSortedMaps {
 private Long2ReferenceSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Long, ?>> entryComparator( final LongComparator comparator ) {
  return new Comparator<Map.Entry<Long, ?>>() {
   public int compare( Map.Entry<Long, ?> x, Map.Entry<Long, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap <V> extends Long2ReferenceMaps.EmptyMap <V> implements Long2ReferenceSortedMap <V>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public LongComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Long2ReferenceMap.Entry <V> > long2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public LongSortedSet keySet() { return LongSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> subMap( final long from, final long to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> headMap( final long to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> tailMap( final long from ) { return EMPTY_MAP; }
  public long firstLongKey() { throw new NoSuchElementException(); }
  public long lastLongKey() { throw new NoSuchElementException(); }
  public Long2ReferenceSortedMap <V> headMap( Long oto ) { return headMap( ((oto).longValue()) ); }
  public Long2ReferenceSortedMap <V> tailMap( Long ofrom ) { return tailMap( ((ofrom).longValue()) ); }
  public Long2ReferenceSortedMap <V> subMap( Long ofrom, Long oto ) { return subMap( ((ofrom).longValue()), ((oto).longValue()) ); }
  public Long firstKey() { return (Long.valueOf(firstLongKey())); }
  public Long lastKey() { return (Long.valueOf(lastLongKey())); }
 }


 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton <V> extends Long2ReferenceMaps.Singleton <V> implements Long2ReferenceSortedMap <V>, java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final LongComparator comparator;

  protected Singleton( final long key, final V value, LongComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final long key, final V value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final long k1, final long k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public LongComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Long2ReferenceMap.Entry <V> > long2ReferenceEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Long2ReferenceMap.Entry <V>)new SingletonEntry(), (Comparator<? super Long2ReferenceMap.Entry <V> >)entryComparator( comparator ) ); return (ObjectSortedSet<Long2ReferenceMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return (ObjectSortedSet)long2ReferenceEntrySet(); }

  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.singleton( key, comparator ); return (LongSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> subMap( final long from, final long to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> headMap( final long to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Long2ReferenceSortedMap <V> tailMap( final long from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public long firstLongKey() { return key; }
  public long lastLongKey() { return key; }


  public Long2ReferenceSortedMap <V> headMap( Long oto ) { return headMap( ((oto).longValue()) ); }
  public Long2ReferenceSortedMap <V> tailMap( Long ofrom ) { return tailMap( ((ofrom).longValue()) ); }
  public Long2ReferenceSortedMap <V> subMap( Long ofrom, Long oto ) { return subMap( ((ofrom).longValue()), ((oto).longValue()) ); }

  public Long firstKey() { return (Long.valueOf(firstLongKey())); }
  public Long lastKey() { return (Long.valueOf(lastLongKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Long2ReferenceSortedMap <V> singleton( final Long key, V value ) {
  return new Singleton <V>( ((key).longValue()), (value) );
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

 public static <V> Long2ReferenceSortedMap <V> singleton( final Long key, V value, LongComparator comparator ) {
  return new Singleton <V>( ((key).longValue()), (value), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static <V> Long2ReferenceSortedMap <V> singleton( final long key, final V value ) {
  return new Singleton <V>( key, value );
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

 public static <V> Long2ReferenceSortedMap <V> singleton( final long key, final V value, LongComparator comparator ) {
  return new Singleton <V>( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap <V> extends Long2ReferenceMaps.SynchronizedMap <V> implements Long2ReferenceSortedMap <V>, java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Long2ReferenceSortedMap <V> sortedMap;

  protected SynchronizedSortedMap( final Long2ReferenceSortedMap <V> m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Long2ReferenceSortedMap <V> m ) {
   super( m );
   sortedMap = m;
  }

  public LongComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Long2ReferenceMap.Entry <V> > long2ReferenceEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.long2ReferenceEntrySet(), sync ); return (ObjectSortedSet<Long2ReferenceMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return (ObjectSortedSet)long2ReferenceEntrySet(); }
  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.synchronize( sortedMap.keySet(), sync ); return (LongSortedSet )keys; }

  public Long2ReferenceSortedMap <V> subMap( final long from, final long to ) { return new SynchronizedSortedMap <V>( sortedMap.subMap( from, to ), sync ); }
  public Long2ReferenceSortedMap <V> headMap( final long to ) { return new SynchronizedSortedMap <V>( sortedMap.headMap( to ), sync ); }
  public Long2ReferenceSortedMap <V> tailMap( final long from ) { return new SynchronizedSortedMap <V>( sortedMap.tailMap( from ), sync ); }

  public long firstLongKey() { synchronized( sync ) { return sortedMap.firstLongKey(); } }
  public long lastLongKey() { synchronized( sync ) { return sortedMap.lastLongKey(); } }


  public Long firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Long lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Long2ReferenceSortedMap <V> subMap( final Long from, final Long to ) { return new SynchronizedSortedMap <V>( sortedMap.subMap( from, to ), sync ); }
  public Long2ReferenceSortedMap <V> headMap( final Long to ) { return new SynchronizedSortedMap <V>( sortedMap.headMap( to ), sync ); }
  public Long2ReferenceSortedMap <V> tailMap( final Long from ) { return new SynchronizedSortedMap <V>( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static <V> Long2ReferenceSortedMap <V> synchronize( final Long2ReferenceSortedMap <V> m ) { return new SynchronizedSortedMap <V>( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static <V> Long2ReferenceSortedMap <V> synchronize( final Long2ReferenceSortedMap <V> m, final Object sync ) { return new SynchronizedSortedMap <V>( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap <V> extends Long2ReferenceMaps.UnmodifiableMap <V> implements Long2ReferenceSortedMap <V>, java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Long2ReferenceSortedMap <V> sortedMap;

  protected UnmodifiableSortedMap( final Long2ReferenceSortedMap <V> m ) {
   super( m );
   sortedMap = m;
  }

  public LongComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Long2ReferenceMap.Entry <V> > long2ReferenceEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.long2ReferenceEntrySet() ); return (ObjectSortedSet<Long2ReferenceMap.Entry <V> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return (ObjectSortedSet)long2ReferenceEntrySet(); }
  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.unmodifiable( sortedMap.keySet() ); return (LongSortedSet )keys; }

  public Long2ReferenceSortedMap <V> subMap( final long from, final long to ) { return new UnmodifiableSortedMap <V>( sortedMap.subMap( from, to ) ); }
  public Long2ReferenceSortedMap <V> headMap( final long to ) { return new UnmodifiableSortedMap <V>( sortedMap.headMap( to ) ); }
  public Long2ReferenceSortedMap <V> tailMap( final long from ) { return new UnmodifiableSortedMap <V>( sortedMap.tailMap( from ) ); }

  public long firstLongKey() { return sortedMap.firstLongKey(); }
  public long lastLongKey() { return sortedMap.lastLongKey(); }


  public Long firstKey() { return sortedMap.firstKey(); }
  public Long lastKey() { return sortedMap.lastKey(); }

  public Long2ReferenceSortedMap <V> subMap( final Long from, final Long to ) { return new UnmodifiableSortedMap <V>( sortedMap.subMap( from, to ) ); }
  public Long2ReferenceSortedMap <V> headMap( final Long to ) { return new UnmodifiableSortedMap <V>( sortedMap.headMap( to ) ); }
  public Long2ReferenceSortedMap <V> tailMap( final Long from ) { return new UnmodifiableSortedMap <V>( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static <V> Long2ReferenceSortedMap <V> unmodifiable( final Long2ReferenceSortedMap <V> m ) { return new UnmodifiableSortedMap <V>( m ); }
}
