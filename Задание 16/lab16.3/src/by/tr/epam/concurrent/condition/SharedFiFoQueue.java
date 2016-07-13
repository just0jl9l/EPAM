package by.tr.epam.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {	
    private Object[] elems = null;		//массив элементов, имеющая определенную длину
    private int current = 0;			//текущее число элементов в очереди		
    private int placeIndex = 0;			//позиция в массиве для добавления элемента
    private int removeIndex = 0;		//позиция в массиве для удаления элемента
    private final Lock lock = new ReentrantLock();			//блокировка очереди
    private final Condition isEmpty = lock.newCondition();	//условие, при котором очередь пуста
    private final Condition isFull = lock.newCondition();	//условие, при котором очередь заполнена

    public SharedFiFoQueue(int capacity) {		//создаётся массив элементов необходимой максимально возможной длины
        this.elems = new Object[capacity];
    }

    public void add(Object elem) throws InterruptedException {	//добавление элемента в очередь
        lock.lock();		//очередь блокируется, чтобы никто другой не мог произвести над ней некоторое действие
        while(current >= elems.length){		//если текущее количество элементов в очереди не меньше длинны очереди
        	isFull.await();					//ждать, пока очередь не перестанет быть полной
        }
        elems[placeIndex] = elem;		//необходимый элемент помещается в очередь (в нужную позицию массива)
        placeIndex = (placeIndex + 1) % elems.length;	//вычисляется новая позиция для записи элемента в зависимости 
        									//от текущей (будет изменяться от 0 до значения, на 1 меньше длины массива)
        ++current;			//текущее число элементов увеличивается на 1
        isEmpty.signal();	//посылается уведомление о том, что очередь не пуста
        lock.unlock();		//очередь разблокируется
    }

    public Object remove() throws InterruptedException {	//получение и удаление элемента из очереди
        Object elem = null;
        lock.lock();			//очередь блокируется
        while(current <= 0){	//если текущее число элементов в очереди не больше нуля
            isEmpty.await();	//ожидать, пока очередь не перестанет быть пустой
        }
        elem = elems[removeIndex];	//получить элемент из очереди (из массива в соответствующей позиции)
        removeIndex = (removeIndex + 1) % elems.length;		//вычисляется новая позиция для чтения элемента в зависимости 
												//от текущей (будет изменяться от 0 до значения, на 1 меньше длины массива)
        --current;			//текущее число элементов уменьшается на 1
        isFull.signal();	//посылается уведомление о том, что очередь не полна
        lock.unlock();		//очередь разблокируется
        return elem;
    }
}

