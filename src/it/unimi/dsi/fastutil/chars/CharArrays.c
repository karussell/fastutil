/* Generic definitions */


#define PACKAGE it.unimi.dsi.fastutil.chars
#define VALUE_PACKAGE it.unimi.dsi.fastutil.objects
/* Assertions (useful to generate conditional code) */
#unassert keyclass
#assert keyclass(Character)
#unassert keys
 #assert keys(primitive)
#unassert valueclass
#assert valueclass(Object)
#unassert values
 #assert values(reference)
/* Current type and class (and size, if applicable) */
#define KEY_TYPE char
#define VALUE_TYPE Object
#define KEY_CLASS Character
#define VALUE_CLASS Object
#if #keyclass(Object) || #keyclass(Reference)
#define KEY_GENERIC_CLASS K
#define KEY_GENERIC_TYPE K
#define KEY_GENERIC <K>
#define KEY_GENERIC_WILDCARD <?>
#define KEY_EXTENDS_GENERIC <? extends K>
#define KEY_SUPER_GENERIC <? super K>
#define KEY_GENERIC_CAST (K)
#define KEY_GENERIC_ARRAY_CAST (K[])
#define KEY_GENERIC_BIG_ARRAY_CAST (K[][])
#else
#define KEY_GENERIC_CLASS KEY_CLASS
#define KEY_GENERIC_TYPE KEY_TYPE
#define KEY_GENERIC
#define KEY_GENERIC_WILDCARD
#define KEY_EXTENDS_GENERIC
#define KEY_SUPER_GENERIC
#define KEY_GENERIC_CAST
#define KEY_GENERIC_ARRAY_CAST
#define KEY_GENERIC_BIG_ARRAY_CAST
#endif
#if #valueclass(Object) || #valueclass(Reference)
#define VALUE_GENERIC_CLASS V
#define VALUE_GENERIC_TYPE V
#define VALUE_GENERIC <V>
#define VALUE_EXTENDS_GENERIC <? extends V>
#define VALUE_GENERIC_CAST (V)
#define VALUE_GENERIC_ARRAY_CAST (V[])
#else
#define VALUE_GENERIC_CLASS VALUE_CLASS
#define VALUE_GENERIC_TYPE VALUE_TYPE
#define VALUE_GENERIC
#define VALUE_EXTENDS_GENERIC
#define VALUE_GENERIC_CAST
#define VALUE_GENERIC_ARRAY_CAST
#endif
#if #keyclass(Object) || #keyclass(Reference)
#if #valueclass(Object) || #valueclass(Reference)
#define KEY_VALUE_GENERIC <K,V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K, ? extends V>
#else
#define KEY_VALUE_GENERIC <K>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K>
#endif
#else
#if #valueclass(Object) || #valueclass(Reference)
#define KEY_VALUE_GENERIC <V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends V>
#else
#define KEY_VALUE_GENERIC
#define KEY_VALUE_EXTENDS_GENERIC
#endif
#endif
/* Value methods */
#define KEY_VALUE charValue
#define VALUE_VALUE ObjectValue
/* Interfaces (keys) */
#define COLLECTION CharCollection

#define SET CharSet

#define HASH CharHash

#define SORTED_SET CharSortedSet

#define STD_SORTED_SET CharSortedSet

#define FUNCTION Char2ObjectFunction
#define MAP Char2ObjectMap
#define SORTED_MAP Char2ObjectSortedMap
#if #keyclass(Object) || #keyclass(Reference)
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Char2ObjectSortedMap

#define STRATEGY PACKAGE.CharHash.Strategy

#endif
#define LIST CharList

#define BIG_LIST CharBigList

#define STACK CharStack

#define PRIORITY_QUEUE CharPriorityQueue

#define INDIRECT_PRIORITY_QUEUE CharIndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE CharIndirectDoublePriorityQueue

#define KEY_ITERATOR CharIterator

#define KEY_ITERABLE CharIterable

#define KEY_BIDI_ITERATOR CharBidirectionalIterator

#define KEY_LIST_ITERATOR CharListIterator

