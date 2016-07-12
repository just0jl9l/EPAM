package by.tr.epam.philosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Philosopher implements Runnable {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

    private final Semaphore waiter;
    private final AtomicInteger eaten;
    private final long eatTime;
    private final long thinkTime;
    private final Fork fork1;
    private final Fork fork2;
    private int count;

    public Philosopher(Semaphore waiter, AtomicInteger eaten,
                       long eatTime, long thinkTime,
                       Fork fork1, Fork fork2
    ) {
        this.waiter = waiter;
        this.eaten = eaten;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                waiter.acquire();

                take(fork1);
                take(fork2);
                eat();
                put(fork1);
                put(fork2);

                think();
            } catch (InterruptedException e) {
                // do nothing
            } finally {
                waiter.release();
                think();
            }
        }
        eaten.set(count);
    }

    private void take(Fork fork) {
        fork.onTake();
    }

    private void put(Fork fork) {
        fork.onPut();
    }

    private void eat() {
        count++;
        Util.waitMillis(eatTime);
        
    }

    private void think() {
        Util.waitMillis(thinkTime);
    }

}
