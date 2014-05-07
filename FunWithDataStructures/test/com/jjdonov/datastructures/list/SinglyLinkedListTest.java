package com.jjdonov.datastructures.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListTest {

	SinglyLinkedList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new SinglyLinkedList<String>();
	}

	@Test
	public void testNewLinkedListt() {
		list.insert("John");
		list.insert("Karl");
		assertEquals(2, list.size());
		assertEquals("Karl, John", list.toString());
	}

	@Test
	public void testInsertAtEnd() {
		list.insert("John");
		list.insertAtEnd("Karl");
		assertEquals(2, list.size());
		assertEquals("John, Karl", list.toString());
	}

	@Test
	public void testEmptyInsertAtEnd() {
		list.insertAtEnd("Max");
		assertEquals(1, list.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyInsertAfter() {
		list.insertAfter("NoSuchElement:", "Test");
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyRemove() {
		list.remove();
	}

	@Test
	public void removeAll() {
		String[] testData = { "Karl", "Max", "John" };
		list.insertAll(testData);
		for (int i = testData.length - 1; i >= 0; i--) {
			assertEquals(testData[i], list.remove());
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyRemoveElement() {
		list.removeElement("Test");
	}

	@Test
	public void testRemoveElement() {
		String[] testData = { "Karl", "Max", "John" };
		list.insertAll(testData);
		list.removeElement("John");
		assertEquals("Max, Karl", list.toString());
	}

	@Test
	public void testRemoveElement2() {
		String[] testData2 = { "Karl", "Max", "John" };
		list.insertAll(testData2);
		list.removeElement("Karl");
		assertEquals("John, Max", list.toString());
	}

	@Test
	public void testEmptyIterator() {
		Iterator<String> i = list.iterator();
		assertFalse(i.hasNext());
	}

	@Test
	public void testIterator() {
		String[] testData = { "Karl", "Max", "John" };
		list.insertAll(testData);
		Iterator<String> iterator = list.iterator();
		int i = 2;
		while (iterator.hasNext()) {
			String temp = iterator.next();
			assertEquals(testData[i], temp);
			i--;
		}
		assertEquals(-1, i);
	}

	@Test
	public void testIteratorRemoveAll() {
		String[] testData = { "Karl", "John", "Max", "Kim" };
		list.insertAll(testData);
		Iterator<String> i = list.iterator();
		while (i.hasNext()) {
			String next = i.next();
			i.remove();
		}
		assertEquals(0, list.size());
		assertEquals("", list.toString());
	}

	@Test
	public void testRemoveEveryOther() {
		int i = 0;
		String[] testData = { "Karl", "John", "Max", "Kim" };
		list.insertAll(testData);
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String temp = iter.next();
			if (i % 2 == 0){
				iter.remove();
			}
			i++;
		}
		assertEquals(2, list.size());
		assertEquals("Max, Karl", list.toString());
	}
}
