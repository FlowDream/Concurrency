/**
 * 
 */
package chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * 在相互协作对象之间的锁顺序死锁
 */
public class CooperatingDeadlock {
	class Taxi {
		private Point location;
		private Point destination;
		private final Dispatcher dispatcher;

		public Taxi(Dispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		public synchronized Point getLocation() {
			return location;
		}

		public synchronized void setLocation(Point location) {
			this.location = location;

			if (location.equals(destination)) {
				dispatcher.notifyAvailable(this);
			}

		}

		public synchronized Point getDestination() {
			return destination;
		}

		public synchronized void setDestination(Point destination) {
			this.destination = destination;
		}

	}

	class Dispatcher {
		private Set<Taxi> taxis;
		private Set<Taxi> availableTaxis;

		public Dispatcher() {
			this.taxis = new HashSet<>();
			this.availableTaxis = new HashSet<>();
		}

		public synchronized void notifyAvailable(Taxi taxi) {
			availableTaxis.add(taxi);
		}

		public synchronized Image getImage() {
			Image image = new Image();

			for (Taxi taxi : taxis) {
				image.drawMarker(taxi.getLocation());
			}

			return image;
		}
	}

	class Image {
		public void drawMarker(Point p) {

		}
	}
}
