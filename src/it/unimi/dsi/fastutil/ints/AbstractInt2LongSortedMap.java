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
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractInt2LongSortedMap extends AbstractInt2LongMap implements Int2LongSortedMap {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractInt2LongSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Int2LongSortedMap headMap( final Integer to ) {
  return headMap( ((to).intValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Int2LongSortedMap tailMap( final Integer from ) {
  return tailMap( ((from).intValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Int2LongSortedMap subMap( final Integer from, final Integer to ) {
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
  public int size() { return AbstractInt2LongSortedMap.this.size(); }
  public void clear() { AbstractInt2LongSortedMap.this.clear(); }
  public IntComparator comparator() { return AbstractInt2LongSortedMap.this.comparator(); }
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
  protected final ObjectBidirectionalIterator<Map.Entry <Integer, Long>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Integer, Long>> i ) {
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

 public LongCollection values() {
  return new ValuesCollection();
 }

 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractLongCollection {
  public LongIterator iterator() { return new ValuesIterator ( entrySet().iterator() ); }
  public boolean contains( final long k ) { return containsValue( k ); }
  public int size() { return AbstractInt2LongSortedMap.this.size(); }
  public void clear() { AbstractInt2LongSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator extends AbstractLongIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Integer, Long>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Integer, Long>> i ) {
   this.i = i;
  }

  public long nextLong() { return ((i.next().getValue()).longValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Integer, Long>> entrySet() {
  return (ObjectSortedSet)int2LongEntrySet();
 }
}
