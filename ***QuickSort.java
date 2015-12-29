/**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    /*
    Solution 1: Using the quick sort.
    */ 

    //there are k different colors
    使用快排，时间复杂度是O(nlogn),空间复杂度是O(Log(N))

    quickSort using sth like BST --- space complexity is LOGN -
    because each time we have two branches as recursions!!!!!

    A rather straight forward solution is a two-pass algorithm 
    using counting sort. That will cost O(k) extra memory.

    counting sort - if k colors - int[] count = new int[k]
    each position will count for one specific kind of color



    public void sortKColors1(int[] colors, int k) {
        // write your code here
        if (colors == null) {
            return;
        }
        
        quickSort(colors, 0, colors.length - 1);
    }
    
    public void quickSort(int[] colors, int left, int right) {
        if (left >= right) {
            return;
        }
        //pick up a pivot value to 
        int pivot = colors[right];
        
        int pos = partition(colors, left, right, pivot);
        
        quickSort(colors, left, pos - 1);
        quickSort(colors, pos + 1, right);
    }
    
    public int partition(int[] colors, int left, int right, int pivot) {
        int leftPoint = left - 1;
        int rightPoint = right;
        
        while (true) {
            ///search until find a left point >>>>>>> pivot
            while (colors[++leftPoint] < pivot);
            
            //search until find a right point <<<<<<< pivot
            while (leftPoint < rightPoint && colors[--rightPoint] > pivot);
            
            //if they meet then we finished
            //jump out of the true loop
            if (leftPoint >= rightPoint) {
                break;
            }
            
            //swap them
            swap(colors, leftPoint, rightPoint);
        }
        
        //swap to put the pivot at the right position
        //because we pick up colors[right] as the pivot value
            swap(colors, leftPoint, right);

            //return the leftpoint
            //which is stopped in the middle
            //could used for the boundary for next two recursions braches
        return leftPoint;
    }
    
    public void swap(int[] colors, int left, int right) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }


---------------------------------------------------------------

    sort list - merge sort - 
    Mergesort   O(n log(n)) O(n log(n))


public ListNode sortList(ListNode head) {  
    return mergeSort(head);  
}  
private ListNode mergeSort(ListNode head)  
{  
    if(head == null || head.next == null)  
        return head;  
    ListNode walker = head;  
    ListNode runner = head;  
    while(runner.next!=null && runner.next.next!=null)  
    {  
        walker = walker.next;  
        runner = runner.next.next;  
    }  
    ListNode head2 = walker.next;  
    walker.next = null;  
    ListNode head1 = head;  
    head1 = mergeSort(head1);  
    head2 = mergeSort(head2);  
    return merge(head1, head2);  
}  


private ListNode merge(ListNode head1, ListNode head2)  
{  
    ListNode helper = new ListNode(0);  
    helper.next = head1;  
    ListNode pre = helper;  
    while(head1!=null && head2!=null)  
    {  
        if(head1.val<head2.val)  
        {  
            head1 = head1.next;  
        }  
        else  
        {  
            ListNode next = head2.next;  
            head2.next = pre.next;  
            pre.next = head2;  
            head2 = next;  
        }  
        pre = pre.next;  
    }  
    if(head2!=null)  
    {  
        pre.next = head2;  
    }  
    return helper.next;  
}  


-------------------------------------------------------------
    insertion sort list - insertion sort
    Insertion Sort  O(n)-best    O(n^2) - average

    /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
            //Insertion Sort就是把一个一个元素往已排好序的list中插入的过程。
            //初始时，sorted list是空，把一个元素插入sorted list中。然后，在每一次插入过程中，都是找到最合适位置进行插入。
        
            //因为是链表的插入操作，需要维护pre，cur和next3个指针。
            //each turn pre始终指向sorted list的fakehead，cur指向当前需要被插入的元素，next指向下一个需要被插入的元素。
            //because we cannot search from back to front in linkedlist
            
            //当sortedlist为空以及pre.next所指向的元素比cur指向的元素值要大时，需要把cur元素插入到pre.next所指向元素之前。否则，pre指针后移。最后返回fakehead的next即可。
            if(head == null||head.next == null)  {
                return head;
            }
            
            //when we do the linked list insertion
            //first we always want a dummy head so that we return dummy.next in the end
            //and we always want the cur/next/pre and cur=head to be the scanner
            ListNode dummy = new ListNode(-1);
            //dummy.next = head;
            ListNode cur = head;//cur is the scanner
            //whenever there is insertion we need to get pre, cur, and next
            while(cur!=null){
                ListNode pre = dummy;//pre is searching for the pre node for insertion
                //next is for the inserted node's next
                ListNode next = cur.next; 
                //after each time we reset the postion of pre to be fakehead
                //we need to go through the list to find out the right position to find
                while(pre.next!=null && pre.next.val<cur.val){
                    
                    //attention we need to stop right before the right position
                    //so we need to pre.next.val<cur.val instead of pre.val < cur.val
                    pre = pre.next;  
                }  
                //we could insert it into the list
                //link the new node with before and after
                cur.next = pre.next;  
                pre.next = cur;  
                
                
                //the next pointer is used to track the position for next's turn 
                //the starting point is no longer cur.next since cur is inserted to the new position
                //and therefore we need to update cur to be next which is cur's original cur.next
                
                cur=next;//next turn
                
                
            }
            
            return dummy.next;
    
    
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //we need to think about if one list is longer than the other
        
        if(l1 == null){
            return l2;
        }
        
        if(l2 == null){
            return l1;
        }
        
        ListNode head = new ListNode(0);
        //does this means they both have the same position?
        //i WANT TO KEEP HEAD but still want to add things later
        //does this just copy by value? or just copy by address?
        
        /*Java does manipulate objects by reference, and all object variables are references. However, Java doesn't pass method arguments by reference; it passes them by value. The method successfully alters the value of pnt1 , even though it is passed by value; however, a swap of pnt1 and pnt2 fails
        */
        
        
        
        //here similarly scanner is just a pointer --> like ListNode head = new ListNode() which means you are assign the object to the pointer head 
        //so scanner is pointing at the same address with head
        ListNode scanner = head;
        //head.val = l1.val;
         System.out.print("1");
        //while(l1.next != null && l2.next !=null){
        while(l1 != null && l2 !=null){
            System.out.print("1");
            if(l1.val<l2.val){
                
                scanner.next = l1;
                l1 = l1.next;
                scanner = scanner.next;
            }else{
                scanner.next = l2;
                l2 = l2.next;
                scanner = scanner.next;
            }
        }
        
        
     //this is wrong!!!!!!!! because if 1
     //2 
     //then l1.next always is null
     //l2.next always is null
     //cannot pass the test
     //!!!!
        //if(l1.next != null)    
          
          
          if(l1 != null){
              scanner.next = l1;
          }
          if(l2 != null)
          {
              scanner.next = l2;
          }
          
        
        
        //we truncate the fake head
        //fuck!: do not use -> in java!!!
        //return head->next;
        return head.next;
        
    }
}




