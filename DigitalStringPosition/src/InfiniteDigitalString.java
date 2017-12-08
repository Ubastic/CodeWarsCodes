//Solution for https://www.codewars.com/kata/582c1092306063791c000c00/train/java

public class InfiniteDigitalString {

	public static long findPosition(final String s){
		
		for(int length = 1; length <= s.length(); length++)
			for(int lengthIndex = 0; lengthIndex < length; lengthIndex++){
				String a = "";
				for(int i = 0; i < length; i++){
					if(lengthIndex + i < s.length())
						a += Character.toString(s.charAt(lengthIndex + i));
					else
						a += "x";
				}
				//checks if a is a valid number
				if(isValidA(a)){
					boolean goToNextLengthIndex = false;
					
					//Check previous if previous exists
					if(lengthIndex != 0){
						String prev = "";
						//pad with x's
						for(int j = 0; j < length - lengthIndex; j++){
							prev += "x";
						}
						//add the prev string
						for(int k = 0; k < lengthIndex; k++){
							prev += Character.toString(s.charAt(k));
						}
						System.out.println(prev);
						System.out.println(a);
						//if a has an x
						if(a.charAt(a.length()-1) == 'x'){
							long temp = fillInAndConsecutive(a, prev);
							if(temp == -1)
								goToNextLengthIndex = true;
							else
								return temp - lengthIndex;
						}
						//if a doesn't have x but previous has x
						else if(!isConsecutive(a,prev)){
							goToNextLengthIndex = true;
						}
					}
					
					//this works
					//iterate through string given the a to check if they are consecutive
					while(!goToNextLengthIndex){
						String curr = a;
						int nextInt = Integer.parseInt(a) + 1;
						String b = Integer.toString(nextInt);
						int strPos = lengthIndex + length;
						while(strPos + b.length() <= s.length()){
							nextInt = Integer.parseInt(curr) + 1;
							b = Integer.toString(nextInt);
							String next = s.substring(strPos, strPos + b.length());
							//System.out.println("Curr is: " + curr + " Next is: " + next);
							if(!isConsecutive(curr, next)){
								goToNextLengthIndex = true;
								break;
							}
							curr = next;
							strPos += b.length();
						}
						//System.out.println("Nothing to check?");
						//nothing left to check
						if(strPos == s.length()){
							return calcPosition(Integer.parseInt(a));
						}
						//System.out.println("Last string check...");
						//check last string
						String last = "";
						for(int m = strPos; m < s.length(); m++)
							last += Character.toString(s.charAt(m));
						for(int n = last.length(); n < b.length(); n++)
							last += "x";
						//System.out.println("Last string is: " + last);
						if(isConsecutive(curr,last))
							return calcPosition(Integer.parseInt(a));
						else
							goToNextLengthIndex = true;
					}
				}
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
	
	//checks if a is a valid number (can't be 01 or 001 or 093, etc.)
	public static boolean isValidA(String a){
		if(a.charAt(0) == '0')
			return false;
		return true;
	}
	
	//checks if two numbers are consecutive
	//FIX THIS SO THAT IT CAN CHECK X'S IN PREV AND X'S IN A
	public static boolean isConsecutive(String a, String b){
		//if number has an x
		int numA = Integer.parseInt(a);
		int tempNum = numA + 1;
		String checkNum = Integer.toString(tempNum);
		for(int i = 0; i < checkNum.length(); i++)
			if(checkNum.charAt(i) != b.charAt(i) && b.charAt(i) != 'x')
				return false;
		return true;
	}
	
	//checks prev and a (with x's) to see if they are consecutive
	public static long fillInAndConsecutive(String a, String prev){
		//if matches return calcPosition(Integer.parseInt(a))
		int i = 0;
		while(true){
			if(prev.charAt(i) != 'x')
				break;
			else 
				i++;
		}
		int num = Integer.parseInt(prev.substring(i)) + 1;
		System.out.println(num);
		String numStr = Integer.toString(num);
		for(int j = numStr.length() - 1, k = a.length() - 1; j >= 0; j--, k--){
			//System.out.println("numStr.charAt(" + j + ") is: " + numStr.charAt(j));
			//System.out.println("a.charAt(" + k + ") is: " + a.charAt(k));
			if(a.charAt(k) != 'x' && numStr.charAt(j) != a.charAt(k))
				return -1;
		}
		String returnStr = a.substring(0,a.length()-numStr.length());
		returnStr += numStr;
		return calcPosition(Integer.parseInt(returnStr));
	}

}
