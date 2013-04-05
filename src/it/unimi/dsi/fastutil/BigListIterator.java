package it.unimi.dsi.fastutil;

/*		 
 * Copyright (C) 2010-2013 Sebastiano Vigna 
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


import java.util.Iterator;
import java.util.ListIterator;

/** A list iterator over a {@link BigList}.
 *
 * <P>This kind of iterator is essentially a {@link ListIterator} with long indices.
 *
 * @see Iterator
 * @see ListIterator
 */

public interface BigListIterator<K> extends BidirectionalIterator<K> {
	/** Returns the index of the element that would be returned by a subsequent call to next.
	 * (Returns list size if the list iterator is at the end of the list.)
	 * 
	 * @return the index of the element that would be returned by a subsequent call to next, or list
	 * size if list iterator is at end of list.
	 * @see ListIterator#nextIndex()
	 */
	long nextIndex();

	/** Returns the index of the element that would be returned by a subsequent call to previous.
	 * (Returns -1 if the list iterator is at the beginning of the list.)
	 * 
	 * @return the index of the element that would be returned by a subsequent call to previous, or
	 * -1 if list iterator is at beginning of list.
	 * @see ListIterator#previousIndex()
	 */

	long previousIndex();

	/** Skips the given number of elements.
	 *
	 * <P>The effect of this call is exactly the same as that of
	 * calling {@link #next()} for <code>n</code> times (possibly stopping
	 * if {@link #hasNext()} becomes false).
	 *
	 * @param n the number of elements to skip.
	 * @return the number of elements actually skipped.
	 */

	long skip( long n );
}
