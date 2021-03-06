package com.common;
 

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

/**
 * 安全工具,用户加密解密
 * @author xueyinhao
 *
 */
public class SecurityUtils {
	public static String COMMON_KEY = "409b0743ca2d0c50";
	public static Random random = new Random(System.currentTimeMillis());

	public static int randomInt(int max) {
		return (int) (Math.random() * (max));
	}

	public static long randomLong() {
		return random.nextLong();
	}

	public static double randomDouble() {
		return random.nextDouble();
	}

	public static String HmacMD5(String str) {
		Provider sunJce = new com.sun.crypto.provider.SunJCE();
		Security.addProvider(sunJce);

		try {
//			// Generate secret key for HMAC-MD5
//			KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
//			//获取key
//			SecretKey sk = kg.generateKey();
			
			SecretKey sk = (SecretKey) getKey("HmacMD5.key");

			// Get instance of Mac object implementing HMAC-MD5, and
			// initialize it with the above secret key
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(sk);
			byte[] result = mac.doFinal(str.getBytes());

			return dumpBytes(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] desEncrypt(String msg, String salt) {
		if (msg == null)
			msg = "";
		if (salt == null) {
			salt = "ddhdsalt";
		}
		byte[] keyBytes = new byte[8];
		int saltLen = salt.length();
		byte[] saltBytes = salt.getBytes();
		for (int i = 0; i < 8; i++) {
			keyBytes[i] = saltBytes[i % saltLen];
		}

		try {
			DESKeySpec keySpec = new DESKeySpec(keyBytes);
			SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(
					keySpec);
			Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			desCipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] text = msg.getBytes("UTF-8");
			byte[] ciphertext = desCipher.doFinal(text);

			return ciphertext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String desDecrypt(byte[] msg, String salt) {
		if (msg == null)
			return null;
		if (salt == null) {
			salt = "ddhdsalt";
		}
		byte[] keyBytes = new byte[8];
		int saltLen = salt.length();
		byte[] saltBytes = salt.getBytes();
		for (int i = 0; i < 8; i++) {
			keyBytes[i] = saltBytes[i % saltLen];
		}

		try {
			DESKeySpec keySpec = new DESKeySpec(keyBytes);
			SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(
					keySpec);
			Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			desCipher.init(Cipher.DECRYPT_MODE, key);
			byte[] deciphertext = desCipher.doFinal(msg);

			return new String(deciphertext, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dumpBytes(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			/*
			 * if (i%32 == 0 && i!=0) { sb.append("\n"); }
			 */
			String s = Integer.toHexString(bytes[i]);
			if (s.length() < 2) {
				s = "0" + s;
			}
			if (s.length() > 2) {
				s = s.substring(s.length() - 2);
			}
			sb.append(s);
		}
		return sb.toString();
	}

	public static byte[] parseBytes(String str) {
		try {
			int len = str.length() / 2;
			if (len <= 2) {
				return new byte[] { Byte.parseByte(str) };
			}
			byte[] arr = new byte[len];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (byte) Integer.parseInt(
						str.substring(i * 2, i * 2 + 2), 16);
			}
			return arr;
		} catch (Exception e) {
			return new byte[0];
		}
	}

	/**
	 * 加密
	 * 
	 * @param encrypt_value
	 *            被加密的字符串
	 * @param encrypt_key
	 *            加密的密钥
	 * @return
	 */
	public static String encryptAsString(String encrypt_value,
			String encrypt_key) {
		return dumpBytes(desEncrypt(encrypt_value, encrypt_key));
	}

	/**
	 * 解密
	 * 
	 * @param encrypt_value
	 *            要解密的字符串
	 * @param encrypt_key
	 *            密钥
	 * @return
	 */
	public static String desEncryptAsString(String encrypt_value,
			String encrypt_key) {
		return desDecrypt(parseBytes(encrypt_value), encrypt_key);
	}

	public static String desEncryptAsString(String encrypt_value) {
		return desEncryptAsString(encrypt_value, COMMON_KEY);
	}

	public static String encryptAsString(String encrypt_value) {
		return dumpBytes(desEncrypt(encrypt_value, COMMON_KEY));
	}

	public static String getHashPath(long parentId) {

		String id = Long.toString(parentId);
		/*
		 * if(id.length()<6) { int m = id.length() ; for(int i = 0 ;i<(6-m);i++)
		 * { id ="0"+id ; } } else { id =
		 * id.substring(id.length()-6,id.length()) ; }
		 */
		// System.out.println("before hash::"+id) ;

		byte[] buff = id.getBytes();
		String curr = "0981276345";
		int len = curr.length();
		int[] res = new int[8];
		int iter = 0;
		for (int i = 0; i < 8; i++) {
			if (buff.length > i && i < 6) {
				res[i] = (buff[i] + buff[buff.length - 1 - i]) % 256;
			} else {
				res[i] = Integer.parseInt(curr.substring(len - iter - 3, len
						- iter)) % 256;
				iter++;
			}
		}
		String str = "";
		str += Integer.toHexString((int) res[0])
				+ Integer.toHexString((int) res[1])
				+ Integer.toHexString((int) res[2])
				+ Integer.toHexString((int) res[3])
				+ Integer.toHexString((int) res[4])
				+ Integer.toHexString((int) res[5])
				+ Integer.toHexString((int) res[6])
				+ Integer.toHexString((int) res[7]);
		str += parentId;
		System.out.println("after hash::" + str);
		return str;

	}

	public static String createRandomPassword() {
		return (System.currentTimeMillis() + "").substring(5, 13);
	}
	
	public static void createKey(String keyPath) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			SecureRandom sr = new SecureRandom();
			//KeyGenerator kg = KeyGenerator.getInstance("DES");
			KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
			kg.init(sr);
			fos = new FileOutputStream(keyPath);
			oos = new ObjectOutputStream(fos);

			Key key = kg.generateKey();
			oos.writeObject(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(oos);
		}
	}
	
	public static Key getKey(String keyPath) {
		Key kp = null;
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = ClassLoader.getSystemClassLoader().getResourceAsStream(keyPath);
			return getKey(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(ois);
		}
		return kp;
	}
	
	public static Key getKey(InputStream is) {
		Key key = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(is);
			key = (Key) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(ois);
		}
		return key;
	}
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//createKey("d:/HmacMD5.key");
		

		Map<String, Object> mapData = new HashMap<String, Object>();
		
		Map<String, String> map = new HashMap<String, String>();
		 map.put("name","姓名");
		 map.put("age","年龄");
		 map.put("card_id", "310101198808088888");
		 map.put("zip", "200000");
		 map.put("address", "上海");
		 
		 
		 mapData.put("data", map);
		 mapData.put("ts", "" + System.currentTimeMillis());
		 mapData.put("sign", "signData");
		 
		 
		 String desString = JSON.toJSONString(mapData);
		 System.out.println("加密前："+desString);
		 String desinfo = SecurityUtils.encryptAsString(desString, SecurityUtils.COMMON_KEY);
		 System.out.println("加密后："+desinfo);

		// 解密
		String result = SecurityUtils.desEncryptAsString(desinfo, SecurityUtils.COMMON_KEY);
		System.out.println("解密后:"+result);
 
		
		System.out.println(SecurityUtils.getHashPath(1000L));
		System.out.println(SecurityUtils.HmacMD5("didihaodai"));
		System.out.println(SecurityUtils.HmacMD5("didihaodai"));
		
		String desinfo2 = "75b8870d052784ecf3930769bbd010867d2b08b8d66f4b6e1774044fb878f0802a87b2579192ef2fd371698b577c5b13e7174149508b4d9c089a15f3f489f5959789bf684a4dabbac322e0c20a6026beb225cb6db4cce793e05deebad9bfe7dc2a25363dac07b1c0e4f940bbb07dfc713cc293d354b6b1bb7e432cf60ad8da10ff49d3960317c6095dc63f3de8964313fdb5e7d9ca2939cd";
		
		String result2 = SecurityUtils.desEncryptAsString(desinfo2, SecurityUtils.COMMON_KEY);
		System.out.println("解密后："+result2);
		
		
	}

}
