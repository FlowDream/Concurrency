/**
 * 
 */
package chapter04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * ���̰߳�ȫί�и� ConcurrentHashMap
 */
public class ThreadSafeDelegatingVehicleTracker {
	private final Map<String, ImmutablePoint> locations;
	private final Map<String, ImmutablePoint> unmodifiableMap;

	public ThreadSafeDelegatingVehicleTracker(Map<String, ImmutablePoint> points) {
		this.locations = new ConcurrentHashMap<String, ImmutablePoint>(points);
		this.unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	/**
	 * ����һ�������޸ĵ�ʱʱ�ĳ�����ͼ
	 * 
	 * @return
	 */
	public Map<String, ImmutablePoint> getLoactions() {
		return unmodifiableMap;
	}

	/**
	 * ����һ���������仯�ĳ�����ͼ
	 * 
	 * @return
	 */
	public Map<String, ImmutablePoint> getLoactionsView() {
		return Collections.unmodifiableMap(new HashMap<>(locations));
	}

	public ImmutablePoint getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
		if (locations.replace(id, new ImmutablePoint(x, y)) == null) {
			throw new IllegalArgumentException();
		}
	}
}
