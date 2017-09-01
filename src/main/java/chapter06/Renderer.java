/**
 * 
 */
package chapter06;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 使用 CompletionService 使页面元素在下载完成后立即显示出来
 */
public abstract class Renderer {
	private final ExecutorService executor;

	public Renderer(ExecutorService executor) {
		this.executor = executor;
	}

	void renderPage(CharSequence source) {
		List<ImageInfo> info = scanForImageInfo(source);
		
		CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
		
		for (ImageInfo imageInfo : info) {
			completionService.submit(new Callable<Renderer.ImageData>() {
				@Override
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		
		renderText(source);
		
		try {
			for (int i = 0, n = info.size(); i < n; i++) {
				Future<ImageData> future = completionService.take();
				ImageData imageData = future.get();
				renderImage(imageData);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
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
