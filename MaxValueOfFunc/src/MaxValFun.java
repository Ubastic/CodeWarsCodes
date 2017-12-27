//Solution for https://www.codewars.com/kata/56085481f82c1672d000001f/train/java
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MaxValFun {

	public static List<Long> maxValF(int[] range1, int[] range2, long hMax, int k) {

		List<Long> list = new ArrayList<Long>();
		long[] arr = new long[k + 1];
		int arrSize = 0;
		for(int i = range1[0]; i <= range1[1]; i++)
			for(int j = range2[0]; j <= range2[1]; j++){
				long temp = func(i,j);
				boolean containsFlag = false;
				if(arrSize <= k && func(i,j) <= hMax){
					for(int p = arrSize-1; p >= 0; p--)
						if(arr[p] == temp)
							containsFlag = true;
					if(!containsFlag)
						arr[arrSize++] = temp;
				}
				else{
					if(temp <= hMax){
						for(int n = 1; n < arr.length; n++)
							if(arr[n] == temp)
								containsFlag = true;
						if(!containsFlag){
							arr[0] = temp;
							Arrays.sort(arr);
						}
					}
				}
			}
		
		for(int m = 0; m < k; m++){
			list.add(arr[m + 1]);	
		}
		
		return list;
			
	}
	 
	public static long func(int x, int y){
		
		double num = (double) x + y;
		double den = Math.abs((double) x - y);
		double quot = Math.floor(num/den);
		long returnNum = (long) Math.pow(quot,den);
		return returnNum;
		
	}

}
