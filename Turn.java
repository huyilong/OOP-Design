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