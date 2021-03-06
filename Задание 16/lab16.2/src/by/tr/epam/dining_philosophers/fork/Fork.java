package by.tr.epam.dining_philosophers.fork;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final Lock lock = new ReentrantLock();	//����������, � ���� ���������� ������ �������
    												//����� ����� ������������ ������ ������ ��������
    
    private int id;				//������������� �����
    
    public Fork(int id){
    	this.id=id;
    }

    public void onTake() {		//����� ��������� ������, ���� � ���������� ������������ ��������� �����
        lock.lock();
    }

    public void onPut() {		//����� ��������� �� ����, ���� � ���������� ������������� �����, ������� � ����������
        lock.unlock();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
