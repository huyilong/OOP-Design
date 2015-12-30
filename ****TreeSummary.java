FLATTEN TREE TO LINKEDLIST
convert LINKEDLIST TO TREE
recursion VERSION BINARY SEARCH + preorder traverse

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
        if(head == null){
            return null;
        }
        //linked list does not very good get()
        //so convert linked list to arraylist which is similar to array at first
        //which is better for us to do the combined binary search recursion with preorder version
        ArrayList<Integer> help = new ArrayList<>();
        
        ListNode scanner = head;
        while(scanner!=null){
            help.add(scanner.val);
            scanner = scanner.next;
        }
        
        int l = 0;
       // int r = help.length()-1;
        int r = help.size()-1;
        TreeNode root = helper(help, l, r);
        return root;
    }
    
    private TreeNode helper(ArrayList<Integer> help, int l, int r){
        if(l>r){
            return null;
        }
        int mid = (l+r)/2;
        TreeNode n = new TreeNode(help.get(mid));
        //TreeNode n = help.get(mid);
        n.left = helper(help, l, mid-1);
        n.right = helper(help, mid+1, r);
        return n;
    }
}
 
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
        
        // s.append(n.val).append(",");
        // if(n.left !=null){
        //     helper1(s, n.left);
            
        // }
        
        // if(n.right!=null){
        //     helper2(s, n.right);
        // }
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

So Machine 1 does:
string encoded_string = encode(strs); 
and Machine 2 does:
vector<string> strs2 = decode(encoded_string); 
strs2 in Machine 2 should be the same as strs in Machine 1.

Note: The string may contain any possible characters out of 256 valid ascii characters.

时间 O(N) 空间 O(1)

本题难点在于如何在合并后的字符串中，区分出原来的每一个子串。这里我采取的编码方式，
是将每个子串的长度先赋在前面，然后用一个#隔开长度和子串本身。这样我们先读出长度，
就知道该读取多少个字符作为子串了。


public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder output = new StringBuilder();
        for(String str : strs){
            // 对于每个子串，先把其长度放在前面，用#隔开
            output.append(String.valueOf(str.length())+"#");
            // 再把子串本身放在后面
            output.append(str);
        }
        return output.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new LinkedList<String>();
        int start = 0;
        while(start < s.length()){
            // 找到从start开始的第一个#，这个#前面是长度
            int idx = s.indexOf('#', start);
            int size = Integer.parseInt(s.substring(start, idx));
            // 根据这个长度截取子串
            res.add(s.substring(idx + 1, idx + size + 1));
            // 更新start为子串后面一个位置
            start = idx + size + 1;
        }
        return res;
    }
}

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
            return;
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


public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           /*
        当前节点不是两个节点中的任意一个，此时应判断左右子树的返回结果。
若左右子树均返回非空节点，那么当前节点一定是所求的根节点，将当前节点逐层向前汇报。// 两个节点分居树的两侧

若左右子树仅有一个子树返回非空节点，则将此非空节点向父节点汇报。// 节点仅存在于树的一侧

若左右子树均返回NULL, 则向父节点返回NULL. // 节点不在这棵树中
当前节点即为两个节点中的一个，此时向父节点返回当前节点。
*/
// no root no LCA.
        if(root==null) {
                return null;
        }

        // if either p or q is the root then root is LCA.
        //or report this root to the uper level
        if(root==p || root==q) {
                return root;
        } else {
                // get LCA of p and q in left subtree.
                TreeNode l=lowestCommonAncestor(root.left , p , q);

                // get LCA of p and q in right subtree.
                TreeNode r=lowestCommonAncestor(root.right , p, q);

                // if one of p or q is in leftsubtree and other is in right
                // then root it the LCA.
                if(l!=null && r!=null) {
                        return root;
                }
                // else if l is not null, l is LCA.
                return (l!=null) ? l : r;
        }
        
        
    }
}

