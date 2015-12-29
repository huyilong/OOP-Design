public class Solution {
    public int numDecodings(String s) {
        //Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
        //The number of ways decoding "12" is 2.
        //dp[i] means the 1~i 's decode ways
        //when it is only digit, we need to judge whether it is valid i.e. between 1~26
        if(s.length() == 0 || s==null || s.equals("0")){
            return 0;
        }
        
        int[] dp = new int[s.length()+1];/////////why it always requires one more bit 
        dp[0] = 1;//the initial state should be 1
        //becasue the decode ways could be * or **
        //we need to check substring 1 or 2
        //this requires we needs to initialize both dp[0] and dp[1]
        
        
        if(decode(s.substring(0,1))){
            System.out.println("asd");
            System.out.println(s.length());
            dp[1] = 1;
        }else{
            dp[1] = 0;
        }
        
        //because at most we need to consider
        //two-digit   26-Z  1-A
        for(int i=2;i<s.length()+1; i++){
            if(decode(s.substring(i-1,i))){
                dp[i] = dp[i] + dp[i-1];
            }
            if(decode(s.substring(i-2,i))){
                dp[i] = dp[i] + dp[i-2];
            }
        }
        
        //s.length()+1
        //int[] dp = new int[s.length()+1];
        //there is a 1 difference existing
        return dp[s.length()];
    }
    
    private boolean decode(String s){
        if(s.charAt(0) == '0'){
            return false;//for the following parse
            //because parse automatically get rid of the leading 0s
            //but actually we do not allow they have the leading 0s
        }
        
        int check = Integer.parseInt(s);
        return check>=1 && check<=26;
    }
    
    
}

public class Solution {
    public int maxProfit(int[] prices) {
        //corner condition -- ARRAY is NULL or array is not long enough
        if(prices == null || prices.length <2){
            return 0 ;
        }
        int profit =0;
        int min = Integer.MAX_VALUE;
        //min sets as the max value in integer range
        for(int i=0; i<prices.length;++i){
            
            //continuosly updating the smallest price
            min = Math.min(min, prices[i]);
            //continuously compare the profit with the current price - min
            profit = Math.max(profit, prices[i]-min);
        }
        
        return profit;
    }
}


  
        if(nums.length == 0 || nums==null){
            return 0;
        }
        
        int local_max = nums[0];
        int global_max = nums[0];
        
        for(int i=1; i<nums.length; i++){
            //must attention!!! nums[i] and local_max+nums[i]
            local_max = Math.max(nums[i], local_max + nums[i]);
            global_max = Math.max(local_max, global_max);
            
        }
        
        return global_max;


public class Solution {
    public int maxProduct(int[] nums) {
        //however here the sign of the number also matters
        //这道题跟Maximum Subarray模型上和思路上都比较类似，还是用一维动态规划中的“局部最优和全局最优法”。这里的区别是维护一个局部最优不足以求得后面的全局最优，这是由于乘法的性质不像加法那样，累加结果只要是正的一定是递增，乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积。不过事实上也没有麻烦很多，我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和，这也是利用乘法的性质得到的。代码如下： 
        
        
        //the thing is that we want to find the max among 
        //nums[i] * temp, nums[i], nums[i] * local.min
        if(nums.length ==0 || nums==null){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        
        int local_max = nums[0];
        int local_min = nums[0];
        int global = nums[0];
        /////////i must start from 1!!!!!!!!!!!!!!! because dp we have already used the initialized values!!! for the num[0]
        for(int i=1; i<nums.length; i++){
            //store it into the temp variable
            int temp = local_max;
            local_max = Math.max(Math.max( temp*nums[i] , nums[i]), local_min * nums[i]);//a negative number could be very big by multiplying -1
            local_min = Math.min(Math.min( temp*nums[i] , nums[i]), local_min *nums[i]);
            //global we still compare the current local max with the old previous global max
            global = Math.max(local_max, global);
        }
        return global;
    }
}



public class Solution {
    public int rob(int[] nums) {
        //这道题的本质相当于在一列数组中取出一个或多个不相邻数，使其和最大。
        //那么我们对于这类求极值的问题首先考虑动态规划Dynamic Programming来解，
        //我们维护一个一位数组dp，其中dp[i]表示到i位置时不相邻数能形成的最大和，经过分析，

        //我们可以得到递推公式dp[i] = max(num[i] + dp[i - 2], dp[i - 1]), 
        //由此看出我们需要初始化dp[0]和dp[1]，其中dp[0]即为num[0]，
        //dp[1]此时应该为max(num[0], num[1])，代码如下
        if(nums.length == 0 || nums == null){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i=2; i< nums.length; i++){
            dp[i] = Math.max(dp[i-1],   dp[i-2] + nums[i]    );
        }
        //new dp[nums.length];
        return dp[nums.length-1];
    }
}

public class Solution {
    public int rob(int[] nums) {
        //这道题是之前那道House Robber 打家劫舍的拓展，现在房子排成了一个圆圈，则如果抢了第一家，就不能抢最后一家，因为首尾相连了，所以第一家和最后一家只能抢其中的一家，或者都不抢，那我们这里变通一下，
        //如果我们把第一家和最后一家分别去掉，各算一遍能抢的最大值，然后比较两个值取其中较大的一个即为所求。那我们只需参考之前的House Robber 打家劫舍中的解题方法，然后调用两边取较大值，代码如下：
        if(nums.length == 0 || nums == null){
            return 0;
        }
        
        if(nums.length == 1){
            return nums[0];
        }
        
        return Math.max( helper(nums, 0, nums.length - 1), helper(nums, 1, nums.length) );
        
    }
    private int helper(int[] nums, int left, int right){
        if (right - left <= 1){ 
            return nums[left];
        }
        
        //int[] dp = new int[nums.length];//because we cut off one of the house
        int[] dp = new int[right];
        //because we are merging two conditions together !!!!
        //we need to 
        
        
        //dp[0] = nums[left];
        dp[left] = nums[left];
        //dp[1] = Math.max(nums[left], nums[left+1]);
        System.out.println(left);
        dp[left+1] = Math.max(nums[left], nums[left+1]);
        
        for(int i=left+2; i<right; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i] );
        }
        
        
        //new int[nums.length-1];//because we cut off one of the house
        return dp[right-1];
        
        
    }
    
}

