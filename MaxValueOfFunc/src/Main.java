import java.util.List;

public class Main {

	public static void main(String[] args){
		
		List<Long> list;
		int[] range1 = new int[] {3, 10}; int[] range2 =new int[] {5, 11}; long hMax = 4777023; int k = 5;
		list = MaxValFun.maxValF(range1, range2, hMax, k);
		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	
}
