package com.jjdonov.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic singly linked list.
 * java.util.LinkedList is doubly linked, needed a singly linked
 * to practice some things with ...
 * @author johndonovan
 *
 * @param <E>
 */
public class SinglyLinkedList<E> implements Iterable<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList() {
		size = 0;
	}
	
	public SinglyLinkedList(E[] all) {
		size = 0;
		insertAll(all);
	}

	/**
	 * Insert element to the head of the list
	 * 
	 * @param element
	 *            the element to add
	 */
	public void insert(E element) {
		Node<E> node = new Node<E>(element, head);
		head = node;
		if (size == 0)
			tail = node;
		size++;
	}

	/**
	 * Insert all elements from the array
	 * 
	 * @param elements
	 *            the elements to add
	 */
	public void insertAll(E[] elements) {
		for (E e : elements) {
			insert(e);
		}
	}

	/**
	 * Insert element after the first element that matches the element
	 * specified.
	 * 
	 * @param key
	 *            the element to insert after
	 * @param element
	 *            the element to insert
	 */
	public void insertAfter(E key, E element) {
		Node<E> temp = head;
		while (temp != null && !(temp.element.equals(key)))
			temp = temp.next;
		if (temp == null)
			throw new NoSuchElementException();
		temp.next = new Node<E>(element, temp.next);
		size++;
	}

	/**
	 * Insert element at the end of the list
	 * 
	 * @param element
	 */
	public void insertAtEnd(E element) {
		Node<E> node = new Node<E>(element, null);
		if (size == 0)
			head = tail = node;
		else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	/**
	 * Removes the element at the head of the list
	 * 
	 * @return the head
	 */
	public E remove() {
		if (size == 0)
			throw new NoSuchElementException();
		Node<E> oldHead = head;
		head = head.next;
		size--;
		return oldHead.element;
	}

	/**
	 * Remove and return the specified element
	 * 
	 * @param element
	 *            to remove
	 * @return the element removed
	 */
	public E removeElement(E element) {
		Node<E> current = head;
		Node<E> previous = null;
		while (current != null && !(current.element.equals(element))) {
			previous = current;
			current = current.next;
		}
		if (current == null)
			throw new NoSuchElementException();
		if (previous != null) {
			previous.next = current.next;
			size--;
			return current.element;
		} else { // if there is no previous then we are at the head
			return remove();
		}
	}

	/**
	 * 
	 * @return the size of the linked list
	 */
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<E> e = head;
		while (e != null) {
			sb.append(e.element.toString()).append(", ");
			e = e.next;
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	private class SinglyLinkedListIterator implements Iterator<E> {

		private Node<E> currentNode;
		private Node<E> previousNode;
		private E lastRet;
		private boolean started;

		public SinglyLinkedListIterator() {
			currentNode = null;
			previousNode = null;
			lastRet = null;
		}

		@Override
		public boolean hasNext() {
			if (!started) {
				return head != null;
			} else {
				return currentNode.next != null;
			}
		}

		@Override
		public E next() {
			if(!started) {
				currentNode = head;
				started = true;
			} else {
				if (!hasNext())
					throw new NoSuchElementException();
				if(lastRet != null) {
					previousNode = currentNode;
				}
				currentNode = currentNode.next;
			}
			return lastRet = currentNode.element;
		}

		@Override
		public void remove() {
			if (currentNode == null)
				throw new NoSuchElementException();
			if(currentNode == head) {
				head = currentNode.next;
				previousNode = null;
			}
			else if(currentNode == tail) {
				previousNode.next = null;
				tail = previousNode;
			}
			else {
				previousNode.next = currentNode.next;
			}
			lastRet = null;
			size--;
		}
	}

	/**
	 * Simple implementation of a singly linked list's node.
	 * @author johndonovan
	 *
	 * @param <E>
	 */
	public static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		public E getElement() {
			return element;
		}
		
		public void setElement(E element) {
			this.element = element; 
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		@Override
		public String toString() {
			return element.toString();
		}
	}
}
