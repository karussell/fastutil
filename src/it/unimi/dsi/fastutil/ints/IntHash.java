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
package it.unimi.dsi.fastutil.ints;
import it.unimi.dsi.fastutil.Hash;
/** A type-specific {@link Hash} interface.
 *
 * @see Hash
 */
public interface IntHash {
 /** A type-specific hash strategy.
	 *
	 * @see it.unimi.dsi.fastutil.Hash.Strategy
	 */
 public interface Strategy {
  /** Returns the hash code of the specified element with respect to this hash strategy.
		 *
		 * @param e an element.
		 * @return the hash code of the given element with respect to this hash strategy.
		 */
  public int hashCode( int e );
  /** Returns true if the given elements are equal with respect to this hash strategy.
		 *
		 * @param a an element.
		 * @param b another element.
		 * @return true if the two specified elements are equal with respect to this hash strategy.
		 */
  public boolean equals( int a, int b );
 }
}
