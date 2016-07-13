package by.tr.epam.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int current = 0;		//текущее значение счётчика
	private Lock lock = new ReentrantLock();	//блокировка счётчика

	public void add(int i) {		//изменение значения счётчика
		current+=i;
	}

	public int getCurrent() {
		return current;
	}
	
	public void lock(){		//блокировка счётчика
		lock.lock();
	}

	public void unlock(){	//разблокировка счётчика
		lock.unlock();
	}
}
