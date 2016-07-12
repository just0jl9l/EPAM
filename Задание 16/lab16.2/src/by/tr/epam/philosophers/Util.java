package by.tr.epam.philosophers;

public class Util {

    public static void waitMillis(long millis) {	//ожидание некоторое время
        if (millis <= 0) {						//если необходимое время ожидания не больше нуля
            return;								//выйти из метода
        }										//иначе
        try {
            Thread.sleep(millis);				//поток спит определенное время
        } catch (InterruptedException e) {		//главный поток может попытаться прервать спящий поток
        										//(поток, который выполняет этот метод)
        										//но вместо прерывания возникнет исключение (его выбросит метод sleep())
        	
            Thread.currentThread().interrupt();	//в этом случае поток необходимо прервать
        }
    }

}
