/**
 * 
 */
package chapter07;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ���쳣д����־�� UncaughtExceptionHandler
 */
public class UEHLogger implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "Thread terminated with exception:" + t.getName(), e);
	}

}
