/* Generic definitions */


#define PACKAGE it.unimi.dsi.fastutil.floats
#define VALUE_PACKAGE it.unimi.dsi.fastutil.floats
/* Assertions (useful to generate conditional code) */
#unassert keyclass
#assert keyclass(Float)
#unassert keys
 #assert keys(primitive)
#unassert valueclass
#assert valueclass(Float)
#unassert values
 #assert values(primitive)
/* Current type and class (and size, if applicable) */
#define KEY_TYPE float
#define VALUE_TYPE float
#define KEY_CLASS Float
#define VALUE_CLASS Float
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
#define KEY_VALUE floatValue
#define VALUE_VALUE floatValue
/* Interfaces (keys) */
#define COLLECTION FloatCollection

#define SET FloatSet

#define HASH FloatHash

#define SORTED_SET FloatSortedSet

#define STD_SORTED_SET FloatSortedSet

#define FUNCTION Float2FloatFunction
#define MAP Float2FloatMap
#define SORTED_MAP Float2FloatSortedMap
#if #keyclass(Object) || #keyclass(Reference)
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Float2FloatSortedMap

#define STRATEGY PACKAGE.FloatHash.Strategy

#endif
#define LIST FloatList

#define BIG_LIST FloatBigList

#define STACK FloatStack

#define PRIORITY_QUEUE FloatPriorityQueue

#define INDIRECT_PRIORITY_QUEUE FloatIndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE FloatIndirectDoublePriorityQueue

#define KEY_ITERATOR FloatIterator

#define KEY_ITERABLE FloatIterable

#define KEY_BIDI_ITERATOR FloatBidirectionalIterator

#define KEY_LIST_ITERATOR FloatListIterator

#define KEY_BIG_LIST_ITERATOR FloatBigListIterator

#define STD_KEY_ITERATOR FloatIterator

#define KEY_COMPARATOR FloatComparator

/* Interfaces (values) */
#define VALUE_COLLECTION FloatCollection

#define VALUE_ARRAY_SET FloatArraySet

#define VALUE_ITERATOR FloatIterator

#define VALUE_LIST_ITERATOR FloatListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractFloatCollection

#define ABSTRACT_SET AbstractFloatSet

#define ABSTRACT_SORTED_SET AbstractFloatSortedSet
#define ABSTRACT_FUNCTION AbstractFloat2FloatFunction
#define ABSTRACT_MAP AbstractFloat2FloatMap
#define ABSTRACT_FUNCTION AbstractFloat2FloatFunction
#define ABSTRACT_SORTED_MAP AbstractFloat2FloatSortedMap
#define ABSTRACT_LIST AbstractFloatList

#define ABSTRACT_BIG_LIST AbstractFloatBigList

#define SUBLIST FloatSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractFloatPriorityQueue

#define ABSTRACT_STACK AbstractFloatStack

#define KEY_ABSTRACT_ITERATOR AbstractFloatIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractFloatBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractFloatListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractFloatBigListIterator

#if #keyclass(Object)
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractFloatComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractFloatCollection

#define VALUE_ABSTRACT_ITERATOR AbstractFloatIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractFloatBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS FloatCollections

#define SETS FloatSets

#define SORTED_SETS FloatSortedSets

#define LISTS FloatLists

#define BIG_LISTS FloatBigLists

#define MAPS Float2FloatMaps
#define FUNCTIONS Float2FloatFunctions
#define SORTED_MAPS Float2FloatSortedMaps
#define PRIORITY_QUEUES FloatPriorityQueues

#define HEAPS FloatHeaps

#define SEMI_INDIRECT_HEAPS FloatSemiIndirectHeaps

#define INDIRECT_HEAPS FloatIndirectHeaps

#define ARRAYS FloatArrays

#define BIG_ARRAYS FloatBigArrays

#define ITERATORS FloatIterators

#define BIG_LIST_ITERATORS FloatBigListIterators

#define COMPARATORS FloatComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS FloatCollections

