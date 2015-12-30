
//binary search
/*
Worst case space complexity‎: ‎O(1) Best case performance‎: ‎O(1)
Average case performance‎: ‎O(log n)    Worst case performance‎: ‎O(log n)
*/
//binary-search iterative
public int helper(int[] nums, target){
    int low = 0;
    int high = nums.length-1;
    while(low<=high){
        int mid = (low+high)/2;
        if(nums[mid] > target){
            high = mid-1;
        }else if(nums[mid] < target){
            low = mid+1;
        }else{
            return mid;
        }
    }

//////outside the while(low<=high)  but still not found!!!
    return -1;
}


//recursion
public int helper2(int[] nums, int target, int low, int high){
    if(high<low){
        return -1;
    }
    //find the mid of two numbers we need to 
    int mid = (low+high)/2;
    if(target > nums[mid]){
        helper2(nums, target, mid+1, high);
    }else if(target < nums[mid]){
        helper2(nums, target, low, mid-1);
    }else{
        //return the index of the target
        return mid;
    }

}

1111. Rotate an array -- bubble sorted
public class Solution {
    public void rotate(int[] nums, int k) {
        /*
             Can we do this in O(1) space?
            This solution is like a bubble sort.*/
            
        if(nums==null || nums.length == 0 || k<=0){
            return;
        }
        
        //here is the time complexity is O(K*N)  space o(1)
        for(int i=0; i<k; i++){
            //if will control how many times we bubble the things to after
            for(int j = nums.length-1 ; j>0; j--){
                //whenever want to swap and declare temp we must int!!!
                int temp = nums[j];
                nums[j] = nums[j-1];//here is why we need to j>0
                nums[j-1] = temp;
            }
        }
    }
}



Search in rotated array

public class Solution {
    public int search(int[] nums, int target) {

        int start =0, end=nums.length-1;


        while(start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target){
                return mid;
            }
        
            //we could always use the nums[mid]!!!!!!!! and the relationship
            //with the nums[end] to judge whether first half!!!!
            //or second half is sorted!!!! always sorted half!!!!
            //456789123 nums[mid] = 8  nums[end] =3
            if(nums[mid]<nums[end] ){
                //right side is sorted -- we could sandwich
                if(target>nums[mid] && target <= nums[end]){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
                
            }
            
            else{
                if(target>=nums[start] && target < nums[mid]){
                    end=mid-1;
                }else{
                    start = mid+1;
                }
            }
        }
        return -1;
    }
}


Search in rotated array -- have duplicates

