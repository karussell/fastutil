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
package it.unimi.dsi.fastutil.bytes;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractByte2LongSortedMap extends AbstractByte2LongMap implements Byte2LongSortedMap {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractByte2LongSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Byte2LongSortedMap headMap( final Byte to ) {
  return headMap( ((to).byteValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte2LongSortedMap tailMap( final Byte from ) {
  return tailMap( ((from).byteValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte2LongSortedMap subMap( final Byte from, final Byte to ) {
  return subMap( ((from).byteValue()), ((to).byteValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte firstKey() {
  return (Byte.valueOf(firstByteKey()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte lastKey() {
  return (Byte.valueOf(lastByteKey()));
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
 public ByteSortedSet keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractByteSortedSet {
  public boolean contains( final byte k ) { return containsKey( k ); }
  public int size() { return AbstractByte2LongSortedMap.this.size(); }
  public void clear() { AbstractByte2LongSortedMap.this.clear(); }
  public ByteComparator comparator() { return AbstractByte2LongSortedMap.this.comparator(); }
  public byte firstByte() { return firstByteKey(); }
  public byte lastByte() { return lastByteKey(); }
  public ByteSortedSet headSet( final byte to ) { return headMap( to ).keySet(); }
  public ByteSortedSet tailSet( final byte from ) { return tailMap( from ).keySet(); }
  public ByteSortedSet subSet( final byte from, final byte to ) { return subMap( from, to ).keySet(); }

  public ByteBidirectionalIterator iterator( final byte from ) { return new KeySetIterator ( entrySet().iterator( new BasicEntry ( from, (0) ) ) ); }
  public ByteBidirectionalIterator iterator() { return new KeySetIterator ( entrySet().iterator() ); }


 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class KeySetIterator extends AbstractByteBidirectionalIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Byte, Long>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Byte, Long>> i ) {
   this.i = i;
  }

  public byte nextByte() { return ((i.next().getKey()).byteValue()); };
  public byte previousByte() { return ((i.previous().getKey()).byteValue()); };

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
  public int size() { return AbstractByte2LongSortedMap.this.size(); }
  public void clear() { AbstractByte2LongSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator extends AbstractLongIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Byte, Long>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Byte, Long>> i ) {
   this.i = i;
  }

  public long nextLong() { return ((i.next().getValue()).longValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Byte, Long>> entrySet() {
  return (ObjectSortedSet)byte2LongEntrySet();
 }
}
