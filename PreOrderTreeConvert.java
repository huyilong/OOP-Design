/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //time O(N)
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        //firstly put into an array list and then solve it recursively
        if(head == null){
            return null;
        }
        

        //here we are still using the array approch
        //but object cannot be stored in the array
        //so we use arraylist at first
        //
        //because binary search we want to use get/ operation for constant time
        //which is oN in the linked list not good
        //and we are not using insertion / deletion
        //so linkedlist is very bad here...
        ArrayList<Integer> a = new ArrayList<>();
        while(head!=null){
            a.add(head.val);
            head = head.next;
        }
        int left =0;
        int right = a.size()-1;
        return deserializeBST(a, left, right);
    }
    
    //this is actually the recursion version of the binary search !!!!!
    //while(left<=right)
    public TreeNode deserializeBST(ArrayList<Integer> a, int left, int right){
        if(left > right){
            return null;
        }
        //we need to get the middle of the median of the array as the next node to insert into the BST
        int mid = (left + right)/2;


        //
        //PREORDER TRAVERSAL
        //combined with binary search recursion version
        //
        TreeNode node =new TreeNode(a.get(mid));
        //sounds like merge sort?
        node.left = deserializeBST(a, left, mid-1);
        node.right = deserializeBST(a, mid+1, right);
        
        return node;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //sorted array to BST
        //this is easy
        if(nums.length == 0){
            return null;
        }
        
        //       //we need to convert the listnode
        //into the arraylist
        //because this way we can get middle element by O(1)
        //FOR LINKEDLIST
        TreeNode root = helper(nums, 0, nums.length-1);
        
        return root;
    }
    
    private TreeNode helper(int[] nums, int low, int high){
        if(low>high){
            return null;//stop here
        }
        int mid = (low+high)/2;
        TreeNode n = new TreeNode(nums[mid]);
        n.left = helper(nums, low, mid-1);
        n.right = helper(nums, mid+1, high);
        return n;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void flatten(TreeNode root) {
         if (root == null) return;
        //把先序遍历的结果存到一个ArrayList<TreeNode>里面，然后从根节点开始，依次把左子树置空，右子树置为ArrayList里面存的下一个节点
         ArrayList<TreeNode> preorder = new ArrayList<TreeNode>();
         helper(root, preorder);
         //remove the root?
         
         //firstly we need to get the root of the tree and let the left tree to be null
         //and let the next index 0 in arraylist to be the right tree --> link together as the preorder sequence
         //TreeNode temp = root;
         TreeNode temp = preorder.get(0);
         preorder.remove(0);
         
         while (preorder.size()!=0) {
             //each node in the preorder -- we let the left node is null and right node is linked in the list
             temp.left = null;
             temp.right = preorder.get(0);
             preorder.remove(0);
             temp = temp.right;
         }
    }
    
    
    public void helper(TreeNode root, ArrayList<TreeNode> preorder) {
        //preorder to store each node in the array list
         if (root == null) return;
         
         //here it was system.out.println(root)// before!!!!!
         //preorder -> pre means the relative position of the root node and left is always before right!!!
         preorder.add(root);
         helper(root.left, preorder);
         helper(root.right, preorder);
     }
}

