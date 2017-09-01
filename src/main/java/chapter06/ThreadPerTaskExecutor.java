/**
 * 
 */
package chapter06;

import java.util.concurrent.Executor;

/**
 * 为每个请求启动一个新线程的 Executor
 */
public class ThreadPerTaskExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}

}
