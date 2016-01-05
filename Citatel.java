ask some questions
what is a typical day in your company as a software engineer

		//int[][] arr = new int[][]{
		//		{ 1, 2, 3},
		//		{ 4, 5, 6 },
		//		{ 7, 8, 9}};
        //[ 1, 2, 3 ],
        //[ 4, 5, 6 ],
        //[ 7, 8, 9 ]
        //the matrix here is actually
        //index is 0 it is the first row [ 1, 2, 3 ]
Priority Queue (Heap)
Insert
 O(lg n) worst-case
 O(1) amortized time if insertion is done in an
uninterrupted sequence (i.e., without being
intervened by deleteMins)
 DeleteMin, FindMin
 O(lg n) worst-case
 Merge
 O(lg n) worst-case

题目的意思是整个过程中只能买一只股票然后卖出，也可以不买股票。也就是我们要找到一对最低价和最高价，最低价在最高价前面，以最低价买入股票，以最低价卖出股票。
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
    public int maxProfit(int[] prices) {
        //calculate every continuous difference between down and high
        //we need to be very carefully
        //we could see the array as a time series 
        //then we just need to add the difference where the later is larger than the first
        if(prices == null || prices.length<=1)  return 0;
        int profit =0;
        for (int i=1; i<prices.length; i++){
            int diff = prices[i] - prices[i-1];        
            if( diff>0 ){
                //profit+ = diff;
                //never write this wat
                profit += diff;
            } 
        }
        return profit;
    }
}

C[i][j] = A[i][0] * B[0][j] + A[i][1] * B[1][j] + A[i][2] * B[2][j] + .... A[i][e-1] * B[e-1][j] 

Both row i and column j have e elements. An loop is used whose counter k, ranges from 0 to e-1. 
Within the loop, A[i][k] and B[k][j] are multiplied and the product obtained is added to the existing value of C[i][j]. 
We can also declare a variable sum before the start of the innermost loop, add the element wise products to this variable a
nd assign the resulting sum to C[i][j]. 

The complete Java program for matrix multiplication is given below. 

import java.util.Scanner;

public class MatrixMultiplication {

   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       System.out.print("Enter number of rows in A: ");
       int rowsInA = s.nextInt();
       System.out.print("Enter number of columns in A / rows in B: ");
       int columnsInA = s.nextInt();
       System.out.print("Enter number of columns in B: ");
       int columnsInB = s.nextInt();
       int[][] a = new int[rowsInA][columnsInA];
       int[][] b = new int[columnsInA][columnsInB];
       System.out.println("Enter matrix A");
       for (int i = 0; i < a.length; i++) {
           for (int j = 0; j < a[0].length; j++) {
               a[i][j] = s.nextInt();
           }
       }
       System.out.println("Enter matrix B");
       for (int i = 0; i < b.length; i++) {
           for (int j = 0; j < b[0].length; j++) {
               b[i][j] = s.nextInt();
           }
       }
       int[][] c = multiply(a, b);
       System.out.println("Product of A and B is");
       for (int i = 0; i < c.length; i++) {
           for (int j = 0; j < c[0].length; j++) {
               System.out.print(c[i][j] + " ");
           }
           System.out.println();
       }
   }

   public static int[][] multiply(int[][] a, int[][] b) {
       int rowsInA = a.length;
       int columnsInA = a[0].length; // same as rows in B
       int columnsInB = b[0].length;
       int[][] c = new int[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
               }
           }
       }
       return c;
   }
}


Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

