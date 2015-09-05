import java.util.Arrays;

public class BuyingDrinks {

	public static int variety(int amount, int[] prices) {
		
		if ((prices.length < 1) || (prices.length > 50)) {
			System.out.println("illegal variety");
			System.exit(1);
		}
		
		Arrays.sort(prices);
		
		if ((prices[0] < 1) || (prices[prices.length -1] > 50)) {
			System.out.println("illegal prices");
			System.exit(1);
		}
		
		if (amount < prices[0])
			return 0;
		int n = 0;
		for (int i=0;i<prices.length;i++) {
			amount = amount - prices[i];
			n++;
			if (amount < 0) {
				n--;
				break;
			} else if (amount == 0) 
				break;
		}
		return n;
	}
	
}
