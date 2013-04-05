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
package it.unimi.dsi.fastutil.ints;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractInt2IntSortedMap extends AbstractInt2IntMap implements Int2IntSortedMap {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractInt2IntSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Int2IntSortedMap headMap( final Integer to ) {
  return headMap( ((to).intValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Int2IntSortedMap tailMap( final Integer from ) {
  return tailMap( ((from).intValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Int2IntSortedMap subMap( final Integer from, final Integer to ) {
  return subMap( ((from).intValue()), ((to).intValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Integer firstKey() {
  return (Integer.valueOf(firstIntKey()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Integer lastKey() {
  return (Integer.valueOf(lastIntKey()));
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
 public IntSortedSet keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractIntSortedSet {
  public boolean contains( final int k ) { return containsKey( k ); }
  public int size() { return AbstractInt2IntSortedMap.this.size(); }
  public void clear() { AbstractInt2IntSortedMap.this.clear(); }
  public IntComparator comparator() { return AbstractInt2IntSortedMap.this.comparator(); }
  public int firstInt() { return firstIntKey(); }
  public int lastInt() { return lastIntKey(); }
  public IntSortedSet headSet( final int to ) { return headMap( to ).keySet(); }
  public IntSortedSet tailSet( final int from ) { return tailMap( from ).keySet(); }
  public IntSortedSet subSet( final int from, final int to ) { return subMap( from, to ).keySet(); }

  public IntBidirectionalIterator iterator( final int from ) { return new KeySetIterator ( entrySet().iterator( new BasicEntry ( from, (0) ) ) ); }
  public IntBidirectionalIterator iterator() { return new KeySetIterator ( entrySet().iterator() ); }


 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class KeySetIterator extends AbstractIntBidirectionalIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Integer, Integer>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Integer, Integer>> i ) {
   this.i = i;
  }

  public int nextInt() { return ((i.next().getKey()).intValue()); };
  public int previousInt() { return ((i.previous().getKey()).intValue()); };

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

 public IntCollection values() {
  return new ValuesCollection();
 }

 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractIntCollection {
  public IntIterator iterator() { return new ValuesIterator ( entrySet().iterator() ); }
  public boolean contains( final int k ) { return containsValue( k ); }
  public int size() { return AbstractInt2IntSortedMap.this.size(); }
  public void clear() { AbstractInt2IntSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator extends AbstractIntIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Integer, Integer>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Integer, Integer>> i ) {
   this.i = i;
  }

  public int nextInt() { return ((i.next().getValue()).intValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Integer, Integer>> entrySet() {
  return (ObjectSortedSet)int2IntEntrySet();
 }
}
