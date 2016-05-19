import java.math.BigDecimal;

public class OtherTest {
	public static void main(String[] args) {
		int formatLength = 3;
		int sourceDate = 88;
		
		//String newString = String.format("%0"+formatLength+"d", sourceDate);
//		String newString = String.format("%03d", sourceDate);
//		System.out.println(newString);
		
		double money = Math.round(5.26545554444444444 * 100);
		System.out.println(money);
		money = money  * 0.01d;
		System.out.println(money);
		
		
		System.out.println(new java.text.DecimalFormat("#.00").format(3.1415926));
		
		
		money = 1.0D;
//		BigDecimal b = new BigDecimal(money);
//		money = b.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
//		System.out.println(money);
////		money = 5.265D;
		BigDecimal b = new BigDecimal(money);
		money = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(money);
	}
}
