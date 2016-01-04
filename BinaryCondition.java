这个我有一个很好的方法，是这样的，我认为所有情况下都可以用 left = mid + 1 和 right = mid - 1

并且这样子code是最高效的，而且看上去十分简洁，

一般binary search 就处理两种情况：
第一种情况：假设数组是按从小到大的顺序排列，从数组v中找一个元素，exactly 和 target相等，
那么code是这样(返回 index)

int index(vector<int> v, int target) {
    int left = 0;
    int right = int(v.size()) - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (v[mid] > target) {
            right = mid - 1;
        } else if (v[mid] < target) {
            left = mid + 1;
        } else {
            return mid;
        }
    }
    return -1;
}


第二种情况：我们有个性质叫 f, 是用一个函数来表示， f(x) 为真，表示x具有此性质， f(x)为假，表示x不具有此性质，举个例子：f(x) := x > 3 就表示 “x是否>3
这个性质，然后呢？我们需要假设v具有某种二分性质，其中所有满足 f(x)为假的x都在左半边， f(x)为真的都在右半边，
现在要求找出 “第一个 f(x)为真的 那个x” (leetcode: first bad version)，那么code是这样的：

int index(vector<int> v, int target) {
    int left = 0;
    int right = int(v.size()) - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (f(v[mid])) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

注意这个code处理了所有情况，其中left就是所要求的index，那如果数组中所有元素都满足f(x)为假会怎么样呢？
这时候 left最终输出的值就是 数组的size，也就是 v.end() 对应的position，可以理解为 “数组中第一个满足f(x)性质的元素的index是 v.end()”，其实就是说所有v[i] 都不满足性质，是不是非常和谐？哈哈

如果在情况2里面 不使用 +-1, 而用一些类似 right = mid; 这样的东西，那最后还要根据mid的值来判断到底是怎样的情况，code就不elegent了，
另外对于 “找出最后一个不满足 f(x) 性质” 的code，完全可以类似以上情况2去写， 最终结果返回如果是 -1 的话，说明所有v[i]都满足性质。


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) { 
        int left = 1;
        int right = n;
        
        while (left <= right) {
          int mid = left + (right - left) / 2;
          if (isBadVersion(mid)){//f(v[mid])) {
             right = mid - 1;
          } else {
            left = mid + 1;
            }
         }
         return left;
    
    }
}

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


public class Solution {
    public int missingNumber(int[] nums) {
        
        Arrays.sort(nums);
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] != mid) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] != start) {
            return start;
        }
        
        if (nums[end] != end) {
            return end;
        }
        
        return end + 1;
    }
}
二分查找的话题目很多 所以应该用不变应万变的原则.
所以基本是三个原则
start + 1 < end
更新首尾的时候, 永远只用 start = mid 和 end = mid
退出循环之后, 验证的顺序要写对


如果遵守了前两条, 那么循环退出之后就会有两个值: 一个 start 一个 end 需要验证.

这个题的话, 把题目改成二分查找的说法就是: 找出第一个 number[index] != index 的数.


所以第三条说的验证顺序, 必须考虑到所有的结束状态, 然后综合结束状态来确认返回值.
应该先验证 start , 如果 start 符合, 就返回.    E.g.: [0,1,2,4,5] start = 3, end = 4.
如果 start 不符合, 再验证 end.                     E.g.: [0,1,2,4,5] start = 2, end = 3.
如果 end 也不符合.                                     E.g.: [0,1,2,3] start = 2, end = 3.


