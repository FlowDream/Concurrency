/**
 * 
 */
package chapter04;

/**
 * 与 Java.awt.Point 类似的可变 Point 类（不要这么做）
 */
public class NotThreadSafeMutablePoint {
	public int x, y;

	public NotThreadSafeMutablePoint() {
		x = 0;
		y = 0;
	}

	public NotThreadSafeMutablePoint(NotThreadSafeMutablePoint p) {
		this.x = p.x;
		this.y = p.y;
	}

}
