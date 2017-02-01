package sort.util;

public class SortingUtil {

	/**
	 * print A , from 1 to n;
	 * @param A
	 * @param n
	 */
	public static void printArray(int[] A,int n){
		for(int i=1;i<=n;i++)
			System.out.print(A[i]+"   ");
		System.out.println();
	}
	
	
	/**
	 * 	swap A[a] with A[b];
	 */
	public static void swap(int[] A,int a,int b){
		int tmp = A[a];
		A[a] = A[b];
		A[b] = tmp;
	}
}
