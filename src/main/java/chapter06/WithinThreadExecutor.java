/**
 * 
 */
package chapter06;

import java.util.concurrent.Executor;

/**
 * �ڵ����߳�����ͬ����ʽִ����������� Executor
 */
public class WithinThreadExecutor implements Executor {

	public void execute(Runnable command) {
		command.run();
	}

}
