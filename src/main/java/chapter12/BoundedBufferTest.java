/**
 * 
 */
package chapter12;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * BoundedBuffer 的基本单元测试
 */
public class BoundedBufferTest {

	/**
	 * 新建缓存为空，而不是满的
	 */
	@Test
	public void testIsEmptyWhenConstucted() {
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

	/**
	 * 将 N 个元素插入到容量为 N 的缓存中（这个过程应该可以成功，并且不会阻塞），然后测试缓存是否已经填满。
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
	 * 测试阻塞行为以及对中断的响应性
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
