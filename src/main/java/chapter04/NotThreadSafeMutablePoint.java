/**
 * 
 */
package chapter04;

/**
 * �� Java.awt.Point ���ƵĿɱ� Point �ࣨ��Ҫ��ô����
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
