package by.tr.epam.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
	private boolean[] parking_places;	//массив парковочных мест стоянки
	private Semaphore semaphore;		//общий семафор на количество мест стоянки
	private int id;					//идентификатор машины

	public Car(int id, boolean[] parking_places, Semaphore semaphore) {
		this.id = id;
		this.parking_places = parking_places;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		System.out.println("Автомобиль "+id+" подъехал к парковке.");
		try {
			semaphore.acquire();		//машина пытается припарковаться (поток запрашивает доступ к следующему 
									//за вызовом этого метода блоку кода, если доступ не разрешен (в блоке кода
									//максимально разрешенное число потоков), поток вызвавший этот метод блокируется
									//до тех пор, пока семафор не разрешит доступ)

			int parkingNumber = -1;			
			synchronized (parking_places) {// поиск свободного места на парковке
				int n = parking_places.length;
				for (int i = 0; i < n; i++)		//для каждого места парковки
					if (!parking_places[i]) { //если место свободно
						parking_places[i] = true; //машина занимает его (семафор гарантирует наличиесвободного места)
						parkingNumber = i;
						System.out.println("Автомобиль "+id+" припарковался на месте "+i+".");
						break;		//дальше не нужно искать свободное место
					}
			}

			Thread.sleep(3000); 	//машина стоит на парковке некоторое время

			synchronized (parking_places) {
				parking_places[parkingNumber] = false;	//машина освобождает парковочное место
			}			
			semaphore.release();		// поток освобождает ресурс (увеличивает его счётчик)
			System.out.println("Автомобиль "+id+" покинул парковку.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
