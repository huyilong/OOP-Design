/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 import java.util.StringTokenizer;
public class Codec {
//Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         StringBuilder s = new StringBuilder();
         if(root == null){
             return s.toString();
         }     
         
         helper1(s, root);
         return s.toString();
    }
    
    
    public void helper1(StringBuilder s, TreeNode n){
        if(n==null){
            s.append("#,");
        }else{
            s.append(n.val).append(",");
            helper1(s, n.left);
             helper1(s, n.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data ==null || data.length() ==0){
            return null;
        }
        
        StringTokenizer t = new StringTokenizer(data, ",");
        return helper2(t);
    }
    
    //this should finally return the root of the tree
    public TreeNode helper2(StringTokenizer t){
        if(! t.hasMoreTokens()) return null;
        //You almost always want to useObjects.equals(). In the rare situation where you know you're dealing with interned strings, you can use ==.
        
        
        String val = t.nextToken();//delimited by the ","
        if(val.equals("#")){
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = helper2(t);
        root.right = helper2(t);
        
        //return the root of the entire tree
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


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
    LinkedList<String> res = new LinkedList<>();
    StringBuilder sub = new StringBuilder();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root!=null){
            helper(root, res, sub);
        }
        
        return res;
    }
    
    public void helper(TreeNode n, LinkedList l, StringBuilder s){
        //s.append(n.val);
        
        if(n.left ==null && n.right == null){
            s.append(n.val);
            //without ->
            //l.add(s);
            l.add(s.toString());
            //we need to clear the string builder
            //s.deleteCharAt(s.length()-1);
            //s.setLength(1);
            //clear a string builder
            //s.setLength(0);
            //return;
        }
        //put -> here we could avoid appending -> in the last position
        s.append(n.val);
        s.append("->");
        
        if(n.left!=null){
            //helper(n.left, l, s);
            helper(n.left, l,new StringBuilder(s));
        }
        if(n.right!=null){
            //helper(n.right, l, s);
            helper(n.right,l,new StringBuilder(s));
        }
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root == null){
            return null;
        }
        
        if(root == p || root == q){
            return root;
        }
        
        TreeNode l = lowestCommonAncestor(root.left, p,q);
        TreeNode r = lowestCommonAncestor(root.right, p,q);
        
        if(l!=null && r!=null){
            //the nodes were found on the two sides of the root
            return root;
        }
        
        
        return r != null ? r:l;
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
    public TreeNode invertTree(TreeNode root) {
        
        if(root!=null){
            helper(root);
            
        }      
        return root;
    }
    
    private void helper(TreeNode root){
        if(root == null){
            return;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        if(root.left!=null){
            helper(root.left);
        }
        
        if(root.right!=null){
            helper(root.right);
        }
    }
       
} 




../**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int i=0; i<size; i++){
                //we need to get the node out inside the loop
                TreeNode out = q.poll();
                if(i == size-1){
                    //this is the rightmost node in that level
                    res.add(out.val);
                }
                
                //here we need to get more children on the next level 
                //which is BFS
                if(out.left != null) q.offer(out.left);
                if(out.right != null) q.offer(out.right);
            }
        }
        
        return res;
    }
}


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 
 ///////stack!!!!!!!!!
 //push until the leftmost leaf node
 //pop and check each node's right child
 //if not null
 //push until its leftmost leaf node and then return the previous value

public class BSTIterator {
    //create a stack push the left most branch
    //everytime pop up we need to repush the left path in the right subtree
    Stack<TreeNode> s = new Stack<>();
    public BSTIterator(TreeNode root) {
        while(root !=null){
            s.push(root);
            //we continuously push to the leftmost node
            root=root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        //what if it does not have anything
        if(!hasNext()){
            return 0;
        }
        TreeNode out = s.pop();
        int res = out.val;
        if(out.right!=null){
            TreeNode n = out.right;
            while(n!=null){
                s.push(n);
                //we then continuously push to the leftmost node once we find a right subtree
                n = n.left;
            }
        }
        return res;
        
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
    public int maxDepth(TreeNode root) {
        //if it is an empty tree
        if(root == null){
            return 0;
        }
        // if (node == null) return 0;
        //      return 1 + Math.max(maxDepth(root.left), maxDepth(root.right)); 
        int length = 0;
        length = maxdepth(root);
        return length;
    }
    
    public int maxdepth(TreeNode root){
        //this is the base case
        //when the stack no longer grows -- we need to return and pop up the previous stack
        if(root== null){
            return 0;
        }
        //here we need to get the stack grows up and return the value accordingly
        //why am i so sb??? root->left is c++!!! you idiot!!!
        
        //Math.max( maxdepth(root.left, depth+1),maxdepth(root.right, depth+1) ) not correct???
        //the depth has nothing to do with the height!!!!
        //why we need to +1 in the end?
        //actually the depth has no funtion in this recursion???
        return Math.max( maxdepth(root.left),maxdepth(root.right) )+1;
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null){
            return res;
        }
        boolean odd = true;
        //if it is odd we need to traverse from left to right
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> sub = new ArrayList<>();
            
            for (int i=0; i <size; i++){
                TreeNode out = q.poll();
                sub.add(out.val);
                if(out.left!=null) q.offer(out.left);
                if(out.right!=null) q.offer(out.right);
            }
            
            //outside the for loop we have a finished level
            if(odd){
                res.add(sub);
                //from left to right
            }else{
                Collections.reverse(sub);
                res.add(sub);
            }
            
            odd = !odd;
        }
        
        
        return res;
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
        
public class Solution {
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
   