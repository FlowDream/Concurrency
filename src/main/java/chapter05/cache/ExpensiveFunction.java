/**
 * 
 */
package chapter05.cache;

import java.math.BigInteger;

/**
 *
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

	@Override
	public BigInteger compute(String arg) throws InterruptedException {
		// 经过长时间的计算
		return new BigInteger(arg);
	}

}
