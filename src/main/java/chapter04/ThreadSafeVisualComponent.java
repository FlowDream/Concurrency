/**
 * 
 */
package chapter04;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ���̰߳�ȫ��ί�и����״̬����
 */
public class ThreadSafeVisualComponent {
	private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
	private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

	public void addKeyListener(KeyListener key) {
		keyListeners.add(key);
	}

	public void addMouseListener(MouseListener mouse) {
		mouseListeners.add(mouse);
	}

	public void removeKeyListener(KeyListener key) {
		keyListeners.remove(key);
	}

	public void removeMouseListener(MouseListener mouse) {
		mouseListeners.remove(mouse);
	}

	private static class KeyListener {
	}

	private static class MouseListener {
	}
}
