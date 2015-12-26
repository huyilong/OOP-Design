public class Solution {
    public boolean isBalanced(TreeNode root) {
        //when it recursives to the end it is correct
        if(root == null){
            return true;
        }
        
        if(Math.abs(depth(root.left)-depth(root.right)) > 1){
            return false;
        }
        
        //any sub tree needs to satisfy the condition above
        //RECURSION WITH RECURSION
        //EVERY subtree will need to be balanced!! until leaf node!!!!
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int depth(TreeNode n){
        if(n==null){
            return 0;
        }
        //just a small function used by the checking
        //a single step in the isBalanced function!!!!
        return 1 + Math.max(depth(n.left), depth(n.right));
    }
        
}      


/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
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
//convert a sorted sequence -- we could preorder a BST to get the sorted array
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
        //we need to convert the listnode
        //into the arraylist
        //because this way we can get middle element by O(1)
        ArrayList<Integer> a = new ArrayList<>();
        while(head!=null){
            a.add(head.val);
            head = head.next;
        }
        int left =0;
        int right = a.size()-1;
        return deserializeBST(a, left, right);
    }
    
    public TreeNode deserializeBST(ArrayList<Integer> a, int left, int right){
        if(left > right){
            return null;
        }
        //we need to get the middle of the median of the array as the next node to insert into the BST
        int mid = (left + right)/2;
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


     
        
        
 public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    
    public boolean isSymmetric(TreeNode l, TreeNode r){
        if(l == null && r== null){
            //end case if left and right children are null
            //simultaneoursly then it is true for sure
            return true;
        }else if(r == null || l==null){
            //if only one of the child is null
            //another one has val then it is not symmetric
            return false;
        }
        
        if(l.val != r.val){
            return false;
        }
        
        return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        //we firstly judge both null
        if(p==null &&  q == null){
            return true;
        }
        //then here is XOR
        if(p==null || q ==null){
            return false;
        }
        
        return helper(p,q);
    }
    
    private boolean helper(TreeNode p, TreeNode q){
        if(p==null &&  q == null){
            return true;
        }
        //then here is XOR
        if(p==null || q ==null){
            return false;
        }
        
        if(p.val != q.val){
            return false;
        }
        
        return helper(p.left,q.left) && helper(p.right, q.right);
    }

    public boolean isValidBST(TreeNode root) {
        //return isValid(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    
    public boolean isValid(TreeNode p, int min, int max){
        if(p==null){
            return true;
        }
        
        if(p.val <=min || p.val>=max){
            return false;
        }
        
        return isValid(p.left, min, p.val) && isValid (p.right, p.val, max);
        
    }


    public int minDepth(TreeNode root) {
        ////minmum depth???
        if(root==null){
            return 0;
        }
        
        if(root.left == null){
            return minDepth(root.right) +1;
        }
        
        if(root.right == null){
            return minDepth(root.left)+1 ;
        }
        
        
        
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        
    }

