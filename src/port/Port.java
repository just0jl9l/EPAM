package port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import ship.Ship;
import warehouse.Container;
import warehouse.Warehouse;

public class Port {
	private final static Logger logger = Logger.getRootLogger();

	private List<Berth> berthList; // очередь причалов
	private Warehouse portWarehouse; // хранилище порта
	private Map<Ship, Berth> usedBerths; // какой корабль у какого причала стоит

	public Port(int berthSize, int warehouseSize) {
		portWarehouse = new Warehouse(warehouseSize); // создаем пустое
														// хранилище
		berthList = new ArrayList<Berth>(berthSize); // создаем очередь причалов
		for (int i = 0; i < berthSize; i++) { // заполняем очередь причалов
												// непосредственно самими
												// причалами
			berthList.add(new Berth(i, portWarehouse));
		}
		usedBerths = new HashMap<Ship, Berth>(); // создаем объект, который
													// будет
		logger.debug("Порт создан.");
	}

	/*
	 * public void setContainersToWarehouse(List<Container> containerList){
	 * portWarehouse.addContainer(containerList); }
	 */

	public boolean lockBerth(Ship ship) {
		boolean result = false;
		Berth berth;

		//!!!!!!!!!
		synchronized (berthList) {
			berth = berthList.remove(0);
		}
		
		if (berth != null) {
			result = true;
			usedBerths.put(ship, berth);
		}	
		
		return result;
	}

	public boolean unlockBerth(Ship ship) {
		Berth berth = usedBerths.get(ship);

		synchronized (berthList) {
			berthList.add(berth);
			usedBerths.remove(ship);	
		}		
		
		return true;
	}

	public Berth getBerth(Ship ship) throws PortException {

		Berth berth = usedBerths.get(ship);
		if (berth == null) {
			throw new PortException("Try to use Berth without blocking.");
		}
		return berth;
	}
}
