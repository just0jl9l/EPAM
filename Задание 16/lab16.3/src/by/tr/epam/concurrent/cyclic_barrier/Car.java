package by.tr.epam.concurrent.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
	private CyclicBarrier barrier;	//общий барьер
	private int id;		//идентификатор машины

	public Car(int id,CyclicBarrier barrier) {		//при создании машина получает доступ к общему барьеру
		this.id = id;
		this.barrier=barrier;
	}

	@Override
	public void run() {
		try {
			System.out.println("Автомобиль "+id+" подъехал к паромной переправе.");
			barrier.await();	//автомобиль сообщает главному потоку, что достиг барьера, и ждёт, 
								//пока паром переправит её (для этого на пароме должно быть 3 машины)
			System.out.println("Автомобиль "+id+" продолжил движение.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
