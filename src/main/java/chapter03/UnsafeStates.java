/**
 * 
 */
package chapter03;

/**
 * 使内部的可变状态逸出
 * 
 * @author hzguantao
 *
 */
public class UnsafeStates {

	private String[] states = new String[] { "AK", "AL" };

	public String[] getStates() {
		return states;
	}
}
