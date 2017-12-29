
public class Main {

	public static void main(String[] args){
		
		long[] arrList = KPrimes.countKprimes(5, 500, 600);
		for(int i = 0; i < arrList.length ; i++)
			System.out.println(arrList[i]);
		
		System.out.println(KPrimes.puzzle(143));
	}
	
}