题解：
这道题跟Spiral Matrix想法也是类似的，就是依照矩阵从外圈到内圈建立。
要考虑如果是奇数行列的话，最中心的那个点要单加。
public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int k = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (left < right && top < bottom) {
            for (int j = left; j < right; j++) {
                res[top][j] = k++;
            }
            for (int i = top; i < bottom; i++) {
                res[i][right] = k++;
            }
            for (int j = right; j > left; j--) {
                res[bottom][j] = k++;
            }
            for (int i = bottom; i > top; i--) {
                res[i][left] = k++;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        if (n % 2 != 0)
            res[n / 2][n / 2] = k;
        return res;
    }
  }

  public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {  
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) return result;
        //the row and col could be different we need 
        //to make sure this point
        int row = matrix.length;
        int col = matrix[0].length;
        int x=0;
        int y=0;   
        while(row>0 && col>0){
            //if the row/column left out, no circle -- 
            //we just iterate through this 
            if(row == 1){
                for(int i=0; i<col; ++i){
                    result.add(matrix[x][y++]);
                }
                break;          
            }else if(col==1){
                //only one column left out
                for(int i=0;i<row;++i){
                    result.add(matrix[x++][y]);
                }
                break;
            }
            //below, process a circle
            //top ->>>>> right
            for(int i=0; i<col-1;i++){
                result.add(matrix[x][y++]);
            }
            //right ->>>>>>>> down
            for(int i=0; i<row-1;++i){
                result.add(matrix[x++][y]);   
            }
            //down ->>>>>>>>left
            for(int i=0; i<col-1; ++i){
                //index one is for nth row
                //index two is for nth col
                result.add(matrix[x][y--]);
            }
            //left->>>>>>>>>>>>>up
            for(int i=0;i<row-1;++i){
                //do not be silly this is matrix
                //where the x and y is different from x and y in 
                //mathmatic  here x is just the y!!!!
                result.add(matrix[x--][y]);
            }    
            x++;
            y++;
            row=row-2;
            col=col-2;
        }
        return result;
        
    }
}

public class Solution {
    public int myAtoi(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        String trim = str.trim();
        int len =trim.length();
        int sign=1;
        //i is the scanner from the very beginning  -- it firstly checks the sign of the string
        int i=0;
        //we need use ' ' and  == for checking char
        //we need to use .equals("string")
        if(trim.charAt(0) == '+'){
            i++;
        }else if(trim.charAt(0) == '-'){
            sign = -1;
            i++;
        }
        //set as long to avoid overflow
        long result = 0;
        while(i<len){
            if(trim.charAt(i) < '0' || trim.charAt(i) >'9'){
                break;
            }
            result = 10*result + sign*(trim.charAt(i) - '0');
            //in the middle we need to check whether it already exceeds the limit of int with long
            if(result > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(result < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int)result; // convert the long back to int
    }
}

public int reverse(int x) {
            boolean isNegative = x < 0;
            x = Math.abs(x);
            int result = 0;
            int max_diff = Integer.MAX_VALUE / 10;
            while(x > 0) {
                if(result > max_diff) return 0;
                result = 10*result + x % 10;
                x /= 10;
            }
            return isNegative ? -result : result;
        }
    }
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //firstly search vertically -- determine row
        //secondly search horizontally -- determine col
        if(matrix == null || matrix.length == 0){
            return false;
        }
        
        int l = 0;
        int r = matrix.length-1;
        //search vertically
        while(l<=r){
            int mid1 = (l+r)/2;
            if(matrix[mid1][0] == target){
                //best
                return true;
            }else if(matrix[mid1][0] < target){
                l = mid1+1;
            }else{
                r = mid1-1;
            }
        }
        //out of each binary search we need to early return 
        //lazy evaluation
        if(r<0){
            return false;
        }
        
        //when l and r merge together
        //we need to pick up r!!!!!!!!
        //就是当循环结束时，如果没有找到目标元素，
        //那么l一定停在恰好比目标大的index上，r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式。
        //and then search row as r
        int row = r;
        l = 0;
        r = matrix[row].length-1;
        //search horizontally
        while(l<=r){
            int mid2 = (l+r)/2;
            if(matrix[row][mid2] == target){
                return true;
            }else if(matrix[row][mid2] < target){
                l = mid2+1;
            }else{
                r = mid2-1;
            }
        }
        
