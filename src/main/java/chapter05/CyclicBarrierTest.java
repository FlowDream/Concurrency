/**
 * 
 */
package chapter05;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINE_PARTICIPANT = 2000;

		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		Results results = new Results(ROWS);
		Grouper grouper = new Grouper(results);

		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		Searcher[] searchers = new Searcher[PARTICIPANTS];

		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINE_PARTICIPANT, (i * LINE_PARTICIPANT) + LINE_PARTICIPANT, mock, results,
					5, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}

		System.out.printf("Main: The main thread has finished.\n");
	}

	private static class MatrixMock {
		private int[][] data;

		public MatrixMock(int size, int length, int number) {
			int counter = 0;
			data = new int[size][length];
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < length; j++) {
					data[i][j] = random.nextInt(10);
					if (data[i][j] == number) {
						counter++;
					}
				}
			}
			System.out.printf("Mock: There are %d occurrences of number %d " + "in generated data.\n", counter, number);
		}

		public int[] getRow(int row) {
			if (row >= 0 && row < data.length) {
				return data[row];
			} else {
				return null;
			}
		}
	}

	private static class Results {
		private int[] data;

		public Results(int size) {
			super();
			this.data = new int[size];
		}

		public void setData(int index, int value) {
			data[index] = value;
		}

		public int[] getData() {
			return data;
		}
	}

	private static class Searcher implements Runnable {
		private int firstRow;
		private int lastRow;
		private MatrixMock mock;
		private Results results;
		private int number;
		private final CyclicBarrier barrier;

		public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number,
				CyclicBarrier barrier) {
			this.firstRow = firstRow;
			this.lastRow = lastRow;
			this.mock = mock;
			this.results = results;
			this.number = number;
			this.barrier = barrier;
		}

		@Override
		public void run() {
			int counter;
			System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow,
					lastRow);

			for (int i = firstRow; i < lastRow; i++) {
				int[] row = mock.getRow(i);
				counter = 0;

				for (int j = 0; j < row.length; j++) {
					if (row[j] == number) {
						counter++;
					}
				}

				results.setData(i, counter);
			}

			System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Grouper implements Runnable {
		private Results results;

		public Grouper(Results results) {
			this.results = results;
		}

		@Override
		public void run() {
			int finalResult = 0;
			System.out.printf("Grouper: Processing results...\n");
			int[] data = results.getData();

			for (int number : data) {
				finalResult += number;
			}

			System.out.printf("Grouper: Total result: %d.\n", finalResult);
		}
	}
}
