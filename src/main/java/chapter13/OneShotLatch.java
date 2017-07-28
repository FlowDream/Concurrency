/**
 * 
 */
package chapter13;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * ʹ�� AbstractQueuedSynchronizer ʵ�ֵĶ�Ԫ����
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
			// ��������ǿ���(state == 1)����ô����������ɹ�������ʧ��
			return getState() == 1 ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			setState(1);
			return true;
		}

	}
}
