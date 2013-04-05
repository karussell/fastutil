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
 * Copyright (C) 2007-2013 Sebastiano Vigna 
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
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectCollections;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
/** A simple, brute-force implementation of a map based on two parallel backing arrays. 
 * 
 * <p>The main purpose of this
 * implementation is that of wrapping cleanly the brute-force approach to the storage of a very 
 * small number of pairs: just put them into two parallel arrays and scan linearly to find an item.
 */
public class Double2ObjectArrayMap <V> extends AbstractDouble2ObjectMap <V> implements java.io.Serializable, Cloneable {
 private static final long serialVersionUID = 1L;
 /** The keys (valid up to {@link #size}, excluded). */
 private transient double[] key;
 /** The values (parallel to {@link #key}). */
 private transient Object[] value;
 /** The number of valid entries in {@link #key} and {@link #value}. */
 private int size;
 /** Creates a new empty array map with given key and value backing arrays. The resulting map will have as many entries as the given arrays.
	 * 
	 * <p>It is responsibility of the caller that the elements of <code>key</code> are distinct.
	 * 
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 */
 public Double2ObjectArrayMap( final double[] key, final Object[] value ) {
  this.key = key;
  this.value = value;
  size = key.length;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
 }
 /** Creates a new empty array map.
	 */
 public Double2ObjectArrayMap() {
  this.key = DoubleArrays.EMPTY_ARRAY;
  this.value = ObjectArrays.EMPTY_ARRAY;
 }
 /** Creates a new empty array map of given capacity.
	 *
	 * @param capacity the initial capacity.
	 */
 public Double2ObjectArrayMap( final int capacity ) {
  this.key = new double[ capacity ];
  this.value = new Object[ capacity ];
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Double2ObjectArrayMap( final Double2ObjectMap <V> m ) {
  this( m.size() );
  putAll( m );
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Double2ObjectArrayMap( final Map<? extends Double, ? extends V> m ) {
  this( m.size() );
  putAll( m );
 }
 /** Creates a new array map with given key and value backing arrays, using the given number of elements.
	 * 
	 * <p>It is responsibility of the caller that the first <code>size</code> elements of <code>key</code> are distinct.
	 * 
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 * @param size the number of valid elements in <code>key</code> and <code>value</code>.
	 */
 public Double2ObjectArrayMap( final double[] key, final Object[] value, final int size ) {
  this.key = key;
  this.value = value;
  this.size = size;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
  if ( size > key.length ) throw new IllegalArgumentException( "The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")" );
 }
 private final class EntrySet extends AbstractObjectSet<Double2ObjectMap.Entry <V> > implements FastEntrySet <V> {
  @Override
  public ObjectIterator<Double2ObjectMap.Entry <V> > iterator() {
   return new AbstractObjectIterator<Double2ObjectMap.Entry <V> >() {
    int next = 0;
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry <V> next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     return new AbstractDouble2ObjectMap.BasicEntry <V>( key[ next ], (V) value[ next++ ] );
    }
   };
  }
  public ObjectIterator<Double2ObjectMap.Entry <V> > fastIterator() {
   return new AbstractObjectIterator<Double2ObjectMap.Entry <V> >() {
    int next = 0;
    final BasicEntry <V> entry = new BasicEntry <V> ( ((double)0), (null) );
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry <V> next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     entry.key = key[ next ];
     entry.value = (V) value[ next++ ];
     return entry;
    }
   };
  }
  public int size() {
   return size;
  }
  @SuppressWarnings("unchecked")
  public boolean contains( Object o ) {
   if ( ! ( o instanceof Map.Entry ) ) return false;
   final Map.Entry<Double, V> e = (Map.Entry<Double, V>)o;
   final double k = ((e.getKey()).doubleValue());
   return Double2ObjectArrayMap.this.containsKey( k ) && ( (Double2ObjectArrayMap.this.get( k )) == null ? ((e.getValue())) == null : (Double2ObjectArrayMap.this.get( k )).equals((e.getValue())) );
  }
 }
 public FastEntrySet <V> double2ObjectEntrySet() {
  return new EntrySet();
 }

 private int findKey( final double k ) {
  final double[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return i;
  return -1;
 }

 @SuppressWarnings("unchecked")

 public V get( final double k ) {



  final double[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return (V) value[ i ];
  return defRetValue;
 }

 public int size() {
  return size;
 }

 @Override
 public void clear() {

  for( int i = size; i-- != 0; ) {




   value[ i ] = null;

  }

  size = 0;
 }

 @Override
 public boolean containsKey( final double k ) {
  return findKey( k ) != -1;
 }

 @Override
 @SuppressWarnings("unchecked")
 public boolean containsValue( Object v ) {
  for( int i = size; i-- != 0; ) if ( ( (value[ i ]) == null ? (v) == null : (value[ i ]).equals(v) ) ) return true;
  return false;
 }

 @Override
 public boolean isEmpty() {
  return size == 0;
 }

 @Override
 @SuppressWarnings("unchecked")
 public V put( double k, V v ) {
  final int oldKey = findKey( k );
  if ( oldKey != -1 ) {
   final V oldValue = (V) value[ oldKey ];
   value[ oldKey ] = v;
   return oldValue;
  }
  if ( size == key.length ) {
   final double[] newKey = new double[ size == 0 ? 2 : size * 2 ];
   final Object[] newValue = new Object[ size == 0 ? 2 : size * 2 ];
   for( int i = size; i-- != 0; ) {
    newKey[ i ] = key[ i ];
    newValue[ i ] = value[ i ];
   }
   key = newKey;
   value = newValue;
  }
  key[ size ] = k;
  value[ size ] = v;
  size++;
  return defRetValue;
 }

 @Override
 @SuppressWarnings("unchecked")


 public V remove( final double k ) {



  final int oldPos = findKey( k );
  if ( oldPos == -1 ) return defRetValue;
  final V oldValue = (V) value[ oldPos ];
  final int tail = size - oldPos - 1;
  for( int i = 0; i < tail; i++ ) {
   key[ oldPos + i ] = key[ oldPos + i + 1 ];
   value[ oldPos + i ] = value[ oldPos + i + 1 ];
  }
  size--;




  value[ size ] = null;

  return oldValue;
 }

 @Override

 @SuppressWarnings("unchecked")
 public DoubleSet keySet() {
  return new DoubleArraySet ( key, size );
 }

 @Override
 public ObjectCollection <V> values() {
  return ObjectCollections.unmodifiable( new ObjectArraySet <V>( value, size ) );
 }

 /** Returns a deep copy of this map. 
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */

 @SuppressWarnings("unchecked")
 public Double2ObjectArrayMap <V> clone() {
  Double2ObjectArrayMap <V> c;
  try {
   c = (Double2ObjectArrayMap <V>)super.clone();
  }
  catch(CloneNotSupportedException cantHappen) {
   throw new InternalError();
  }
  c.key = key.clone();
  c.value = value.clone();
  return c;
 }

 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
  s.defaultWriteObject();
  for( int i = 0; i < size; i++ ) {
   s.writeDouble( key[ i ] );
   s.writeObject( value[ i ] );
  }
 }

 @SuppressWarnings("unchecked")
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  key = new double[ size ];
  value = new Object[ size ];
  for( int i = 0; i < size; i++ ) {
   key[ i ] = s.readDouble();
   value[ i ] = s.readObject();
  }
 }
}
