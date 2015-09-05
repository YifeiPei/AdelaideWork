

/**
 * @author Troy Studiwick with Student ID a1211765
 * @author Yifei Pei with Student ID a1611648
 * course Algorithm and Data Structure Analysis
 * the solution for Assignment 2, Exercise 3 Karatsuba Multiplication
 */

public class Karatsuba_Mult 
{
	/**
	 * Test three multiplications stated in the exercise
	 */
	
	public static void main(String a[])
	{
		System.out.println("\nTest case 1:");
		int A[]={3,0,6,7};
		System.out.println("the multiplier");
		for(int i = 0; i < A.length; i++)
		System.out.print(A[i]);
		System.out.println();
		int B[]={2,7,8,9};
		System.out.println("the multiplicand");
		for(int i = 0; i < B.length; i++)
		System.out.print(B[i]);
		System.out.println();
		int x1 = B.length;
		int [] mult1 = Karatsuba(x1, A, B);
		System.out.print("the result is: ");
		for (int i=0;i<mult1.length;i++)
		    System.out.print(mult1[i]);
        

        System.out.println("\nTest case 2:");
		int C[]={1,0,9,3,7,9};
		System.out.println("the multiplier");
		for(int i = 0; i < C.length; i++)
		System.out.print(C[i]);
		System.out.println();
		int D[]={9,3,5,9,8,7};
		System.out.println("the multiplicand");
		for(int i = 0; i < D.length; i++)
		System.out.print(D[i]);
		System.out.println();
		int x2 = D.length;
		int [] mult2 = Karatsuba(x2, A, B);
		System.out.print("the result is: ");
		for (int i=0;i<mult2.length;i++)
		    System.out.print(mult2[i]);
       
        
        System.out.println("\nTest case 3:");
		int E[]={9,8,9,8,7,0,4,9,3,2,1,0,3,2};
		System.out.println("the multiplier");
		for(int i = 0; i < E.length; i++)
		System.out.print(E[i]);
		System.out.println();
		int F[]={0,9,3,2,3,1,7,4,9,5,3};
		System.out.println("the multiplicand");
		for(int i = 0; i < F.length; i++)
		System.out.print(F[i]);
		System.out.println();
		int x3 = F.length;
		int [] mult3 = Karatsuba(x3, A, B);
		System.out.print("the result is: ");
		for (int i=0;i<mult3.length;i++)
		    System.out.print(mult3[i]);
        System.out.println("\n");
	}
	
	/**
	 * @param n0 the length of the parameter
	 * @param a the n-digit multiplier
	 * @param b the n-digit multiplicand
	 * Doing the Karatsuba algorithm for the input numbers
	 */
	
