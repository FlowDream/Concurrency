/**
 * 
 */
package chapter10;

import javax.naming.InsufficientResourcesException;

/**
 * ¶¯Ì¬µÄË³Ðò
 */
public class DynamicOrderDeadlock {

	public static void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount)
			throws InsufficientResourcesException {

		synchronized (fromAccount) {
			synchronized (toAccount) {
				if (fromAccount.getBalance().compareTo(amount) < 0) {
					throw new InsufficientResourcesException();
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}

	}

	public static class Account {
		private DollarAmount balance;

		void debit(DollarAmount d) {
			balance = balance.subtract(d);
		}

		void credit(DollarAmount d) {
			balance = balance.add(d);
		}

		public DollarAmount getBalance() {
			return balance;
		}

	}

	public static class DollarAmount implements Comparable<DollarAmount> {

		public DollarAmount(int amount) {

		}

		public DollarAmount add(DollarAmount d) {
			return null;
		}

		public DollarAmount subtract(DollarAmount d) {
			return null;
		}

		public int compareTo(DollarAmount dollarAmount) {
			return 0;
		}
	}
}
