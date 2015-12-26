
//find turning point in dups
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



//find min in roatated - nodups
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


//find min in roatated - allow dups
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



//////////search in rotated array
//allow dups
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
                //dup
                end--;
            }
        
        }
        
        return false;
    }
}


//no dups
public class Solution {
    public int search(int[] nums, int target) {
        int start =0, end=nums.length-1;
        while(start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target){
                return mid;
            }
            
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