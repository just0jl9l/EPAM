package by.tr.epam.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/* Существует парковка, которая одновременно может вмещать не более 5 автомобилей. Если парковка 
 * заполнена полностью, то вновь прибывший автомобиль должен подождать пока не освободится хотя 
 * бы одно место. После этого он сможет припарковаться.
 */

public class SemaphoreMain {
    private static final boolean[] PARKING_PLACES = new boolean[5];	//массив парковочных мест 
    																//(занято - true, свободно - false)
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);	//общий "справедливый" семафор на 5 мест
    																//(справедливый - раздаёт места в порядке очереди)
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 9; i++) {	//создание 6 машин
            new Thread(new Car(i, PARKING_PLACES, SEMAPHORE)).start();
            Thread.sleep(400);
        }
    }
}
