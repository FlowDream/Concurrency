/**
 * 
 */
package chapter15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过 CAS 类维持包含多个变量的不变性条件
 */
public class CasNumberRange {
	private final AtomicReference<IntPari> values = new AtomicReference<>();

	public int getLower() {
		return values.get().lower;
	}

	public int getUpper() {
		return values.get().upper;
	}

	private static class IntPari {
		final int lower;
		final int upper;

		public IntPari(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}

	public void setLower(int i) {
		while (true) {
			IntPari oldValue = values.get();

			if (i > oldValue.lower) {
				throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
			}

			IntPari newValue = new IntPari(i, oldValue.upper);

			if (values.compareAndSet(oldValue, newValue)) {
				return;
			}
		}
	}

	public void setUpper(int i) {
		while (true) {
			IntPari oldValue = values.get();

			if (i < oldValue.upper) {
				throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
			}

			IntPari newValue = new IntPari(oldValue.lower, i);

			if (values.compareAndSet(oldValue, newValue)) {
				return;
			}
		}
	}
}