public class Solution{
    // public static void main(String[] args){

    // }
    public static int uniquePaths(int m, int n){
        //int dp[][] = new dp[m][n];
        int[][] dp = new int [m][n];
        int i, j;
        for(i=0; i<n; i++){
            dp[0][i] =1;
        }
        for(j=0; j<m; j++){
            dp[j][0] = 1;
        }

        //initialize the whole board's two board line

        for(i=1;i<m; i++){
            for(j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }


        return dp[m-1][n-1];
    }
}

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //如果是，则res[i][j]=0，否则还是res[i][j]=res[i-1][j]+res[i][j-1]。实现中还是只需要一个一维数组，因为更新的时候所需要的信息足够了。
        if( obstacleGrid == null || obstacleGrid[0][0] ==1){
            return 0;
        }
        
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //we initialize the original point
        //here we still need to consider
        //becasue we have check that 
        // if(obstacleGrid[0][0] == 1 ){//just to avoid duplicates
        //     //useless
        //     System.out.println("3");
        //     dp[0][0] = 0;
        // }else{
        //     System.out.println("123");
        //     dp[0][0] = 1;
        // }
        
        //here we are wrong in initialzing row and col
        //once we find a 0 the remaining should be all zeros
        
        for(int i=0 ; i<m; i++){
            //last result plus the new data point
            //dp[i][0] = dp[i-1][0] + obstacleGrid[i][0];
            //sb this one is not for asking sum!!!
            
            //sb!!! here we are not the same !!!!!
            if(obstacleGrid[i][0] != 1){
                dp[i][0] = 1;
            }else{
                dp[i][0]=0;
                break;///break!!!!! the remaining path cannot pass!!
                //even if the remaining is still 0 but it is blocked by the previous 1
                //so must break here
                //once find the obstacle in the row and colomn we must break and leave the following 
                //positions all 0s
            }
        }
        
