package by.tr.epam.dining_philosophers.philosopher;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.epam.dining_philosophers.fork.Fork;
import by.tr.epam.dining_philosophers.util.Util;

public class Philosopher extends Thread {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	private final Semaphore waiter;		//семафор, к которому обращается филосов для "разрешения" 
										//начать приём пищи (брать вилки, кушать, класть вилки)
	
	private final long eatTime;			//время, которое филосов тратит на один приём пиши
	private final long thinkTime;		//время, которое филосов тратит на размышление
	private final Fork fork1;			//левая вилка
	private final Fork fork2;			//правая вилка
	private int id;						//идентификатор философа

	public Philosopher(Semaphore waiter, long eatTime, long thinkTime, Fork fork1, Fork fork2, int id) {
		this.waiter = waiter;
		this.eatTime = eatTime;
		this.thinkTime = thinkTime;
		this.fork1 = fork1;
		this.fork2 = fork2;
		this.id=id;
	}

	public void run() {
		logger.info("Философ "+id+" сел за стол");
		while (!this.isInterrupted()) {				//пока главный поток не прервёт философа
			try {
				waiter.acquire();			//филосов ждёт, пока семафор не даст разрешение
											//приступить к приёму пищи (т.е. пока хотя бы ещё один филосов не будет
											//принимать пищу)

				take(fork1);				//филосов берёт левую вилку
				take(fork2);				//филосов берёт правую вилку
				eat();						//филосов кушает
				put(fork1);					//филосов кладёт левую вилку
				put(fork2);					//филосов кладёт правую вилку

			} catch (InterruptedException e) { //данное исключение возникает, если во время ожидания захвата семафора
												//главный поток захочет прервать философа, но вместо этого возникнет
												//исключение (его выбросит метод acquire())
				
				this.interrupt();			//необходимо прервать философа, т.к. это пытался сделать главный поток
			} finally {
				waiter.release();			//филосов освобождает семафор (его счётчик уменьшается на 1)
				think();					//филосов размышляет
			}
		}
		logger.info("Философ "+id+" вышел из-за стола");
	}

	private void take(Fork fork) {
		fork.onTake();			//филосов берёт вилку
		logger.info("Философ "+id+" взял вилку "+fork.getId());
	}

	private void put(Fork fork) {
		fork.onPut();			//филосов кладёт вилку
		logger.info("Философ "+id+" положил вилку "+fork.getId());
	}

	private void eat() {
		Util.waitMillis(eatTime);	//филосов "кушает" (поток спит определенное время)
		logger.info("Философ "+id+" покушал");
	}

	private void think() {
		Util.waitMillis(thinkTime);	//филосов "размышляет" (поток спит определенное время)
		logger.info("Философ "+id+" поразмышлял");
	}

}
