/**
 * 
 */
package chapter04;

/**
 * ͨ��һ��˽����������״̬
 */
public class ThreadSafePrivateLock {

	private final Object myLock = new Object();

	@SuppressWarnings("unused")
	private Widget widget;

	void doSomeMethod() {
		synchronized (myLock) {
			// ���ʻ��޸� Widget ��״̬
		}
	}

	private class Widget {

	}
}
