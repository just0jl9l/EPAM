package by.tr.epam.concurrent.phaser;

import java.util.concurrent.Phaser;

public class Passenger extends Thread {
	private Phaser phaser;	//общий фазер
	private int departure;	//остановка, на которой пассажир ждёт автобус
	private int destination;	//остановка, на которой пассажир хочет выйти

	public Passenger(int departure, int destination, Phaser phaser) {
		this.departure = departure;
		this.destination = destination;
		this.phaser = phaser;
		System.out.println(this.show() + " ждёт на остановке " + this.departure);
	}

	@Override
	public void run() {
		try {
			System.out.println(this.show() + " сел в автобус.");

			while (phaser.getPhase() < destination) //пока автобус не приедет на нужную остановку(фазу)
				phaser.arriveAndAwaitAdvance(); //заявляет в каждой фазе о готовности и ждет

			Thread.sleep(1);
			System.out.println(this.show() + " покинул автобус.");
			phaser.arriveAndDeregister(); //отменяет регистрацию на нужной остановке(фазе)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String show() {
		return "Пассажир {" + departure + " -> " + destination + '}';
	}

	public int getDeparture() {
		return departure;
	}
}
