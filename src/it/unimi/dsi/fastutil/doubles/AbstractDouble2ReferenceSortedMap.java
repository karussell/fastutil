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
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractDouble2ReferenceSortedMap <V> extends AbstractDouble2ReferenceMap <V> implements Double2ReferenceSortedMap <V> {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractDouble2ReferenceSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Double2ReferenceSortedMap <V> headMap( final Double to ) {
  return headMap( ((to).doubleValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Double2ReferenceSortedMap <V> tailMap( final Double from ) {
  return tailMap( ((from).doubleValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Double2ReferenceSortedMap <V> subMap( final Double from, final Double to ) {
  return subMap( ((from).doubleValue()), ((to).doubleValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Double firstKey() {
  return (Double.valueOf(firstDoubleKey()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Double lastKey() {
  return (Double.valueOf(lastDoubleKey()));
 }
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
 public DoubleSortedSet keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractDoubleSortedSet {
  public boolean contains( final double k ) { return containsKey( k ); }
  public int size() { return AbstractDouble2ReferenceSortedMap.this.size(); }
  public void clear() { AbstractDouble2ReferenceSortedMap.this.clear(); }
  public DoubleComparator comparator() { return AbstractDouble2ReferenceSortedMap.this.comparator(); }
  public double firstDouble() { return firstDoubleKey(); }
  public double lastDouble() { return lastDoubleKey(); }

  public DoubleSortedSet headSet( final double to ) { return headMap( to ).keySet(); }
  public DoubleSortedSet tailSet( final double from ) { return tailMap( from ).keySet(); }
  public DoubleSortedSet subSet( final double from, final double to ) { return subMap( from, to ).keySet(); }

  public DoubleBidirectionalIterator iterator( final double from ) { return new KeySetIterator <V>( entrySet().iterator( new BasicEntry <V>( from, (null) ) ) ); }
  public DoubleBidirectionalIterator iterator() { return new KeySetIterator <V>( entrySet().iterator() ); }


 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class KeySetIterator <V> extends AbstractDoubleBidirectionalIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Double, V>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Double, V>> i ) {
   this.i = i;
  }

  public double nextDouble() { return ((i.next().getKey()).doubleValue()); };
  public double previousDouble() { return ((i.previous().getKey()).doubleValue()); };

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

 public ReferenceCollection <V> values() {
  return new ValuesCollection();
 }

 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractReferenceCollection <V> {
  public ObjectIterator <V> iterator() { return new ValuesIterator <V>( entrySet().iterator() ); }
  public boolean contains( final Object k ) { return containsValue( k ); }
  public int size() { return AbstractDouble2ReferenceSortedMap.this.size(); }
  public void clear() { AbstractDouble2ReferenceSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator <V> extends AbstractObjectIterator <V> {
  protected final ObjectBidirectionalIterator<Map.Entry <Double, V>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Double, V>> i ) {
   this.i = i;
  }

  public V next() { return (i.next().getValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Double, V>> entrySet() {
  return (ObjectSortedSet)double2ReferenceEntrySet();
 }
}
