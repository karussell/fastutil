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
package it.unimi.dsi.fastutil.chars;
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
public class Char2ShortSortedMaps {
 private Char2ShortSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Character, ?>> entryComparator( final CharComparator comparator ) {
  return new Comparator<Map.Entry<Character, ?>>() {
   public int compare( Map.Entry<Character, ?> x, Map.Entry<Character, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap extends Char2ShortMaps.EmptyMap implements Char2ShortSortedMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public CharComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Char2ShortMap.Entry > char2ShortEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Character, Short>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public CharSortedSet keySet() { return CharSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap subMap( final char from, final char to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap headMap( final char to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap tailMap( final char from ) { return EMPTY_MAP; }
  public char firstCharKey() { throw new NoSuchElementException(); }
  public char lastCharKey() { throw new NoSuchElementException(); }
  public Char2ShortSortedMap headMap( Character oto ) { return headMap( ((oto).charValue()) ); }
  public Char2ShortSortedMap tailMap( Character ofrom ) { return tailMap( ((ofrom).charValue()) ); }
  public Char2ShortSortedMap subMap( Character ofrom, Character oto ) { return subMap( ((ofrom).charValue()), ((oto).charValue()) ); }
  public Character firstKey() { return (Character.valueOf(firstCharKey())); }
  public Character lastKey() { return (Character.valueOf(lastCharKey())); }
 }

 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton extends Char2ShortMaps.Singleton implements Char2ShortSortedMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final CharComparator comparator;

  protected Singleton( final char key, final short value, CharComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final char key, final short value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final char k1, final char k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public CharComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Char2ShortMap.Entry )new SingletonEntry(), (Comparator<? super Char2ShortMap.Entry >)entryComparator( comparator ) ); return (ObjectSortedSet<Char2ShortMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Character, Short>> entrySet() { return (ObjectSortedSet)char2ShortEntrySet(); }

  public CharSortedSet keySet() { if ( keys == null ) keys = CharSortedSets.singleton( key, comparator ); return (CharSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap subMap( final char from, final char to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap headMap( final char to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Char2ShortSortedMap tailMap( final char from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public char firstCharKey() { return key; }
  public char lastCharKey() { return key; }


  public Char2ShortSortedMap headMap( Character oto ) { return headMap( ((oto).charValue()) ); }
  public Char2ShortSortedMap tailMap( Character ofrom ) { return tailMap( ((ofrom).charValue()) ); }
  public Char2ShortSortedMap subMap( Character ofrom, Character oto ) { return subMap( ((ofrom).charValue()), ((oto).charValue()) ); }

  public Character firstKey() { return (Character.valueOf(firstCharKey())); }
  public Character lastKey() { return (Character.valueOf(lastCharKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Char2ShortSortedMap singleton( final Character key, Short value ) {
  return new Singleton ( ((key).charValue()), ((value).shortValue()) );
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

 public static Char2ShortSortedMap singleton( final Character key, Short value, CharComparator comparator ) {
  return new Singleton ( ((key).charValue()), ((value).shortValue()), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Char2ShortSortedMap singleton( final char key, final short value ) {
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

 public static Char2ShortSortedMap singleton( final char key, final short value, CharComparator comparator ) {
  return new Singleton ( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap extends Char2ShortMaps.SynchronizedMap implements Char2ShortSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Char2ShortSortedMap sortedMap;

  protected SynchronizedSortedMap( final Char2ShortSortedMap m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Char2ShortSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public CharComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.char2ShortEntrySet(), sync ); return (ObjectSortedSet<Char2ShortMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Character, Short>> entrySet() { return (ObjectSortedSet)char2ShortEntrySet(); }
  public CharSortedSet keySet() { if ( keys == null ) keys = CharSortedSets.synchronize( sortedMap.keySet(), sync ); return (CharSortedSet )keys; }

  public Char2ShortSortedMap subMap( final char from, final char to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Char2ShortSortedMap headMap( final char to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Char2ShortSortedMap tailMap( final char from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }

  public char firstCharKey() { synchronized( sync ) { return sortedMap.firstCharKey(); } }
  public char lastCharKey() { synchronized( sync ) { return sortedMap.lastCharKey(); } }


  public Character firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Character lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Char2ShortSortedMap subMap( final Character from, final Character to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Char2ShortSortedMap headMap( final Character to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Char2ShortSortedMap tailMap( final Character from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static Char2ShortSortedMap synchronize( final Char2ShortSortedMap m ) { return new SynchronizedSortedMap ( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static Char2ShortSortedMap synchronize( final Char2ShortSortedMap m, final Object sync ) { return new SynchronizedSortedMap ( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap extends Char2ShortMaps.UnmodifiableMap implements Char2ShortSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Char2ShortSortedMap sortedMap;

  protected UnmodifiableSortedMap( final Char2ShortSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public CharComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.char2ShortEntrySet() ); return (ObjectSortedSet<Char2ShortMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Character, Short>> entrySet() { return (ObjectSortedSet)char2ShortEntrySet(); }
  public CharSortedSet keySet() { if ( keys == null ) keys = CharSortedSets.unmodifiable( sortedMap.keySet() ); return (CharSortedSet )keys; }

  public Char2ShortSortedMap subMap( final char from, final char to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Char2ShortSortedMap headMap( final char to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Char2ShortSortedMap tailMap( final char from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }

  public char firstCharKey() { return sortedMap.firstCharKey(); }
  public char lastCharKey() { return sortedMap.lastCharKey(); }


  public Character firstKey() { return sortedMap.firstKey(); }
  public Character lastKey() { return sortedMap.lastKey(); }

  public Char2ShortSortedMap subMap( final Character from, final Character to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Char2ShortSortedMap headMap( final Character to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Char2ShortSortedMap tailMap( final Character from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static Char2ShortSortedMap unmodifiable( final Char2ShortSortedMap m ) { return new UnmodifiableSortedMap ( m ); }
}