	public static int [] Karatsuba(int n0, int[] a, int[] b)
	{   
			
		padding (a,b);
				
		int [] p = new int [2*n];
		int [] a0 = new int [mid];
		int [] a1 = new int [mid];
		int [] b0 = new int [mid];
		int [] b1 = new int [mid];
		
		split1 (a1,a0);
		split2 (b1,b0);
		
		int [] p2 = Karatsuba (n, a1, b1);
		int [] p1 = Karatsuba (n, add(a1,a0), add(b1,b0));
		int [] p0 = Karatsuba (n, a0, b0);
		
		for (int i=0;i<2*mid;i++)
			p[i] = p0[i];
		for (int i=2*mid;i<(2*n);i++)
			p[i] = p2 [i-(2*mid)];
		sub (p1,p0);
		sub (p1,p2);
		addA (p,p1,mid);
		return p;
	}
	

	
private static int [] Aa;
private static int [] Bb;
private static int n;
private static int mid = n/2;
	
	
private static void padding (int [] a, int [] b)
{
	if (((a.length) != (b.length)) && ((Math.max(a.length, b.length))%2 != 0))
	{
		n = Math.max(a.length, b.length) + 1;
		Aa = new int [n];
		Bb = new int [n];
		int k = n - Math.min(a.length, b.length);
		if (a.length > b.length)
		{
			for (int i=0;i<n-1;i++)
			{
				Aa[0] = 0;				
				Aa[i+1] = a[i];				
			}
			for (int i=0;i<(Math.min(a.length, b.length));i++)
			{
				for (int j=0; j<k; j++)
					Bb[j] = 0;
				Bb[k+i] = b[i];
			}
		}
		else
		{
			for (int i=0;i<n-1;i++)
			{
				Bb[0] = 0;				
				Bb[i+1] = b[i];				
			}
			for (int i=0;i<(Math.min(a.length, b.length));i++)
			{
				for (int j=0; j<k; j++)
					Aa[j] = 0;
				Aa[k+i] = a[i];
			}
		}
	}
	else if ((a.length != b.length) && ((Math.max(a.length, b.length))%2 == 0))
	{
		n = Math.max(a.length, b.length);
		Aa = new int [n];
		Bb = new int [n];
		int k = n - Math.min(a.length, b.length);
		if (a.length > b.length)
		{
			for (int i=0;i<n;i++)
			{
				Aa[i] = a[i];				
			}
			for (int i=0;i<(Math.min(a.length, b.length));i++)
			{
				for (int j=0; j<k; j++)
					Bb[j] = 0;
				Bb[k+i] = b[i];
			}
		}
		else
		{
			for (int i=0;i<n;i++)
			{
				Bb[i] = b[i];				
			}
			for (int i=0;i<(Math.min(a.length, b.length));i++)
			{
				for (int j=0; j<k; j++)
					Aa[j] = 0;
				Aa[k+i] = a[i];
			}
		}
	}
	else if ((a.length == b.length) && (((a.length)%2)!= 0))
	{
		n = a.length + 1;
		Aa = new int [n];
		Bb = new int [n];
		for (int i=0;i<n-1;i++)
		{
			Aa[0] = 0;
			Aa[i+1] = a[i];
		}
		for (int i=0;i<n-1;i++)
		{
			Bb[0] = 0;
			Bb[i+1] = b[i];
		}
	}
	else if((a.length == b.length) && (((a.length)%2) == 0))
	{
		n = a.length;
		Aa = new int [n];
		Bb = new int [n];
		for (int i=0;i<n;i++)
			Aa[i] = a[i];
		for (int i=0;i<n;i++)
			Bb[i] = b[i];
	}
	else
		return ;
}

private static void split1 (int [] a1, int [] a0)
{
	for (int i=0;i<mid;i++)
		a0[i] = Aa[i];
	for (int i=0;i<mid;i++)
		a1[i] = Aa[mid+i];
}

private static void split2 (int [] b1, int [] b0)
{
	for (int i=0;i<mid;i++)
		b0[i] = Bb[i];
	for (int i=0;i<mid;i++)
		b1[i] = Bb[mid+i];
}

	
	private static int getD (int [] a, int i)
	{
		return a[i];
	}
	private static void adder (int a, int b, int c, int s, int carry)
	{
		int sum = a + b + c;
		carry = sum/10;
		s = sum - carry*10;
	}
	private static int [] add (int [] a, int [] b)
	{
		int n = Math.max(a.length, b.length);
		int [] s = new int [n+1];
		int carry = 0;
		for (int i=0;i<n;i++)
			adder (getD(a,i),getD(b,i),carry,s[i],carry);
		s[n] = carry;
		return s;
	}
	private static void sub (int [] a, int [] b)
	{
		int carry = 0;
		for (int i=0;i<a.length;i++)
		{
			if (a[i] >= (getD(b,i)+carry))
			{
				a[i] = a[i] - getD(b,i) - carry;
				carry = 0;
			}
			else
			{
				a[i] = a[i] + 10 - getD(b,i) - carry;
				carry =1;
			}
		}
		if( carry != 0 )
	    {
	        System.err.println("error");
	        System.exit(1);
	        return ;
	    }
	}
	private static void addA (int [] p, int [] r, int j)
	{
		int carry = 0;
		int L = p.length;
		for (int i=j;i<L;i++)
		    adder (p[i], getD(r,i-j), carry, p[i], carry);
		if( carry != 0 )
	    {
	        System.err.println("error");
	        System.exit(1);
	        return ;
	    }
	}
}

/**
 * It seems that this cannot compile
 * however we have tried our best
 * now our brains are mess
 * hope you a good day
 */

