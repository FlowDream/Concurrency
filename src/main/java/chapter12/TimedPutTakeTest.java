/**
 * 
 */
package chapter12;

import java.util.concurrent.CyclicBarrier;

/**
 * 采用基于栅栏的定时器进行测试
 */
public class TimedPutTakeTest extends PutTakeTest {
	private BarrierTimer timer = new BarrierTimer();

	public TimedPutTakeTest(int capacity, int nTrials, int nPairs) {
		super(capacity, nTrials, nPairs);
		barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
	}

	@Override
	void test() {
		try {
			timer.clear();

			for (int i = 0; i < nPairs; i++) {
				pool.execute(new PutTakeTest.Producer());
				pool.execute(new PutTakeTest.Consumer());
			}

			barrier.await();
			barrier.await();

			long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
			System.out.print("Throughput: " + nsPerItem + " ns/item");
			System.out.println(putSum.get() == takeSum.get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		int tpt = 100000;

		for (int cap = 1; cap <= 1000; cap *= 10) {
			System.out.println("Capacity: " + cap);
			for (int pairs = 1; pairs <= 128; pairs *= 2) {
				TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
				System.out.print("Pairs: " + pairs + "\t");
				t.test();
				System.out.print("\t");
				Thread.sleep(1000);
				t.test();
				System.out.println();
				Thread.sleep(1000);
			}
		}
		PutTakeTest.pool.shutdown();
	}
}
