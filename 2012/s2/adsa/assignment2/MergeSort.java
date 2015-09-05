
/**
 * @author Troy Strudwick with Student ID a1211765
 * @author Yifei Pei with Student ID a1611648
 * course Algortiehms and Data Structure Analysis
 * the solution for Assignement 2, Exercise 2 Sorting, the first element
 */

public class MergeSort
{
	
	/**
	 * Test the two sequeces stated in the exercise
	 */
	
	public static void main(String a[])
	{
		int i;
		System.out.println("\nTest case 1:");
		int array[]={13,5,8,5,12,13,17};
		System.out.println("Values Before sort:");
		for(i = 0; i < array.length; i++)
		System.out.print( array[i]+"  ");
		System.out.println();
		MergeSort(array,0,array.length-1);
		System.out.print("Values after sort:\n");
		for(i = 0; i <array.length; i++)
		System.out.print(array[i]+"  ");
		System.out.println("\n");

		System.out.println("Test case 2:");
		int array1[]={101, 25, 117, 15, 132, 133};
		System.out.println("Values Before sort:");
		for(i = 0; i < array1.length; i++)
		System.out.print( array1[i]+"  ");
		System.out.println();
		MergeSort(array1,0,array1.length-1);
		System.out.print("Values after sort:\n");
		for(i = 0; i <array1.length; i++)
		System.out.print(array1[i]+"  ");
		System.out.println("\n");
	}
	
	/**
	 * @param array the input array 
         * @param low the position of the the first element
         * @param n the position of the last element 
	 * Doing the merge sort algorithm for the input array
	 */

	public static void MergeSort(int array[],int low, int n)
	{
		int lower = low;
		int upper = n;
		if (lower >= n) 
		{
			return;
		}
		int mid = (lower + upper) / 2;

		MergeSort(array, lower, mid);
		MergeSort(array, mid+1, n);

		int [] temp = new int [array.length];
		for (int i = 0; i <= n ; i++) 
		{
			temp[i] = array[i];
		}

		int t_lower = lower;
		int t_mid = mid + 1;
		int k = lower;
  
		while (t_lower <= mid && t_mid <= upper) 
		{
			if (temp[t_lower] <= temp[t_mid]) 
			{
				array [k] = temp[t_lower];
				t_lower++;
			} 
			else 
			{
				array[k] = temp[t_mid];
				t_mid++;
			}
			k++;
		}
		while (t_lower <= mid) 
		{
			array [k] = temp[t_lower];
			k++;
			t_lower++;
		}
 
	}
}	
