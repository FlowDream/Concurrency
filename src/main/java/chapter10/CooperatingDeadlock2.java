/**
 * 
 */
package chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * ͨ�������������������໥Э���Ķ���֮���������
 */
public class CooperatingDeadlock2 {
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

		public void setLocation(Point location) {
			boolean reachedDestination;

			synchronized (this) {
				this.location = location;
				reachedDestination = location.equals(destination);
			}

			if (reachedDestination) {
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

		public Image getImage() {
			Set<Taxi> copy;

			synchronized (this) {
				copy = new HashSet<>(taxis);
			}

			Image image = new Image();

			for (Taxi taxi : copy) {
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
