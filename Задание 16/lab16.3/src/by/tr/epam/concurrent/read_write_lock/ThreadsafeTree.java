package by.tr.epam.concurrent.read_write_lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadsafeTree {
	private ReadWriteLock lock = new ReentrantReadWriteLock(); // блокировка на чтение или запись
	private static Map<Integer, String> levelSpacesMap = new ConcurrentHashMap<Integer, String>();	//отступ для каждого уровня
	private TreeNode root = new TreeNode();		//корень дерева

	public void put(String s) {	//добавление строки
		TreeNode v = root;
		lock.writeLock().lock();	//блокировка дерева на запись элементов (считывать можно, записывать может 
									//только этот поток)
		for (char ch : s.toLowerCase().toCharArray()) {	//для каждого символа строки
			if (!v.getChildren().containsKey(ch)) {		//если у родительского элемента ещё нет такого дочернего элемента
				v.getChildren().put(ch, new TreeNode());		//добавляем его
			}
			v = v.getChildren().get(ch);		//опускаемся на один уровень в дереве
		}
		v.setLeaf(true);		//последний символ строки устанавливается листом
		lock.writeLock().unlock();		//дерево разблокированно
	}

	public boolean find(String s) {	//содержится ли в данный момент строка в дереве
		TreeNode v = root;
		boolean result = true;
		lock.readLock().lock();		//блокировка на чтение
		for (char ch : s.toLowerCase().toCharArray()) {	//для каждого символа строки
			if (!v.getChildren().containsKey(ch)) {	//если у родительского элемента нет такого дочернего элемента
				result = false;		//значит в дереве нет искомой строки
				break;				//цикл поиска элементов прерывается
			} else {	//если у родительского элемента есть такой дочерний элемент
				v = v.getChildren().get(ch);	//устанавливаем его родительским и продолжаем поиск
			}
		}
		lock.readLock().unlock();	//снимается блокировка на чтение
		return result;
	}

	private static String getSpace(int level) {		//отступ для некоторого уровня в дереве
		String result = levelSpacesMap.get(level);	//получение необходимого отступа
		if (result == null) {						//если для такого уровня ещё нет отступа
			StringBuilder sb = new StringBuilder();	//создать новый
			for (int i = 0; i < level; i++) {		//с количеством пробелов = уровню
				sb.append(" ");
			}
			result = sb.toString();
			levelSpacesMap.put(level, result);		//добавить его в карту всех отступов
		}
		return result;
	}

	public void printSorted() {			//вывод дерева с сортировкой
		lock.readLock().lock();			//дерево блокируется для чтения
		print(root, 0);
		lock.readLock().unlock();		//блокировка на чтение снимается
	}

	private void print(TreeNode node, int level) {			//вывод дочерних элементов вершины определенного уровня
		for (Character ch : node.getChildren().keySet()) {	//для каждого дочернего элемента
			System.out.println(getSpace(level) + ch);		//вывести его с соответствующим уровню отступом
			print(node.getChildren().get(ch), level + 1);	//вывести все его дочерние элементы
		}
		if (node.isLeaf()) {		//если вершина является листом
			System.out.println();	//вывести пустую строку
		}
	}
}
