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
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongCollections;
import it.unimi.dsi.fastutil.longs.LongSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Char2LongMaps {
 private Char2LongMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap extends Char2LongFunctions.EmptyFunction implements Char2LongMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final long v ) { return false; }
  public void putAll( final Map<? extends Character, ? extends Long> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Char2LongMap.Entry > char2LongEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public CharSet keySet() { return CharSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public LongCollection values() { return LongSets.EMPTY_SET; }
  public boolean containsValue( final Object ov ) { return false; }
        private Object readResolve() { return EMPTY_MAP; }
  public Object clone() { return EMPTY_MAP; }
  public boolean isEmpty() { return true; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Character, Long>> entrySet() { return (ObjectSet)char2LongEntrySet(); }

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

 public static class Singleton extends Char2LongFunctions.Singleton implements Char2LongMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Char2LongMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile LongCollection values;

  protected Singleton( final char key, final long value ) {
   super( key, value );
  }

  public boolean containsValue( final long v ) { return ( (value) == (v) ); }

  public boolean containsValue( final Object ov ) { return ( (((((Long)(ov)).longValue()))) == (value) ); }


  public void putAll( final Map<? extends Character, ? extends Long> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Char2LongMap.Entry > char2LongEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Char2LongMap.Entry )new SingletonEntry() ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.singleton( key ); return keys; }
  public LongCollection values() { if ( values == null ) values = LongSets.singleton( value ); return values; }

  protected class SingletonEntry implements Char2LongMap.Entry , Map.Entry<Character,Long> {
   public Character getKey() { return (Character.valueOf(Singleton.this.key)); }
   public Long getValue() { return (Long.valueOf(Singleton.this.value)); }


   public char getCharKey() { return Singleton.this.key; }



   public long getLongValue() { return Singleton.this.value; }
   public long setValue( final long value ) { throw new UnsupportedOperationException(); }


   public Long setValue( final Long value ) { throw new UnsupportedOperationException(); }

   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;

    return ( (Singleton.this.key) == (((((Character)(e.getKey())).charValue()))) ) && ( (Singleton.this.value) == (((((Long)(e.getValue())).longValue()))) );
   }

   public int hashCode() { return (Singleton.this.key) ^ it.unimi.dsi.fastutil.HashCommon.long2int(Singleton.this.value); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }

  public boolean isEmpty() { return false; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Character, Long>> entrySet() { return (ObjectSet)char2LongEntrySet(); }

  public int hashCode() { return (key) ^ it.unimi.dsi.fastutil.HashCommon.long2int(value); }

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

 public static Char2LongMap singleton( final char key, long value ) {
  return new Singleton ( key, value );
 }



 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Char2LongMap singleton( final Character key, final Long value ) {
  return new Singleton ( ((key).charValue()), ((value).longValue()) );
 }




 /** A synchronized wrapper class for maps. */

 public static class SynchronizedMap extends Char2LongFunctions.SynchronizedFunction implements Char2LongMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Char2LongMap map;

  protected transient volatile ObjectSet<Char2LongMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile LongCollection values;

  protected SynchronizedMap( final Char2LongMap m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }

  protected SynchronizedMap( final Char2LongMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final char k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final long v ) { synchronized( sync ) { return map.containsValue( v ); } }

  public long defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final long defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }

  public long put( final char k, final long v ) { synchronized( sync ) { return map.put( k, v ); } }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends Character, ? extends Long> m ) { synchronized( sync ) { map.putAll( m ); } }

  public ObjectSet<Char2LongMap.Entry > char2LongEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.char2LongEntrySet(), sync ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.synchronize( map.keySet(), sync ); return keys; }
  public LongCollection values() { if ( values == null ) return LongCollections.synchronize( map.values(), sync ); return values; }

  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }


  public Long put( final Character k, final Long v ) { synchronized( sync ) { return map.put( k, v ); } }



  public long remove( final char k ) { synchronized( sync ) { return map.remove( k ); } }
  public long get( final char k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean containsKey( final Object ok ) { synchronized( sync ) { return map.containsKey( ok ); } }



  public boolean containsValue( final Object ov ) { synchronized( sync ) { return map.containsValue( ov ); } }







  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<Character, Long>> entrySet() { synchronized( sync ) { return map.entrySet(); } }

  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }

 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static Char2LongMap synchronize( final Char2LongMap m ) { return new SynchronizedMap ( m ); }

 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */

 public static Char2LongMap synchronize( final Char2LongMap m, final Object sync ) { return new SynchronizedMap ( m, sync ); }



 /** An unmodifiable wrapper class for maps. */

 public static class UnmodifiableMap extends Char2LongFunctions.UnmodifiableFunction implements Char2LongMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Char2LongMap map;

  protected transient volatile ObjectSet<Char2LongMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile LongCollection values;

  protected UnmodifiableMap( final Char2LongMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { return map.size(); }
  public boolean containsKey( final char k ) { return map.containsKey( k ); }
  public boolean containsValue( final long v ) { return map.containsValue( v ); }

  public long defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final long defRetValue ) { throw new UnsupportedOperationException(); }

  public long put( final char k, final long v ) { throw new UnsupportedOperationException(); }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends Character, ? extends Long> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Char2LongMap.Entry > char2LongEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.char2LongEntrySet() ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.unmodifiable( map.keySet() ); return keys; }
  public LongCollection values() { if ( values == null ) return LongCollections.unmodifiable( map.values() ); return values; }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }


  public Long put( final Character k, final Long v ) { throw new UnsupportedOperationException(); }



  public long remove( final char k ) { throw new UnsupportedOperationException(); }
  public long get( final char k ) { return map.get( k ); }
  public boolean containsKey( final Object ok ) { return map.containsKey( ok ); }



  public boolean containsValue( final Object ov ) { return map.containsValue( ov ); }







  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<Character, Long>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }

 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static Char2LongMap unmodifiable( final Char2LongMap m ) { return new UnmodifiableMap ( m ); }

}
