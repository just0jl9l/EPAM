package by.tr.epam.concurrent.condition;

/* Существует некоторый файл. Поток поставщика читает из него слова и помещает их в общую разделяемую
 * очередь. Поток потребителя считывает и удаляет слова из этой очереди и выводит их на консоль. 
 */

public class ConditionMain {
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);
		Thread producer = new Producer(sharedQueue);
		Thread consumer = new Consumer(sharedQueue);
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();
	}
}