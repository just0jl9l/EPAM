package by.tr.epam.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final Lock lock = new ReentrantLock();	//блокировка, в один конкретный момент времени
    												//вилка может принадлежать только одному философу
    
    private int id;				//идентификатор вилки
    
    public Fork(int id){
    	this.id=id;
    }

    public void onTake() {		//вилка считается взятой, если её блокировку заблокировал некоторый поток
        lock.lock();
    }

    public void onPut() {		//вилка вернулась на стол, если её блокировку разблокировал поток, который её блокировал
        lock.unlock();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
