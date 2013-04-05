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
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
import java.util.Comparator;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractReference2FloatSortedMap <K> extends AbstractReference2FloatMap <K> implements Reference2FloatSortedMap <K> {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractReference2FloatSortedMap() {}
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
 public ReferenceSortedSet <K> keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractReferenceSortedSet <K> {
  public boolean contains( final Object k ) { return containsKey( k ); }
  public int size() { return AbstractReference2FloatSortedMap.this.size(); }
  public void clear() { AbstractReference2FloatSortedMap.this.clear(); }
  public Comparator <? super K> comparator() { return AbstractReference2FloatSortedMap.this.comparator(); }
  public K first() { return firstKey(); }
  public K last() { return lastKey(); }
  public ReferenceSortedSet <K> headSet( final K to ) { return headMap( to ).keySet(); }
  public ReferenceSortedSet <K> tailSet( final K from ) { return tailMap( from ).keySet(); }
  public ReferenceSortedSet <K> subSet( final K from, final K to ) { return subMap( from, to ).keySet(); }
  public ObjectBidirectionalIterator <K> iterator( final K from ) { return new KeySetIterator <K>( entrySet().iterator( new BasicEntry <K>( from, (0) ) ) ); }
  public ObjectBidirectionalIterator <K> iterator() { return new KeySetIterator <K>( entrySet().iterator() ); }
 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
 protected static class KeySetIterator <K> extends AbstractObjectBidirectionalIterator <K> {
  protected final ObjectBidirectionalIterator<Map.Entry <K, Float>> i;
  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <K, Float>> i ) {
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
 public FloatCollection values() {
  return new ValuesCollection();
 }
 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractFloatCollection {
  public FloatIterator iterator() { return new ValuesIterator <K>( entrySet().iterator() ); }
  public boolean contains( final float k ) { return containsValue( k ); }
  public int size() { return AbstractReference2FloatSortedMap.this.size(); }
  public void clear() { AbstractReference2FloatSortedMap.this.clear(); }
 }
 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */
 protected static class ValuesIterator <K> extends AbstractFloatIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <K, Float>> i;
  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <K, Float>> i ) {
   this.i = i;
  }
  public float nextFloat() { return ((i.next().getValue()).floatValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }
 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<K, Float>> entrySet() {
  return (ObjectSortedSet)reference2FloatEntrySet();
 }
}
