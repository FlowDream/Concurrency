/**
 * 
 */
package chapter05;

import java.util.Vector;

/**
 * 在使用客户端加锁的 Vector 上的复合操作
 */
public class VectorTest {

	public static String getLast(Vector<String> list) {
		synchronized (list) {
			int lastIndex = list.size() - 1;
			return list.get(lastIndex);
		}
	}

	public static void deleteLast(Vector<String> list) {
		synchronized (list) {
			int lastIndex = list.size() - 1;
			list.remove(lastIndex);
		}
	}

	public static void iteratorLista(Vector<String> list) {
		synchronized (list) {
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
}
