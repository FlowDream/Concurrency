/**
 * 
 */
package chapter05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用 FutureTask 来提前加载稍后需要的数据
 */
public class PreLoader {
	private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {

		@Override
		public ProductInfo call() throws Exception {
			return loadProductInfo();
		}

	});

	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws InterruptedException, ExecutionException {
		return future.get();
	}

	ProductInfo loadProductInfo() throws DataLoadException {
		return null;
	}

	interface ProductInfo {

	}
}

class DataLoadException extends Exception {
	private static final long serialVersionUID = 1957558305741432589L;
}
