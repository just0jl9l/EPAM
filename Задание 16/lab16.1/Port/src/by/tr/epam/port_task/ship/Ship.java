package by.tr.epam.port_task.ship;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import by.tr.epam.port_task.port.Berth;
import by.tr.epam.port_task.port.Port;
import by.tr.epam.port_task.port.PortException;
import by.tr.epam.port_task.warehouse.Container;
import by.tr.epam.port_task.warehouse.Warehouse;

public class Ship implements Runnable {

	private final static Logger logger = Logger.getRootLogger();
	private volatile boolean stopThread = false;

	private String name;
	private Port port;
	private Warehouse shipWarehouse;

	public Ship(String name, Port port, int shipWarehouseSize) {
		this.name = name;
		this.port = port;
		shipWarehouse = new Warehouse(shipWarehouseSize);
	}

	public void setContainersToWarehouse(List<Container> containerList) {
		shipWarehouse.addContainer(containerList);
	}

	public String getName() {
		return name;
	}

	public void stopThread() {
		stopThread = true;
	}

	public void run() {
		try {
			while (!stopThread) {
				atSea();
				inPort();
			}
		} catch (InterruptedException e) {
			logger.error("С кораблем случилась неприятность и он уничтожен.", e);
		} catch (PortException e) {
			logger.error("Причал разрушен, с кораблем случилась неприятность и он уничтожен.", e);
		}
	}

	private void atSea() throws InterruptedException {
		Thread.sleep(1000);
	}

	private void inPort() throws PortException, InterruptedException {
		boolean isLockedBerth = false;
		Berth berth = null;
		try {
			isLockedBerth = port.lockBerth(this);
			if (isLockedBerth) {
				berth = port.getBerth(this);
				if (berth != null) {
					logger.debug("Корабль " + name + " пришвартовался к причалу " + berth.getId());
					ShipAction action = getNextAction();
					executeAction(action, berth);
				}
			} else {
				logger.debug("Кораблю " + name + " отказано в швартовке к причалу ");
			}
		} finally {
			if (isLockedBerth) {
				port.unlockBerth(this);
				logger.debug("Корабль " + name + " отошел от причала " + berth.getId());
			}
		}

	}

	private void executeAction(ShipAction action, Berth berth) throws InterruptedException {
		switch (action) {
		case LOAD_TO_PORT:
			loadToPort(berth);
			break;
		case LOAD_FROM_PORT:
			loadFromPort(berth);
			break;
		}
	}

	private boolean loadToPort(Berth berth) throws InterruptedException {
		boolean result = false;
		int containersNumberToMove;
		int shipConteinersNumber = shipWarehouse.getRealSize();
		if (shipConteinersNumber > 0) {
			containersNumberToMove = conteinersCount(shipConteinersNumber);
			logger.debug("Корабль " + name + " хочет загрузить " + containersNumberToMove + " контейнеров на склад порта.");
			result = berth.add(shipWarehouse, containersNumberToMove);
			if (!result) {
				logger.debug("Недостаточно места на складе порта для выгрузки кораблем " + name + " " + containersNumberToMove + " контейнеров.");
			} else {
				logger.debug("Корабль " + name + " выгрузил " + containersNumberToMove + " контейнеров в порт.");
			}
		} else {
			logger.debug("Корабль " + name + " пуст и не может выгрузить контейнеры на склад порта.");
		}
		return result;
	}

	private boolean loadFromPort(Berth berth) throws InterruptedException {
		boolean result = false;
		int containersNumberToMove;
		int free = shipWarehouse.getFreeSize();
		if (free > 0) {
			containersNumberToMove = conteinersCount(free);
			logger.debug("Корабль " + name + " хочет загрузить " + containersNumberToMove + " контейнеров со склада порта.");
			result = berth.get(shipWarehouse, containersNumberToMove);
			if (result) {
				logger.debug("Корабль " + name + " загрузил " + containersNumberToMove + " контейнеров из порта.");
			} else {
				logger.debug("Недостаточно контейнеров в порту для погрузки на корабль " + name + " " + containersNumberToMove + " контейнеров.");
			}
		} else {
			logger.debug("Корабль " + name + " полон и не может загрузить контейнеры из порта.");
		}
		return result;
	}

	private int conteinersCount(int n) {
		Random random = new Random();
		return random.nextInt(n) + 1;
	}

	private ShipAction getNextAction() {
		Random random = new Random();
		int value = random.nextInt(4000);
		if (value < 1000) {
			return ShipAction.LOAD_TO_PORT;
		} else if (value < 2000) {
			return ShipAction.LOAD_FROM_PORT;
		}
		return ShipAction.LOAD_TO_PORT;
	}

	enum ShipAction {
		LOAD_TO_PORT, LOAD_FROM_PORT
	}
}
