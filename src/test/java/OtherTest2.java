import java.text.DecimalFormat;


class Test1 {
	final int var_final = 100;

	void doit() {
		System.out.println(var_final);
	}

	static int myFunction() {
		return 500;
	}
}

public class OtherTest2 extends Test1 {
	final int var_final = 200;

	public OtherTest2() {
		super();
	}

	void doit() {
		System.out.println(var_final);
	}

	static int myFunction() {
		return 1000;
	}

	public static void main(String[] str) {
//		Test1 test = new OtherTest2();
//		System.out.println("var_final = " + test.var_final);
//		System.out.println("myFunction = " + test.myFunction());
//		test.doit();
		
		DecimalFormat df = new DecimalFormat("0.00");
	    String ds = df.format(6.367D);
	    System.out.println(Double.parseDouble(ds));
	}
}