In a complete binary tree every level, 
except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.
public class Solution {
    public int countNodes(TreeNode root) {
        //In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes 
        
        //find the left most and right most height if they are equal then we use formula #node = 2^h -1
        //O(h^2) 1+2+3+....+h = n^2-1/2 if they are not equal we need to find the left and right node
        // if(root == null){
        //     return 0;
        // }
        
        // return countNodes(root.left) + countNodes(root.right) + 1;
        
        //time exceed
        if(root==null){
            return 0;
        }
        //get the leftmost branch and the right most branch
        int left = getlefth(root);
        int right = getrighth(root);
        if(left == right){
            //this perfect complete tree
            return 2<<left -1;
        }else{
            System.out.println("123");
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
    
    
    public int getlefth(TreeNode n){
        if(n == null){
            return 0;
        }
        int height =0;
        while(n!=null){
            height++;
            n=n.left;
        }
        return height;
    }
    
    public int getrighth(TreeNode n){
        if(n == null){
            return 0;
        }
        int height =0;
        while(n!=null){
            height++;
            n=n.right;
        }
        return height;
    }
}


class TrieNode {
    // Initialize your data structure here.
    //Prefix Tree
     //the value is a char
    char c;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf;
        
        //public TrieNode(){}
        
        public TrieNode(char c){
            this.c =c;
        }
    public TrieNode() {
       
        
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        //we need to get the reference
       HashMap<Character, TrieNode> children = root.children;
        
        //for string we need to call length()
        //for array we need to call length
        for(int i=0; i<word.length(); i++){
            char c=word.charAt(i);
            TrieNode t;
            //we need to get the character one by one and make sure whether the tree already contains it
            if(children.containsKey(c)){
                t= children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
            //we get the new children to recursively do things
            children = t.children;
            
            //set the leaf node
            //leaf node is for searching string entirely
            //or make sure whether it is a prefix
            //prefix is general of the string
            if(i == word.length()-1){
                t.isLeaf = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        if(t!=null && t.isLeaf){
            return true;
        }else{
            return false;
        }
    }
    
    public TrieNode searchNode(String str){
       HashMap<Character, TrieNode> children = root.children;
       TrieNode t =null;
       for(int i=0; i<str.length(); i++){
           char c=str.charAt(i);
           //we dirve to search each character
           if(children.containsKey(c)){
               //get the trie node from the key of character
               t = children.get(c);
               //newchildren for the current node
               children = t.children;
           }else{
               return null;
           }
       }
       
       return t;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        //this is very similar to the search
        // the only diffrent is that this one it could be not a leaf
        //but search the last node must be the leaf
        TrieNode t = searchNode(prefix);
        if(t!=null){
            return true;
        }else{
            return false;
        }
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

LLLLLLLEEEVVVEEEEELLLLLL ORRRRRDDERRR traverse

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
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root==null){
            return res;
        }
        LinkedList<List<Integer>> stack = new LinkedList<List<Integer>>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            
            for(int i=0; i<size; i++)
            {
                
                TreeNode out = q.poll();
                
                //System.our.println(out.val);
                level.add(out.val);
                if(out.left!= null){
                    q.offer(out.left);
                }
                if(out.right!=null){
                    q.offer(out.right);
                }
                
            }
            
            stack.push(level);
        }        
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
        
        return res;
    }
}

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

EAAAAAAAAASSSSYYY --- BASSISCCCCC  BASICC

public class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
     //preorder means that the position of the current node!!!! 
     // 2
     ///\
     //1 3
     ///preorder:  213  inorder 123   postorder 132
     if(root!=null){
         helper(root);
     }
     
     return res;
     
    }
    public void helper(TreeNode n){
        res.add(n.val);
        if(n.left!=null){
            helper(n.left);
            
        }
        
        if(n.right!=null){
            helper(n.right);
        }
    }
}

-------------------------------------------
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

public class Solution {
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
}

public class Solution {
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
----------------------------------------------

ALLL the nodes on the left subtree need to be less than root
ALLL the nodes on the right subtree need to be greater than root
public class Solution {
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
}

ALLLLL THE SUBTREE NEEDS TO BE isBalanced SAME TO BST  -- VERY CLEVER
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
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int depth(TreeNode n){
        if(n==null){
            return 0;
        }
        
        return 1 + Math.max(depth(n.left), depth(n.right));
    }
        
}      

-----the depth function above is the maxdepth function below-------

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
        
        //Math.max( maxdepth(root.left, depth+1),maxdepth(root.right, depth+1) ) not correct???
        //the depth has nothing to do with the height!!!!
        //why we need to +1 in the end?
        //actually the depth has no funtion in this recursion???
        return Math.max( maxdepth(root.left),maxdepth(root.right) )+1;
    }
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