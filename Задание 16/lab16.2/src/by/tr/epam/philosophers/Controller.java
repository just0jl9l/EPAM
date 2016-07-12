package by.tr.epam.philosophers;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Controller {
	private final static int WORK_TIME = 100; //�����, ���� ������� ����� ����, � �������� (��������) �����������
	private final static int WAIT_TIME = 50;	//�����, ������� ������� ����� ���, ���� ����������� �������� ������
    private final List<Philosopher> philosophers = new ArrayList<>();	//������ ���� ���������
    private final int n;					//���������� ��������� (� ���������� �����)
    private final long THINK_TIME = 1L;		//�����, ������� ���� ������� ������ �� �����������
    private final long EAT_TIME = 1L;		//�����, ������� ���� ������� ������ �� ���� ���� ����

    public Controller(int n) {				
        this.n = n;							//��������� ����� ���������
    }

    public void newRun() {
        Semaphore waiter = new Semaphore(n - 1);	// �������� ������� � ������������ ���������
													// ��� � ��������� ������ ������� ����� ����������,
													// ����� ������
        
        List<Fork> forks = new ArrayList<>();		//�������� ������ �����
        for (int i = 0; i < n; i++) {			//� ������ ����������� ������� �� �����, ������� � ���������					
            forks.add(new Fork(i));				//������ ����� ��������������� id, ��������������� ������ � ������
        }
        for (int i = 0; i < n; i++) {			//�������� n ��������� � ������������� ������� "������" � "�����" �����
            Fork left = forks.get(i);			//"������" ��������� �����, ��������������� ������ ��������
            Fork right = forks.get((i + 1) % n);	//����� ��������������� ��������� ����� (��� ���� �� 1 ������ ������,
            										//����� ����������, ��� ���� "�����" ����� ������ ����� (�.�. � ������� 0)
            
            philosophers.add(new Philosopher(waiter,THINK_TIME,EAT_TIME, left, right, i)); //�������� ������� � 
            															//���������������� �������, ��������� � id 
        }
        philosophers.forEach(Thread::start);	//��� ������ (��������) �����������
        Util.waitMillis(WORK_TIME);			//������� ����� ����
        philosophers.forEach(Thread::interrupt);	//��� ������ (��������) �����������
        Util.waitMillis(WAIT_TIME);		//������� ����� ����
    }

}