/**
 * 
 */
package chapter15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Michael-Scott 非阻塞算法中插入算法
 */
@SuppressWarnings("unused")
public class LinkedQueue<E> {
	private final Node<E> dumy = new Node<E>(null, null);
	private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dumy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dumy);

	public boolean put(E item) {
		Node<E> newNode = new Node<E>(item, null);

		while (true) {
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();

			if (curTail == tail.get()) {
				if (tailNext != null) {
					// 队列处于中间状态，推进尾节点
					tail.compareAndSet(curTail, tailNext);
				} else {
					// 处于稳定状态，尝试插入新节点
					if (curTail.next.compareAndSet(null, newNode)) {
						// 插入成功，尝试推进尾节点
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}

	private static class Node<E> {
		final E item;
		final AtomicReference<Node<E>> next;

		public Node(E item, AtomicReference<Node<E>> next) {
			this.item = item;
			this.next = next;
		}

	}

}
