package by.tr.epam.philosophers;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreEngine {

    static final int SECONDS = 2;

    final List<Philosopher> philosophers = new ArrayList<>();
    private final int n;
    private final long thinkTime;
    private final long eatTime;

    public SemaphoreEngine(int n, long eatTime, long thinkTime) {
        this.n = n;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;

    }

    public void newRun() {
        Semaphore waiter = new Semaphore(n - 1);
        AtomicInteger[] eaten = new AtomicInteger[n];
        List<Fork> forks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            forks.add(new Fork());
        }

        for (int i = 0; i < n; i++) {
            eaten[i] = new AtomicInteger(0);
            Fork left = forks.get(i);
            Fork right = forks.get((i + 1) % n);
            philosophers.add(new Philosopher(waiter, eaten[i], eatTime, thinkTime, left, right));
        }
        List<Thread> philosopherThreads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread philosopherThread = new Thread(philosophers.get(i));
            philosopherThreads.add(philosopherThread);
            philosopherThread.start();
        }

        Util.waitMillis(1000L * SECONDS);
        philosopherThreads.stream().forEach(Thread::interrupt);

        Util.waitMillis(1000);
        Util.printResult(eaten);
    }

}