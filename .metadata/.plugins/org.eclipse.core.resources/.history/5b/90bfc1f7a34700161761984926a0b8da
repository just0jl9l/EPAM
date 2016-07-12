package by.tr.epam.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final Lock lock = new ReentrantLock();
    private int id;

    public void onTake() {
        lock.lock();
    }

    public void onPut() {
        lock.unlock();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
