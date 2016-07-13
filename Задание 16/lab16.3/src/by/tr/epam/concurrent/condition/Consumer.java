package by.tr.epam.concurrent.condition;

import java.util.HashSet;
import java.util.Set;

public class Consumer extends Thread {
	
	private final Set seenObjects = new HashSet();		//множество, куда помещаются считанные слова
	private int total = 0;								//число считанных из очереди слов
	private final SharedFiFoQueue queue;				//общая разделяемая очередь

	public Consumer(SharedFiFoQueue queue) {	//при создании объекта потребителя, он
												//получает доступ к общей разделяемой очереди
		this.queue = queue;
	}

	public void run() {
		try {
			do {
				Object obj = queue.remove();		//из общей очереди берём объект (слово)
				if (obj == null)				//если считанный объект является "особенным" 
												//(null, т.е. сообщающим о конце очереди слов)
					
					break;						//завершить выполнение цикла чтения слов из очереди
				if (!seenObjects.contains(obj)) {	//если слово ещё не содержится во множестве считанных слов
					++total;					//увеличить число считанных слов на 1
					seenObjects.add(obj);		//и добавить считанное слово во множество считанных слов
				}
				System.out.println("Потребитель считал элемент: " + obj.toString());
			} while (true);
		} catch (InterruptedException ex) {
			System.err.println("Произошло InterruptedException: " + ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Потребитель считал всего "+total + " отдельных слов.");
	}
}

