package com.jjdonov.datastructures.peeking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jjdonov.datastructures.peeking.PeekingIterator;
import com.jjdonov.datastructures.peeking.PeekingIteratorImpl;

public class PeekingIteratorImplTest {
	private Collection<Integer> testList = null;
	private Integer testData[] = new Integer[] { 1, 2, 3, 4, 5 };

	@Before
	public void setUp() throws Exception {
		testList = new ArrayList<Integer>();
		testList.addAll(Arrays.asList(testData));
	}

	@Test
	public void testIterating() {
		int i = 0;
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				testList);
		while (pItr.hasNext()) {
			Integer j = pItr.next();
			assertEquals(j, testData[i]);
			i++;
		}
	}

	@Test
	public void testHasOneMoreThanNext() {
		Integer oneItemArray[] = new Integer[] { 1 };
		Integer twoItemArray[] = new Integer[] { 1, 2 };
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				Arrays.asList(oneItemArray));
		assertFalse(pItr.hasOneMoreThanNext());
		pItr = new PeekingIteratorImpl<Integer>(Arrays.asList(twoItemArray));
		assertTrue(pItr.hasOneMoreThanNext());
		pItr.next();
		assertFalse(pItr.hasOneMoreThanNext());
	}

	@Test
	public void testPeekAhead() {
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				testList);
		int i = 0;
		while (pItr.hasNext()) {
			pItr.next();
			if (i < testList.size() - 1) {
				Integer j = pItr.peekAhead();
				assertEquals(j, testData[i + 1]);
			} else {
				break;
			}
			i++;
		}
		assertEquals(i, testList.size() - 1);
	}

	@Test
	public void testSingleRemove() {
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				testList);
		pItr.next();
		pItr.remove();
		assertFalse(testList.contains(testData[0]));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveIllegalState() {
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				testList);
		pItr.remove();
		pItr.remove();
	}
	
	@Test
	public void removeAll() {
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(
				testList);
		while(pItr.hasNext()){
			pItr.next();
			pItr.remove();
		}
		assertTrue(testList.size() == 0);
	}
	
	@Test
	public void testIteratingHashSet() {
		Set<Integer> testSet = new HashSet<Integer>();
		testSet.addAll(Arrays.asList(testData));
		PeekingIterator<Integer> pItr = new PeekingIteratorImpl<Integer>(testSet);
		int i = 0;
		while(pItr.hasNext()) {
			assertEquals(testData[i], pItr.next());
			i++;
		}
	}

}
