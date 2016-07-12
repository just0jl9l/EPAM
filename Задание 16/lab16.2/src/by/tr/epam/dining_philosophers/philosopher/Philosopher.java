package by.tr.epam.dining_philosophers.philosopher;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.epam.dining_philosophers.fork.Fork;
import by.tr.epam.dining_philosophers.util.Util;

public class Philosopher extends Thread {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	private final Semaphore waiter;		//�������, � �������� ���������� ������� ��� "����������" 
										//������ ���� ���� (����� �����, ������, ������ �����)
	
	private final long eatTime;			//�����, ������� ������� ������ �� ���� ���� ����
	private final long thinkTime;		//�����, ������� ������� ������ �� �����������
	private final Fork fork1;			//����� �����
	private final Fork fork2;			//������ �����
	private int id;						//������������� ��������

	public Philosopher(Semaphore waiter, long eatTime, long thinkTime, Fork fork1, Fork fork2, int id) {
		this.waiter = waiter;
		this.eatTime = eatTime;
		this.thinkTime = thinkTime;
		this.fork1 = fork1;
		this.fork2 = fork2;
		this.id=id;
	}

	public void run() {
		logger.info("������� "+id+" ��� �� ����");
		while (!this.isInterrupted()) {				//���� ������� ����� �� ������ ��������
			try {
				waiter.acquire();			//������� ���, ���� ������� �� ���� ����������
											//���������� � ����� ���� (�.�. ���� ���� �� ��� ���� ������� �� �����
											//��������� ����)

				take(fork1);				//������� ���� ����� �����
				take(fork2);				//������� ���� ������ �����
				eat();						//������� ������
				put(fork1);					//������� ����� ����� �����
				put(fork2);					//������� ����� ������ �����

			} catch (InterruptedException e) { //������ ���������� ���������, ���� �� ����� �������� ������� ��������
												//������� ����� ������� �������� ��������, �� ������ ����� ���������
												//���������� (��� �������� ����� acquire())
				
				this.interrupt();			//���������� �������� ��������, �.�. ��� ������� ������� ������� �����
			} finally {
				waiter.release();			//������� ����������� ������� (��� ������� ����������� �� 1)
				think();					//������� ����������
			}
		}
		logger.info("������� "+id+" ����� ��-�� �����");
	}

	private void take(Fork fork) {
		fork.onTake();			//������� ���� �����
		logger.info("������� "+id+" ���� ����� "+fork.getId());
	}

	private void put(Fork fork) {
		fork.onPut();			//������� ����� �����
		logger.info("������� "+id+" ������� ����� "+fork.getId());
	}

	private void eat() {
		Util.waitMillis(eatTime);	//������� "������" (����� ���� ������������ �����)
		logger.info("������� "+id+" �������");
	}

	private void think() {
		Util.waitMillis(thinkTime);	//������� "����������" (����� ���� ������������ �����)
		logger.info("������� "+id+" �����������");
	}

}
