/**
 * 
 */
package chapter06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ʹ�� Future �ȴ�ͼ������
 */
public abstract class FutureRenderer {
	private final ExecutorService executor = Executors.newCachedThreadPool();

	void renderPage(CharSequence source) {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
			@Override
			public List<ImageData> call() throws Exception {
				List<ImageData> result = new ArrayList<>();
				for (ImageInfo imageInfo : imageInfos) {
					result.add(imageInfo.downloadImage());
				}
				return result;
			}
		};

		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);
		try {
			List<ImageData> imageData = future.get();
			for (ImageData data : imageData) {
				renderImage(data);
			}
		} catch (InterruptedException e) {
			// ���������߳��ж�״̬
			Thread.currentThread().interrupt();
			// ���ڲ���Ҫ��������ȡ������
			future.cancel(true);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	interface ImageData {
	}

	interface ImageInfo {
		ImageData downloadImage();
	}

	abstract void renderText(CharSequence s);

	abstract List<ImageInfo> scanForImageInfo(CharSequence s);

	abstract void renderImage(ImageData i);
}