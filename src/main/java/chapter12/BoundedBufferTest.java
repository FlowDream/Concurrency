/**
 * 
 */
package chapter12;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * BoundedBuffer �Ļ�����Ԫ����
 */
public class BoundedBufferTest {

	/**
	 * �½�����Ϊ�գ�����������
	 */
	@Test
	public void testIsEmptyWhenConstucted() {
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

	/**
	 * �� N ��Ԫ�ز��뵽����Ϊ N �Ļ����У��������Ӧ�ÿ��Գɹ������Ҳ�����������Ȼ����Ի����Ƿ��Ѿ�������
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testIsFullAfterPuts() throws InterruptedException {
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

		for (int i = 0; i < 10; i++) {
			bb.put(i);
		}

		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}

	/**
	 * ����������Ϊ�Լ����жϵ���Ӧ��
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testTakeBlocksWhenEmpty() throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

		Thread taker = new Thread() {

			@Override
			public void run() {
				try {
					bb.take();
					fail();
				} catch (InterruptedException e) {

				}
			}
		};

		try {
			taker.start();
			Thread.sleep(2000);
			taker.interrupt();
			taker.join(2000);
			assertFalse(taker.isAlive());
		} catch (Exception e) {
			fail();
		}
	}
}
