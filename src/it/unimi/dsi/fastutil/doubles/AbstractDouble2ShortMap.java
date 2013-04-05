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
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Iterator;
import java.util.Map;
/** An abstract class providing basic methods for maps implementing a type-specific interface.
 *
 * <P>Optional operations just throw an {@link
 * UnsupportedOperationException}. Generic versions of accessors delegate to
 * the corresponding type-specific counterparts following the interface rules
 * (they take care of returning <code>null</code> on a missing key).
 *
 * <P>As a further help, this class provides a {@link BasicEntry BasicEntry} inner class
 * that implements a type-specific version of {@link java.util.Map.Entry}; it
 * is particularly useful for those classes that do not implement their own
 * entries (e.g., most immutable maps).
 */
public abstract class AbstractDouble2ShortMap extends AbstractDouble2ShortFunction implements Double2ShortMap , java.io.Serializable {
 private static final long serialVersionUID = -4940583368468432370L;
 protected AbstractDouble2ShortMap() {}
 public boolean containsValue( Object ov ) {
  return containsValue( ((((Short)(ov)).shortValue())) );
 }
 /** Checks whether the given value is contained in {@link #values()}. */
 public boolean containsValue( short v ) {
  return values().contains( v );
 }
 /** Checks whether the given value is contained in {@link #keySet()}. */
 public boolean containsKey( double k ) {
  return keySet().contains( k );
 }
 /** Puts all pairs in the given map.
	 * If the map implements the interface of this map,
	 * it uses the faster iterators.
	 *
	 * @param m a map.
	 */
 @SuppressWarnings("unchecked")
 public void putAll(Map<? extends Double,? extends Short> m) {
  int n = m.size();
  final Iterator<? extends Map.Entry<? extends Double,? extends Short>> i = m.entrySet().iterator();
  if (m instanceof Double2ShortMap) {
   Double2ShortMap.Entry e;
   while(n-- != 0) {
    e = (Double2ShortMap.Entry )i.next();
    put(e.getDoubleKey(), e.getShortValue());
   }
  }
  else {
   Map.Entry<? extends Double,? extends Short> e;
   while(n-- != 0) {
    e = i.next();
    put(e.getKey(), e.getValue());
   }
  }
 }
 public boolean isEmpty() {
  return size() == 0;
 }
 /** This class provides a basic but complete type-specific entry class for all those maps implementations
	 * that do not have entries on their own (e.g., most immutable maps). 
	 *
	 * <P>This class does not implement {@link java.util.Map.Entry#setValue(Object) setValue()}, as the modification
	 * would not be reflected in the base map.
	 */
 public static class BasicEntry implements Double2ShortMap.Entry {
  protected double key;
  protected short value;
  public BasicEntry( final Double key, final Short value ) {
   this.key = ((key).doubleValue());
   this.value = ((value).shortValue());
  }
  public BasicEntry( final double key, final short value ) {
   this.key = key;
   this.value = value;
  }


  public Double getKey() {
   return (Double.valueOf(key));
  }


  public double getDoubleKey() {
   return key;
  }


  public Short getValue() {
   return (Short.valueOf(value));
  }


  public short getShortValue() {
   return value;
  }


  public short setValue( final short value ) {
   throw new UnsupportedOperationException();
  }



  public Short setValue( final Short value ) {
   return Short.valueOf(setValue(value.shortValue()));
  }



  public boolean equals( final Object o ) {
   if (!(o instanceof Map.Entry)) return false;
   Map.Entry<?,?> e = (Map.Entry<?,?>)o;

   return ( (key) == (((((Double)(e.getKey())).doubleValue()))) ) && ( (value) == (((((Short)(e.getValue())).shortValue()))) );
  }

  public int hashCode() {
   return it.unimi.dsi.fastutil.HashCommon.double2int(key) ^ (value);
  }


  public String toString() {
   return key + "->" + value;
  }
 }


 /** Returns a type-specific-set view of the keys of this map.
	 *
	 * <P>The view is backed by the set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a set view of the keys of this map; it may be safely cast to a type-specific interface.
	 */


 public DoubleSet keySet() {
  return new AbstractDoubleSet () {

    public boolean contains( final double k ) { return containsKey( k ); }

    public int size() { return AbstractDouble2ShortMap.this.size(); }
    public void clear() { AbstractDouble2ShortMap.this.clear(); }

    public DoubleIterator iterator() {
     return new AbstractDoubleIterator () {
       final ObjectIterator<Map.Entry<Double,Short>> i = entrySet().iterator();

       public double nextDouble() { return ((Double2ShortMap.Entry )i.next()).getDoubleKey(); };

       public boolean hasNext() { return i.hasNext(); }
      };
    }
   };
 }

 /** Returns a type-specific-set view of the values of this map.
	 *
	 * <P>The view is backed by the set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a set view of the values of this map; it may be safely cast to a type-specific interface.
	 */


 public ShortCollection values() {
  return new AbstractShortCollection () {

    public boolean contains( final short k ) { return containsValue( k ); }

    public int size() { return AbstractDouble2ShortMap.this.size(); }
    public void clear() { AbstractDouble2ShortMap.this.clear(); }

    public ShortIterator iterator() {
     return new AbstractShortIterator () {
       final ObjectIterator<Map.Entry<Double,Short>> i = entrySet().iterator();

       public short nextShort() { return ((Double2ShortMap.Entry )i.next()).getShortValue(); };

       public boolean hasNext() { return i.hasNext(); }
      };
    }
   };
 }


 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSet<Map.Entry<Double, Short>> entrySet() {
  return (ObjectSet)double2ShortEntrySet();
 }



 /** Returns a hash code for this map.
	 *
	 * The hash code of a map is computed by summing the hash codes of its entries.
	 *
	 * @return a hash code for this map.
	 */

 public int hashCode() {
  int h = 0, n = size();
  final ObjectIterator<? extends Map.Entry<Double,Short>> i = entrySet().iterator();

  while( n-- != 0 ) h += i.next().hashCode();
  return h;
 }

 public boolean equals(Object o) {
  if ( o == this ) return true;
  if ( ! ( o instanceof Map ) ) return false;

  Map<?,?> m = (Map<?,?>)o;
  if ( m.size() != size() ) return false;
  return entrySet().containsAll( m.entrySet() );
 }


 public String toString() {
  final StringBuilder s = new StringBuilder();
  final ObjectIterator<? extends Map.Entry<Double,Short>> i = entrySet().iterator();
  int n = size();
  Double2ShortMap.Entry e;
  boolean first = true;

  s.append("{");

  while(n-- != 0) {
   if (first) first = false;
   else s.append(", ");

   e = (Double2ShortMap.Entry )i.next();




    s.append(String.valueOf(e.getDoubleKey()));
   s.append("=>");



    s.append(String.valueOf(e.getShortValue()));
  }

  s.append("}");
  return s.toString();
 }


}