#define KEY_BIG_LIST_ITERATOR CharBigListIterator

#define STD_KEY_ITERATOR CharIterator

#define KEY_COMPARATOR CharComparator

/* Interfaces (values) */
#define VALUE_COLLECTION ObjectCollection

#define VALUE_ARRAY_SET ObjectArraySet

#define VALUE_ITERATOR ObjectIterator

#define VALUE_LIST_ITERATOR ObjectListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractCharCollection

#define ABSTRACT_SET AbstractCharSet

#define ABSTRACT_SORTED_SET AbstractCharSortedSet
#define ABSTRACT_FUNCTION AbstractChar2ObjectFunction
#define ABSTRACT_MAP AbstractChar2ObjectMap
#define ABSTRACT_FUNCTION AbstractChar2ObjectFunction
#define ABSTRACT_SORTED_MAP AbstractChar2ObjectSortedMap
#define ABSTRACT_LIST AbstractCharList

#define ABSTRACT_BIG_LIST AbstractCharBigList

#define SUBLIST CharSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractCharPriorityQueue

#define ABSTRACT_STACK AbstractCharStack

#define KEY_ABSTRACT_ITERATOR AbstractCharIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractCharBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractCharListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractCharBigListIterator

#if #keyclass(Object)
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractCharComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractObjectCollection

#define VALUE_ABSTRACT_ITERATOR AbstractObjectIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractObjectBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS CharCollections

#define SETS CharSets

#define SORTED_SETS CharSortedSets

#define LISTS CharLists

#define BIG_LISTS CharBigLists

#define MAPS Char2ObjectMaps
#define FUNCTIONS Char2ObjectFunctions
#define SORTED_MAPS Char2ObjectSortedMaps
#define PRIORITY_QUEUES CharPriorityQueues

#define HEAPS CharHeaps

#define SEMI_INDIRECT_HEAPS CharSemiIndirectHeaps

#define INDIRECT_HEAPS CharIndirectHeaps

#define ARRAYS CharArrays

#define BIG_ARRAYS CharBigArrays

#define ITERATORS CharIterators

#define BIG_LIST_ITERATORS CharBigListIterators

#define COMPARATORS CharComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS ObjectCollections

#define VALUE_SETS ObjectSets

#define VALUE_ARRAYS ObjectArrays

/* Implementations */
#define OPEN_HASH_SET CharOpenHashSet

#define OPEN_HASH_BIG_SET CharOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET CharOpenDoubleHashSet

#define OPEN_HASH_MAP Char2ObjectOpenHashMap

#define STRIPED_OPEN_HASH_MAP StripedChar2ObjectOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Char2ObjectOpenDoubleHashMap

#define ARRAY_SET CharArraySet

#define ARRAY_MAP Char2ObjectArrayMap

#define LINKED_OPEN_HASH_SET CharLinkedOpenHashSet

#define AVL_TREE_SET CharAVLTreeSet

#define RB_TREE_SET CharRBTreeSet

#define AVL_TREE_MAP Char2ObjectAVLTreeMap

#define RB_TREE_MAP Char2ObjectRBTreeMap

#define ARRAY_LIST CharArrayList

#define BIG_ARRAY_BIG_LIST CharBigArrayBigList

#define ARRAY_FRONT_CODED_LIST CharArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE CharHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE CharHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE CharHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE CharHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE CharHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE CharArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE CharArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE CharArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE CharArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedCharCollection

#define SYNCHRONIZED_SET SynchronizedCharSet

#define SYNCHRONIZED_SORTED_SET SynchronizedCharSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedChar2ObjectFunction

#define SYNCHRONIZED_MAP SynchronizedChar2ObjectMap

#define SYNCHRONIZED_LIST SynchronizedCharList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableCharCollection

#define UNMODIFIABLE_SET UnmodifiableCharSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableCharSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableChar2ObjectFunction

#define UNMODIFIABLE_MAP UnmodifiableChar2ObjectMap

#define UNMODIFIABLE_LIST UnmodifiableCharList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableCharIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableCharBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableCharListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER CharReaderWrapper

