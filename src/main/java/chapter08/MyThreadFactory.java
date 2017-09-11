/**
 * 
 */
package chapter08;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 */
public class MyThreadFactory implements ThreadFactory {
	private final String name;

	public MyThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable runnable) {
		return new MyAppThread(runnable, name);
	}
}
