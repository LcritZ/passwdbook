/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author 郭  璞
 *
 */
public class utiltest {

	@Test
	public void test() {
		String plaintext = "PRCStylewarmup";
		
		String ciphertext = EncyptUtil.encode(plaintext);
		
		System.out.println(ciphertext);
		
		String result = EncyptUtil.decode(ciphertext);
		System.out.println(result);
	}

}
