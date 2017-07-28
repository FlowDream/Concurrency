/**
 * 
 */
package chapter13;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用 AbstractQueuedSynchronizer 实现的二元闭锁
 */
public class OneShotLatch {
	private final Sync sync = new Sync();

	public void signal() {
		sync.releaseShared(0);
	}

	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}

	private static class Sync extends AbstractQueuedSynchronizer {

		private static final long serialVersionUID = -8193676011970935173L;

		@Override
		protected int tryAcquireShared(int arg) {
			// 如果闭锁是开的(state == 1)，那么这个操作将成功，否则将失败
			return getState() == 1 ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			setState(1);
			return true;
		}

	}
}
