import com.test.TestClassStaticA;

public class OtherTest3 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		String str = "abc";
//		Class cla1 = str.getClass();
//		Class cla2 = Class.forName("java.lang.String");
//		Class cla3 = String.class;
//		
//		System.out.println(cla1);
//		System.out.println(cla2);
//		System.out.println(cla3);
//		
//		Integer i = new Integer(22);
//		System.out.println(i.getClass());
		
		Class a = Class.forName("com.test.TestClassStaticA");
		System.out.println(a);
		TestClassStaticA o = (TestClassStaticA) a.newInstance();
		o.test();
	}
}
