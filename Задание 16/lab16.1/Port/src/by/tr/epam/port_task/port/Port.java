package by.tr.epam.port_task.port;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.apache.log4j.Logger;

import by.tr.epam.port_task.ship.Ship;
import by.tr.epam.port_task.warehouse.Container;
import by.tr.epam.port_task.warehouse.Warehouse;

public class Port {
	private final static Logger logger = Logger.getRootLogger();

	private BlockingQueue<Berth> berthList; // очередь причалов
	private Warehouse portWarehouse; // хранилище порта
	private Map<Ship, Berth> usedBerths; // какой корабль у какого причала стоит
	private static final int WAIT_TIME = 80;

	public Port(int berthSize, int warehouseSize) {
		portWarehouse = new Warehouse(warehouseSize); // создаем пустое
														// хранилище
		berthList = new ArrayBlockingQueue<Berth>(berthSize); // создаем очередь причалов
		for (int i = 0; i < berthSize; i++) { // заполняем очередь причалов
												// непосредственно самими
												// причалами
			berthList.add(new Berth(i, portWarehouse));
		}
		usedBerths = new ConcurrentHashMap<Ship, Berth>(); // создаем объект, который
													// будет
		logger.debug("Порт создан.");
	}

	 public void setContainersToWarehouse(List<Container> containerList) throws InterruptedException{
		 Lock portWarehouseLock = portWarehouse.getLock();
			try{
				if (portWarehouseLock.tryLock(WAIT_TIME, TimeUnit.SECONDS)){
					portWarehouse.addContainer(containerList);
				}
			}finally{	
				portWarehouseLock.unlock();
			}
	 }
	 

	public boolean lockBerth(Ship ship) throws InterruptedException {
		boolean result = false;
		Berth berth;
		berth = berthList.take();		
		if (berth != null) {
			result = true;
			usedBerths.put(ship, berth);
		}		
		return result;
	}

	public boolean unlockBerth(Ship ship) throws InterruptedException {
		Berth berth = usedBerths.get(ship);
		usedBerths.remove(ship);
		berthList.put(berth);				
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
