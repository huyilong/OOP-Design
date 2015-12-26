Climbing Stairs  
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

简单题目，相当于fibonacci数列问题，难点就是要会思维转换，转换成为递归求解问题，多训练就可以了。
所以这种类型的题目相对于没有形成递归逻辑思维的人来说，应该算是难题。
我的想法是：
每次有两种选择，两种选择之后又是各有两种选择，如此循环，正好是递归求解的问题。
写成递归程序其实非常简单，三个语句就可以：
[cpp] view plaincopyprint?在CODE上查看代码片派生到我的代码片
int climbStairsRecur(int n) {  
        if (n == 1) return 1;  
        if (n == 2) return 2;  
        return climbStairsRecur(n-1) + climbStairsRecur(n-2);  
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

    

但是递归程序一般都是太慢了，因为像Fibonacci问题一样，重复计算了很多分支，我们使用动态规划法填表，提高效率，程序也很简单，如下：
[cpp] view plaincopyprint?在CODE上查看代码片派生到我的代码片
int climbStairs(int n)  
    {  
        vector<int> res(n+1);  
        res[0] = 1;  
        res[1] = 1;  
        for (int i = 2; i <= n; i++)  
        {  
            res[i] = res[i-1] + res[i-2];  
        }  
        return res[n];  
    }  

动态规划法用熟了，高手就需要节省空间了，如下：
[cpp] view plaincopyprint?在CODE上查看代码片派生到我的代码片
int climbStairs2(int n)  
    {  
        vector<int> res(3);  
        res[0] = 1;  
        res[1] = 1;  
        for (int i = 2; i <= n; i++)  
        {  
            res[i%3] = res[(i-1)%3] + res[(i-2)%3];  
        }  
        return res[n%3];  
    }  

当然，不使用上面的数组也是可以的，直接使用三个变量保存结果也是一样的。
[cpp] view plaincopyprint?在CODE上查看代码片派生到我的代码片
//2014-2-10 update  
    int climbStairs(int n)  
    {  
        if (n < 4) return n;  
        int a = 2, b = 3, c = 5;  
        for (int i = 5; i <= n; i++)  
        {  
            a = c;  
            c = b+c;  
            b = a;  
        }  
        return c;  
    }  
