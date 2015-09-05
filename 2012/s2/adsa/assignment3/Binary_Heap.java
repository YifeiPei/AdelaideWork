
public class Binary_Heap 
{
	public static int n;
	public static int [] binaryheap;
	
	public static void main(String[] args)
	{
		int [] input =  {15, 8, 7, 4, 17, 12, 14, 12, 1, 13, 9, 10};
		System.out.println("Test, the original array: ");
		for(int i=0;i<input.length; i++)
			System.out.print(input[i]+"  ");
		System.out.println();
		int [] output1 = build (input);
		System.out.println("The built binaryheap array from the original one: ");
		for(int i=0;i<output1.length; i++)
			System.out.print(output1[i]+"  ");
		System.out.println();
		int [] output2 = insert (5);
		System.out.println("The binaryheap array after insertion of 5: ");
		for(int i=0;i<output2.length; i++)
			System.out.print(output2[i]+"  ");
		System.out.println();
		System.out.println("The minimum number in the binaryheap array is: ");
		System.out.print(" " + min() + ". ");
		System.out.println();
		int [] output3 = deleteMin ();
		System.out.println("The binaryheap array after deletion of the minimum number: ");
		for(int i=0;i<output3.length; i++)
			System.out.print(output3[i]+"  ");
		System.out.println();
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
	
	public static int [] insert (int input)
	{
		n++;
		int [] temp = new int [n];
		for (int i=0;i<n-1;i++)
		{
			temp[i] = binaryheap[i];
		}
		temp [n-1] = input;
		binaryheap = new int [n];
		binaryheap = temp;
		siftUp (n-1);
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
		for (int i=1;i<n;i++)
		{
			temp[i] = binaryheap [i];
		}
		binaryheap = new int [n];
		binaryheap = temp;
		siftDown (0);
		return binaryheap;
	}
	
	
	
	public static void siftUp (int i)
	{
	
			if (i == 0 || binaryheap[(i-1)/2]<=binaryheap[i])
			{
				return ;
			}
			else
			{
				int temp;
				temp = binaryheap[(i-1)/2];
				binaryheap[(i-1)/2] = binaryheap[i];
				binaryheap[i] = temp;
			}
                        siftUp((i-1)/2);
	
	}
}