        for(int j=0; j<n; j++){
            //dp[0][j] = dp[0][j-1] + obstacleGrid[0][j];
            if(obstacleGrid[0][j] !=1){
                dp[0][j] = 1;
            }else{
                dp[0][j] = 0;
                break;///break!!!!! the remaining path cannot pass!! do not initialize -- >default as 0s
            }
            
            
        }
        ////after initilizing the dp row and col
        ///we need to start from 1!!!!
        
        
        for(int i=1 ; i<m; i++){
            for(int j=1; j<n; j++){
                //if there is an obstacle then zero out
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        
        
        return dp[m-1][n-1];
        
        
    }
}

public class Solution {
    public int minPathSum(int[][] grid) {
        //now we are still going to create a dp matrix
        //but we are not provided with the dimensions
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        //backtracking!!!???? we need to remember the statistical characters!!!!!!
        //we initialize this up corner -- original point at first
        dp[0][0] = grid[0][0];
        
        
        //initialize the top row --> each point to where is the sum // because this is 1d so we just initialize 
        //each 1d's position records the sum of path to this point
        //so the sum max problem 1-demensional dp is needed to create a new variable to find the max value in the array
        //we do not just need to create the transition
        //unless we have already compared the minimum while we are creating the value!!! but that is not insufficient because we do not have the global view!!!
        for(int i=1; i<m; i++){
            //dp matrix is adding up the array
            dp[i][0] = dp[i-1][0] + grid[i][0]; 
        }
        
        for(int j=1;j<n; j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        //we have initilized the row with index 0 and colomn with index 0
        
        for(int i=1;i<m;++i){
            for(int j=1; j<n; ++j){
                //we are trying to find the minimum path !!!!
                if(dp[i-1][j] > dp[i][j-1]){
                    //we are adding up the minimum source so far!!! source!!!!
                    //wrong!!!!  we are calculating with the matrix!!! dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                    //we are adding the source so far with the current position!!!
                }else{
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
            }
        }
        
        //return the last one!!!
        return dp[m-1][n-1];        
        
    }
}



public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        //i-1 means it comes from the previous level
        //j or j-1 means it is adjacent to the current position
        //
        //sum[i][j]=min(sum[i-1][j-1],sum[i-1][j]) + triangle[i][j]
        //
        if(triangle.size() ==0 ||triangle == null){
            return 0;
        }
        if(triangle.size() == 1){
            return triangle.get(0).get(0);//the first level must only have 1 element
        }
        //换个角度考虑一下，如果这道题不自顶向下进行动态规划，而是放过来自底向上来规划，递归式只是变成下一层对应的相邻两个元素的最小路径和加上自己的值，原理和上面的方法是相同的，这样考虑到优势在于不需要最后对于所有路径找最小的，而且第一个元素和最后元素也不需要单独处理了，所以代码简洁了很多。代码如下：
         int[] res = new int[triangle.size()];  
          for(int i=0;i<triangle.size();i++)  {
              //add each element in the last level into the res[i]
              res[i] = triangle.get(triangle.size()-1).get(i);  
          }
          
          //starting from the one previous  to the last level
          for(int i=triangle.size()-2;i>=0;i--) 
          {
              for(int j=0;j<=i;j++){
                  //because the index of j on the up level
                  //is the adjacent to the j and j+1 in the low level
                  //
                  //so we have res[j] = Math.min(res[j], res[j+1]) + triangle.get(i).get(j);
                  //here res is initialized with the elements in the last level
                  //and the # of the elements in the last level == the total # of levels in the triangle
                  //
                  res[j] = Math.min(res[j],res[j+1])+triangle.get(i).get( j );   
              }
          }
        
        
         return res[0];
         }
}


public class Solution {
    public int lengthOfLIS(int[] nums) {
        //dynamic programming: 1.state 2. transition function 3.initialization 4. answer
        //max/min   whether have a feasible solution?  data could not be adjusted postion
        //if find subset or permutation :: all possible solutions: not DP!!! that is backtracking / recursive
        
        //f[i] means the LIS in the first i elements
        //f[i] = { 1 + max f[j] }    j<i  && nums[j]<nums[i]
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
        int max =0; 
        
        for(int i=1; i <nums.length; i++){
            for(int j=0; j<i; j++){
                //if(nums[j] < nums[i] && dp[i] < dp[j] +1)
                //i is going forward and check each j from 0 to i-1
                if(nums[j] < nums[i] && dp[i] <= dp[j]){
                    dp[i] = dp[j] +1;
                   // max = Math.max(max,dp[i] );
                }
            }
        }
        
        
        for(int i=0; i<dp.length; ++i){
            if(dp[i] > max) max = dp[i];
        }
        
        return max;
        //get the max number 
        
    }
}


public class Solution {
    public int climbStairs(int n) {
        // if(n == 1){
        //     return 1;
        // }
        
        // if(n==2){
        //     return 2;
        // }
        
        // return climbStairs(n-1) + climbStairs(n-2);
        if(n<3){
            return n;
        }
        //相当于fibonacci数列问题，难点就是要会思维转换，转换成为递归求解问题，多训练就可以了。
        //but the first two is not 0,1
        //they became 2,3
        // int prepre=1;
        // int pre=2;
        // int sum = -1;
        // for(int i =3 ; i<=n; i++){
        //     sum = pre+prepre;
        //     prepre=pre;
        //     pre = sum;
        // }
        
        // return sum;
        
        //because we need 1 ... n 
        //we need to new n+1
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2; i<n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[dp.length-1];
        
        
    }
}

public class Solution {
    public int numSquares(int n) {
        //任意一个正整数均可表示为4个整数的平方和，其实是可以表示为4个以内的平方数之和，那么就是说返回结果只有1,2,3或4其中的一个，首先我们将数字化简一下，由于一个数如果含有因子4，那么我们可以把4都去掉，并不影响结果，比如2和8,3和12等等，
         while (n % 4 == 0) n /= 4;
        // 还有一个可以化简的地方就是，如果一个数除以8余7的话，那么肯定是由4个完全平方数组成，这里就不证明了，因为我也不会证明，读者可自行举例验证。
         if (n % 8 == 7) return 4;
         for (int a = 0; a * a <= n; ++a) {
             
             //我们就来尝试的将其拆为两个平方数之和，如果拆成功了那么就会返回1或2，因为其中一个平方数可能为0. (注：由于输入的n是正整数，所以不存在两个平方数均为0的情况)。注意下面的!!a + !!b这个表达式，可能很多人不太理解这个的意思，其实很简单，感叹号!表示逻辑取反，那么一个正整数逻辑取反为0，再取反为1，所以用两个感叹号!!的作用就是看a和b是否为正整数，都为正整数的话返回2，只有一个是正整数的话返回1，
             int b = (int)Math.sqrt(n - a * a);
             if (a * a + b * b == n) {
                 //check whether a and b both greater than 0 strictly
                 //
                 //here we could possibly return 1 or 2 
                 //because one of them could be 0
                return a > 0 && b>0 ? 2: 1;
            }
         }
         
         //no 1,2,4
         //only could possibly be 3
         return 3;
    }
}