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
package it.unimi.dsi.fastutil.doubles;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollections;
import it.unimi.dsi.fastutil.doubles.DoubleSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Double2DoubleMaps {
 private Double2DoubleMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap extends Double2DoubleFunctions.EmptyFunction implements Double2DoubleMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final double v ) { return false; }
  public void putAll( final Map<? extends Double, ? extends Double> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Double2DoubleMap.Entry > double2DoubleEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public DoubleSet keySet() { return DoubleSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public DoubleCollection values() { return DoubleSets.EMPTY_SET; }
  public boolean containsValue( final Object ov ) { return false; }
        private Object readResolve() { return EMPTY_MAP; }
  public Object clone() { return EMPTY_MAP; }
  public boolean isEmpty() { return true; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Double, Double>> entrySet() { return (ObjectSet)double2DoubleEntrySet(); }

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

 public static class Singleton extends Double2DoubleFunctions.Singleton implements Double2DoubleMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Double2DoubleMap.Entry > entries;
  protected transient volatile DoubleSet keys;
  protected transient volatile DoubleCollection values;

  protected Singleton( final double key, final double value ) {
   super( key, value );
  }

  public boolean containsValue( final double v ) { return ( (value) == (v) ); }

  public boolean containsValue( final Object ov ) { return ( (((((Double)(ov)).doubleValue()))) == (value) ); }


  public void putAll( final Map<? extends Double, ? extends Double> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Double2DoubleMap.Entry > double2DoubleEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Double2DoubleMap.Entry )new SingletonEntry() ); return entries; }
  public DoubleSet keySet() { if ( keys == null ) keys = DoubleSets.singleton( key ); return keys; }
  public DoubleCollection values() { if ( values == null ) values = DoubleSets.singleton( value ); return values; }

  protected class SingletonEntry implements Double2DoubleMap.Entry , Map.Entry<Double,Double> {
   public Double getKey() { return (Double.valueOf(Singleton.this.key)); }
   public Double getValue() { return (Double.valueOf(Singleton.this.value)); }


   public double getDoubleKey() { return Singleton.this.key; }



   public double getDoubleValue() { return Singleton.this.value; }
   public double setValue( final double value ) { throw new UnsupportedOperationException(); }


   public Double setValue( final Double value ) { throw new UnsupportedOperationException(); }

   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;

    return ( (Singleton.this.key) == (((((Double)(e.getKey())).doubleValue()))) ) && ( (Singleton.this.value) == (((((Double)(e.getValue())).doubleValue()))) );
   }

   public int hashCode() { return it.unimi.dsi.fastutil.HashCommon.double2int(Singleton.this.key) ^ it.unimi.dsi.fastutil.HashCommon.double2int(Singleton.this.value); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }

  public boolean isEmpty() { return false; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Double, Double>> entrySet() { return (ObjectSet)double2DoubleEntrySet(); }

  public int hashCode() { return it.unimi.dsi.fastutil.HashCommon.double2int(key) ^ it.unimi.dsi.fastutil.HashCommon.double2int(value); }

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

 public static Double2DoubleMap singleton( final double key, double value ) {
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

 public static Double2DoubleMap singleton( final Double key, final Double value ) {
  return new Singleton ( ((key).doubleValue()), ((value).doubleValue()) );
 }




 /** A synchronized wrapper class for maps. */

 public static class SynchronizedMap extends Double2DoubleFunctions.SynchronizedFunction implements Double2DoubleMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Double2DoubleMap map;

  protected transient volatile ObjectSet<Double2DoubleMap.Entry > entries;
  protected transient volatile DoubleSet keys;
  protected transient volatile DoubleCollection values;

  protected SynchronizedMap( final Double2DoubleMap m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }

  protected SynchronizedMap( final Double2DoubleMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final double k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final double v ) { synchronized( sync ) { return map.containsValue( v ); } }

  public double defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final double defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }

  public double put( final double k, final double v ) { synchronized( sync ) { return map.put( k, v ); } }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends Double, ? extends Double> m ) { synchronized( sync ) { map.putAll( m ); } }

  public ObjectSet<Double2DoubleMap.Entry > double2DoubleEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.double2DoubleEntrySet(), sync ); return entries; }
  public DoubleSet keySet() { if ( keys == null ) keys = DoubleSets.synchronize( map.keySet(), sync ); return keys; }
  public DoubleCollection values() { if ( values == null ) return DoubleCollections.synchronize( map.values(), sync ); return values; }

  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }


  public Double put( final Double k, final Double v ) { synchronized( sync ) { return map.put( k, v ); } }



  public double remove( final double k ) { synchronized( sync ) { return map.remove( k ); } }
  public double get( final double k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean containsKey( final Object ok ) { synchronized( sync ) { return map.containsKey( ok ); } }



  public boolean containsValue( final Object ov ) { synchronized( sync ) { return map.containsValue( ov ); } }







  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<Double, Double>> entrySet() { synchronized( sync ) { return map.entrySet(); } }

  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }

 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static Double2DoubleMap synchronize( final Double2DoubleMap m ) { return new SynchronizedMap ( m ); }

 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */

 public static Double2DoubleMap synchronize( final Double2DoubleMap m, final Object sync ) { return new SynchronizedMap ( m, sync ); }



 /** An unmodifiable wrapper class for maps. */

 public static class UnmodifiableMap extends Double2DoubleFunctions.UnmodifiableFunction implements Double2DoubleMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Double2DoubleMap map;

  protected transient volatile ObjectSet<Double2DoubleMap.Entry > entries;
  protected transient volatile DoubleSet keys;
  protected transient volatile DoubleCollection values;

  protected UnmodifiableMap( final Double2DoubleMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { return map.size(); }
  public boolean containsKey( final double k ) { return map.containsKey( k ); }
  public boolean containsValue( final double v ) { return map.containsValue( v ); }

  public double defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final double defRetValue ) { throw new UnsupportedOperationException(); }

  public double put( final double k, final double v ) { throw new UnsupportedOperationException(); }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends Double, ? extends Double> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Double2DoubleMap.Entry > double2DoubleEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.double2DoubleEntrySet() ); return entries; }
  public DoubleSet keySet() { if ( keys == null ) keys = DoubleSets.unmodifiable( map.keySet() ); return keys; }
  public DoubleCollection values() { if ( values == null ) return DoubleCollections.unmodifiable( map.values() ); return values; }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }


  public Double put( final Double k, final Double v ) { throw new UnsupportedOperationException(); }



  public double remove( final double k ) { throw new UnsupportedOperationException(); }
  public double get( final double k ) { return map.get( k ); }
  public boolean containsKey( final Object ok ) { return map.containsKey( ok ); }



  public boolean containsValue( final Object ov ) { return map.containsValue( ov ); }







  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<Double, Double>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }

 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static Double2DoubleMap unmodifiable( final Double2DoubleMap m ) { return new UnmodifiableMap ( m ); }

}