#define KEY_DATA_INPUT_WRAPPER CharDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY nextChar
#define PREV_KEY previousChar
#define FIRST_KEY firstCharKey
#define LAST_KEY lastCharKey
#define GET_KEY getChar
#define REMOVE_KEY removeChar
#define READ_KEY readChar
#define WRITE_KEY writeChar
#define DEQUEUE dequeueChar
#define DEQUEUE_LAST dequeueLastChar
#define SUBLIST_METHOD charSubList
#define SINGLETON_METHOD charSingleton

#define FIRST firstChar
#define LAST lastChar
#define TOP topChar
#define PEEK peekChar
#define POP popChar
#define KEY_ITERATOR_METHOD charIterator

#define KEY_LIST_ITERATOR_METHOD charListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyCharIterator

#define AS_KEY_ITERATOR asCharIterator

#define TO_KEY_ARRAY toCharArray
#define ENTRY_GET_KEY getCharKey
#define REMOVE_FIRST_KEY removeFirstChar
#define REMOVE_LAST_KEY removeLastChar
#define PARSE_KEY parseChar
#define LOAD_KEYS loadChars
#define LOAD_KEYS_BIG loadCharsBig
#define STORE_KEYS storeChars
/* Methods (values) */
#define NEXT_VALUE next
#define PREV_VALUE previous
#define READ_VALUE readObject
#define WRITE_VALUE writeObject
#define VALUE_ITERATOR_METHOD objectIterator

#define ENTRY_GET_VALUE getValue
#define REMOVE_FIRST_VALUE removeFirst
#define REMOVE_LAST_VALUE removeLast
/* Methods (keys/values) */
#define ENTRYSET char2ObjectEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if #keyclass(Object) || #keyclass(Reference)
#define GET_VALUE get
#define REMOVE_VALUE remove
#else
#define GET_VALUE get
#define REMOVE_VALUE remove
#endif
/* Equality */
#ifdef Custom
#define KEY_EQUALS(x,y) ( strategy.equals( (x),  KEY_GENERIC_CAST (y) ) )
#else
#if #keyclass(Object)
#define KEY_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x).equals(y) )
#else
#define KEY_EQUALS(x,y) ( (x) == (y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x) == (y) )
#endif
#endif

#if #valueclass(Object)
#define VALUE_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#else
#define VALUE_EQUALS(x,y) ( (x) == (y) )
#endif

