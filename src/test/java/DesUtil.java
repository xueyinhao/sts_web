import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import org.apache.commons.io.IOUtils;

public class DesUtil {

	public static void encryptTest() {

	}

	public static void decryptTest() {
		try {
			Key key = getKey("desdb.key");

			java.io.File f = new java.io.File(
					"e:/desdb_qdjsit.properties");
			FileInputStream input = new FileInputStream(f);
			InputStream in = decrypt(input, key);
			byte b[] = new byte[(int) f.length()]; // 创建合适文件大小的数组
			in.read(b); // 读取文件中的内容到b[]数组
			in.close();
			System.out.println(new String(b));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void decryptBbsTest() {
		try {
			Key key = getKey("bbs.key");

			java.io.File f = new java.io.File(
					"e:/bbs-custom-sit.conf");
			FileInputStream input = new FileInputStream(f);
			InputStream in = decrypt(input, key);
			byte b[] = new byte[(int) f.length()]; // 创建合适文件大小的数组
			in.read(b); // 读取文件中的内容到b[]数组
			in.close();
			System.out.println(new String(b));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void encryptDecryptTest() {
		Key key = getKey("desdb.key");
		// try {
		// encrypt("db.properties", "desdb.properties", key);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// createDesKey("e:/deskey.key");

		try {
			System.out.println("key=========== " + key);
			encrypt("db.properties", "e:/desdb_sit3.properties", key);
			java.io.File f = new java.io.File("e:/desdb_sit3.properties");
			FileInputStream input = new FileInputStream(f);
			InputStream in = decrypt(input, key);
			byte b[] = new byte[(int) f.length()]; // 创建合适文件大小的数组
			in.read(b); // 读取文件中的内容到b[]数组
			in.close();
			System.out.println(new String(b));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void encryptDecryptBbsTest() {
		Key key = getKey("bbs.key");

		try {
			System.out.println("key=========== " + key);
			encrypt("db.properties", "e:/bbs-custom-sit3.conf", key);
			java.io.File f = new java.io.File("e:/bbs-custom-sit3.conf");
			FileInputStream input = new FileInputStream(f);
			InputStream in = decrypt(input, key);
			byte b[] = new byte[(int) f.length()]; // 创建合适文件大小的数组
			in.read(b); // 读取文件中的内容到b[]数组
			in.close();
			System.out.println(new String(b));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//加密
		encryptDecryptTest();
		
		//encryptDecryptBbsTest();
		//解密
		//decryptTest();
		
		//decryptBbsTest();

	}

	public static void createDesKey(String keyPath) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			SecureRandom sr = new SecureRandom();
			KeyGenerator kg = KeyGenerator.getInstance("DES");
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
			is = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(keyPath);
			System.out.println("is ============== " + is);
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

	public static void encrypt(String srcFile, String destFile, Key key)
			throws Exception {
		InputStream is = null;
		OutputStream out = null;
		CipherInputStream cis = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(1, key);
			is = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(srcFile);
			out = new FileOutputStream(destFile);
			cis = new CipherInputStream(is, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
		} finally {
			IOUtils.closeQuietly(cis);
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(out);
		}
	}

	public static InputStream decrypt(InputStream is, Key key) throws Exception {
		OutputStream out = null;
		CipherOutputStream cos = null;
		ByteArrayOutputStream bout = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(2, key);

			bout = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = is.read(buf)) != -1) {
				bout.write(buf, 0, count);
				buf = new byte[1024];
			}
			byte[] orgData = bout.toByteArray();
			byte[] raw = cipher.doFinal(orgData);
			return new ByteArrayInputStream(raw);
		} finally {
			IOUtils.closeQuietly(cos);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(bout);
		}
	}
}
