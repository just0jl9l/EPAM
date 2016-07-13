package by.tr.epam.concurrent.read_write_lock;

/*	Потоконезависимое дерево для строк.
 */

public class ReadWriteLockMain {
	
	public static void main(String[] args) {
		ThreadsafeTree tree = new ThreadsafeTree();
		tree.put("слово");
		tree.put("слон");
		tree.put("солнце");
		tree.put("мир");
		System.out.println(tree.find("слон"));
		tree.printSorted();
	}
}