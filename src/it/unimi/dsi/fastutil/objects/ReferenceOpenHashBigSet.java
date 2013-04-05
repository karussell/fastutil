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
import it.unimi.dsi.fastutil.BigArrays;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.Size64;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
import static it.unimi.dsi.fastutil.HashCommon.bigArraySize;
import static it.unimi.dsi.fastutil.HashCommon.maxFill;
import java.util.Collection;
import java.util.NoSuchElementException;
/**  A type-specific hash big set with with a fast, small-footprint implementation.
 *
 * <P>Instances of this class use a hash table to represent a big set: the number
 * of elements in the set is limited only by the amount of core memory. The table is
 * backed by a {@linkplain it.unimi.dsi.fastutil.BigArrays big array} and is
 * enlarged as needed by doubling its size when new entries are created, but it is <em>never</em> made
 * smaller (even on a {@link #clear()}). A family of {@linkplain #trim(long) trimming
 * method} lets you control the size of the table; this is particularly useful
 * if you reuse instances of this class.
 *
 * <p>The methods of this class are about 30% slower than those of the corresponding non-big set.
 *
 * @see Hash
 * @see HashCommon
 */
public class ReferenceOpenHashBigSet <K> extends AbstractReferenceSet <K> implements java.io.Serializable, Cloneable, Hash, Size64 {
    private static final long serialVersionUID = 0L;
 private static final boolean ASSERTS = false;
 /** The big array of keys. */
 protected transient K[][] key;
 /** The big array telling whether a position is used. */
 protected transient boolean[][] used;
 /** The acceptable load factor. */
 protected final float f;
 /** The current table size (always a power of 2). */
 protected transient long n;
 /** Threshold after which we rehash. It must be the table size times {@link #f}. */
 protected transient long maxFill;
 /** The mask for wrapping a position counter. */
 protected transient long mask;
 /** The mask for wrapping a segment counter. */
 protected transient int segmentMask;
 /** The mask for wrapping a base counter. */
 protected transient int baseMask;
 /** Number of entries in the set. */
 protected long size;
 /** Initialises the mask values. */
 private void initMasks() {
  mask = n - 1;
  /* Note that either we have more than one segment, and in this case all segments
		 * are BigArrays.SEGMENT_SIZE long, or we have exactly one segment whose length
		 * is a power of two. */
  segmentMask = key[ 0 ].length - 1;
  baseMask = key.length - 1;
 }
 /** Creates a new hash big set.
	 *
	 * <p>The actual table size will be the least power of two greater than <code>expected</code>/<code>f</code>.
	 *
	 * @param expected the expected number of elements in the set. 
	 * @param f the load factor.
	 */
 @SuppressWarnings("unchecked")
 public ReferenceOpenHashBigSet( final long expected, final float f ) {
  if ( f <= 0 || f > 1 ) throw new IllegalArgumentException( "Load factor must be greater than 0 and smaller than or equal to 1" );
  if ( n < 0 ) throw new IllegalArgumentException( "The expected number of elements must be nonnegative" );
  this.f = f;
  n = bigArraySize( expected, f );
  maxFill = maxFill( n, f );
  key = (K[][]) ObjectBigArrays.newBigArray( n );
  used = BooleanBigArrays.newBigArray( n );
  initMasks();
 }


 /** Creates a new hash big set with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor.
	 *
	 * @param expected the expected number of elements in the hash big set. 
	 */

 public ReferenceOpenHashBigSet( final long expected ) {
  this( expected, DEFAULT_LOAD_FACTOR );
 }

 /** Creates a new hash big set with initial expected {@link Hash#DEFAULT_INITIAL_SIZE} elements
	 * and {@link Hash#DEFAULT_LOAD_FACTOR} as load factor.
	 */

 public ReferenceOpenHashBigSet() {
  this( DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR );
 }

 /** Creates a new hash big set copying a given collection.
	 *
	 * @param c a {@link Collection} to be copied into the new hash big set. 
	 * @param f the load factor.
	 */

 public ReferenceOpenHashBigSet( final Collection<? extends K> c, final float f ) {
  this( c.size(), f );
  addAll( c );
 }

 /** Creates a new hash big set  with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor 
	 * copying a given collection.
	 *
	 * @param c a {@link Collection} to be copied into the new hash big set. 
	 */

 public ReferenceOpenHashBigSet( final Collection<? extends K> c ) {
  this( c, DEFAULT_LOAD_FACTOR );
 }

