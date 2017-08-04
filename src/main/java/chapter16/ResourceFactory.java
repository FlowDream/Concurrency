/**
 * 
 */
package chapter16;

/**
 * �ӳ���ʼ��ռλ��ģʽ
 */
public class ResourceFactory {
	
	private static class ResourceHolder {
		public static Resource resource = new Resource();
	}

	public static synchronized Resource getInstance() {
		return ResourceHolder.resource;
	}

	private static class Resource {

	}
}
