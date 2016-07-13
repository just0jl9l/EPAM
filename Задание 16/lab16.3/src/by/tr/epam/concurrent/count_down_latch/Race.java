package by.tr.epam.concurrent.count_down_latch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Race {
	private Random rand = new Random();

	private int distance = rand.nextInt(250);		//дистанция забега лошадей
	private CountDownLatch start;					//условие начала (сигнал начала забега)
	private CountDownLatch finish;					//условие окончания	(когда все лошади достигнут конца дистанции)

	private List<String> horses = new ArrayList<String>();	//имена участников

	public Race(String... names) {
		this.horses.addAll(Arrays.asList(names));	//список имён участников
	}

	public void run() throws InterruptedException {
		System.out.println("Лошади встали у ворот");
		start = new CountDownLatch(1);			//условие размера 1 (т.к. сигнал начала забега посылает только 1 поток)
		finish = new CountDownLatch(horses.size());		//условий столько же, сколько лошадей
		final List<String> places = Collections.synchronizedList(new ArrayList<String>()); //список лошадей в порядке 
																	//занятых мест на забеге (чем раньше достиг
																	//конца, тем меньше номер в списке)

		for (final String h : horses) {	//каждая лошадь
			new Thread(new Runnable() {	//представляет собой отдельный поток
				public void run() {
					try {
						System.out.println(h + " встала у ворот");	//лошадь подошла к воротам (начало дистанции)
						start.await();	//ожидание выполнения условия, сообщающем о начале забега

						int traveled = 0;	//пройденный лошадью путь
						while (traveled < distance) {	//пока пройденная дистанция меньше общей дистанции забега
							Thread.sleep(rand.nextInt(3) * 1000); //лошадь бежит (поток спит от 0 до 2 секунд)
							traveled += rand.nextInt(15); //лошадь проходит дистанцию от 0 до 14 пунктов
							System.out.println(h + " достигла " + traveled + " пунктов!");
						}
						finish.countDown();	//счётчик выполненных условий уменьшается на 1 
											//(ещё одна лошадь достигла до конца дистанции)
						System.out.println(h + " пересекла финиш!");
						places.add(h);	//добавляет лошадь в список лошадей по местам, занятых в скачках
					} catch (InterruptedException intEx) {
						System.out.println("Заезд прерван!!!");
						intEx.printStackTrace();
					}
				}
			}).start();
		}
		Thread.sleep(50);	//ожидаем некоторое время, чтобы все лошади успели подойти к воротам
		System.out.println("И они начинают!");
		start.countDown();	//счётчик условий уменьшается на 1, тем самым сообщая всем лошадям о начале скачек

		finish.await();	//ждать, пока все лошади не достигнут конца дистанции
		System.out.println("У нас есть победители!");
		System.out.println(places.get(0) + " получает золото");
		System.out.println(places.get(1) + " получает серебро");
		System.out.println("and " + places.get(2) + " получает бронзу");
	}

	public int getDistance() {
		return distance;
	}

}
