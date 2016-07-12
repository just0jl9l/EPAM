package by.tr.epam.dining_philosophers.main;

import by.tr.epam.dining_philosophers.controller.Controller;

public class Main {

    public static int PHILOSOPHERS_NUMBER = 5;		//число обедающих философов

    public static void main(String[] args) throws InterruptedException {
        new Controller(PHILOSOPHERS_NUMBER).newRun();		
    }

}
