/**
 * 
 */
package chapter04;

/**
 * 通过一个私有锁来保护状态
 */
public class ThreadSafePrivateLock {

	private final Object myLock = new Object();

	@SuppressWarnings("unused")
	private Widget widget;

	void doSomeMethod() {
		synchronized (myLock) {
			// 访问或修改 Widget 的状态
		}
	}

	private class Widget {

	}
}
