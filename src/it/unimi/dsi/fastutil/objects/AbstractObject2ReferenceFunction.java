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
/** An abstract class providing basic methods for functions implementing a type-specific interface.
 *
 * <P>Optional operations just throw an {@link
 * UnsupportedOperationException}. Generic versions of accessors delegate to
 * the corresponding type-specific counterparts following the interface rules
 * (they take care of returning <code>null</code> on a missing key).
 *
 * <P>This class handles directly a default return
 * value (including {@linkplain #defaultReturnValue() methods to access
 * it}). Instances of classes inheriting from this class have just to return
 * <code>defRetValue</code> to denote lack of a key in type-specific methods. The value
 * is serialized.
 *
 * <P>Implementing subclasses have just to provide type-specific <code>get()</code>,
 * type-specific <code>containsKey()</code>, and <code>size()</code> methods.
 *
 */
public abstract class AbstractObject2ReferenceFunction <K,V> implements Object2ReferenceFunction <K,V>, java.io.Serializable {
 private static final long serialVersionUID = -4940583368468432370L;
 protected AbstractObject2ReferenceFunction() {}
 /**
	 * The default return value for <code>get()</code>, <code>put()</code> and
	 * <code>remove()</code>.  
	 */
 protected V defRetValue;
 public void defaultReturnValue( final V rv ) {
  defRetValue = rv;
 }
 public V defaultReturnValue() {
  return defRetValue;
 }
 public V put( K key, V value ) {
  throw new UnsupportedOperationException();
 }
 public V remove( Object key ) {
  throw new UnsupportedOperationException();
 }
 public void clear() {
  throw new UnsupportedOperationException();
 }
}
