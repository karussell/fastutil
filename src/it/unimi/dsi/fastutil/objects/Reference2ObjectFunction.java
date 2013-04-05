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
import it.unimi.dsi.fastutil.Function;
/** A type-specific {@link Function}; provides some additional methods that use polymorphism to avoid (un)boxing.
 *
 * <P>Type-specific versions of <code>get()</code>, <code>put()</code> and
 * <code>remove()</code> cannot rely on <code>null</code> to denote absence of
 * a key.  Rather, they return a {@linkplain #defaultReturnValue() default
 * return value}, which is set to 0 cast to the return type (<code>false</code>
 * for booleans) at creation, but can be changed using the
 * <code>defaultReturnValue()</code> method. 
 *
 * <P>For uniformity reasons, even maps returning objects implement the default
 * return value (of course, in this case the default return value is
 * initialized to <code>null</code>).
 *
 * <P><strong>Warning:</strong> to fall in line as much as possible with the
 * {@linkplain java.util.Map standard map interface}, it is strongly suggested
 * that standard versions of <code>get()</code>, <code>put()</code> and
 * <code>remove()</code> for maps with primitive-type values <em>return
 * <code>null</code> to denote missing keys</em> rather than wrap the default
 * return value in an object (of course, for maps with object keys and values
 * this is not possible, as there is no type-specific version).
 *
 * @see Function
 */
public interface Reference2ObjectFunction <K,V> extends Function<K, V> {
 /** Sets the default return value. 
	 *
	 * This value must be returned by type-specific versions of
	 * <code>get()</code>, <code>put()</code> and <code>remove()</code> to
	 * denote that the map does not contain the specified key. It must be
	 * 0/<code>false</code>/<code>null</code> by default.
	 *
	 * @param rv the new default return value.
	 * @see #defaultReturnValue()
	 */
 void defaultReturnValue( V rv );
 /** Gets the default return value.
	 *
	 * @return the current default return value.
	 */
 V defaultReturnValue();
}
