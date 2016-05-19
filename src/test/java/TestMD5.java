import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;


public class TestMD5 {
	
	@Test 
	public void testMD() {  
		String plainText = "test";
		String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(re_md5);
        //return re_md5;
	}
}
