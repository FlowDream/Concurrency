/**
 * 
 */
package chapter04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * NumberRange 类并不足以保护它的不变性条件
 */
public class NotThreadSafeNumberRange {
	private final AtomicInteger lower;
	private final AtomicInteger upper;

	public NotThreadSafeNumberRange(int lower, int upper) {
		this.lower = new AtomicInteger(lower);
		this.upper = new AtomicInteger(upper);
	}

	public void setLower(int i) {
		// 注意：不安全的“先检查后执行”
		if (i > upper.get()) {
			throw new IllegalArgumentException();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lower.set(i);
	}

	public void setUpper(int i) {
		// 注意：不安全的“先检查后执行”
		if (i < lower.get()) {
			throw new IllegalArgumentException();
		}

		upper.set(i);
	}

	public boolean isInRange(int i) {
		return (i >= lower.get()) && (i <= upper.get());
	}

	@Override
	public String toString() {
		return "NotThreadSafeNumberRange [lower=" + lower + ", upper=" + upper + "]";
	}

	public static void main(String[] args) {
		NotThreadSafeNumberRange ntsnr = new NotThreadSafeNumberRange(0, 10);

		new Thread() {
			@Override
			public void run() {
				ntsnr.setLower(5);
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				ntsnr.setUpper(4);
			}
		}.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(ntsnr);
	}
}
