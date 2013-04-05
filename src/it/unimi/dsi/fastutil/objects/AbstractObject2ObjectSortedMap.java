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
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
import java.util.Comparator;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractObject2ObjectSortedMap <K,V> extends AbstractObject2ObjectMap <K,V> implements Object2ObjectSortedMap <K,V> {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractObject2ObjectSortedMap() {}
 /** Returns a type-specific-sorted-set view of the keys of this map.
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a sorted set view of the keys of this map; it may be safely cast to a type-specific interface.
	 */
 public ObjectSortedSet <K> keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractObjectSortedSet <K> {
  public boolean contains( final Object k ) { return containsKey( k ); }
  public int size() { return AbstractObject2ObjectSortedMap.this.size(); }
  public void clear() { AbstractObject2ObjectSortedMap.this.clear(); }
  public Comparator <? super K> comparator() { return AbstractObject2ObjectSortedMap.this.comparator(); }
  public K first() { return firstKey(); }
  public K last() { return lastKey(); }
  public ObjectSortedSet <K> headSet( final K to ) { return headMap( to ).keySet(); }
  public ObjectSortedSet <K> tailSet( final K from ) { return tailMap( from ).keySet(); }
  public ObjectSortedSet <K> subSet( final K from, final K to ) { return subMap( from, to ).keySet(); }
  public ObjectBidirectionalIterator <K> iterator( final K from ) { return new KeySetIterator <K,V>( entrySet().iterator( new BasicEntry <K,V>( from, (null) ) ) ); }
  public ObjectBidirectionalIterator <K> iterator() { return new KeySetIterator <K,V>( entrySet().iterator() ); }
 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
 protected static class KeySetIterator <K,V> extends AbstractObjectBidirectionalIterator <K> {
  protected final ObjectBidirectionalIterator<Map.Entry <K, V>> i;
  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <K, V>> i ) {
   this.i = i;
  }
  public K next() { return (i.next().getKey()); };
  public K previous() { return (i.previous().getKey()); };
  public boolean hasNext() { return i.hasNext(); }
  public boolean hasPrevious() { return i.hasPrevious(); }
 }
 /** Returns a type-specific collection view of the values contained in this map.
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a type-specific collection view of the values contained in this map.
	 */
 public ObjectCollection <V> values() {
  return new ValuesCollection();
 }
 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractObjectCollection <V> {
  public ObjectIterator <V> iterator() { return new ValuesIterator <K,V>( entrySet().iterator() ); }
  public boolean contains( final Object k ) { return containsValue( k ); }
  public int size() { return AbstractObject2ObjectSortedMap.this.size(); }
  public void clear() { AbstractObject2ObjectSortedMap.this.clear(); }
 }
 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
 protected static class ValuesIterator <K,V> extends AbstractObjectIterator <V> {
  protected final ObjectBidirectionalIterator<Map.Entry <K, V>> i;
  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <K, V>> i ) {
   this.i = i;
  }
  public V next() { return (i.next().getValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }
 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
  return (ObjectSortedSet)object2ObjectEntrySet();
 }
}
