longest common prefix
123
12
1
we use a flag to mark whether all first index of the all strings are same
if true, append to final result
stop condition -- find one of them differs from str[0].charAt(0) -- random standard
OR the index is already exceed the length of the shortest string among all these


3 sum --- we need to sort the array!!!! so that we could use left++/right--;
i =0 -- n 
left=i+1
right=num.length-1  for(left<right)


public class Solution{
	public Arraylist<Arraylist<Integer>> threeSum(int[] num){
		List<List<Integer>> res = new List<Arraylist<Integer>>();
		Arrays.sort(num);
		for(int i=0; i<num.length-2;i++){

			int left = i+1;
			int right = num.length-1;

			while(left<right){

				int sum = num[left] + num[right] + num[i];
				/////////
				if(sum ==0){
					Arraylist<Integer> sub = new Arraylist<>();
					sub.add(num[left]);
					sub.add(num[right]);
					sub.add(num[i]);
					res.add(sub);


					//dont forget!!!!!! we need 
					left++;
					right--;
					///////////////////////

					while(left<right && num[left] == num[left-1]){
						left++;
					}
					while(left<right && num[right] == num[right+1]){
						right--;
					}

				}else if(sum < 0){
					left++;
				}else{
					right--;
				}
			}
		}
	}
}


3 sum closest!!

public class Solution{
	public Arraylist<Arraylist<Integer>> threeSum(int[] num, int target){
		if(num == null || num.length<3){
			return Integer.MAX_VALUE;
		}

		List<List<Integer>> res = new List<Arraylist<Integer>>();
		Arrays.sort(num);
		int closest = Integer.MAX_VALUE/2;
		for(int i=0; i<num.length-2; i++){
			int left = i+1;
			int right = num.length-1;
			while(left<right){
				int sum = num[left] + num[right]+ num[i];
				if(sum == target){
					return sum;
					//no left++ and right-- here
					//because we do not need to find all sub solutions
				}else if(sum < target){
					left++;

				}else{
					right--;
				}


				//the later two conditions needs to update the closest
				//according to the diff btw the new sum and target
				closest = Math.abs(sum-target) < Math.abs(closest-target) ?
							sum : closest;
			}
		}

		return closest;
	}
}


