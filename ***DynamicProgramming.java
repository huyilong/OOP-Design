public class Solution {
    public boolean canJump(int[] nums) {
        if(nums==null || nums.length == 0){
            return false;
        }
        
        int global_max= nums[0];
        
        //we get this global max to record the max
        for(int i=1; i<nums.length; i++){
            if(i>global_max){
                return false;
                //we cannot reach this point based on all the past record
            }else{
                global_max = Math.max(global_max, i+nums[i]);//which one is larger
            }
        }
        
        return global_max >= nums.length-1 ? true:false;
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

public class Solution {
    public int maxSubArray(int[] nums) {
            if(nums.length == 0 || nums==null){
            return 0;
        }
        
        int local_max = nums[0];
        int global_max = nums[0];
        
        for(int i=1; i<nums.length; i++){
            local_max = Math.max(nums[i], local_max + nums[i]);
            global_max = Math.max(local_max, global_max);
            
        }
        
        return global_max;
    }
}

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

There are a row of n houses, each house can be painted with one of the three colors: 
red, blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on… 
Find the minimum cost to paint all houses.

/*
Dynamic Programming
subproblem: paint a single house, #subproblem = #house
guess: use one of 3 colors #choice = 3
relation: to paint house[i]
use different color from house[i-1]
use valid with minimum cost
dp[i][color] = cost[i][color] + min(dp[i-1][color1], dp[i-1][color2])
//there are three colors in total
topological order: row by row and left to right
solution: min(dp[end][color] for each color)
*/

public class Solution {
    public int minCost(int[][] costs) {
        if(costs==null || costs.length==0 || costs[0].length==0) {
            return 0;
        }
        int row = costs.length;
        int col = costs[0].length;
        int[][] dp = new int[row][col];

        //initialization
        for(int i=0; i<col; i++) {
            dp[0][i] = costs[0][i];
        }

        //the rows are different houses
        //the three colomn are three different colors
        //paint each house
        for(int i=1; i<row; i++) {
            //dp[i][color] = cost[i][color] + min(dp[i-1][color1], dp[i-1][color2])
            //use different color with minium cost
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        //find the minimum of the three numbers
        //we are using nested min to find the min of three numbers
        return Math.min(Math.min(dp[row-1][0], dp[row-1][1]), dp[row-1][2]);
    }
}


public class Solution{
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
                //only two source
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        
        //return the last one!!!
        return dp[m-1][n-1];        
        
    }
}

public class Solution {
    public int maximalSquare(char[][] matrix) {
        //This problem can be solved by dynamic programming. The changing condition is:
        //t[i][j] = min(t[i][j-1], t[i-1][j], t[i-1][j-1]) + 1. It means the square formed before this point.
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
    int n = matrix[0].length;
 
    int[][] t = new int[m][n];
 //Character.getNumericValue(char...);
    //top row
    for (int i = 0; i < m; i++) {
        t[i][0] = Character.getNumericValue(matrix[i][0]);
    }
 
    //left column
    for (int j = 0; j < n; j++) {
        t[0][j] = Character.getNumericValue(matrix[0][j]);
    }
 
    //cells inside
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == '1') {
                int min = Math.min(t[i - 1][j], t[i - 1][j - 1]);
                min = Math.min(min,t[i][j - 1]);
                t[i][j] = min + 1;
            } else {
                t[i][j] = 0;
            }
        }
    }
 
 ///we do not have the global view and therefore we need to iterate through 
 //again --- similar to maximum sum subarray if we use 1-D array dp
    int max = 0;
    //get maximal length
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (t[i][j] > max) {
                max = t[i][j];
            }
        }
    }
    return max * max; 
    }
}


public class NumMatrix {
    private int[][] sum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        sum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
         return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
    }
}

public class NumArray {
    private int[] sum;
    
    public NumArray(int[] nums) {
        //sum[i] denotes sum(nums[0], …, nums[i – 1])
        sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i]; 
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);

