#define SET_PREV( f64, p32 )       SET_UPPER( f64, p32 )
#define SET_NEXT( f64, n32 )       SET_LOWER( f64, n32 )
#define COPY_PREV( f64, p64 )      SET_UPPER64( f64, p64 )
#define COPY_NEXT( f64, n64 )      SET_LOWER64( f64, n64 )
#define GET_PREV( f64 )            GET_UPPER( f64 )
#define GET_NEXT( f64 )            GET_LOWER( f64 )
#define SET_UPPER_LOWER( f64, up32, low32 )    f64 = ( ( up32 & 0xFFFFFFFFL ) << 32 ) | ( low32 & 0xFFFFFFFFL )
#define SET_UPPER( f64, up32 )     f64 ^= ( ( f64 ^ ( ( up32 & 0xFFFFFFFFL ) << 32 ) ) & 0xFFFFFFFF00000000L )
#define SET_LOWER( f64, low32 )    f64 ^= ( ( f64 ^ ( low32 & 0xFFFFFFFFL ) ) & 0xFFFFFFFFL )
#define SET_UPPER64( f64, up64 )   f64 ^= ( ( f64 ^ ( up64 & 0xFFFFFFFF00000000L ) ) & 0xFFFFFFFF00000000L )
#define SET_LOWER64( f64, low64 )  f64 ^= ( ( f64 ^ ( low64 & 0xFFFFFFFFL ) ) & 0xFFFFFFFFL )
#define GET_UPPER( f64 )           (int) ( f64 >>> 32 )
#define GET_LOWER( f64 )           (int) f64

/* Generic definitions */
#define Linked

#define PACKAGE it.unimi.dsi.fastutil.objects
#define VALUE_PACKAGE it.unimi.dsi.fastutil.booleans
/* Assertions (useful to generate conditional code) */
#unassert keyclass
#assert keyclass(Reference)
#unassert keys
 #assert keys(reference)
#unassert valueclass
#assert valueclass(Boolean)
#unassert values
 #assert values(primitive)
/* Current type and class (and size, if applicable) */
#define KEY_TYPE Object
#define VALUE_TYPE boolean
#define KEY_CLASS Reference
#define VALUE_CLASS Boolean
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
#define KEY_VALUE ObjectValue
#define VALUE_VALUE booleanValue
/* Interfaces (keys) */
#define COLLECTION ReferenceCollection

#define SET ReferenceSet

#define HASH ReferenceHash

#define SORTED_SET ReferenceSortedSet

#define STD_SORTED_SET SortedSet

#define FUNCTION Reference2BooleanFunction
#define MAP Reference2BooleanMap
#define SORTED_MAP Reference2BooleanSortedMap
#if #keyclass(Object) || #keyclass(Reference)
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Reference2BooleanSortedMap

#define STRATEGY PACKAGE.ReferenceHash.Strategy

#endif
#define LIST ReferenceList

#define BIG_LIST ReferenceBigList

#define STACK Stack

#define PRIORITY_QUEUE PriorityQueue

#define INDIRECT_PRIORITY_QUEUE IndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE IndirectDoublePriorityQueue

#define KEY_ITERATOR ObjectIterator

#define KEY_ITERABLE ObjectIterable

#define KEY_BIDI_ITERATOR ObjectBidirectionalIterator

#define KEY_LIST_ITERATOR ObjectListIterator

#define KEY_BIG_LIST_ITERATOR ObjectBigListIterator

#define STD_KEY_ITERATOR Iterator

#define KEY_COMPARATOR Comparator

/* Interfaces (values) */
#define VALUE_COLLECTION BooleanCollection

#define VALUE_ARRAY_SET BooleanArraySet

#define VALUE_ITERATOR BooleanIterator

#define VALUE_LIST_ITERATOR BooleanListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractReferenceCollection

#define ABSTRACT_SET AbstractReferenceSet

#define ABSTRACT_SORTED_SET AbstractReferenceSortedSet
#define ABSTRACT_FUNCTION AbstractReference2BooleanFunction
#define ABSTRACT_MAP AbstractReference2BooleanMap
#define ABSTRACT_FUNCTION AbstractReference2BooleanFunction
#define ABSTRACT_SORTED_MAP AbstractReference2BooleanSortedMap
#define ABSTRACT_LIST AbstractReferenceList

#define ABSTRACT_BIG_LIST AbstractReferenceBigList

#define SUBLIST ReferenceSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractPriorityQueue

#define ABSTRACT_STACK AbstractStack

#define KEY_ABSTRACT_ITERATOR AbstractObjectIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractObjectBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractObjectListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractObjectBigListIterator

#if #keyclass(Object)
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractReferenceComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractBooleanCollection

#define VALUE_ABSTRACT_ITERATOR AbstractBooleanIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractBooleanBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS ReferenceCollections

