package by.tr.epam.port_task.port;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import by.tr.epam.port_task.warehouse.Container;
import by.tr.epam.port_task.warehouse.Warehouse;

public class Berth {

	private int id;
	private Warehouse portWarehouse;
	private static final int WAIT_TIME = 80;

	public Berth(int id, Warehouse warehouse) {
		this.id = id;
		portWarehouse = warehouse;
	}

	public int getId() {
		return id;
	}

	public boolean add(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException{
		boolean result = false;
		Lock portWarehouseLock = portWarehouse.getLock();
		Lock shipWarehouseLock = shipWarehouse.getLock();
		try{
			if (portWarehouseLock.tryLock(WAIT_TIME, TimeUnit.SECONDS)){
				if (shipWarehouseLock.tryLock()){
					List<Container> list = shipWarehouse.getContainer(numberOfConteiners);
					if(list!=null){
						result = portWarehouse.addContainer(list);
					}
				}
			}
		}finally{
			shipWarehouseLock.unlock();		
			portWarehouseLock.unlock();
		}		
		return result;
		
	}

	public boolean get(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException{
		boolean result = false;
		Lock portWarehouseLock = portWarehouse.getLock();
		Lock shipWarehouseLock = shipWarehouse.getLock();
		try{
			if (portWarehouseLock.tryLock(WAIT_TIME, TimeUnit.SECONDS)){
				if (shipWarehouseLock.tryLock()){
					List<Container> list = portWarehouse.getContainer(numberOfConteiners);
					if(list!=null){
						result = shipWarehouse.addContainer(list);
					}
				}
			}
		}finally{
			shipWarehouseLock.unlock();		
			portWarehouseLock.unlock();
		}
		return result;
	}
	
	
}
