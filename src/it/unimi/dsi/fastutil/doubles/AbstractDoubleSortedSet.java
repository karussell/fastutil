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
 * Copyright (C) 2003-2013 Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.doubles;
/** An abstract class providing basic methods for sorted sets implementing a type-specific interface. */
public abstract class AbstractDoubleSortedSet extends AbstractDoubleSet implements DoubleSortedSet {
 protected AbstractDoubleSortedSet() {}
 /** Delegates to the corresponding type-specific method. */
 public DoubleSortedSet headSet( final Double to ) {
  return headSet( to.doubleValue() );
 }
 /** Delegates to the corresponding type-specific method. */
 public DoubleSortedSet tailSet( final Double from ) {
  return tailSet( from.doubleValue() );
 }
 /** Delegates to the corresponding type-specific method. */
 public DoubleSortedSet subSet( final Double from, final Double to ) {
  return subSet( from.doubleValue(), to.doubleValue() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Double first() {
  return (Double.valueOf(firstDouble()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Double last() {
  return (Double.valueOf(lastDouble()));
 }
 /** Delegates to the new covariantly stronger generic method. */
 @Deprecated
 public DoubleBidirectionalIterator doubleIterator() {
  return iterator();
 }
 public abstract DoubleBidirectionalIterator iterator();
}
