package by.tr.epam.concurrent.cyclic_barrier;

public class FerryBoat implements Runnable {
	@Override
    public void run() {
        try {
            Thread.sleep(500);	//паром переправляет машины
            System.out.println("Паром переправил автомобили!");
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
