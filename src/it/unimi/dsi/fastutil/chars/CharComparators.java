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
 * Copyright (C) 2003-2013 Paolo Boldi and Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.chars;
/** A class providing static methods and objects that do useful things with comparators.
 */
public class CharComparators {
 private CharComparators() {}
 /** A type-specific comparator mimicking the natural order. */
 @SuppressWarnings({"unchecked","rawtypes"})
 public static final CharComparator NATURAL_COMPARATOR = new AbstractCharComparator() {
   public final int compare( final char a, final char b ) {
    return ( (a) < (b) ? -1 : ( (a) == (b) ? 0 : 1 ) );
   }
  };
 /** A type-specific comparator mimicking the opposite of the natural order. */
 @SuppressWarnings({"unchecked","rawtypes"})
 public static final CharComparator OPPOSITE_COMPARATOR = new AbstractCharComparator() {
   public final int compare( final char a, final char b ) {
    return - ( (a) < (b) ? -1 : ( (a) == (b) ? 0 : 1 ) );
   }
  };
 /** Returns a comparator representing the opposite order of the given comparator. 
	 *
	 * @param c a comparator.
	 * @return a comparator representing the opposite order of <code>c</code>.
	 */
 public static CharComparator oppositeComparator( final CharComparator c ) {
  return new AbstractCharComparator () {
    private final CharComparator comparator = c;
    public final int compare( final char a, final char b ) {
     return - comparator.compare( a, b );
    }
   };
 }
}
