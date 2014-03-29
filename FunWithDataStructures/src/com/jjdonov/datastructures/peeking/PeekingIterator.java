package com.jjdonov.datastructures.peeking;

import java.util.Iterator;

public interface PeekingIterator<E> extends Iterator<E> {
	public boolean hasOneMoreThanNext();
	public E peekAhead();
}
