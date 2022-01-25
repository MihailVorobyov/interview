package ru.vorobev.interview.homework_03.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MegaCounter {
	private long value = 0;
	private Lock lock = new ReentrantLock();
	
	public void increase() {
		lock.lock();
		this.value++;
		lock.unlock();
	}
	
	public void decrease() {
		lock.lock();
		this.value--;
		lock.unlock();
	}
	
	public long getValue() {
		return this.value;
	}
}
