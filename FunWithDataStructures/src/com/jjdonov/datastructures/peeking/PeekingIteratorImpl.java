package com.jjdonov.datastructures.peeking;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Note : this implementation for a PeekingIterator has the overhead
 * of maintaining a copy of the collection's data, elementData.
 * 
 * @author johndonovan
 *
 * @param <E>
 */
public class PeekingIteratorImpl<E> implements PeekingIterator<E> {
	Collection<E> parent;
	private E elementData[];
	int size;
	int cursor;			//index of next element to return
	int lastRet = -1;	//index of last element returned; -1 if no such
						//or if remove() was last called...
	
	//int expextedModCount = modCount; how can we protect against concurrent mod?
	
	@SuppressWarnings("unchecked")
	public PeekingIteratorImpl(Collection<E> collection) {
		parent = collection;
		size = collection.size();
		elementData = (E[]) collection.toArray();
	}
	
	@Override
	public boolean hasNext() {
		return cursor != size;
	}

	@Override
	public E next() {
		if(cursor >= size)
			throw new NoSuchElementException();
		lastRet = cursor;
		return elementData[cursor++];
	}

	@Override
	public void remove() {
		if(lastRet < 0)
			throw new IllegalStateException();
		parent.remove(elementData[lastRet]);
		System.arraycopy(elementData, lastRet + 1, elementData, lastRet, size - lastRet -1);
		elementData[--size] = null;
		cursor = lastRet;
		lastRet = -1;
	}

	@Override
	public boolean hasOneMoreThanNext() {
		return cursor + 1 != size;
	}

	@Override
	public E peekAhead() {
		if(cursor >= size)
			throw new NoSuchElementException();
		return elementData[cursor];
	}

}