做了，就是你上面回复的那道题吧。这道题他返回的turning point是返回的min的index，比如说[4, 5, 6, 1, 2, 3]中，返回的是3，因为nums[3]=1.你代入算一下就知道了。
我觉得不能返回min的index-1的原因是，有可能这个数组没有rotate，那么你就会返回0-1，就出错了。
这道题后面再用binary search来寻找target的时候，根据target的位置，要么在[4,5,6,1]中进行binary search，要么在[1,2,3]中进行binary search。对于这个时候的binary search就是用的ls的大神讲的第一种方法。我觉得之所以可以直接用[4,5,6,1]进行binary search，对于int mid=(lo+hi)/2，mid会把小数点后面的数字抛弃，所以mid永远不会走到最后那里去。
根据我现在做题，总结的规律就是。
1.和上面大神说的一样，如果有一个target，当nums[mid]==target，就返回的时候，就写成while(lo<=hi)。lo=mi+1.   hi=mid-1
2.没有有一个target，当nums[mid]==target，需要返回的话。即肯定要把这个array全部都进行binary search（比如刚刚你说的rotate point和我说的那个missing number），就用的是while(lo<hi). lo=mid+1. hi=mid
我也很弱，具体为什么我也不清楚，只是看别人答案总结的，有可能因为样本不够大，所以是错误的，欢迎大家批评指正！

Union and Intersection of two sorted arrays
Given two sorted arrays, find their union and intersection.

For example, if the input arrays are: 
arr1[] = {1, 3, 4, 5, 7}
arr2[] = {2, 3, 5, 6}
Then your program should print Union as {1, 2, 3, 4, 5, 6, 7} and Intersection as {3, 5}.
Algorithm Union(arr1[], arr2[]):
For union of two arrays, follow the following merge procedure.
1) Use two index variables i and j, initial values i = 0, j = 0
2) If arr1[i] is smaller than arr2[j] then print arr1[i] and increment i.
3) If arr1[i] is greater than arr2[j] then print arr2[j] and increment j.
4) If both are same then print any of them and increment both i and j.
5) Print remaining elements of the larger array.

#include<stdio.h>
 
/* Function prints union of arr1[] and arr2[]
   m is the number of elements in arr1[]
   n is the number of elements in arr2[] */
int printUnion(int arr1[], int arr2[], int m, int n)
{
  int i = 0, j = 0;
  while (i < m && j < n)
  {
    if (arr1[i] < arr2[j])
      printf(" %d ", arr1[i++]);
    else if (arr2[j] < arr1[i])
      printf(" %d ", arr2[j++]);
    else
    {
      printf(" %d ", arr2[j++]);
      i++;
    }
  }
 
  /* Print remaining elements of the larger array */
  while(i < m)
   printf(" %d ", arr1[i++]);
  while(j < n)
   printf(" %d ", arr2[j++]);
}
 
/* Driver program to test above function */
int main()
{
  int arr1[] = {1, 2, 4, 5, 6};
  int arr2[] = {2, 3, 5, 7};
  int m = sizeof(arr1)/sizeof(arr1[0]);
  int n = sizeof(arr2)/sizeof(arr2[0]);
  printUnion(arr1, arr2, m, n);
  getchar();
  return 0;
}
Run on IDE
Time Complexity: O(m+n)

Algorithm Intersection(arr1[], arr2[]):
For Intersection of two arrays, print the element only if the element is present in both arrays.
1) Use two index variables i and j, initial values i = 0, j = 0
2) If arr1[i] is smaller than arr2[j] then increment i.
3) If arr1[i] is greater than arr2[j] then increment j.
4) If both are same then print any of them and increment both i and j.

#include<stdio.h>
 
/* Function prints Intersection of arr1[] and arr2[]
   m is the number of elements in arr1[]
   n is the number of elements in arr2[] */
int printIntersection(int arr1[], int arr2[], int m, int n)
{
  int i = 0, j = 0;
  while (i < m && j < n)
  {
    if (arr1[i] < arr2[j])
      i++;
    else if (arr2[j] < arr1[i])
      j++;
    else /* if arr1[i] == arr2[j] */
    {
      printf(" %d ", arr2[j++]);
      i++;
    }
  }
}
 
/* Driver program to test above function */
int main()
{
  int arr1[] = {1, 2, 4, 5, 6};
  int arr2[] = {2, 3, 5, 7};
  int m = sizeof(arr1)/sizeof(arr1[0]);
  int n = sizeof(arr2)/sizeof(arr2[0]);
  printIntersection(arr1, arr2, m, n);
  getchar();
  return 0;
}
Run on IDE
Time Complexity: O(m+n)