#define VALUE_SETS FloatSets

#define VALUE_ARRAYS FloatArrays

/* Implementations */
#define OPEN_HASH_SET FloatOpenHashSet

#define OPEN_HASH_BIG_SET FloatOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET FloatOpenDoubleHashSet

#define OPEN_HASH_MAP Float2FloatOpenHashMap

#define STRIPED_OPEN_HASH_MAP StripedFloat2FloatOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Float2FloatOpenDoubleHashMap

#define ARRAY_SET FloatArraySet

#define ARRAY_MAP Float2FloatArrayMap

#define LINKED_OPEN_HASH_SET FloatLinkedOpenHashSet

#define AVL_TREE_SET FloatAVLTreeSet

#define RB_TREE_SET FloatRBTreeSet

#define AVL_TREE_MAP Float2FloatAVLTreeMap

#define RB_TREE_MAP Float2FloatRBTreeMap

#define ARRAY_LIST FloatArrayList

#define BIG_ARRAY_BIG_LIST FloatBigArrayBigList

#define ARRAY_FRONT_CODED_LIST FloatArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE FloatHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE FloatHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE FloatHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE FloatHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE FloatHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE FloatArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE FloatArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE FloatArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE FloatArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedFloatCollection

#define SYNCHRONIZED_SET SynchronizedFloatSet

#define SYNCHRONIZED_SORTED_SET SynchronizedFloatSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedFloat2FloatFunction

#define SYNCHRONIZED_MAP SynchronizedFloat2FloatMap

#define SYNCHRONIZED_LIST SynchronizedFloatList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableFloatCollection

#define UNMODIFIABLE_SET UnmodifiableFloatSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableFloatSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableFloat2FloatFunction

#define UNMODIFIABLE_MAP UnmodifiableFloat2FloatMap

#define UNMODIFIABLE_LIST UnmodifiableFloatList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableFloatIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableFloatBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableFloatListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER FloatReaderWrapper

#define KEY_DATA_INPUT_WRAPPER FloatDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY nextFloat
#define PREV_KEY previousFloat
#define FIRST_KEY firstFloatKey
#define LAST_KEY lastFloatKey
#define GET_KEY getFloat
#define REMOVE_KEY removeFloat
#define READ_KEY readFloat
#define WRITE_KEY writeFloat
#define DEQUEUE dequeueFloat
#define DEQUEUE_LAST dequeueLastFloat
#define SUBLIST_METHOD floatSubList
#define SINGLETON_METHOD floatSingleton

#define FIRST firstFloat
#define LAST lastFloat
#define TOP topFloat
#define PEEK peekFloat
#define POP popFloat
#define KEY_ITERATOR_METHOD floatIterator

#define KEY_LIST_ITERATOR_METHOD floatListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyFloatIterator

#define AS_KEY_ITERATOR asFloatIterator

#define TO_KEY_ARRAY toFloatArray
#define ENTRY_GET_KEY getFloatKey
#define REMOVE_FIRST_KEY removeFirstFloat
#define REMOVE_LAST_KEY removeLastFloat
#define PARSE_KEY parseFloat
#define LOAD_KEYS loadFloats
#define LOAD_KEYS_BIG loadFloatsBig
#define STORE_KEYS storeFloats
/* Methods (values) */
#define NEXT_VALUE nextFloat
#define PREV_VALUE previousFloat
#define READ_VALUE readFloat
#define WRITE_VALUE writeFloat
#define VALUE_ITERATOR_METHOD floatIterator

#define ENTRY_GET_VALUE getFloatValue
#define REMOVE_FIRST_VALUE removeFirstFloat
#define REMOVE_LAST_VALUE removeLastFloat
/* Methods (keys/values) */
#define ENTRYSET float2FloatEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if #keyclass(Object) || #keyclass(Reference)
#define GET_VALUE getFloat
#define REMOVE_VALUE removeFloat
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
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.float2int(x)
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
#include "drv/AbstractSortedMap.drv"

