Given an array of n positive integers and a positive integer s, 
find the minimal length of a subarray of which the sum ≥ s. 
If there isnt one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //we just need to return the length of sub array!!!
        //two pointers make a window!!!!  TIME COMPLEXITY O(N)  OR WE COULD USE BINARY SERACH O(NlogN)
        if(nums == null || nums.length == 0){
            return 0;//not found
        }
        int l =0, r=0;//window is 0 for the beginning
        int sum=0, res = Integer.MAX_VALUE;
        while(r<nums.length){
            //here we firstly increse window size to satisfy that sum>s
            //we use while!!!!
            while(sum<s && r<nums.length){
                sum = sum+nums[r];
                r++;
            }
            
            //then we possibly decrease the length of window
            while(sum>=s){
                res = Math.min(res, r-l);
                sum = sum-nums[l];
                l++;
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 :res;
    }
}