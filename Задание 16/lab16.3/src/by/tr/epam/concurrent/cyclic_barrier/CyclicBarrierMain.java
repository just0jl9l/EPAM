package by.tr.epam.concurrent.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

/* Существует паромная переправа. Паром может переправлять одновременно по три автомобиля. 
 * Паром отправляется лишь тогда, когда у переправы соберется минимум три автомобиля.
 */

public class CyclicBarrierMain {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());	//барьер на три потока (машины)
    //когда у барьера соберется три машины, паром переправит их (т.е. запустится выполнение потока FerryBoat),
    //и после этого машины будут освобождены.

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i,BARRIER)).start();	//создание новой машины с общим барьером
        	Thread.sleep(400);	//некоторое время перед созданием новой машины
        }
    }

}
