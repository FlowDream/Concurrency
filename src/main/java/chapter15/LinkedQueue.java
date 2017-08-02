/**
 * 
 */
package chapter15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Michael-Scott �������㷨�в����㷨
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
					// ���д����м�״̬���ƽ�β�ڵ�
					tail.compareAndSet(curTail, tailNext);
				} else {
					// �����ȶ�״̬�����Բ����½ڵ�
					if (curTail.next.compareAndSet(null, newNode)) {
						// ����ɹ��������ƽ�β�ڵ�
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
