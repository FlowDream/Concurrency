/**
 * 
 */
package chapter11;

import java.util.List;
import java.util.Vector;

/**
 * �ڴ�ͬ��
 */
public class MemorySynchronized {

	/**
	 * û�����õ�ͬ��
	 */
	public void testMemorySyn() {

		synchronized (new Object()) {

		}

	}

	/**
	 * ��ͨ���������Ż�ȥ������ȡ����
	 * 
	 * @return
	 */
	public String getStoogeNames() {
		List<String> stooges = new Vector<>();
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
		return stooges.toString();
	}

}
