package by.tr.epam.dining_philosophers.util;

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
