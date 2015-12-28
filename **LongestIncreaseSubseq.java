public class Solution {
    public int lengthOfLIS(int[] nums) {
        //dynamic programming: 1.state 2. transition function 3.initialization 4. answer
        //max/min   whether have a feasible solution?  data could not be adjusted postion
        //if find subset or permutation :: all possible solutions: not DP!!! that is backtracking / recursive
        
        //f[i] means the LIS in the first i elements
        //f[i] = { 1 + max f[j] }    j<i  && nums[j]<nums[i]
        //which means j comes before i and also j's value is less then i's value
        //which is the concept of this problem

        //at last we need to return max( f[0-i] )
        if(nums == null || nums.length ==0 ){
            return 0;
            //they are different!!! nums=nul and nums.length = 0 one is new one is not new
        }
        
        int[] dp = new int[nums.length];
        //Character.getNumericValue(char ch)
        //Collections
        //Integer.parseInt
        Arrays.fill(dp, 1);
        //dp[0] = 1;
        
        
        for(int i=1; i <nums.length; i++){
            for(int j=0; j<i; j++){
                //if(nums[j] < nums[i] && dp[i] < dp[j] +1)
                //i is going forward and check each j from 0 to i-1

                //we already satisfy j<i for the loops over
                //and here we need to 1. nums[j] < nums[i]
                //2.dp[i] <= dp[j]  -- in which case we have the need to update it
                //although i is followed after j
                if(nums[j] < nums[i] && dp[i] <= dp[j]){

                    //only when dp[i] <= dp[j] we need to update it
                    //otherwise the max dp is not changed
                    dp[i] = dp[j] +1;
                   // max = Math.max(max,dp[i] );
                }
            }
        }
        
        
        int max =0; 
        for(int i=0; i<dp.length; ++i){
            if(dp[i] > max) max = dp[i];
        }
        
        return max;
        //get the max number 
        
    }
}