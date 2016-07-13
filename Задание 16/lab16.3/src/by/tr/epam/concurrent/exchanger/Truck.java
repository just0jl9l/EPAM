package by.tr.epam.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class Truck implements Runnable {
	private Exchanger<String> exchanger; //общий обменник
	
    private int id;	//идентификатор грузовика
    private String from;	//откуда отправляется грузовик
    private String to;	//куда отправляется грузовик
    private String[] parcels;	//посылки, находящиеся в грузовике

    public Truck(int number, String departure, String destination, String[] parcels,Exchanger<String> exchanger) {
        this.id = number;
        this.from = departure;
        this.to = destination;
        this.parcels = parcels;
        this.exchanger=exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("В грузовик "+id+" погрузили: "+parcels[0]+" и "+parcels[1]+".");
            System.out.println("Грузовик "+id+" выехал из пункта "+from+" в пунк "+to+".");
            Thread.sleep(1000 + (long) Math.random() * 5000);	//грузовик едет
            System.out.println("Грузовик "+id+" приехал в пункт Е.");
            parcels[1] = exchanger.exchange(parcels[1]);//поток (грузовик) блокируется и ждет пока другой поток 
            											//вызовет exchange(), после этого произойдет обмен посылками
            System.out.println("В грузовик "+id+" переместили посылку для пункта "+to+".");
            Thread.sleep(1000 + (long) Math.random() * 5000);	//грузовик едет
            System.out.println("Грузовик "+id+" приехал в "+to+" и доставил: "+parcels[0]+" и "+parcels[1]+".");
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