public class Solution {
    public boolean search(int[] nums, int target) {
        int start =0, end=nums.length-1;
        while(start <= end){
            int mid = (start+end)/2;
            if(nums[mid] == target){
                return true;
            }
            
            if(nums[mid]<nums[end]){
                //right side is sorted
                if(target>nums[mid] && target<=nums[end]){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }else if(nums[mid] > nums[end]){
                //left side is sorted
                if(target<nums[mid]&& target>=nums[start]){
                    end=mid-1;
                }else{
                    start = mid+1;
                }
            }else{

                //this is the only difference
                end--;
            }
        
        }
        
        return false;
    }
}


11111. Search rotating point -- the turning point in a rotated array
{4,5,6,7,8,9,1,2,3}; --- out put is 9
which is the peak value in the array and also the maximum value in the array
public class Turn{

public static void main(String[] args){
    int[] test = {4,5,6,7,8,9,1,2,3};
    int turning_index = FindRotationPoint(test);
    System.out.println("the turning point is " + test[turning_index]);
}

public static int FindRotationPoint(int[] nums)
{
    int first =0;
    int last = nums.length -1;
    int middle =-1;
    
    while(first<=last)
    {
        //456789123  //should return index = 5
        middle = (first+last)/2;
        // if(middle == last){
        //     break;
        // } 
        if(nums[0]>nums[middle]){ 
            //this means the first half is not sorted
            //we need to find turning point in the first half
        
            last = middle-1;
            
        }
        if (nums[0]<nums[middle]) {
            //this means the first half is sorted
            //which means we need to find the turning point in the second half     
            first = middle+1;
           
        }
    }
    return middle;
    }
}

2222. fIND Peak ELEMENT -- this is not a rotated array!!!!!!
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 
3 is a peak element and your function should return the index number 2.
这题要求我们在一个无序的数组里面找到一个peak元素，
所谓peak，就是值比两边邻居大就行了。

对于这题，最简单地解法就是遍历数组，只要找到第一个元素，大于两边就可以了，复杂度为O(N)。
但这题还可以通过二分来做。

首先我们找到中间节点mid，如果大于两边返回当前index就可以了，
如果左边的节点比mid大，那么我们可以继续在左半区间查找，这里面一定存在一个peak，
为什么这么说呢？假设此时的区间范围为[0, mid - 1]， 
因为num[mid - 1]一定大于num[mid]了，如果num[mid - 2] <= num[mid - 1]，
那么num[mid - 1]就是一个peak。如果num[mid - 2] > num[mid - 1]，
那么我们就继续在[0, mid - 2]区间查找，

因为num[-1]为负无穷，所以最终我们绝对能在左半区间找到一个peak。同理右半区间一样。


public class Solution {
    public int findPeakElement(int[] nums) {
        //对于这题，最简单地解法就是遍历数组，只要找到第一个元素，大于两边就可以了，复杂度为O(N)。但这题还可以通过二分来做。

        //首先我们找到中间节点mid，如果大于两边返回当前index就可以了，如果左边的节点比mid大，那么我们可以继续在左半区间查找，这里面一定存在一个peak，为什么这么说呢？假设此时的区间范围为[0, mid - 1]， 因为num[mid - 1]一定大于num[mid]了，如果num[mid - 2] <= num[mid - 1]，那么num[mid - 1]就是一个peak。如果num[mid - 2] > num[mid - 1]，那么我们就继续在[0, mid - 2]区间查找，因为num[-1]为负无穷，所以最终我们绝对能在左半区间找到一个peak。同理右半区间一样。
        //num[-1] = num[n] = -∞.
        if(nums.length==0 || nums == null){
            return 0;
        }
        
        int l=0;
        int r=nums.length-1;
        while(l<=r){
            int mid= (l+r)/2;
            
            //suppose num[-1] = num[n] = -∞.
            //Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
            if( (mid ==0 || nums[mid] > nums[mid-1]) && (mid==nums.length-1 || nums[mid] > nums[mid+1])){
                return mid;//we find the peak
            }//else if(nums[mid] > nums[mid+1]){
            else if(mid > 0 && nums[mid-1] > nums[mid]){
                //because we already have num[-1] = -infinite
                r = mid-1;
            }else{
                l = mid+1;
            }
            
        }
        
        
        return -1;
    }
}

1. find minimum value in a rotated array -- actually the turning point is
the one right before this min values index
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
This is actually so similar to find the turning point of the rotated array,
which is also to find the maximum value in the rotated array because they
are just neighboring each other!!!! maxs right is the min value in rotated array

public class Solution {
    public int findMin(int[] nums) {
        
        //find minimum in a rotated array
        //this is similar to the binary search
        //o(logn)
        int start =0, end=nums.length-1;
        while(start<end){
            int mid =(start + end)/2;
            if(nums[mid] > nums[end]){
                    //the minumum is in the right side
                    //because mid should < end in a sorted array
                    start= mid+1;
            }else{
                
              end= mid;  
            
            }
        }
        
        return nums[start];
        
    }
}

2. find the min value in the rotated array -- again allow duplicates
just add one line based on the previous solution!!!!
public class Solution {
    public int findMin(int[] nums) {
        //one more case when there is a relationship that A[mid] == A[end] we cannot easily find that the 
        //turning point resides where
        //hence we just update end to be end -- to search from start to end-1
        //instead of start = mid+1; or end=mid;
        
        int start = 0, end = nums.length-1;
        while(start<end){
            int mid = (start +end)/2;
            
            if(nums[mid]>nums[end]){
                //the turning point should be the right side
                //we update start to search
                start = mid +1;
                
            }else if(nums[mid] < nums[end]){
                end =mid;
            }else{
                //dup
                end--;
            }
            
        }
        
        return nums[start];
        
    }
}




Search Insert Position
///Binary Search!!!!! O(logN) and must be sorted beforehand!!!!

Given a sorted array and a target value, return the index if the target is found. 
f not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1

//这道题比较简单，就是二分查找。思路就是每次取中间，如果等于目标即返回，
//否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)。代码如下： 

///given a sorted array!!!!
//binary search must be done over a sorted array!!!!
public int searchInsert(int[] A, int target) {
    if(A == null || A.length == 0)
    {
        return 0;
    }
    int l = 0;
    int r = A.length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(A[mid]==target)
            return mid;
        if(A[mid]<target)
            l = mid+1;
        else
            r = mid-1;
    }

