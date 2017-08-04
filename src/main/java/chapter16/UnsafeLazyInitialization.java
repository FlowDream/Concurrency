/**
 * 
 */
package chapter16;

/**
 * ����ȫ���ӳٳ�ʼ��
 */
public class UnsafeLazyInitialization {
	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			resource = new Resource();
		}

		return resource;
	}

	private static class Resource {

	}
}
