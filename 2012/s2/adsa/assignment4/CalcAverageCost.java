/**
 * @author Yifei Pei and Troy Strudwick
 *
 */

public class CalcAverageCost {

	public static void main(String[] args) {
	System.out.println ("test for 10 numbers for the cost method.");
	System.out.println ("The input of 1: " + cost (1));
	System.out.println ("The input of 2: " + cost (2));
	System.out.println ("The input of 3: " + cost (3));
	System.out.println ("The input of 4: " + cost (4));
	System.out.println ("The input of 5: " + cost (5));
	System.out.println ("The input of 6: " + cost (6));
	System.out.println ("The input of 7: " + cost (7));
	System.out.println ("The input of 8: " + cost (8));
	System.out.println ("The input of 9: " + cost (9));
	System.out.println ("The input of 10: " + cost (10));
	}
	
	public static double cost (int n) {
		double result = 0;
		double divided = 1.0 /n;
		double recursion = 0;
	
		if (n == 0) {
			result = 0;
		}else if (n < 0) {
			System.out.println ("error");
			System.exit(1);
		}else{
		for (int i=1;i<n;i++)
	        recursion += 2*cost(i);
		result = n + divided * recursion;
		}
		return result;
	}

}
