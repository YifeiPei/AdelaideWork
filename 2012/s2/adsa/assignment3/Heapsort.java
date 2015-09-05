
public class Heapsort 
{
	public static int n;
	public static int [] binaryheap;
	
	public static void main(String[] args) 
	{
		int [] input = {15, 8, 7, 4, 17, 12, 14, 12, 1, 13, 9, 10};
		System.out.println("Test, the original array: ");
		for(int i=0;i<input.length; i++)
			System.out.print(input[i]+"  ");
		System.out.println();
		int [] output = heapsort (input);
		System.out.println("The sorted array is: ");
		for(int i=0;i<output.length; i++)
			System.out.print(output[i]+"  ");
		System.out.println();
	}
	
	public static int [] heapsort (int [] input)
	{
		build (input);
		int [] temp1 = new int [input.length];
                int x = input.length;
		for (int i=0;i<x;i++)
		{
			temp1[i] = min();
                        deleteMin ();
		}
		return temp1;
	}
	
	public static int [] build (int[] input)
	{
		binaryheap = input;
		n = input.length;
		for (int j=(n/2)-1; j>=0; j--)
		{
			siftDown (j);
		}
		return binaryheap;
	}

	public static int min ()
	{
		return binaryheap[0];
	}

	public static int [] deleteMin ()
	{
		n--;
		int [] temp = new int [n];
		temp[0] = binaryheap [n];
		for (int i=1;i<=n-1;i++)
		{
			temp[i] = binaryheap [i];
		}
		binaryheap = new int [n];
		binaryheap = temp;
		siftDown (0);
		return binaryheap;
	}

	public static void siftDown (int i)
	{
		int m;
		if (2*i+1 <= n-1)
		{
			if ( (2*i+2>n-1) || (binaryheap[2*i+1]<=binaryheap[2*i+2]))
			{
				m = 2*i+1;
			}
			else
			{
				m = 2*i + 2;
			}

			if (binaryheap[i]>binaryheap[m])
			{

				int temp;
				temp = binaryheap[i];
				binaryheap[i] = binaryheap[m];
				binaryheap[m] = temp;
                                siftDown (m);
			}
		}
	}
}

