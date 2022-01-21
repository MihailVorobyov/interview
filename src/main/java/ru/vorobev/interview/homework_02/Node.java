package ru.vorobev.interview.homework_02;

public class Node<E> {
	private Node<E> previous;
	private Node<E> next;
	
	private final E item;
	
	public Node(E item, Node<E> previous, Node<E> next) {
		this.item = item;
		this.previous = previous;
		this.next = next;
	}
	
	public E item() {
		return item;
	}
	
	Node<E> getPrevious() {
		return previous;
	}
	
	Node<E> getNext() {
		return next;
	}
	
	void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
	
	void setNext(Node<E> next) {
		this.next = next;
	}
}
