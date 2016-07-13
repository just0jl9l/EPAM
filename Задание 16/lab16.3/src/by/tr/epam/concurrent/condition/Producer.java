package by.tr.epam.concurrent.condition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Producer extends Thread {
	private final static String FILENAME = "input.txt";		//файл, из которого читает поставщик
	private final SharedFiFoQueue queue;					//разделяемая очередь, в которую поставщик
															//помещает считанные элементы

	public Producer(SharedFiFoQueue queue) {		//при создании объекта поставщика,
													//он получает доступ к общей разделяемой очереди
		this.queue = queue;
	}

	public void run() {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(FILENAME));
			String inputLine = null;
			while ((inputLine = rd.readLine()) != null) {		//считывается новая строка из файла
				String[] inputWords = inputLine.split(" ");		//строка разбивается на массив слов, разделенных пробелом
				for (String inputWord : inputWords)				//каждое слово из массива считанных слов
					queue.add(inputWord);						//помещается в очередь
			}
			queue.add(null);							//специальный объект null, обозначающий конец очереди слов 
														//(больше не будет слов, т.к. конец файла)
		} catch (InterruptedException ex) {
			System.err.println("Произошло InterruptedException: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Произошло IOException: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (rd != null)
					rd.close();
			} catch (IOException ex) {
				System.err.println("Произошло IOException: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
