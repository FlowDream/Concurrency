/**
 * 
 */
package chapter04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪
 */
public class ThreadSafeMonitorVehicleTracker {
	private final Map<String, NotThreadSafeMutablePoint> locations;

	public ThreadSafeMonitorVehicleTracker(Map<String, NotThreadSafeMutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	public synchronized Map<String, NotThreadSafeMutablePoint> getLocations() {
		return deepCopy(locations);
	}

	public synchronized NotThreadSafeMutablePoint getLocation(String id) {
		NotThreadSafeMutablePoint loc = locations.get(id);
		return loc == null ? null : new NotThreadSafeMutablePoint(loc);
	}

	public synchronized void setLocation(String id, int x, int y) {
		NotThreadSafeMutablePoint loc = locations.get(id);

		if (null == loc) {
			throw new IllegalArgumentException();
		}

		loc.x = x;
		loc.y = y;
	}

	private static Map<String, NotThreadSafeMutablePoint> deepCopy(Map<String, NotThreadSafeMutablePoint> m) {
		Map<String, NotThreadSafeMutablePoint> result = new HashMap<>();

		for (String id : m.keySet()) {
			result.put(id, new NotThreadSafeMutablePoint(m.get(id)));
		}

		return Collections.unmodifiableMap(result);
	}

}
