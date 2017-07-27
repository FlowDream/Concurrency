/**
 * 
 */
package chapter04;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全发布底层状态的车辆追踪器
 */
public class ThreadSafePublishingVehicleTracker {
	private final Map<String, ThreadSafePoint> locations;
	private final Map<String, ThreadSafePoint> unmodifiableMap;

	public ThreadSafePublishingVehicleTracker(Map<String, ThreadSafePoint> locations) {
		this.locations = new ConcurrentHashMap<String, ThreadSafePoint>(locations);
		this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}

	public Map<String, ThreadSafePoint> getLoactions() {
		return this.unmodifiableMap;
	}

	public ThreadSafePoint getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException();
		}

		locations.get(id).set(x, y);
	}
}
