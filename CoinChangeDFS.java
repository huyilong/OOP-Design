import java.io.*;
import java.util.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * tanay.shah@citadel.com
 */

// n, [a,b, c, d ... n]
// 3, [1, 2, 5] --> 10 [5,5], 5 [5], 3 [1,2]
// 5, [1,5 10, 20, 100] --> 75 [20, 20, 20, 10, 5]
// 5, [1,2,3,5,6]

// number of coins in the system -> n 
// distinct values of each coin -> [1, b, c, d .. n]
// number x that has to be converted to coins

// 6 [1,3,4]
//   4,1,1
//   3,3
        

class CoinChange {
  public static void main(String[] args) {
  	int[] coin_test1 = {1,3,4};
  	int[] coin_test2 = {1,2,5};
  	int[] coin_test3 = {1,5,10,20,100};
  	int[] coin_test4 = {1,2,3,5,6};
  	int[] res1 = leastCoin(coin_test1, 3, 6);//should be [3,3]
  	int[] res2 = leastCoin(coin_test2, 3, 10);//should be [5,5]
  	int[] res3 = leastCoin(coin_test3, 5, 75);//should be [20, 20, 20, 10, 5]
  	int[] res4 = leastCoin(coin_test4, 5, 3);//should be [3]

  	System.out.println(Arrays.toString(res1));
  	System.out.println(Arrays.toString(res2));
  	System.out.println(Arrays.toString(res3));
  	System.out.println(Arrays.toString(res4));
  }
  
  private static int[] leastCoin(int[] coins, int len, int target){
  		//illegal checking
	    if(coins == null || len < 1){
	      return null;
	    }
	    Arrays.sort(coins);//make sure the array is sorted
	    int min_num = helper(coins, len, target);
	    int[] res = new int[min_num];
	    int index = 0;
	    //restore the coins array based on the min number and target
	    while(target>0){
	    	int check = target/min_num;
	    	for(int i=0; i<len; i++){
	    		if(coins[i]>=check){
	    			res[index++] = coins[i];
	    			break;
	    		}
	    	}
	    	target = target - res[index-1]; 
	    	min_num--;
	    }
	    //return the result
	    return res; 
  }

  private static int helper(int[] coins, int len, int target){
        int[] dp = new int[target+1];
        dp[0] = 0;
        //initialize the dp array
        for(int i=1; i<=target; i++){
        	dp[i] = Integer.MAX_VALUE;
        }
        //find the min number of coins needed
        for(int i=1; i<=target; i++){
            for(int j=0; j<len; j++){
                if(coins[j]<=i){
                	//fetch the sub res from dp array
	                int sub_res = dp[i-coins[j]];
	                if(sub_res != Integer.MAX_VALUE && sub_res + 1 < dp[i])
	               		 dp[i] = sub_res + 1;//update the min number
                }
            }
        }
        //return the result
        return dp[target] == Integer.MAX_VALUE ? -1 : dp[target];
  }
}