/* Object/Reference-only definitions (keys) */
#if #keyclass(Object) || #keyclass(Reference)
#define REMOVE remove
#define KEY_OBJ2TYPE(x) (x)
#define KEY_CLASS2TYPE(x) (x)
#define KEY2OBJ(x) (x)
#if #keyclass(Object)
#ifdef Custom
#define KEY2JAVAHASH(x) ( strategy.hashCode( KEY_GENERIC_CAST (x)) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( strategy.hashCode( KEY_GENERIC_CAST (x)) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)strategy.hashCode( KEY_GENERIC_CAST (x)) ) )
#else
#define KEY2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#define KEY2INTHASH(x) ( (x) == null ? 0x87fcd5c : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (x).hashCode() ) )
#define KEY2LONGHASH(x) ( (x) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)(x).hashCode() ) )
#endif
#else
#define KEY2JAVAHASH(x) ( (x) == null ? 0 : System.identityHashCode(x) )
#define KEY2INTHASH(x) ( (x) == null ? 0x87fcd5c : it.unimi.dsi.fastutil.HashCommon.murmurHash3( System.identityHashCode(x) ) )
#define KEY2LONGHASH(x) ( (x) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(x) ) )
#endif
#define KEY_CMP(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) )
#define KEY_CMP_EQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) == 0 )
#define KEY_LESS(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) < 0 )
#define KEY_LESSEQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) <= 0 )
#define KEY_NULL (null)
#else
/* Primitive-type-only definitions (keys) */
#define REMOVE rem
#define KEY_CLASS2TYPE(x) ((x).KEY_VALUE())
#define KEY_OBJ2TYPE(x) (KEY_CLASS2TYPE((KEY_CLASS)(x)))
#define KEY2OBJ(x) (KEY_CLASS.valueOf(x))
#if #keyclass(Boolean)
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_NULL (false)
#define KEY_CMP(x,y) ( !(x) && (y) ? -1 : ( (x) == (y) ? 0 : 1 ) )
#define KEY_LESS(x,y) ( !(x) && (y) )
#define KEY_LESSEQ(x,y) ( !(x) || (y) )
#else
#define KEY_NULL ((KEY_TYPE)0)
#if #keyclass(Float) || #keyclass(Double)
#define KEY_CMP_EQ(x,y) ( KEY_CLASS.compare((x),(y)) == 0 )
#define KEY_CMP(x,y) ( KEY_CLASS.compare((x),(y)) )
#define KEY_LESS(x,y) ( KEY_CLASS.compare((x),(y)) < 0 )
#define KEY_LESSEQ(x,y) ( KEY_CLASS.compare((x),(y)) <= 0 )
#else
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_CMP(x,y) ( (x) < (y) ? -1 : ( (x) == (y) ? 0 : 1 ) )
#define KEY_LESS(x,y) ( (x) < (y) )
#define KEY_LESSEQ(x,y) ( (x) <= (y) )
#endif
#if #keyclass(Float)
#define KEY2LEXINT(x) fixFloat(x)
#elif #keyclass(Double)
#define KEY2LEXINT(x) fixDouble(x)
#else
#define KEY2LEXINT(x) (x)
#endif
#endif
#ifdef Custom
#define KEY2JAVAHASH(x) ( strategy.hashCode(x) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( strategy.hashCode(x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)strategy.hashCode(x) ) )
#else
#if #keyclass(Float)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.float2int(x)
#define KEY2INTHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3( it.unimi.dsi.fastutil.HashCommon.float2int(x) )
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)it.unimi.dsi.fastutil.HashCommon.float2int(x) )
#elif #keyclass(Double)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.double2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.murmurHash3(Double.doubleToRawLongBits(x))
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3(Double.doubleToRawLongBits(x))
#elif #keyclass(Long)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.long2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.murmurHash3(x)
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3(x)
#elif #keyclass(Boolean)
#define KEY2JAVAHASH(x) ((x) ? 1231 : 1237)
#define KEY2INTHASH(x) ((x) ? 0xfab5368 : 0xcba05e7b)
#define KEY2LONGHASH(x) ((x) ? 0x74a19fc8b6428188L : 0xbaeca2031a4fd9ecL)
#else
#define KEY2JAVAHASH(x) (x)
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)(x) ) )
#endif
#endif
#endif
/* Object/Reference-only definitions (values) */
#if #valueclass(Object) || #valueclass(Reference)
#define VALUE_OBJ2TYPE(x) (x)
#define VALUE_CLASS2TYPE(x) (x)
#define VALUE2OBJ(x) (x)
#if #valueclass(Object)
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#else
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : System.identityHashCode(x) )
#endif
#define VALUE_NULL (null)
#define OBJECT_DEFAULT_RETURN_VALUE (this.defRetValue)
#else
/* Primitive-type-only definitions (values) */
#define VALUE_CLASS2TYPE(x) ((x).VALUE_VALUE())
#define VALUE_OBJ2TYPE(x) (VALUE_CLASS2TYPE((VALUE_CLASS)(x)))
#define VALUE2OBJ(x) (VALUE_CLASS.valueOf(x))
#if #valueclass(Float) || #valueclass(Double) || #valueclass(Long)
#define VALUE_NULL (0)
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.Object2int(x)
#elif #valueclass(Boolean)
#define VALUE_NULL (false)
#define VALUE2JAVAHASH(x) (x ? 1231 : 1237)
#else
#if #valueclass(Integer)
#define VALUE_NULL (0)
#else
#define VALUE_NULL ((VALUE_TYPE)0)
#endif
#define VALUE2JAVAHASH(x) (x)
#endif
#define OBJECT_DEFAULT_RETURN_VALUE (null)
#endif
#include "drv/Arrays.drv"

