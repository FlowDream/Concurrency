/**
 * 
 */
package chapter04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 将线程安全委托给 ConcurrentHashMap
 */
public class ThreadSafeDelegatingVehicleTracker {
	private final Map<String, ImmutablePoint> locations;
	private final Map<String, ImmutablePoint> unmodifiableMap;

	public ThreadSafeDelegatingVehicleTracker(Map<String, ImmutablePoint> points) {
		this.locations = new ConcurrentHashMap<String, ImmutablePoint>(points);
		this.unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	/**
	 * 返回一个不可修改但时时的车辆视图
	 * 
	 * @return
	 */
	public Map<String, ImmutablePoint> getLoactions() {
		return unmodifiableMap;
	}

	/**
	 * 返回一个不发生变化的车辆视图
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
