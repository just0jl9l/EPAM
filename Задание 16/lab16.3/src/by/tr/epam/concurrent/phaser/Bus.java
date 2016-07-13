package by.tr.epam.concurrent.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class Bus {
	ArrayList<Passenger> passengers;	//список всех пассажиров
	private Phaser phaser;				//общий фазер

	public Bus(ArrayList<Passenger> passengers,Phaser phaser) {		//установка пассажиров и фазера
		this.passengers = passengers;
		this.phaser = phaser;
	}

	public void run() {
		for (int i = 0; i < 7; i++) {
			switch (i) {
			case 0:
				System.out.println("Автобус выехал из парка.");
				phaser.arrive();//в фазе 0 всего 1 участник - автобус
				break;
			case 6:
				System.out.println("Автобус уехал в парк.");
				phaser.arriveAndDeregister();//снимаем главный поток, ломаем барьер
				break;
			default:
				int currentBusStop = phaser.getPhase();
				System.out.println("Остановка " + currentBusStop);

				for (Passenger p : passengers){	//для каждого пассажира
					if (p.getDeparture() == currentBusStop) { //ждёт ли он автобус на текущей остановке
						phaser.register();//пассажир заходит в автобус (регистрируется поток, который 
											//будет участвовать в фазах
						p.start(); //запускает пассажира
					}
				}
				phaser.arriveAndAwaitAdvance();	//сообщает о готовности (завершение фазы)
			}
		}
	}
}
