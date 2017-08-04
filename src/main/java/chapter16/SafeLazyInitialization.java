/**
 * 
 */
package chapter16;

/**
 * �̰߳�ȫ���ӳٳ�ʼ��
 */
public class SafeLazyInitialization {
	private static Resource resource;

	public static synchronized Resource getInstance() {
		if (resource == null) {
			resource = new Resource();
		}

		return resource;
	}

	private static class Resource {

	}
}
