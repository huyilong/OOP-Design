Java Solution 1 - Iterative

The key to solve inorder traversal of binary tree includes the following:

The order of "inorder" is: left child -> parent -> right child
Use a stack to track nodes
Understand when to push node into the stack and when to pop node out of the stack

//Definition for binary tree
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
 
public class Solution {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
         ArrayList<Integer> lst = new ArrayList<Integer>();
 
        if(root == null)
            return lst; 
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //define a pointer to track nodes
        TreeNode p = root;
 
        while(!stack.empty() || p != null){
 
            // if it is not null, push to stack
            //and go down the tree to left
            if(p != null){
                stack.push(p);
                p = p.left;
 
            // if no left child
            // pop stack, process the node
            // then let p point to the right
            }else{
                TreeNode t = stack.pop();
                lst.add(t.val);
                p = t.right;
            }
        }
 
        return lst;
    }
}

public class Solution {
    List<Integer> result = new ArrayList<Integer>();
 
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root !=null){
            helper(root);
        }
 
        return result;
    }
 
    public void helper(TreeNode p){
        if(p.left!=null)
            helper(p.left);
 
        result.add(p.val);
 
        if(p.right!=null)
            helper(p.right);
    }
}




//binary search tree iterator
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