/**
 * 
 */
package chapter08;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * �����߳���Ϊ��������Ϊ�߳�ָ�����ƣ������Զ��� UncaughtExceptionHandler �� Logger
 * ��д����Ϣ��ά��һЩͳ����Ϣ�������ж��ٸ��̱߳����������٣��� �Լ����̱߳�����������ֹʱ�ѵ�����Ϣд����־��
 * 
 * @author hzguantao
 *
 */
public class MyAppThread extends Thread {
	private static final String DEFAULT_NAME = "MyAppThread";
	private static volatile boolean debugLifecycle = false;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger logger = Logger.getAnonymousLogger();

	public MyAppThread(Runnable target) {
		this(target, DEFAULT_NAME);
	}

	public MyAppThread(Runnable target, String name) {
		super(target, name + "-" + created.incrementAndGet());
		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
			}
		});
	}

	@Override
	public void run() {
		// ���� debug ��־��ȷ��һ�µ�ֵ
		boolean debug = debugLifecycle;
		if (debug) {
			logger.log(Level.FINE, "Create" + getName());
		}

		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			
			if (debug) {
				logger.log(Level.FINE, "Exiting" + getName());
			}
		}
	}

	public static boolean getDebug() {
		return debugLifecycle;
	}

	public static void setDebug(boolean isDebug) {
		MyAppThread.debugLifecycle = isDebug;
	}

	public static AtomicInteger getThreadsCreated() {
		return created;
	}

	public static AtomicInteger getThreadsAlive() {
		return alive;
	}
}
