package by.tr.epam.philosophers;

public class Main {

    public static int PHILOSOPHERS_NUMBER = 5;		//число обедающих философов

    public static void main(String[] args) throws InterruptedException {
        new Controller(PHILOSOPHERS_NUMBER).newRun();		
    }

}
