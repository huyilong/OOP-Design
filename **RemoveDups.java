//http://www.jiuzhang.com/solutions/remove-duplicates-from-sorted-list-ii/

/**/

General way:  
1/ for dups --- using HashSet
2/ for linkedlist -- using dummy head to record head and head could be used
as scanner since the original head could be tracked by dummy.next
3/ for arrays -- using two pointers we will create a newnew index for the 
newnew array and another one to scan the old array - only care about non-zeros/dups



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//remove dup in a sorted linkedlist I -- dup -> hash set
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // if(head.val == head.next.val){
        //     head.next = null;
        //     return head;
        // }
        
        //once mentioned hash set we need to use data structure like hashset to avoid dups
        
        HashSet<Integer> unique = new HashSet<Integer>();
        unique.add(head.val);
        ListNode cur;
        //cur is to check value
        ListNode pre = head;
        //pre is also the scanner and also the previous node for the current node 
        //once find dups we need to pre.next = cur.next
        //otherwise just do pre = pre.next
        for( cur = head.next ; cur!=null; cur = cur.next){
            
            if(!unique.contains(cur.val)){
                //this is a unque value
                unique.add(cur.val);
                pre = pre.next;
            }else{
                
                pre.next = cur.next;
            }
            //originally
            //I wrote pre = pre.next; here
            //but the thing is that we should not carry on the pre 
            //when there is a duplicate afterwards
            
        }
        
        return head;
    }
}


//remove dup in a sorted linkedlist II -- once find one dup 
//remove them all as along with the original value
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //whenever we meet with dups we need to use hash set to help us solve the problem
        if(head == null){
            return head;
        }
        //why we need dummy again!!!!!
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        //as we already have dummy to record the *head* which is dummy.next
        //the head could actually work as the scanner
        
        while (head.next != null && head.next.next != null) {
            //we need to peek at the next and the next/next for dummy
            //because now head is pointing to the dummy
            if (head.next.val == head.next.next.val) {
                int dup = head.next.val;
                while(head.next!=null && head.next.val == dup){
                    //once we found one dup 
                    //we could jump over all these dups
                    head.next = head.next.next;
                }
            }else{
                //else we need to move the scanner forward
                //to check next pait of nodes
                head = head.next;
            }
        }
        
        return dummy.next;//because head is no longer head
    }
}




///remove dup in a sorted array
//for array this is similar to move all 0s to the end of array!!!
//two pointers!!! we just need to create another real index!!!
//and then only care about non-zeros/non-dups while the remaining is discarded
public class Solution {
    public int removeDuplicates(int[] nums) {
        //count for the corner case at first
        if(nums.length <2){
            return nums.length;
        }

        
        //two pointers 
        
        
        int index=0;
        for(int i =1; i<nums.length ; i++){
            if(nums[i] != nums[index]){
                //just move it to there
                nums[++index] = nums[i];
            }
        }
        
        ///we are return the length of the whole array!!!!!!
        //therefore index and the length have the relationship of   
        //length = index +1
        return index +1;
        
        
        
    }
}



//What if duplicates are allowed at most twice?
//remove 2
//we are using a nice feature!!! --  if(nums[index-1] != nums[i]){ then nodup
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //two pointers!
        if(nums.length == 1 || nums.length == 2){
            return nums.length == 1 ? 1:2;
        }

        //because the array has already been sorted
        int index = 1;//two pointers
        for(int i=2; i<nums.length; i++){
            //not care about the rep
            //we just move the non-rep to here and return index which is the new length
            if(nums[index-1] != nums[i]){
                //according to the characteristic of sorted array
                //if nums[index] == nums[index-1] == nums[i]
                //we need to move 
                //
                //but nums[index] must equal nums[index-1] if there is dup
                nums[++index] = nums[i];
            }
        }
        
        return index+1;//index to length
        
        
        
    }
}