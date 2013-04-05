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
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntCollections;
import it.unimi.dsi.fastutil.ints.IntSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Short2IntMaps {
 private Short2IntMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap extends Short2IntFunctions.EmptyFunction implements Short2IntMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final int v ) { return false; }
  public void putAll( final Map<? extends Short, ? extends Integer> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Short2IntMap.Entry > short2IntEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ShortSet keySet() { return ShortSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public IntCollection values() { return IntSets.EMPTY_SET; }
  public boolean containsValue( final Object ov ) { return false; }
        private Object readResolve() { return EMPTY_MAP; }
  public Object clone() { return EMPTY_MAP; }
  public boolean isEmpty() { return true; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Short, Integer>> entrySet() { return (ObjectSet)short2IntEntrySet(); }

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

 public static class Singleton extends Short2IntFunctions.Singleton implements Short2IntMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Short2IntMap.Entry > entries;
  protected transient volatile ShortSet keys;
  protected transient volatile IntCollection values;

  protected Singleton( final short key, final int value ) {
   super( key, value );
  }

  public boolean containsValue( final int v ) { return ( (value) == (v) ); }

  public boolean containsValue( final Object ov ) { return ( (((((Integer)(ov)).intValue()))) == (value) ); }


  public void putAll( final Map<? extends Short, ? extends Integer> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Short2IntMap.Entry )new SingletonEntry() ); return entries; }
  public ShortSet keySet() { if ( keys == null ) keys = ShortSets.singleton( key ); return keys; }
  public IntCollection values() { if ( values == null ) values = IntSets.singleton( value ); return values; }

  protected class SingletonEntry implements Short2IntMap.Entry , Map.Entry<Short,Integer> {
   public Short getKey() { return (Short.valueOf(Singleton.this.key)); }
   public Integer getValue() { return (Integer.valueOf(Singleton.this.value)); }


   public short getShortKey() { return Singleton.this.key; }



   public int getIntValue() { return Singleton.this.value; }
   public int setValue( final int value ) { throw new UnsupportedOperationException(); }


   public Integer setValue( final Integer value ) { throw new UnsupportedOperationException(); }

   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;

    return ( (Singleton.this.key) == (((((Short)(e.getKey())).shortValue()))) ) && ( (Singleton.this.value) == (((((Integer)(e.getValue())).intValue()))) );
   }

   public int hashCode() { return (Singleton.this.key) ^ (Singleton.this.value); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }

  public boolean isEmpty() { return false; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Short, Integer>> entrySet() { return (ObjectSet)short2IntEntrySet(); }

  public int hashCode() { return (key) ^ (value); }

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

 public static Short2IntMap singleton( final short key, int value ) {
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

 public static Short2IntMap singleton( final Short key, final Integer value ) {
  return new Singleton ( ((key).shortValue()), ((value).intValue()) );
 }




 /** A synchronized wrapper class for maps. */

 public static class SynchronizedMap extends Short2IntFunctions.SynchronizedFunction implements Short2IntMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Short2IntMap map;

  protected transient volatile ObjectSet<Short2IntMap.Entry > entries;
  protected transient volatile ShortSet keys;
  protected transient volatile IntCollection values;

  protected SynchronizedMap( final Short2IntMap m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }

  protected SynchronizedMap( final Short2IntMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final short k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final int v ) { synchronized( sync ) { return map.containsValue( v ); } }

  public int defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final int defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }

  public int put( final short k, final int v ) { synchronized( sync ) { return map.put( k, v ); } }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends Short, ? extends Integer> m ) { synchronized( sync ) { map.putAll( m ); } }

  public ObjectSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.short2IntEntrySet(), sync ); return entries; }
  public ShortSet keySet() { if ( keys == null ) keys = ShortSets.synchronize( map.keySet(), sync ); return keys; }
  public IntCollection values() { if ( values == null ) return IntCollections.synchronize( map.values(), sync ); return values; }

  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }


  public Integer put( final Short k, final Integer v ) { synchronized( sync ) { return map.put( k, v ); } }



  public int remove( final short k ) { synchronized( sync ) { return map.remove( k ); } }
  public int get( final short k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean containsKey( final Object ok ) { synchronized( sync ) { return map.containsKey( ok ); } }



  public boolean containsValue( final Object ov ) { synchronized( sync ) { return map.containsValue( ov ); } }







  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<Short, Integer>> entrySet() { synchronized( sync ) { return map.entrySet(); } }

  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }

 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static Short2IntMap synchronize( final Short2IntMap m ) { return new SynchronizedMap ( m ); }

 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */

 public static Short2IntMap synchronize( final Short2IntMap m, final Object sync ) { return new SynchronizedMap ( m, sync ); }



 /** An unmodifiable wrapper class for maps. */

 public static class UnmodifiableMap extends Short2IntFunctions.UnmodifiableFunction implements Short2IntMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Short2IntMap map;

  protected transient volatile ObjectSet<Short2IntMap.Entry > entries;
  protected transient volatile ShortSet keys;
  protected transient volatile IntCollection values;

  protected UnmodifiableMap( final Short2IntMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { return map.size(); }
  public boolean containsKey( final short k ) { return map.containsKey( k ); }
  public boolean containsValue( final int v ) { return map.containsValue( v ); }

  public int defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final int defRetValue ) { throw new UnsupportedOperationException(); }

  public int put( final short k, final int v ) { throw new UnsupportedOperationException(); }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends Short, ? extends Integer> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Short2IntMap.Entry > short2IntEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.short2IntEntrySet() ); return entries; }
  public ShortSet keySet() { if ( keys == null ) keys = ShortSets.unmodifiable( map.keySet() ); return keys; }
  public IntCollection values() { if ( values == null ) return IntCollections.unmodifiable( map.values() ); return values; }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }


  public Integer put( final Short k, final Integer v ) { throw new UnsupportedOperationException(); }



  public int remove( final short k ) { throw new UnsupportedOperationException(); }
  public int get( final short k ) { return map.get( k ); }
  public boolean containsKey( final Object ok ) { return map.containsKey( ok ); }



  public boolean containsValue( final Object ov ) { return map.containsValue( ov ); }







  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<Short, Integer>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }

 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static Short2IntMap unmodifiable( final Short2IntMap m ) { return new UnmodifiableMap ( m ); }

}