    //here very convenient!!!! because l is exatcly the right
    //position to insert
    return l;
}

	注意以上实现方式有一个好处，就是当循环结束时，如果没有找到目标元素，
	那么l一定停在恰好比目标大的index上，r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式。


Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithms runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


有朋友在留言中提到这里可以只用两次二分查找就足够了，确实如此。 
如果我们不寻找那个元素先，而是直接相等的时候也向一个方向继续夹逼，
如果向右夹逼，最后就会停在右边界，而向左夹逼则会停在左边界，
如此用停下来的两个边界就可以知道结果了，只需要两次二分查找。代码如下： 

public int[] searchRange(int[] A, int target) {
    int[] res = {-1,-1};
    if(A==null || A.length==0)
    {
        return res;
    }
    int ll = 0;
    int lr = A.length-1;
    while(ll<=lr)
    {
        int m = (ll+lr)/2;
        if(A[m]<target)
        {
            ll = m+1;
        }
        else
        {
            lr = m-1;
        }
    }
    int rl = 0;
    int rr = A.length-1;
    while(rl<=rr)
    {
        int m = (rl+rr)/2;

        ///if(A[m]<=target) here is the main point
        //when it is == we still increase rl = m+1
        if(A[m]<=target)
        {
            rl = m+1;
        }
        else
        {
            rr = m-1;
        }
    }
    if(ll<=rr)
    {
        res[0] = ll;
        res[1] = rr;
    }
    return res;
}

//Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix.
 This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.


这道题是二分查找Search Insert Position的题目，因为矩阵是行有序并且列有序，
查找只需要先按行查找，定位出在哪一行之后再进行列查找即可，所以就是进行两次二分查找。
时间复杂度是O(logm+logn)，空间上只需两个辅助变量，因而是O(1)，代码如下：

Firstly we search vertically    logm
Secondly we search horizontally  logN


public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length==0 || matrix[0].length==0)
        return false;
    int l = 0;
    int r = matrix.length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(matrix[mid][0] == target) return true;
        if(matrix[mid][0] > target)
        {
            r = mid-1;
        }
        else
        {
            l = mid+1;
        }
    }
    int row = r;
    if(row<0)
        return false;
    l = 0;
    r = matrix[0].length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(matrix[row][mid] == target) return true;
        if(matrix[row][mid] > target)
        {
            r = mid-1;
        }
        else
        {
            l = mid+1;
        }
    }   
    return false;
}


这道题是经典题, 我在微软和YELP的onsite和电面的时候都遇到了. 
从右上角开始, 比较target 和 matrix[i][j]的值. 如果小于target, 
则该行不可能有此数,  所以i++; 如果大于target, 则该列不可能有此数, 
所以j--. 遇到边界则表明该矩阵不含target.

Challenge
O(m+n) time and O(1) extra space

Write an efficient algorithm that searches for a value in an m x n matrix, 
return the occurrence of it.

This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //这道题是经典题, 我在微软和YELP的onsite和电面的时候都遇到了. 
        //从右上角开始, 比较target 和 matrix[i][j]的值. 如果小于target, 则该行不可能有此数,  所以i++; 如果大于target, 则该列不可能有此数, 所以j--. 遇到边界则表明该矩阵不含target.
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


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        //You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
        if(n==1){
            return 1;
        }
        
        int left=1;
        int right =n;
        while(left+1<right){
            int mid = (left+right)/2;
            if(isBadVersion(mid)){
                right = mid;
            }else{
                left = mid;
            }
            
        }
        
        return isBadVersion(left) ? left:right;
    }
}



//use two maps to implement the doubly linked list!!1
//which is pretty similar to LRU -- this is two maps!!!

public static boolean check(String s,String t){
		if(s.length()!=t.length()) return false;
		HashMap<Character,Character> map1=new HashMap<Character, Character>();
		HashMap<Character,Character> map2=new HashMap<Character, Character>();
		
		for(int i=0;i<s.length();i++){
			char c1=s.charAt(i);
			char c2=t.charAt(i);
			if(map1.containsKey(c1)){
				if(map1.get(c1)!=c2) return false;
			}
			if(map2.containsKey(c2)){
				if(map2.get(c2)!=c1) return false;
			}
			
			map1.put(c1, c2);
			map2.put(c2, c1);
		}
		return true;
	}