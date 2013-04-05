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
package it.unimi.dsi.fastutil.shorts;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractShort2CharSortedMap extends AbstractShort2CharMap implements Short2CharSortedMap {
 private static final long serialVersionUID = -1773560792952436569L;
 protected AbstractShort2CharSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Short2CharSortedMap headMap( final Short to ) {
  return headMap( ((to).shortValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Short2CharSortedMap tailMap( final Short from ) {
  return tailMap( ((from).shortValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Short2CharSortedMap subMap( final Short from, final Short to ) {
  return subMap( ((from).shortValue()), ((to).shortValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Short firstKey() {
  return (Short.valueOf(firstShortKey()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Short lastKey() {
  return (Short.valueOf(lastShortKey()));
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
 public ShortSortedSet keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractShortSortedSet {
  public boolean contains( final short k ) { return containsKey( k ); }
  public int size() { return AbstractShort2CharSortedMap.this.size(); }
  public void clear() { AbstractShort2CharSortedMap.this.clear(); }
  public ShortComparator comparator() { return AbstractShort2CharSortedMap.this.comparator(); }
  public short firstShort() { return firstShortKey(); }
  public short lastShort() { return lastShortKey(); }
  public ShortSortedSet headSet( final short to ) { return headMap( to ).keySet(); }
  public ShortSortedSet tailSet( final short from ) { return tailMap( from ).keySet(); }
  public ShortSortedSet subSet( final short from, final short to ) { return subMap( from, to ).keySet(); }

  public ShortBidirectionalIterator iterator( final short from ) { return new KeySetIterator ( entrySet().iterator( new BasicEntry ( from, ((char)0) ) ) ); }
  public ShortBidirectionalIterator iterator() { return new KeySetIterator ( entrySet().iterator() ); }


 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class KeySetIterator extends AbstractShortBidirectionalIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Short, Character>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Short, Character>> i ) {
   this.i = i;
  }

  public short nextShort() { return ((i.next().getKey()).shortValue()); };
  public short previousShort() { return ((i.previous().getKey()).shortValue()); };

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

 public CharCollection values() {
  return new ValuesCollection();
 }

 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractCharCollection {
  public CharIterator iterator() { return new ValuesIterator ( entrySet().iterator() ); }
  public boolean contains( final char k ) { return containsValue( k ); }
  public int size() { return AbstractShort2CharSortedMap.this.size(); }
  public void clear() { AbstractShort2CharSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator extends AbstractCharIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Short, Character>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Short, Character>> i ) {
   this.i = i;
  }

  public char nextChar() { return ((i.next().getValue()).charValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Short, Character>> entrySet() {
  return (ObjectSortedSet)short2CharEntrySet();
 }
}