#define SETS ReferenceSets

#define SORTED_SETS ReferenceSortedSets

#define LISTS ReferenceLists

#define BIG_LISTS ReferenceBigLists

#define MAPS Reference2BooleanMaps
#define FUNCTIONS Reference2BooleanFunctions
#define SORTED_MAPS Reference2BooleanSortedMaps
#define PRIORITY_QUEUES ObjectPriorityQueues

#define HEAPS ObjectHeaps

#define SEMI_INDIRECT_HEAPS ObjectSemiIndirectHeaps

#define INDIRECT_HEAPS ObjectIndirectHeaps

#define ARRAYS ObjectArrays

#define BIG_ARRAYS ObjectBigArrays

#define ITERATORS ObjectIterators

#define BIG_LIST_ITERATORS ObjectBigListIterators

#define COMPARATORS ObjectComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS BooleanCollections

#define VALUE_SETS BooleanSets

#define VALUE_ARRAYS BooleanArrays

/* Implementations */
#define OPEN_HASH_SET ReferenceLinkedOpenHashSet

#define OPEN_HASH_BIG_SET ReferenceLinkedOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET ReferenceLinkedOpenDoubleHashSet

#define OPEN_HASH_MAP Reference2BooleanLinkedOpenHashMap

#define STRIPED_OPEN_HASH_MAP StripedReference2BooleanOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Reference2BooleanLinkedOpenDoubleHashMap

#define ARRAY_SET ReferenceArraySet

#define ARRAY_MAP Reference2BooleanArrayMap

#define LINKED_OPEN_HASH_SET ReferenceLinkedOpenHashSet

#define AVL_TREE_SET ReferenceAVLTreeSet

#define RB_TREE_SET ReferenceRBTreeSet

#define AVL_TREE_MAP Reference2BooleanAVLTreeMap

#define RB_TREE_MAP Reference2BooleanRBTreeMap

#define ARRAY_LIST ReferenceArrayList

#define BIG_ARRAY_BIG_LIST ReferenceBigArrayBigList

#define ARRAY_FRONT_CODED_LIST ReferenceArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE ObjectHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE ObjectHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE ObjectHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE ObjectArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE ObjectArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE ObjectArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedReferenceCollection

#define SYNCHRONIZED_SET SynchronizedReferenceSet

#define SYNCHRONIZED_SORTED_SET SynchronizedReferenceSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedReference2BooleanFunction

#define SYNCHRONIZED_MAP SynchronizedReference2BooleanMap

#define SYNCHRONIZED_LIST SynchronizedReferenceList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableReferenceCollection

#define UNMODIFIABLE_SET UnmodifiableReferenceSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableReferenceSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableReference2BooleanFunction

#define UNMODIFIABLE_MAP UnmodifiableReference2BooleanMap

#define UNMODIFIABLE_LIST UnmodifiableReferenceList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableReferenceIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableReferenceBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableReferenceListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER ReferenceReaderWrapper

#define KEY_DATA_INPUT_WRAPPER ReferenceDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY next
#define PREV_KEY previous
#define FIRST_KEY firstKey
#define LAST_KEY lastKey
#define GET_KEY get
#define REMOVE_KEY remove
#define READ_KEY readObject
#define WRITE_KEY writeObject
#define DEQUEUE dequeue
#define DEQUEUE_LAST dequeueLast
#define SUBLIST_METHOD referenceSubList
#define SINGLETON_METHOD referenceSingleton

#define FIRST first
#define LAST last
#define TOP top
#define PEEK peek
#define POP pop
#define KEY_ITERATOR_METHOD objectIterator

#define KEY_LIST_ITERATOR_METHOD objectListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyObjectIterator

#define AS_KEY_ITERATOR asObjectIterator

#define TO_KEY_ARRAY toArray
#define ENTRY_GET_KEY getKey
#define REMOVE_FIRST_KEY removeFirst
#define REMOVE_LAST_KEY removeLast
#define PARSE_KEY parse
#define LOAD_KEYS loads
#define LOAD_KEYS_BIG loadsBig
#define STORE_KEYS stores
/* Methods (values) */
#define NEXT_VALUE nextBoolean
#define PREV_VALUE previousBoolean
#define READ_VALUE readBoolean
#define WRITE_VALUE writeBoolean
#define VALUE_ITERATOR_METHOD booleanIterator

#define ENTRY_GET_VALUE getBooleanValue
#define REMOVE_FIRST_VALUE removeFirstBoolean
#define REMOVE_LAST_VALUE removeLastBoolean
/* Methods (keys/values) */
#define ENTRYSET reference2BooleanEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if #keyclass(Object) || #keyclass(Reference)
#define GET_VALUE getBoolean
#define REMOVE_VALUE removeBoolean
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
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.boolean2int(x)
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
#include "drv/LinkedOpenHashMap.drv"

