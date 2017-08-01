/**
 * 
 */
package chapter05;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 桌面搜索应用程序中的生产者任务和消费者任务
 */
public class DesktopSearch {

	public static void main(String[] args) {
		BlockingQueue<File> fileQueue = new ArrayBlockingQueue<>(1000);
		File root = new File("D:/apache-tomcat-8.0.26");
		String keywords = "server.xml";

		FileCrawler fileCrawlerThread = new FileCrawler(fileQueue, root, keywords);
		fileCrawlerThread.start();

		Indexer indexerThread = new Indexer(fileQueue, keywords);
		indexerThread.start();
	}

	private static class FileCrawler extends Thread {
		private final BlockingQueue<File> fileQueue;
		private final File root;
		private final String keywords;

		public FileCrawler(BlockingQueue<File> fileQueue, File root, String keywords) {
			super();
			this.fileQueue = fileQueue;
			this.root = root;
			this.keywords = keywords;
		}

		@Override
		public void run() {
			try {
				crawl(root);
			} catch (InterruptedException e) {
				// 恢复被中断的状态
				Thread.currentThread().interrupt();
			}
		}

		private void crawl(File root) throws InterruptedException {
			File[] entries = root.listFiles();

			if (null != entries) {
				for (File file : entries) {
					if (file.isDirectory()) {
						crawl(file);
					} else if (keywords.equals(file.getName())) {
						fileQueue.put(file);
					}
				}
			}
		}
	}

	private static class Indexer extends Thread {
		private final BlockingQueue<File> fileQueue;
		private final String keywords;

		public Indexer(BlockingQueue<File> fileQueue, String keywords) {
			super();
			this.fileQueue = fileQueue;
			this.keywords = keywords;
		}

		@Override
		public void run() {
			try {
				while (true) {
					File file = fileQueue.take();
					if (keywords.equals(file.getName())) {
						System.out.println(file.getPath());
						Thread.currentThread().interrupt();
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