        //out of this while 
        //there is no chance to do more binary search
        return false;
    }
}

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //从右上角开始, 比较target 和 matrix[i][j]的值. 如果小于target, 则该行不可能有此数,  所以i++; 如果大于target, 则该列不可能有此数, 所以j--. 
        //遇到边界则表明该矩阵不含target.
        if(matrix.length ==0 || matrix == null){
            return false;
        }
        
        //search from the right up corner
        //here we just need to traverse o(m+n)
        int row =0;
        int col = matrix[0].length-1;
        
        //inside the board
        while(row<=matrix.length-1 && col >=0){
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] < target){
                //could not be on this row
                row++;
            }else{
                //could not be on this col
                col--;
            }
        }
     
        //out of the search
        return false;
    }
}

 Sparse Matrix Multiplication
 Naive solution. Just do the matrix multiply by definition. Check if non zero during the calculation.
 In numerical analysis, a sparse matrix is a matrix in which most of the elements are zero. 
 By contrast, if most of the elements are nonzero, then the matrix is considered dense.

public int[][] multiply(int[][] A, int[][] B) {
    int n = A.length;
    int m = A[0].length;
    int p = B[0].length;
    int[][] AB = new int[n][p];

    for (int k = 0; k < m; ++k)
        for (int i = 0; i < n; ++i)
            if (A[i][k] != 0)
                for (int j = 0; j < p; ++j)
                    if (B[k][j] != 0)
                        AB[i][j] += A[i][k] * B[k][j];

    return AB;
}

 for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
               }
           }
       }
 return c;

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
public class PrimeNumber{
  // This method tests whether a given number is prime or not.
  public static boolean isPrime ( int num ){
    boolean prime = true;
    int limit = (int) Math.sqrt ( num );  
    for ( int i = 2; i <= limit; i++ ) {
      if ( num % i == 0 ){
        prime = false;
		break;
      }
    }
    return prime;
  }

  public static void main ( String[] args )
  {
    // This loop writes out all the prime numbers less than 1000.
    for ( int i = 2; i <= 1000; i++ )
    {
      if ( isPrime ( i ) )
	  System.out.println ( i );
    }
  }
}

Finally, we know 2 is the "oddest" prime - it happens to be the only even prime number. 
Because of this, we need only check 2 separately, then traverse odd numbers up to the square root of n.
‘ In the end, our code will resemble this:

//checks whether an int is prime or not.
boolean isPrime(int n) {
    //check if n is a multiple of 2
    if (n%2==0) return false;
    //if not, then just check the odds
    for(int i=3;i*i<=n;i+=2) {
        if(n%i==0)
            return false;
    }
    return true;
}
As you can see, weve gone from checking every integer (up to n to find out that a number is prime) to just 
checking half of the integers up to the square root (the odd ones, really). This is a huge improvement,
 especially considering when numbers are large.

And yes, Java indeed implement a collision resolution technique. When two keys get hashed to a same value 
(as the internal array used is finite in size and at some point the hashcode() method will return same hash 
value for two different keys) at this time, a linked list is formed at the bucket location where all the 
informations are entered as an Map.Entry object that contains a key-value pair. Accessing an object 
via a key will at worst require O(n) if the entry in present in such a lists. Comparison between the key
 you passed with each key in such list will be done by the equals() method.

 When you insert the pair (10, 17) and then (10, 20), there is technically no collision involved. You are just 
 replacing the old value with the new value or a given key 10 (since in both the cases, 10 is equal to 10 and 
 	also the hash code for 10 is always 10).

Collision happens when multiple keys hash to the same bucket. In that case, you need to make sure that you can 
distinguish between those keys. Chaining collision resolution is one of those techniques which is used for this. 
As as example, lets suppose that two strings abra ka dabra and wave my wand yield hash codes 100 and 200 
respectively. Assuming the total array size is 10, both of them end up in the same bucket (100 % 10 and 200 % 10). 
Chaining ensures that whenever you do map.get( abra ka dabra );, you end up with the correct value associated with the key. 
In the case of hash map in Java, this is done by using the equals method.

Although, from Java 8 , the linked lists are replaced with trees (O(log n))

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
            }public class Solution {
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
