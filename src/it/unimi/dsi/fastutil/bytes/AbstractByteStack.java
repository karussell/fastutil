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
package it.unimi.dsi.fastutil.bytes;
import it.unimi.dsi.fastutil.AbstractStack;
/** An abstract class providing basic methods for implementing a type-specific stack interface.
 *
 * <P>To create a type-specific stack, you need both object methods and
 * primitive-type methods. However, if you inherit from this class you need
 * just one (anyone).
 */
public abstract class AbstractByteStack extends AbstractStack<Byte> implements ByteStack {
 protected AbstractByteStack() {}
 /** Delegates to the corresponding type-specific method. */
 public void push( Byte o ) {
  push( o.byteValue() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte pop() {
  return Byte.valueOf( popByte() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte top() {
  return Byte.valueOf( topByte() );
 }
 /** Delegates to the corresponding type-specific method. */
 public Byte peek( int i ) {
  return Byte.valueOf( peekByte( i ) );
 }
 /** Delegates to the corresponding generic method. */
 public void push( byte k ) {
  push( Byte.valueOf( k ) );
 }
 /** Delegates to the corresponding generic method. */
 public byte popByte() {
  return pop().byteValue();
 }
 /** Delegates to the corresponding generic method. */
 public byte topByte() {
  return top().byteValue();
 }
 /** Delegates to the corresponding generic method. */
 public byte peekByte( int i ) {
  return peek( i ).byteValue();
 }
}