public class Solution {
    public int rob(int[] nums) {
        //这道题的本质相当于在一列数组中取出一个或多个不相邻数，使其和最大。那么我们对于这类求极值的问题首先考虑动态规划Dynamic Programming来解，我们维护一个一位数组dp，其中dp[i]表示到i位置时不相邻数能形成的最大和，经过分析，我们可以得到递推公式dp[i] = max(num[i] + dp[i - 2], dp[i - 1]), 由此看出我们需要初始化dp[0]和dp[1]，其中dp[0]即为num[0]，dp[1]此时应该为max(num[0], num[1])，代码如下
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
-------------------------Math problem ---------------
public class Solution {
    public int nthUglyNumber(int n) {
        /*
        这道题是之前那道Ugly Number 丑陋数的延伸，这里让我们找到第n个丑陋数，还好题目中给了很多提示，基本上相当于告诉我们解法了，根据提示中的信息，我们知道丑陋数序列可以拆分为下面3个子列表：

(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …

仔细观察上述三个列表，我们可以发现每个子列表都是一个丑陋数乘以2,
3,5，而这些丑陋数的值就是从已经生成的序列中取出来的，我们每次都从三个列表中取出当前最小的那个加入序列，请参见代码如下：
*/
            if(n==1) return n;
            Queue<Long> q = new PriorityQueue<Long>();
            int[] nums = {2,3,5};
            Long result = Long.valueOf(1);
            q.offer(result);
            for(int i=0;i<n;i++){
                // Each time we poll the peak value of q, is the ith number 
                result = q.poll();
                for(int num:nums){
                    Long uglyNum = result*num;
                    if(!q.contains(uglyNum)){
                        q.offer(uglyNum);
                    }
                }
            }
            return result.intValue();
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

public class Solution {
    public int climbStairs(int n) {
        // if(n == 1){
        //     return 1;
        // }
        
        // if(n==2){
        //     return 2;
        // }
        
        // return climbStairs(n-1) + climbStairs(n-2);
        recursion above is correct but time exceed

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

---------------- difficult dp = new dp[len+1] record so far [0, i-1]----------
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        /*首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，
        我们来获得res[i]的表达式。思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，*/
        /*那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。代码如下：*/
        if(s == null || s.length()== 0 ){
            return true;
        }
        
        //why +1?
        boolean[] dp = new boolean[s.length()+1];
        
        dp[0] = true;
        for(int i=0; i<=s.length(); i++){
            //for each i we need to search from the past states
            //from and when dp[j] is true and also wordDict is true
            //then dp [i] is also true
            //j is always start from i-1 and then to 0
            for(int j=0; j<i; j++){
            //for(int j =i-1; j>=0; --j){
                String word = s.substring(j,i);
                if(dp[j] && wordDict.contains(word)){
                    dp[i] = true;
                    //break just jump out of the current loop
                    //dynamic programming
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

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
        
        for(int i=2;i<s.length()+1; i++){
            if(decode(s.substring(i-1,i))){
                dp[i] = dp[i] + dp[i-1];
            }
            if(decode(s.substring(i-2,i))){
                dp[i] = dp[i] + dp[i-2];
            }
        }
        
        //s.length()+1
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

        We still do not have the global view because string can start at anywhere
        for(int i=0; i<dp.length; ++i){
            if(dp[i] > max) max = dp[i];
        }
        
        return max;  
    }
}

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0 || coins == null){
            return -1;
        } 
        //we firstly want to sort the array based on big to small so that
        //each time we try the big value as big as possible at first to save time
        // Comparator<Integer> cmp = new Comparator<Integer>(){
        //     public int compare(int o1, int o2){
        //         return o2 - o1;//big to small
        //     }
        // };
        
        // Arrays.sort(coins, Collections.reverseOrder());
        int res = helper(coins, amount);
        return res;
    }
    
    private int helper(int[] coins, int target){
        if(target == 0){
            //here we reach the bottom
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++){            
            if(coins[i] <= target){
                //recursion here to use that coin
                int temp = helper(coins, target-coins[i]);
                if(temp+1<min && temp+1<= Integer.MAX_VALUE){
                    min = temp+1;
                }
            }
        }      
        return min;
    }
}
