package by.tr.epam.philosophers;

public class Util {

    public static void waitMillis(long millis) {	//�������� ��������� �����
        if (millis <= 0) {						//���� ����������� ����� �������� �� ������ ����
            return;								//����� �� ������
        }										//�����
        try {
            Thread.sleep(millis);				//����� ���� ������������ �����
        } catch (InterruptedException e) {		//������� ����� ����� ���������� �������� ������ �����
        										//(�����, ������� ��������� ���� �����)
        										//�� ������ ���������� ��������� ���������� (��� �������� ����� sleep())
        	
            Thread.currentThread().interrupt();	//� ���� ������ ����� ���������� ��������
        }
    }

}
