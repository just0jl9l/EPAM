package by.tr.epam.philosophers;

public class Main {

    public static int N = 5;

    public static void main(String[] args) throws InterruptedException {
        new SemaphoreEngine(N, 1, 1).newRun();
    }

}
