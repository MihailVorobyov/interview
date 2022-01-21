package ru.vorobev.interview.homework_02;

import java.util.*;

public class MyLinkedList<E> extends AbstractSequentialList<E> implements Deque<E> {
	
	private Node<E> first;
	private Node<E> last;
	private Node<E> current;
	
	private int currentIndex;
	private int size;
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void addFirst(E e) {
		Node<E> newFirst;
		if (size == 0) {
			newFirst = new Node<>(e, null, null);
		} else {
			newFirst = new Node<>(e, null, first);
			first.setPrevious(newFirst);
		}
		first = newFirst;
		size++;
	}
	
	@Override
	public void addLast(E e) {
		Node<E> newLast;
		if (size == 0) {
			newLast = new Node<>(e, null, null);
		} else {
			newLast = new Node<>(e, last, null);
			last.setNext(newLast);
			
		}
		last = newLast;
		size++;
	}
	
	@Override
	public E getFirst() {
		if (first == null) {
			throw new NoSuchElementException("Список пуст");
		}
		currentIndex = 0;
		current = first;
		return first.item();
	}
	
	@Override
	public E getLast() {
		if (last == null) {
			throw new NoSuchElementException("Список пуст");
		}
		currentIndex = size - 1;
		current = last;
		return last.item();
	}
	
	@Override
	public E removeFirst() {
		Node<E> second = first.getNext();
		if (second == null) {
			throw new NoSuchElementException("Список пуст");
		}
		second.setPrevious(null);
		first = second;
		size--;
		if (currentIndex >= size) {
			currentIndex = size - 1;
		}
		currentIndex = 0;
		current = first;
		return first.item();
	}
	
	@Override
	public E removeLast() {
		Node<E> beforeLast = last.getPrevious();
		if (beforeLast == null) {
			throw new NoSuchElementException("Список пуст");
		}
		beforeLast.setNext(null);
		last = beforeLast;
		size--;
		if (currentIndex >= size) {
			currentIndex = size - 1;
		}
		currentIndex = size - 1;
		current = last;
		return last.item();
	}
	
	@Override
	public E element() {
		return getFirst();
	}
	
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Недопустимое значение индекса: " + index);
		}
		Node<E> currentTemp = current;
		while (currentIndex != index) {
			if (currentIndex > index) {
				currentTemp = currentTemp.getPrevious();
				currentIndex--;
			}
			if (currentIndex < index) {
				currentTemp = currentTemp.getNext();
				currentIndex++;
			}
		}
		
		current = currentTemp;
		return current.item();
	}
	
	@Override
	public E pollFirst() {
		Node<E> node = first;
		removeFirst();
		return node.item();
	}
	
	@Override
	public E pollLast() {
		Node<E> node = last;
		removeLast();
		return node.item();
	}
	
	@Override
	public E peekFirst() {
		return first == null ? null : first.item();
	}
	
	@Override
	public E peekLast() {
		return last == null ? null : last.item();
		
	}
	
	@Override
	public E poll() {
		return pollFirst();
	}
	
	@Override
	public E peek() {
		return peekFirst();
	}
	
	@Override
	public E pop() {
		return pollFirst();
	}
	
	@Override
	public void push(E e) {
		addFirst(e);
	}
	
	@Override
	public E remove() {
		if (current == null || currentIndex == -1) {
			throw new NoSuchElementException("Список пуст");
		}
		E result = current.item();
		
		if (current.getPrevious() != null) {
			current.getPrevious().setNext(current.getNext());
		} else {
			removeFirst();
		}
		if (current.getNext() != null) {
			current.getNext().setPrevious(current.getPrevious());
		} else {
			removeLast();
		}
		
		return result;
	}
	
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Недопустимое значение индекса: " + index);
		}
		
		E result = get(index);
		currentIndex = index;
		remove(get(index));
		
		return result;
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new ListIterator<E>() {
			@Override
			public boolean hasNext() {
				return current.getNext() != null;
			}
			
			@Override
			public E next() {
				return current.getNext().item();
			}
			
			@Override
			public boolean hasPrevious() {
				return current.getPrevious() != null;
			}
			
			@Override
			public E previous() {
				return current.getPrevious().item();
			}
			
			@Override
			public int nextIndex() {
				if (hasNext()) {
					return ++currentIndex;
				}
				return currentIndex;
			}
			
			@Override
			public int previousIndex() {
				if (hasPrevious()) {
					return --currentIndex;
				}
				return currentIndex;
			}
			
			@Override
			public void remove() {
			
			}
			
			@Override
			public void set(E e) {
			
			}
			
			@Override
			public void add(E e) {
			
			}
		};
	}
	
	@Override
	public boolean removeFirstOccurrence(Object o) {
		if (o == null) {
			return false;
		}
		
		E obj = null;
		try {
			obj = (E) o;
		} catch (ClassCastException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		current = first;
		while (current.getNext() != null) {
			if (current.item().equals(obj)) {
				remove();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean removeLastOccurrence(Object o) {
		if (o == null) {
			return false;
		}
		
		E obj = null;
		try {
			obj = (E) o;
		} catch (ClassCastException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		current = first;
		Node<E> lastOccure = null;
		while (current.getNext() != null) {
			if (current.item().equals(obj)) {
				lastOccure = current;
			}
		}
		if (lastOccure != null) {
			remove(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return false;
	}
	
	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}
	
	@Override
	public boolean offer(E e) {
		if (e == null) {
			return false;
		}
		if (size == 0) {
			addLast(e);
		}
		return true;
	}
	
	@Override
	public Iterator<E> descendingIterator() {
		return null;
	}
}
