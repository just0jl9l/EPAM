package by.tr.epam.concurrent.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

/* Есть пять остановок. На первых четырех из них могут стоять пассажиры и ждать автобуса. Автобус выезжает 
 * из парка и останавливается на каждой остановке на некоторое время. После конечной остановки автобус 
 * едет в парк. Нам нужно забрать пассажиров и высадить их на нужных остановках.
 */

public class PhaserMain {
	private static final Phaser PHASER = new Phaser(1);	//создаётся фазер и сразу регистрируется главный поток
														//фазы 0 и 6 - это автобусный парк, 1 - 5 остановки

    public static void main(String[] args) throws InterruptedException {
    	ArrayList<Passenger> passengers=new ArrayList<Passenger>();
        for (int i = 1; i < 5; i++) {           //генерация пассажиров на остановках
            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, i + 1,PHASER));	//пассажир выходит на следующей станции

            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, 5,PHASER));    //пассажир выходит на конечной
        }
       new Bus(passengers,PHASER).run();
    }
}
