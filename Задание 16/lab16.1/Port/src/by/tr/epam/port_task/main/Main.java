package by.tr.epam.port_task.main;

import java.util.ArrayList;
import java.util.List;

import by.tr.epam.port_task.port.Port;
import by.tr.epam.port_task.ship.Ship;
import by.tr.epam.port_task.warehouse.Container;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int warehousePortSize = 15;
		List<Container> containerList = new ArrayList<Container>(warehousePortSize);
		for (int i=0; i<warehousePortSize; i++){
			containerList.add(new Container(i));
		}
		
		Port port = new Port(2, 900);
		port.setContainersToWarehouse(containerList);
		
		
		containerList = new ArrayList<Container>(warehousePortSize);
		for (int i=0; i<warehousePortSize; i++){
			containerList.add(new Container(i+15));
		}
		Ship ship1 = new Ship("Ship1", port, 90);
		ship1.setContainersToWarehouse(containerList);
		
		containerList = new ArrayList<Container>(warehousePortSize);
		for (int i=0; i<warehousePortSize; i++){
			containerList.add(new Container(i+30));
		}
		Ship ship2 = new Ship("Ship2", port, 90);
		ship2.setContainersToWarehouse(containerList);
		
		containerList = new ArrayList<Container>(warehousePortSize);
		for (int i=0; i<warehousePortSize; i++){
			containerList.add(new Container(i+45));
		}
		Ship ship3 = new Ship("Ship3", port, 90);
		ship3.setContainersToWarehouse(containerList);		
		
		
		new Thread(ship1).start();		
		new Thread(ship2).start();		
		new Thread(ship3).start();
		

		Thread.sleep(3000);
		
		ship1.stopThread();
		ship2.stopThread();
		ship3.stopThread();

	}

}
