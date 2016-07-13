package by.tr.epam.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int current = 0;
	private Lock lock = new ReentrantLock();

	public void add(int i) {
		current+=i;
	}

	public int getCurrent() {
		return current;
	}
	
	public void lock(){
		lock.lock();
	}

	public void unlock(){
		lock.unlock();
	}
}
