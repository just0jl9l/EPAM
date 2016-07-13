package by.tr.epam.concurrent.lock;

/* Есть счётчит. При его получении человек может увеличить или уменьшить значение счётчика. В некоторый момент времени
 * счётчик может получить только один человек.
*/

public class LockMain {

	public static void main(String args[]) {
		Counter counter = new Counter();				//общий счётчик
		new Person("Человек 1", counter).start();
		new Person("Человек 2", counter).start();
		new Person("Человек 3", counter).start();
		new Person("Человек 4", counter).start();

	}
}