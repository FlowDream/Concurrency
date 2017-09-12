/**
 * 
 */
package chapter10;

/**
 * ¼òµ¥µÄËøË³ÐòËÀËø
 */
public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();

	public void leftRight() {
		synchronized (left) {
			synchronized (right) {
				doSomething();
			}
		}
	}

	public void rightLeft() {
		synchronized (right) {
			synchronized (left) {
				doSomething();
			}
		}
	}

	private void doSomething() {

	}
}
