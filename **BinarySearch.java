Search Insert Position


///Binary Search!!!!! O(logN) and must be sorted beforehand!!!!

Given a sorted array and a target value, return the index if the target is found. 
f not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0


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



/////or we can do sth like this
public int[] searchRange(int[] A, int target) {
    int[] res = new int[2];
    res[0] = -1;
    res[1] = -1;
    if(A==null || A.length==0)
    {
        return res;
    }
    int l=0;
    int r=A.length-1;
    int m=(l+r)/2;
    while(l<=r)
    {
        m=(l+r)/2;
        if(A[m]==target)
        {
            res[0]=m;
            res[1]=m;
            break;
        }
        else if(A[m]>target)
        {
            r = m-1;
        }
        else
        {
            l = m+1;
        }
    }
    if(A[m]!=target)
        return res;
    int newL = m;
    int newR = A.length-1;
    while(newL<=newR)
    {
        int newM=(newL+newR)/2;
        if(A[newM]==target)
        {
            newL = newM+1;
        }
        else
        {
            newR = newM-1;
        }            
    }
    res[1]=newR;
    newL = 0;
    newR = m;
    while(newL<=newR)
    {
        int newM=(newL+newR)/2;
        if(A[newM]==target)
        {
            newR = newM-1;
        }
        else
        {
            newL = newM+1;
        }            
    }
    res[0]=newL;        
    
    return res;
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