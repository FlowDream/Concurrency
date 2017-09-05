/**
 * 
 */
package chapter07;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过 newTaskFor 将非标准的取消操作封装在一个任务中
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
	private Socket socket;

	public synchronized void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public synchronized void cancel() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {

		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				SocketUsingTask.this.cancel();
				return super.cancel(mayInterruptIfRunning);
			}

		};
	}

}

interface CancellableTask<T> extends Callable<T> {
	void cancel();

	RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor {
	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask) {
			return ((CancellableTask<T>) callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}
}
