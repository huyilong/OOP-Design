public class RemoveConsecutive{
	private static String helper(String s){
		//给一个string remove Consecutive Char.
		// 比如： abbc -> ac ，  aa -> ""， acccbdd -> ab，"" -> ""，abbabb-> ""， 
		//aa->'
		if(s.length()==0 || s==null){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		// int scanner = 0;
		// int start = 0;
		//similar to remove dups in an array
		//we only care about different ones and then 
		//build into the new index of new array / append to the sb - otherwise continue
		while(true){
			for(int i=0; i<s.length()-1; i++){
				if(s.charAt(i+1) == s.charAt(i)){
					continue;
				}else{
					if(i>0 && s.charAt(i) == s.charAt(i-1)){
						//take care of the last one
						if(i == s.length() -2){
							sb.append(s.charAt(s.length()-1));
						}
						//bbc turning point is second b
						continue;
					}else{
						//abb turning point is a
						sb.append(s.charAt(i));
					}
				}
			}

			String check = sb.toString();
			if(done(check)){
				break;
			}else{
				s = check;
			}

		}

		return check;

	}

	private static boolean done(String check){
		if(check.length()==0 || check==null){
			return true;
		}

		for(int j =0; j<check.length()-1; j++){
			if(check.charAt(j+1) == check.charAt(j)){
				return false;
			}
		}

		return true;
	}
	public static void main(String[] args){
		String test = "";//"abbabb";//"abbc";
		System.out.println(helper(test));
	}
}