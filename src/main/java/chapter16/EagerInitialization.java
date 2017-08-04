/**
 * 
 */
package chapter16;

/**
 * 提前初始化
 */
public class EagerInitialization {
	private static Resource resource = new Resource();

	public static synchronized Resource getInstance() {
		return resource;
	}

	private static class Resource {

	}
}
