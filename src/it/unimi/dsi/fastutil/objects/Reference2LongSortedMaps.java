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
package it.unimi.dsi.fastutil.objects;
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
public class Reference2LongSortedMaps {
 private Reference2LongSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator( final Comparator <K> comparator ) {
  return new Comparator<Map.Entry<K, ?>>() {
   public int compare( Map.Entry<K, ?> x, Map.Entry<K, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap <K> extends Reference2LongMaps.EmptyMap <K> implements Reference2LongSortedMap <K>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public Comparator <? super K> comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Reference2LongMap.Entry <K> > reference2LongEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<K, Long>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ReferenceSortedSet <K> keySet() { return ReferenceSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> subMap( final K from, final K to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> headMap( final K to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> tailMap( final K from ) { return EMPTY_MAP; }
  public K firstKey() { throw new NoSuchElementException(); }
  public K lastKey() { throw new NoSuchElementException(); }




 }



 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton <K> extends Reference2LongMaps.Singleton <K> implements Reference2LongSortedMap <K>, java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Comparator <? super K> comparator;

  protected Singleton( final K key, final long value, Comparator <? super K> comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final K key, final long value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final K k1, final K k2 ) {
   return comparator == null ? ( ((Comparable<K>)(k1)).compareTo(k2) ) : comparator.compare( k1, k2 );
  }

  public Comparator <? super K> comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Reference2LongMap.Entry <K> > reference2LongEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Reference2LongMap.Entry <K>)new SingletonEntry(), (Comparator<? super Reference2LongMap.Entry <K> >)entryComparator( comparator ) ); return (ObjectSortedSet<Reference2LongMap.Entry <K> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<K, Long>> entrySet() { return (ObjectSortedSet)reference2LongEntrySet(); }

  public ReferenceSortedSet <K> keySet() { if ( keys == null ) keys = ReferenceSortedSets.singleton( key, comparator ); return (ReferenceSortedSet <K>)keys; }

  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> subMap( final K from, final K to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> headMap( final K to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Reference2LongSortedMap <K> tailMap( final K from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public K firstKey() { return key; }
  public K lastKey() { return key; }
 }
 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */
 public static <K> Reference2LongSortedMap <K> singleton( final K key, Long value ) {
  return new Singleton <K>( (key), ((value).longValue()) );
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
 public static <K> Reference2LongSortedMap <K> singleton( final K key, Long value, Comparator <? super K> comparator ) {
  return new Singleton <K>( (key), ((value).longValue()), comparator );
 }
 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */
 public static <K> Reference2LongSortedMap <K> singleton( final K key, final long value ) {
  return new Singleton <K>( key, value );
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
 public static <K> Reference2LongSortedMap <K> singleton( final K key, final long value, Comparator <? super K> comparator ) {
  return new Singleton <K>( key, value, comparator );
 }
  /** A synchronized wrapper class for sorted maps. */
 public static class SynchronizedSortedMap <K> extends Reference2LongMaps.SynchronizedMap <K> implements Reference2LongSortedMap <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final Reference2LongSortedMap <K> sortedMap;
  protected SynchronizedSortedMap( final Reference2LongSortedMap <K> m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }
  protected SynchronizedSortedMap( final Reference2LongSortedMap <K> m ) {
   super( m );
   sortedMap = m;
  }
  public Comparator <? super K> comparator() { synchronized( sync ) { return sortedMap.comparator(); } }
  public ObjectSortedSet<Reference2LongMap.Entry <K> > reference2LongEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.reference2LongEntrySet(), sync ); return (ObjectSortedSet<Reference2LongMap.Entry <K> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<K, Long>> entrySet() { return (ObjectSortedSet)reference2LongEntrySet(); }
  public ReferenceSortedSet <K> keySet() { if ( keys == null ) keys = ReferenceSortedSets.synchronize( sortedMap.keySet(), sync ); return (ReferenceSortedSet <K>)keys; }
  public Reference2LongSortedMap <K> subMap( final K from, final K to ) { return new SynchronizedSortedMap <K>( sortedMap.subMap( from, to ), sync ); }
  public Reference2LongSortedMap <K> headMap( final K to ) { return new SynchronizedSortedMap <K>( sortedMap.headMap( to ), sync ); }
  public Reference2LongSortedMap <K> tailMap( final K from ) { return new SynchronizedSortedMap <K>( sortedMap.tailMap( from ), sync ); }
  public K firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public K lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }
 }
 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static <K> Reference2LongSortedMap <K> synchronize( final Reference2LongSortedMap <K> m ) { return new SynchronizedSortedMap <K>( m ); }
 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static <K> Reference2LongSortedMap <K> synchronize( final Reference2LongSortedMap <K> m, final Object sync ) { return new SynchronizedSortedMap <K>( m, sync ); }
 /** An unmodifiable wrapper class for sorted maps. */
 public static class UnmodifiableSortedMap <K> extends Reference2LongMaps.UnmodifiableMap <K> implements Reference2LongSortedMap <K>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final Reference2LongSortedMap <K> sortedMap;
  protected UnmodifiableSortedMap( final Reference2LongSortedMap <K> m ) {
   super( m );
   sortedMap = m;
  }
  public Comparator <? super K> comparator() { return sortedMap.comparator(); }
  public ObjectSortedSet<Reference2LongMap.Entry <K> > reference2LongEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.reference2LongEntrySet() ); return (ObjectSortedSet<Reference2LongMap.Entry <K> >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<K, Long>> entrySet() { return (ObjectSortedSet)reference2LongEntrySet(); }
  public ReferenceSortedSet <K> keySet() { if ( keys == null ) keys = ReferenceSortedSets.unmodifiable( sortedMap.keySet() ); return (ReferenceSortedSet <K>)keys; }
  public Reference2LongSortedMap <K> subMap( final K from, final K to ) { return new UnmodifiableSortedMap <K>( sortedMap.subMap( from, to ) ); }
  public Reference2LongSortedMap <K> headMap( final K to ) { return new UnmodifiableSortedMap <K>( sortedMap.headMap( to ) ); }
  public Reference2LongSortedMap <K> tailMap( final K from ) { return new UnmodifiableSortedMap <K>( sortedMap.tailMap( from ) ); }
  public K firstKey() { return sortedMap.firstKey(); }
  public K lastKey() { return sortedMap.lastKey(); }
 }
 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static <K> Reference2LongSortedMap <K> unmodifiable( final Reference2LongSortedMap <K> m ) { return new UnmodifiableSortedMap <K>( m ); }
}
