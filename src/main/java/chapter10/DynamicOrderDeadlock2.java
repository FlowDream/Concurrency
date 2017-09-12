/**
 * 
 */
package chapter10;

import javax.naming.InsufficientResourcesException;

/**
 * Õ®π˝À¯À≥–Ú¿¥±‹√‚À¿À¯
 */
public class DynamicOrderDeadlock2 {
	private static final Object tieLock = new Object();

	public void transferMoney(final Account fromAccount, final Account toAccount, final DollarAmount amount)
			throws InsufficientResourcesException {
		class Hellper {
			public void transfer() throws InsufficientResourcesException {
				if (fromAccount.getBalance().compareTo(amount) < 0) {
					throw new InsufficientResourcesException();
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}

		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);

		if (fromHash < toHash) {
			synchronized (fromAccount) {
				synchronized (toAccount) {
					new Hellper().transfer();
				}
			}
		} else if (fromHash > toHash) {
			synchronized (toAccount) {
				synchronized (fromAccount) {
					new Hellper().transfer();
				}
			}
		} else {
			synchronized (tieLock) {
				synchronized (fromAccount) {
					synchronized (toAccount) {
						new Hellper().transfer();
					}
				}
			}
		}
	}

	private static class Account {
		private DollarAmount balance;

		public void debit(DollarAmount amount) {

		}

		public void credit(DollarAmount amount) {

		}

		public DollarAmount getBalance() {
			return balance;
		}

	}

	private static class DollarAmount implements Comparable<DollarAmount> {

		@Override
		public int compareTo(DollarAmount o) {
			return 0;
		}

	}
}
