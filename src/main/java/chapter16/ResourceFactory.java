/**
 * 
 */
package chapter16;

/**
 * 延长初始化占位类模式
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
