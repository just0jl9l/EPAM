package by.tr.epam.concurrent.lock;

import java.util.Random;

public class Person extends Thread {

	private Counter counter; // общий счётчик
	private String name; // имя человека

	public Person(String name, Counter cart) {
		this.counter = cart;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + " хочет получить счётчик");
		Random random = new Random();
		try {
			counter.lock();		//человек получает счётчик (блокирует его)
			counter.add(random.nextInt(11) - 5);	//человек изменяет счётчик, прибавив к его значению число от -5 до 5 
			System.out.println(name + " изменил значение счётчика до " + counter.getCurrent());
			try {
				Thread.sleep(100);		//человек ждёт некоторое время
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " закончил работать со счётчиком");
		} finally {
			counter.unlock();		//и возвращает блокировку счётчика
		}
	}

}
