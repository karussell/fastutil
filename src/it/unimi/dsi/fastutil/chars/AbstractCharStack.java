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
package it.unimi.dsi.fastutil.chars;
import it.unimi.dsi.fastutil.AbstractStack;
/** An abstract class providing basic methods for implementing a type-specific stack interface.
 *
 * <P>To create a type-specific stack, you need both object methods and
 * primitive-type methods. However, if you inherit from this class you need
 * just one (anyone).
 */
public abstract class AbstractCharStack extends AbstractStack<Character> implements CharStack {
 protected AbstractCharStack() {}
 /** Delegates to the corresponding type-specific method. */
 public void push( Character o ) {
  push( o.charValue() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Character pop() {
  return Character.valueOf( popChar() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Character top() {
  return Character.valueOf( topChar() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Character peek( int i ) {
  return Character.valueOf( peekChar( i ) );
 }
 /** Delegates to the corresponding generic method. */
 public void push( char k ) {
  push( Character.valueOf( k ) );
 }
 /** Delegates to the corresponding generic method. */
 public char popChar() {
  return pop().charValue();
 }
 /** Delegates to the corresponding generic method. */
 public char topChar() {
  return top().charValue();
 }
 /** Delegates to the corresponding generic method. */
 public char peekChar( int i ) {
  return peek( i ).charValue();
 }
}
