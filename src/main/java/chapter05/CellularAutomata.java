/**
 * 
 */
package chapter05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过 CyclicBarrier 协调细胞自动衍生系统中的计算
 */
public class CellularAutomata {
	private final Board mainBoard;
	private final CyclicBarrier barrier;
	private final Worker[] worker;

	public CellularAutomata(Board mainBoard) {
		this.mainBoard = mainBoard;
		int count = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(count, new Runnable() {

			@Override
			public void run() {
				mainBoard.commitNewValues();
			}
		});

		this.worker = new Worker[count];

		for (int i = 0; i < worker.length; i++) {
			worker[i] = new Worker(mainBoard.getSubBoard(count, i));
		}
	}

	public void start() {
		for (int i = 0; i < worker.length; i++) {
			new Thread(worker[i]).start();
		}

		mainBoard.waitForConvergence();
	}

	private class Worker implements Runnable {
		private final Board board;

		public Worker(Board board) {
			this.board = board;
		}

		@Override
		public void run() {
			while (!board.hasConverged()) {
				for (int x = 0; x < board.getMaxX(); x++) {
					for (int y = 0; y < board.getMaxY(); y++) {
						board.setNewValue(x, y, computeValue(x, y));
					}
				}

				try {
					barrier.await();
				} catch (InterruptedException ex) {
					return;
				} catch (BrokenBarrierException ex) {
					return;
				}
			}
		}

		private int computeValue(int x, int y) {
			// Compute the new value that goes in (x,y)
			return 0;
		}
	}

	interface Board {
		int getMaxX();

		int getMaxY();

		int getValue(int x, int y);

		int setNewValue(int x, int y, int value);

		void commitNewValues();

		boolean hasConverged();

		void waitForConvergence();

		Board getSubBoard(int numPartitions, int index);
	}
}
