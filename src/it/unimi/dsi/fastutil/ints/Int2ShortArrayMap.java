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
package it.unimi.dsi.fastutil.ints;
import java.util.Map;
import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import it.unimi.dsi.fastutil.shorts.ShortArraySet;
import it.unimi.dsi.fastutil.shorts.ShortArrays;
/** A simple, brute-force implementation of a map based on two parallel backing arrays. 
 * 
 * <p>The main purpose of this
 * implementation is that of wrapping cleanly the brute-force approach to the storage of a very 
 * small number of pairs: just put them into two parallel arrays and scan linearly to find an item.
 */
public class Int2ShortArrayMap extends AbstractInt2ShortMap implements java.io.Serializable, Cloneable {
 private static final long serialVersionUID = 1L;
 /** The keys (valid up to {@link #size}, excluded). */
 private transient int[] key;
 /** The values (parallel to {@link #key}). */
 private transient short[] value;
 /** The number of valid entries in {@link #key} and {@link #value}. */
 private int size;
 /** Creates a new empty array map with given key and value backing arrays. The resulting map will have as many entries as the given arrays.
	 * 
	 * <p>It is responsibility of the caller that the elements of <code>key</code> are distinct.
	 * 
	 * @param key the key array.
	 * @param value the value array (it <em>must</em> have the same length as <code>key</code>).
	 */
 public Int2ShortArrayMap( final int[] key, final short[] value ) {
  this.key = key;
  this.value = value;
  size = key.length;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
 }
 /** Creates a new empty array map.
	 */
 public Int2ShortArrayMap() {
  this.key = IntArrays.EMPTY_ARRAY;
  this.value = ShortArrays.EMPTY_ARRAY;
 }
 /** Creates a new empty array map of given capacity.
	 *
	 * @param capacity the initial capacity.
	 */
 public Int2ShortArrayMap( final int capacity ) {
  this.key = new int[ capacity ];
  this.value = new short[ capacity ];
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Int2ShortArrayMap( final Int2ShortMap m ) {
  this( m.size() );
  putAll( m );
 }
 /** Creates a new empty array map copying the entries of a given map.
	 *
	 * @param m a map.
	 */
 public Int2ShortArrayMap( final Map<? extends Integer, ? extends Short> m ) {
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
 public Int2ShortArrayMap( final int[] key, final short[] value, final int size ) {
  this.key = key;
  this.value = value;
  this.size = size;
  if( key.length != value.length ) throw new IllegalArgumentException( "Keys and values have different lengths (" + key.length + ", " + value.length + ")" );
  if ( size > key.length ) throw new IllegalArgumentException( "The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")" );
 }
 private final class EntrySet extends AbstractObjectSet<Int2ShortMap.Entry > implements FastEntrySet {
  @Override
  public ObjectIterator<Int2ShortMap.Entry > iterator() {
   return new AbstractObjectIterator<Int2ShortMap.Entry >() {
    int next = 0;
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     return new AbstractInt2ShortMap.BasicEntry ( key[ next ], value[ next++ ] );
    }
   };
  }
  public ObjectIterator<Int2ShortMap.Entry > fastIterator() {
   return new AbstractObjectIterator<Int2ShortMap.Entry >() {
    int next = 0;
    final BasicEntry entry = new BasicEntry ( ((int)0), ((short)0) );
    public boolean hasNext() {
     return next < size;
    }
    @SuppressWarnings("unchecked")
    public Entry next() {
     if ( ! hasNext() ) throw new NoSuchElementException();
     entry.key = key[ next ];
     entry.value = value[ next++ ];
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
   final Map.Entry<Integer, Short> e = (Map.Entry<Integer, Short>)o;
   final int k = ((e.getKey()).intValue());
   return Int2ShortArrayMap.this.containsKey( k ) && ( (Int2ShortArrayMap.this.get( k )) == (((e.getValue()).shortValue())) );
  }
 }
 public FastEntrySet int2ShortEntrySet() {
  return new EntrySet();
 }
 private int findKey( final int k ) {
  final int[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return i;
  return -1;
 }

 @SuppressWarnings("unchecked")

 public short get( final int k ) {



  final int[] key = this.key;
  for( int i = size; i-- != 0; ) if ( ( (key[ i ]) == (k) ) ) return value[ i ];
  return defRetValue;
 }

 public int size() {
  return size;
 }

 @Override
 public void clear() {
  size = 0;
 }
 @Override
 public boolean containsKey( final int k ) {
  return findKey( k ) != -1;
 }
 @Override
 @SuppressWarnings("unchecked")
 public boolean containsValue( short v ) {
  for( int i = size; i-- != 0; ) if ( ( (value[ i ]) == (v) ) ) return true;
  return false;
 }
 @Override
 public boolean isEmpty() {
  return size == 0;
 }
 @Override
 @SuppressWarnings("unchecked")
 public short put( int k, short v ) {
  final int oldKey = findKey( k );
  if ( oldKey != -1 ) {
   final short oldValue = value[ oldKey ];
   value[ oldKey ] = v;
   return oldValue;
  }
  if ( size == key.length ) {
   final int[] newKey = new int[ size == 0 ? 2 : size * 2 ];
   final short[] newValue = new short[ size == 0 ? 2 : size * 2 ];
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
 public short remove( final int k ) {
  final int oldPos = findKey( k );
  if ( oldPos == -1 ) return defRetValue;
  final short oldValue = value[ oldPos ];
  final int tail = size - oldPos - 1;
  for( int i = 0; i < tail; i++ ) {
   key[ oldPos + i ] = key[ oldPos + i + 1 ];
   value[ oldPos + i ] = value[ oldPos + i + 1 ];
  }
  size--;
  return oldValue;
 }
 @Override
 @SuppressWarnings("unchecked")
 public IntSet keySet() {
  return new IntArraySet ( key, size );
 }
 @Override
 public ShortCollection values() {
  return ShortCollections.unmodifiable( new ShortArraySet ( value, size ) );
 }
 /** Returns a deep copy of this map. 
	 *
	 * <P>This method performs a deep copy of this hash map; the data stored in the
	 * map, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this map.
	 */
 @SuppressWarnings("unchecked")
 public Int2ShortArrayMap clone() {
  Int2ShortArrayMap c;
  try {
   c = (Int2ShortArrayMap )super.clone();
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
   s.writeInt( key[ i ] );
   s.writeShort( value[ i ] );
  }
 }
 @SuppressWarnings("unchecked")
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  key = new int[ size ];
  value = new short[ size ];
  for( int i = 0; i < size; i++ ) {
   key[ i ] = s.readInt();
   value[ i ] = s.readShort();
  }
 }
}
