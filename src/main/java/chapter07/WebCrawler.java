/**
 * 
 */
package chapter07;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Õ¯“≥≈¿≥Ê
 */
public abstract class WebCrawler {
	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawl = new HashSet<>();
	private final ConcurrentHashMap<URL, Boolean> seen = new ConcurrentHashMap<>();
	private static final long TIMEOUT = 500;
	private static final TimeUnit UNIT = TimeUnit.MICROSECONDS;

	public WebCrawler(URL startURL) {
		urlsToCrawl.add(startURL);
	}

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());

		for (URL url : urlsToCrawl) {
			submitCrawTask(url);
		}

		urlsToCrawl.clear();
	}

	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());

			if (exec.awaitTermination(TIMEOUT, UNIT)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	private void submitCrawTask(URL url) {
		exec.execute(new CrawlTask(url));
	}

	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable runnable : uncrawled) {
			CrawlTask task = (CrawlTask) runnable;
			urlsToCrawl.add(task.getPage());
		}
	}

	protected abstract List<URL> processPage(URL url);

	@SuppressWarnings("unused")
	private class CrawlTask implements Runnable {
		private final URL url;
		private int count = 1;

		CrawlTask(URL url) {
			this.url = url;
		}

		@Override
		public void run() {
			for (URL url : processPage(url)) {
				if (Thread.currentThread().isInterrupted()) {
					return;
				}

				submitCrawTask(url);
			}
		}

		boolean alreadyCrawled() {
			return seen.putIfAbsent(url, true) != null;
		}

		void markUncrawled() {
			seen.remove(url);
			System.out.printf("marking %s uncrawled%n", url);
		}

		public URL getPage() {
			return url;
		}

	}
}
