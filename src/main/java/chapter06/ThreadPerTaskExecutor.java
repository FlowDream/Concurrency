/**
 * 
 */
package chapter06;

import java.util.concurrent.Executor;

/**
 * Ϊÿ����������һ�����̵߳� Executor
 */
public class ThreadPerTaskExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}

}
