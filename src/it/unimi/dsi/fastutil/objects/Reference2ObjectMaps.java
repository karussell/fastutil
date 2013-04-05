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
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectCollections;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Reference2ObjectMaps {
 private Reference2ObjectMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap <K,V> extends Reference2ObjectFunctions.EmptyFunction <K,V> implements Reference2ObjectMap <K,V>, java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final Object v ) { return false; }
  public void putAll( final Map<? extends K, ? extends V> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Reference2ObjectMap.Entry <K,V> > reference2ObjectEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ReferenceSet <K> keySet() { return ReferenceSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectCollection <V> values() { return ObjectSets.EMPTY_SET; }

        private Object readResolve() { return EMPTY_MAP; }

  public Object clone() { return EMPTY_MAP; }

  public boolean isEmpty() { return true; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<K, V>> entrySet() { return (ObjectSet)reference2ObjectEntrySet(); }

  public int hashCode() { return 0; }

  public boolean equals( final Object o ) {
   if ( ! ( o instanceof Map ) ) return false;

   return ((Map<?,?>)o).isEmpty();
  }

  public String toString() { return "{}"; }
 }



 /** An empty type-specific map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptyMap EMPTY_MAP = new EmptyMap();


 /** An immutable class representing a type-specific singleton map.	 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */

 public static class Singleton <K,V> extends Reference2ObjectFunctions.Singleton <K,V> implements Reference2ObjectMap <K,V>, java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Reference2ObjectMap.Entry <K,V> > entries;
  protected transient volatile ReferenceSet <K> keys;
  protected transient volatile ObjectCollection <V> values;

  protected Singleton( final K key, final V value ) {
   super( key, value );
  }

  public boolean containsValue( final Object v ) { return ( (value) == null ? (v) == null : (value).equals(v) ); }




  public void putAll( final Map<? extends K, ? extends V> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Reference2ObjectMap.Entry <K,V> > reference2ObjectEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Reference2ObjectMap.Entry <K,V>)new SingletonEntry() ); return entries; }
  public ReferenceSet <K> keySet() { if ( keys == null ) keys = ReferenceSets.singleton( key ); return keys; }
  public ObjectCollection <V> values() { if ( values == null ) values = ObjectSets.singleton( value ); return values; }

  protected class SingletonEntry implements Reference2ObjectMap.Entry <K,V>, Map.Entry<K,V> {
   public K getKey() { return (Singleton.this.key); }
   public V getValue() { return (Singleton.this.value); }
   public V setValue( final V value ) { throw new UnsupportedOperationException(); }
   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;
    return ( (Singleton.this.key) == ((e.getKey())) ) && ( (Singleton.this.value) == null ? ((e.getValue())) == null : (Singleton.this.value).equals((e.getValue())) );
   }
   public int hashCode() { return ( (Singleton.this.key) == null ? 0 : System.identityHashCode(Singleton.this.key) ) ^ ( (Singleton.this.value) == null ? 0 : (Singleton.this.value).hashCode() ); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }
  public boolean isEmpty() { return false; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<K, V>> entrySet() { return (ObjectSet)reference2ObjectEntrySet(); }
  public int hashCode() { return ( (key) == null ? 0 : System.identityHashCode(key) ) ^ ( (value) == null ? 0 : (value).hashCode() ); }
  public boolean equals( final Object o ) {
   if ( o == this ) return true;
   if ( ! ( o instanceof Map ) ) return false;
   Map<?,?> m = (Map<?,?>)o;
   if ( m.size() != 1 ) return false;
   return entrySet().iterator().next().equals( m.entrySet().iterator().next() );
  }
  public String toString() { return "{" + key + "=>" + value + "}"; }
 }
 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */
 public static <K,V> Reference2ObjectMap <K,V> singleton( final K key, V value ) {
  return new Singleton <K,V>( key, value );
 }
 /** A synchronized wrapper class for maps. */
 public static class SynchronizedMap <K,V> extends Reference2ObjectFunctions.SynchronizedFunction <K,V> implements Reference2ObjectMap <K,V>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final Reference2ObjectMap <K,V> map;
  protected transient volatile ObjectSet<Reference2ObjectMap.Entry <K,V> > entries;
  protected transient volatile ReferenceSet <K> keys;
  protected transient volatile ObjectCollection <V> values;
  protected SynchronizedMap( final Reference2ObjectMap <K,V> m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }
  protected SynchronizedMap( final Reference2ObjectMap <K,V> m ) {
   super( m );
   this.map = m;
  }
  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final Object k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final Object v ) { synchronized( sync ) { return map.containsValue( v ); } }
  public V defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final V defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }
  public V put( final K k, final V v ) { synchronized( sync ) { return map.put( k, v ); } }
  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends K, ? extends V> m ) { synchronized( sync ) { map.putAll( m ); } }
  public ObjectSet<Reference2ObjectMap.Entry <K,V> > reference2ObjectEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.reference2ObjectEntrySet(), sync ); return entries; }
  public ReferenceSet <K> keySet() { if ( keys == null ) keys = ReferenceSets.synchronize( map.keySet(), sync ); return keys; }
  public ObjectCollection <V> values() { if ( values == null ) return ObjectCollections.synchronize( map.values(), sync ); return values; }
  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }
  public V remove( final Object k ) { synchronized( sync ) { return map.remove( k ); } }
  public V get( final Object k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<K, V>> entrySet() { synchronized( sync ) { return map.entrySet(); } }
  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }
 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static <K,V> Reference2ObjectMap <K,V> synchronize( final Reference2ObjectMap <K,V> m ) { return new SynchronizedMap <K,V>( m ); }
 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static <K,V> Reference2ObjectMap <K,V> synchronize( final Reference2ObjectMap <K,V> m, final Object sync ) { return new SynchronizedMap <K,V>( m, sync ); }
 /** An unmodifiable wrapper class for maps. */
 public static class UnmodifiableMap <K,V> extends Reference2ObjectFunctions.UnmodifiableFunction <K,V> implements Reference2ObjectMap <K,V>, java.io.Serializable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected final Reference2ObjectMap <K,V> map;
  protected transient volatile ObjectSet<Reference2ObjectMap.Entry <K,V> > entries;
  protected transient volatile ReferenceSet <K> keys;
  protected transient volatile ObjectCollection <V> values;
  protected UnmodifiableMap( final Reference2ObjectMap <K,V> m ) {
   super( m );
   this.map = m;
  }
  public int size() { return map.size(); }
  public boolean containsKey( final Object k ) { return map.containsKey( k ); }
  public boolean containsValue( final Object v ) { return map.containsValue( v ); }
  public V defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final V defRetValue ) { throw new UnsupportedOperationException(); }
  public V put( final K k, final V v ) { throw new UnsupportedOperationException(); }
  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends K, ? extends V> m ) { throw new UnsupportedOperationException(); }
  public ObjectSet<Reference2ObjectMap.Entry <K,V> > reference2ObjectEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.reference2ObjectEntrySet() ); return entries; }
  public ReferenceSet <K> keySet() { if ( keys == null ) keys = ReferenceSets.unmodifiable( map.keySet() ); return keys; }
  public ObjectCollection <V> values() { if ( values == null ) return ObjectCollections.unmodifiable( map.values() ); return values; }
  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }
  public V remove( final Object k ) { throw new UnsupportedOperationException(); }
  public V get( final Object k ) { return map.get( k ); }
  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<K, V>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }
 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static <K,V> Reference2ObjectMap <K,V> unmodifiable( final Reference2ObjectMap <K,V> m ) { return new UnmodifiableMap <K,V>( m ); }
}
