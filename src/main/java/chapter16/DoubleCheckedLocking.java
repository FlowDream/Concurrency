/**
 * 
 */
package chapter16;

/**
 * ˫�ؼ���������Ҫ��ô����
 */
public class DoubleCheckedLocking {
	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			synchronized (DoubleCheckedLocking.class) {
				if (resource == null) {
					resource = new Resource();
				}
			}
		}
		
		return resource;
	}

	private static class Resource {

	}
}
