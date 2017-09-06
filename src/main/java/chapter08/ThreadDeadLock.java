/**
 * 
 */
package chapter08;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * �ڵ��߳� Executor ������������
 */
public class ThreadDeadLock {
	private final ExecutorService exec = Executors.newSingleThreadExecutor();

	public class RenderPageTask implements Callable<String> {

		@Override
		public String call() throws Exception {
			Future<String> header;
			Future<String> footer;

			header = exec.submit(new LoadFileTask("header.html"));
			footer = exec.submit(new LoadFileTask("footer.html"));

			String page = renderBody();
			// ���������� ���� ���������ڵȴ�������Ľ��
			return header.get() + page + footer.get();
		}

		private String renderBody() {
			return "";
		}
	}

	public class LoadFileTask implements Callable<String> {
		@SuppressWarnings("unused")
		private final String fileName;

		public LoadFileTask(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public String call() throws Exception {
			return "";
		}
	}

}
