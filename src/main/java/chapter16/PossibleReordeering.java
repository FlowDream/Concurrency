/**
 * 
 */
package chapter16;

/**
 * 在程序中没有包含足够的同步，可能产生奇怪的结果
 */
public class PossibleReordeering {
	static int x = 0, y = 0;
	static int a = 0, b = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread() {

			@Override
			public void run() {
				a = 1;
				x = b;
			}

		};

		Thread thread2 = new Thread() {

			@Override
			public void run() {
				b = 1;
				y = a;
			}

		};

		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		System.out.println("(" + x + "," + y + ")");
	}
}