 /** Creates a new hash big set copying a given type-specific collection.
	 *
	 * @param c a type-specific collection to be copied into the new hash big set. 
	 * @param f the load factor.
	 */

 public ReferenceOpenHashBigSet( final ReferenceCollection <? extends K> c, final float f ) {
  this( c.size(), f );
  addAll( c );
 }

 /** Creates a new hash big set  with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor 
	 * copying a given type-specific collection.
	 *
	 * @param c a type-specific collection to be copied into the new hash big set. 
	 */

 public ReferenceOpenHashBigSet( final ReferenceCollection <? extends K> c ) {
  this( c, DEFAULT_LOAD_FACTOR );
 }

 /** Creates a new hash big set using elements provided by a type-specific iterator.
	 *
	 * @param i a type-specific iterator whose elements will fill the new hash big set.
	 * @param f the load factor.
	 */

 public ReferenceOpenHashBigSet( final ObjectIterator <K> i, final float f ) {
  this( DEFAULT_INITIAL_SIZE, f );
  while( i.hasNext() ) add( i.next() );
 }

 /** Creates a new hash big set with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor using elements provided by a type-specific iterator.
	 *
	 * @param i a type-specific iterator whose elements will fill the new hash big set.
	 */

 public ReferenceOpenHashBigSet( final ObjectIterator <K> i ) {
  this( i, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash big set and fills it with the elements of a given array.
	 *
	 * @param a an array whose elements will be used to fill the new hash big set.
	 * @param offset the first element to use.
	 * @param length the number of elements to use.
	 * @param f the load factor.
	 */
 public ReferenceOpenHashBigSet( final K[] a, final int offset, final int length, final float f ) {
  this( length < 0 ? 0 : length, f );
  ObjectArrays.ensureOffsetLength( a, offset, length );
  for( int i = 0; i < length; i++ ) add( a[ offset + i ] );
 }
 /** Creates a new hash big set with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor and fills it with the elements of a given array.
	 *
	 * @param a an array whose elements will be used to fill the new hash big set.
	 * @param offset the first element to use.
	 * @param length the number of elements to use.
	 */
 public ReferenceOpenHashBigSet( final K[] a, final int offset, final int length ) {
  this( a, offset, length, DEFAULT_LOAD_FACTOR );
 }
 /** Creates a new hash big set copying the elements of an array.
	 *
	 * @param a an array to be copied into the new hash big set. 
	 * @param f the load factor.
	 */
 public ReferenceOpenHashBigSet( final K[] a, final float f ) {
  this( a, 0, a.length, f );
 }
 /** Creates a new hash big set with {@link Hash#DEFAULT_LOAD_FACTOR} as load factor 
	 * copying the elements of an array.
	 *
	 * @param a an array to be copied into the new hash big set. 
	 */
 public ReferenceOpenHashBigSet( final K[] a ) {
  this( a, DEFAULT_LOAD_FACTOR );
 }
 public boolean add( final K k ) {
  final long h = ( (k) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(k) ) );
  // The starting point.
  int displ = (int)( h & segmentMask );
  int base = (int)( ( h & mask ) >>> BigArrays.SEGMENT_SHIFT );
  // There's always an unused entry.
  while( used[ base ][ displ ] ) {
   if ( ( (key[ base ][ displ ]) == (k) ) ) return false;
   base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) ) & baseMask;
  }
  used[ base ][ displ ] = true;
  key[ base ][ displ ] = k;
  if ( ++size >= maxFill ) rehash( 2 * n );
  if ( ASSERTS ) checkTable();
  return true;
 }
 /** Shifts left entries with the specified hash code, starting at the specified position,
	 * and empties the resulting free entry.
	 *
	 * @param pos a starting position.
	 * @return the position cleared by the shifting process.
	 */
 protected final long shiftKeys( long pos ) {
  // Shift entries with the same hash.
  long last, slot;
  /*
		for( int i = 0; i < 10; i++ ) System.err.print( key[ ( t + i ) & mask ] + "(" + (avalanche( (long)KEY2INT( key[ ( t + i ) & mask ] ) ) & mask) + "; " + used[ ( t + i ) & mask ] + ") ");
		System.err.println();
		*/
  for(;;) {
   pos = ( ( last = pos ) + 1 ) & mask;
   while( BooleanBigArrays.get( used, pos ) ) {
    slot = ( (ObjectBigArrays.get( key, pos )) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(ObjectBigArrays.get( key, pos )) ) ) & mask;
    if ( last <= pos ? last >= slot || slot > pos : last >= slot && slot > pos ) break;
    pos = ( pos + 1 ) & mask;
   }
   if ( ! BooleanBigArrays.get( used, pos ) ) break;
   ObjectBigArrays.set( key, last, ObjectBigArrays.get( key, pos ) );
  }
  BooleanBigArrays.set( used, last, false );
  ObjectBigArrays.set( key, last, null );
  return last;
 }
 @SuppressWarnings("unchecked")
 public boolean remove( final Object k ) {
  final long h = ( (k) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(k) ) );
  // The starting point.
  int displ = (int)( h & segmentMask );
  int base = (int)( ( h & mask ) >>> BigArrays.SEGMENT_SHIFT );
  // There's always an unused entry.
  while( used[ base ][ displ ] ) {
   if ( ( (key[ base ][ displ ]) == (k) ) ) {
    size--;
    shiftKeys( base * (long)BigArrays.SEGMENT_SIZE + displ );
    if ( ASSERTS ) checkTable();
    return true;
   }
   base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) ) & baseMask;
  }
  return false;
 }
 @SuppressWarnings("unchecked")
 public boolean contains( final Object k ) {
  final long h = ( (k) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(k) ) );
  // The starting point.
  int displ = (int)( h & segmentMask );
  int base = (int)( ( h & mask ) >>> BigArrays.SEGMENT_SHIFT );
  // There's always an unused entry.
  while( used[ base ][ displ ] ) {
   if ( ( (key[ base ][ displ ]) == (k) ) ) return true;
   base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) ) & baseMask;
  }
  return false;
 }
 /* Removes all elements from this set.
	 *
	 * <P>To increase object reuse, this method does not change the table size.
	 * If you want to reduce the table size, you must use {@link #trim(long)}.
	 *
	 */
 public void clear() {
  if ( size == 0 ) return;
  size = 0;
  BooleanBigArrays.fill( used, false );
  ObjectBigArrays.fill( key, null );
 }
 /** An iterator over a hash big set. */
 private class SetIterator extends AbstractObjectIterator <K> {
  /** The base of the next entry to be returned, if positive or zero. If negative, the next entry to be
			returned, if any, is that of index -base -2 from the {@link #wrapped} list. */
  int base;
  /** The displacement of the next entry to be returned. */
  int displ;
  /** The base of the last entry that has been returned. It is -1 if either
			we did not return an entry yet, or the last returned entry has been removed. */
  int lastBase;
  /** The displacement of the last entry that has been returned. It is undefined if either
			we did not return an entry yet, or the last returned entry has been removed. */
  int lastDispl;
  /** A downward counter measuring how many entries must still be returned. */
  long c = size;
  /** A lazily allocated list containing elements that have wrapped around the table because of removals; such elements
			would not be enumerated (other elements would be usually enumerated twice in their place). */
  ReferenceArrayList <K> wrapped;
  {
   base = key.length;
   lastBase = -1;
   final boolean used[][] = ReferenceOpenHashBigSet.this.used;
   if ( c != 0 ) do
    if ( displ-- == 0 ) {
     base--;
        displ = (int)mask;
    }
   while( ! used[ base ][ displ ] );
  }
  public boolean hasNext() {
   return c != 0;
  }
  public K next() {
   if ( ! hasNext() ) throw new NoSuchElementException();
   c--;
   // We are just enumerating elements from the wrapped list.
   if ( base < 0 ) return wrapped.get( - ( lastBase = --base ) - 2 );
   final K retVal = key[ lastBase = base ][ lastDispl = displ ];
   if ( c != 0 ) {
    final boolean used[][] = ReferenceOpenHashBigSet.this.used;
    do
     if ( displ-- == 0 ) {
      if ( base-- == 0 ) break;
         displ = (int)mask;
     }
    while( ! used[ base ][ displ ] );
    // When here base < 0 there are no more elements to be enumerated by scanning, but wrapped might be nonempty.
   }
   return retVal;
  }
  /** Shifts left entries with the specified hash code, starting at the specified position,
		 * and empties the resulting free entry. If any entry wraps around the table, instantiates
		 * lazily {@link #wrapped} and stores the entry.
		 *
		 * @param pos a starting position.
		 * @return the position cleared by the shifting process.
		 */
  protected final long shiftKeys( long pos ) {
   // Shift entries with the same hash.
   long last, slot;
   /*
			for( int i = 0; i < 10; i++ ) System.err.print( key[ ( t + i ) & mask ] + "(" + (avalanche( (long)KEY2INT( key[ ( t + i ) & mask ] ) ) & mask) + "; " + used[ ( t + i ) & mask ] + ") ");
			System.err.println();
			*/
   for(;;) {
    pos = ( ( last = pos ) + 1 ) & mask;
    while( BooleanBigArrays.get( used, pos ) ) {
     slot = ( (ObjectBigArrays.get( key, pos )) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(ObjectBigArrays.get( key, pos )) ) ) & mask;
     if ( last <= pos ? last >= slot || slot > pos : last >= slot && slot > pos ) break;
     pos = ( pos + 1 ) & mask;
    }
    if ( ! BooleanBigArrays.get( used, pos ) ) break;
    if ( pos < last ) {
     // Wrapped entry.
     if ( wrapped == null ) wrapped = new ReferenceArrayList <K>();
     wrapped.add( ObjectBigArrays.get( key, pos ) );
    }
    ObjectBigArrays.set( key, last, ObjectBigArrays.get( key, pos ) );
   }
   BooleanBigArrays.set( used, last, false );
   ObjectBigArrays.set( key, last, null );
   return last;
  }
  @SuppressWarnings("unchecked")
  public void remove() {
   if ( lastBase == -1 ) throw new IllegalStateException();
   if ( base < -1 ) {
    // We're removing wrapped entries.
    ReferenceOpenHashBigSet.this.remove( wrapped.set( - base - 2, null ) );
    lastBase = -1;
    return;
   }
   size--;
   if ( shiftKeys( lastBase * (long)BigArrays.SEGMENT_SIZE + lastDispl ) == base * (long)BigArrays.SEGMENT_SIZE + displ && c > 0 ) {
    c++;
    next();
   }
   lastBase = -1; // You can no longer remove this entry.
   if ( ASSERTS ) checkTable();
  }
 }
 public ObjectIterator <K> iterator() {
  return new SetIterator();
 }
 /** A no-op for backward compatibility. The kind of tables implemented by
	 * this class never need rehashing.
	 *
	 * <P>If you need to reduce the table size to fit exactly
	 * this set, use {@link #trim()}.
	 *
	 * @return true.
	 * @see #trim()
	 * @deprecated A no-op.
	 */
 @Deprecated
 public boolean rehash() {
  return true;
 }
 /** Rehashes this set, making the table as small as possible.
	 * 
	 * <P>This method rehashes the table to the smallest size satisfying the
	 * load factor. It can be used when the set will not be changed anymore, so
	 * to optimize access speed and size.
	 *
	 * <P>If the table size is already the minimum possible, this method
	 * does nothing.
	 *
	 * @return true if there was enough memory to trim the set.
	 * @see #trim(long)
	 */
 public boolean trim() {
  final long l = bigArraySize( size, f );
  if ( l >= n ) return true;
  try {
   rehash( l );
  }
  catch(OutOfMemoryError cantDoIt) { return false; }
  return true;
 }
 /** Rehashes this set if the table is too large.
	 * 
	 * <P>Let <var>N</var> be the smallest table size that can hold
	 * <code>max(n,{@link #size64()})</code> entries, still satisfying the load factor. If the current
	 * table size is smaller than or equal to <var>N</var>, this method does
	 * nothing. Otherwise, it rehashes this set in a table of size
	 * <var>N</var>.
	 *
	 * <P>This method is useful when reusing sets.  {@linkplain #clear() Clearing a
	 * set} leaves the table size untouched. If you are reusing a set
	 * many times, you can call this method with a typical
	 * size to avoid keeping around a very large table just
	 * because of a few large transient sets.
	 *
	 * @param n the threshold for the trimming.
	 * @return true if there was enough memory to trim the set.
	 * @see #trim()
	 */
 public boolean trim( final long n ) {
  final long l = bigArraySize( n, f );
  if ( this.n <= l ) return true;
  try {
   rehash( l );
  }
  catch( OutOfMemoryError cantDoIt ) { return false; }
  return true;
 }
 /** Resizes the set.
	 *
	 * <P>This method implements the basic rehashing strategy, and may be
	 * overriden by subclasses implementing different rehashing strategies (e.g.,
	 * disk-based rehashing). However, you should not override this method
	 * unless you understand the internal workings of this class.
	 *
	 * @param newN the new size
	 */
 @SuppressWarnings("unchecked")
 protected void rehash( final long newN ) {
  final boolean used[][] = this.used;
  final K key[][] = this.key;
  final boolean newUsed[][] = BooleanBigArrays.newBigArray( newN );
  final K newKey[][] = (K[][]) ObjectBigArrays.newBigArray( newN );
  final long newMask = newN - 1;
  final int newSegmentMask = newKey[ 0 ].length - 1;
  final int newBaseMask = newKey.length - 1;
  int base = 0, displ = 0;
  long h;
  K k;
  for( long i = size; i-- != 0; ) {
   while( ! used[ base ][ displ ] ) base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) );
   k = key[ base ][ displ ];
   h = ( (k) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(k) ) );
   // The starting point.
   int d = (int)( h & newSegmentMask );
   int b = (int)( ( h & newMask ) >>> BigArrays.SEGMENT_SHIFT );
   while( newUsed[ b ][ d ] ) b = ( b + ( ( d = ( d + 1 ) & newSegmentMask ) == 0 ? 1 : 0 ) ) & newBaseMask;
   newUsed[ b ][ d ] = true;
   newKey[ b ][ d ] = k;
   base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) );
  }
  this.n = newN;
  this.key = newKey;
  this.used = newUsed;
  initMasks();
  maxFill = maxFill( n, f );
 }
 @Deprecated
 public int size() {
  return (int)Math.min( Integer.MAX_VALUE, size );
 }
 public long size64() {
  return size;
 }
 public boolean isEmpty() {
  return size == 0;
 }
 /** Returns a deep copy of this big set. 
	 *
	 * <P>This method performs a deep copy of this big hash set; the data stored in the
	 * set, however, is not cloned. Note that this makes a difference only for object keys.
	 *
	 *  @return a deep copy of this big set.
	 */
 @SuppressWarnings("unchecked")
 public ReferenceOpenHashBigSet <K> clone() {
  ReferenceOpenHashBigSet <K> c;
  try {
   c = (ReferenceOpenHashBigSet <K>)super.clone();
  }
  catch(CloneNotSupportedException cantHappen) {
   throw new InternalError();
  }
  c.key = ObjectBigArrays.copy( key );
  c.used = BooleanBigArrays.copy( used );
  return c;
 }
 /** Returns a hash code for this set.
	 *
	 * This method overrides the generic method provided by the superclass. 
	 * Since <code>equals()</code> is not overriden, it is important
	 * that the value returned by this method is the same value as
	 * the one returned by the overriden method.
	 *
	 * @return a hash code for this set.
	 */
 public int hashCode() {
  final boolean used[][] = this.used;
  final K key[][] = this.key;
  int h = 0;
  int base = 0, displ = 0;
  for( long j = size; j-- != 0; ) {
   while( ! used[ base ][ displ ] ) base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) );
   if ( this != key[ base ][ displ ] )
    h += ( (key[ base ][ displ ]) == null ? 0 : System.identityHashCode(key[ base ][ displ ]) );
   base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) );
  }
  return h;
 }
 private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
  final ObjectIterator <K> i = iterator();
  s.defaultWriteObject();
  for( long j = size; j-- != 0; ) s.writeObject( i.next() );
 }
 @SuppressWarnings("unchecked")
 private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
  s.defaultReadObject();
  n = bigArraySize( size, f );
  maxFill = maxFill( n, f );
  final K[][] key = this.key = (K[][]) ObjectBigArrays.newBigArray( n );
  final boolean used[][] = this.used = BooleanBigArrays.newBigArray( n );
  initMasks();
  long h;
  K k;
  int base, displ;
  for( long i = size; i-- != 0; ) {
   k = (K) s.readObject();
   h = ( (k) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(k) ) );
   base = (int)( ( h & mask ) >>> BigArrays.SEGMENT_SHIFT );
   displ = (int)( h & segmentMask );
   while( used[ base ][ displ ] ) base = ( base + ( ( displ = ( displ + 1 ) & segmentMask ) == 0 ? 1 : 0 ) ) & baseMask;
   used[ base ][ displ ] = true;
   key[ base ][ displ ] = k;
  }
  if ( ASSERTS ) checkTable();
 }
 private void checkTable() {}
}
