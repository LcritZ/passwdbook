/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * @author 郭 璞
 *
 */
public class EncyptUtil {

	public static String encode(String plaintext) {
		try {
			Encoder encoder = Base64.getEncoder();
			byte[] cipherbytes = encoder.encode(plaintext.getBytes());
			return new String(cipherbytes);

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public static String decode(String ciphertext) {
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] plaintextbytes = decoder.decode(ciphertext);
			return new String(plaintextbytes);
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

}
