//Solution for https://www.codewars.com/kata/582c1092306063791c000c00/train/java

public class InfiniteDigitalString {

	public static long findPosition(final String s){
		
		//this works
		int a = Integer.parseInt(Character.toString(s.charAt(0)));
		String str = "";
		int index = 1;
		int iteration = 1;
		while(true){
			System.out.println("Iteration: " + iteration++);
			if(a == 0)
				break;
			str = String.valueOf(++a);
			System.out.println("str is: " + str);
			if(!(index + str.length() > s.length())){
				String subStr = s.substring(index, index + str.length());
				System.out.println("subStr is: " + subStr);
				if(!str.equals(subStr))
					break;
			}
			else{
				System.out.println("Entered last check");
				str = str.substring(0, s.length() - index);
				String subStr = s.substring(index, s.length());
				if(str.equals(subStr))
					return calcPosition(Integer.parseInt(Character.toString(s.charAt(0))));
			}
			index += str.length();
			System.out.println("Index is: " + index);
		}
		return 0;
	}
	
	//this works
	public static long calcPosition(long index){
		long sum = 0;
		long checkAmt = 10;
		while(checkAmt < index){
			sum += 9 * (checkAmt / 10) * (Math.log10(checkAmt));
			checkAmt *= 10;
		}
		checkAmt /= 10;
		sum += (index - checkAmt) * Math.log10(checkAmt * 10);
		return sum;
	}

}
