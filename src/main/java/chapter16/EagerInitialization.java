/**
 * 
 */
package chapter16;

/**
 * ��ǰ��ʼ��
 */
public class EagerInitialization {
	private static Resource resource = new Resource();

	public static synchronized Resource getInstance() {
		return resource;
	}

	private static class Resource {

	}
}
