══════════════╦═════════════════════╦═══════════════════╦══════════════════════╗
║   Property   ║       HashMap       ║      TreeMap      ║     LinkedHashMap    ║
╠══════════════╬═════════════════════╬═══════════════════╬══════════════════════╣
║              ║  no guarantee order ║ sorted according  ║                      ║
║   Order      ║ will remain constant║ to the natural    ║    insertion-order   ║
║              ║      over time      ║    ordering       ║                      ║
╠══════════════╬═════════════════════╬═══════════════════╬══════════════════════╣
║  Get/put     ║                     ║                   ║                      ║
║   remove     ║         O(1)        ║      O(log(n))    ║         O(1)         ║
║ containsKey  ║                     ║                   ║                      ║
╠══════════════╬═════════════════════╬═══════════════════╬══════════════════════╣
║              ║                     ║   NavigableMap    ║                      ║
║  Interfaces  ║         Map         ║       Map         ║         Map          ║
║              ║                     ║    SortedMap      ║                      ║
╠══════════════╬═════════════════════╬═══════════════════╬══════════════════════╣
║              ║                     ║                   ║                      ║
║     Null     ║       allowed       ║    only values    ║       allowed        ║
║ values/keys  ║                     ║                   ║                      ║
╠══════════════╬═════════════════════╩═══════════════════╩══════════════════════╣
║              ║   Fail-fast behavior of an iterator cannot be guaranteed       ║
║   Fail-fast  ║ impossible to make any hard guarantees in the presence of      ║
║   behavior   ║           unsynchronized concurrent modification               ║
╠══════════════╬═════════════════════╦═══════════════════╦══════════════════════╣
║              ║                     ║                   ║                      ║
║Implementation║      buckets        ║   Red-Black Tree  ║    double-linked     ║
║              ║                     ║                   ║       buckets        ║
╠══════════════╬═════════════════════╩═══════════════════╩══════════════════════╣
║      Is      ║                                                                ║
║ synchronized ║              implementation is not synchronized                ║

HashMap makes absolutely no guarantees about the iteration order. It can (and will) even change completely when new elements are added.
TreeMap will iterate according to the "natural ordering" of the keys according to their compareTo() method (or an externally supplied Comparator). Additionally, it implements the SortedMap interface, which contains methods that depend on this sort order.
LinkedHashMap will iterate in the order in which the entries were put into the map

A red–black tree is a kind of self-balancing binary search tree. 
Each node of the binary tree has an extra bit, and that bit is often interpreted 
as the color (red or black) of the node.


Do the inorder traversal.
Take a vari­able called level, when ever you go left, do level++ AND whenever 
you go right do level–.
With step above we have sep­a­rated out the lev­els vertically.
Now you need to store the ele­ments of each level, so cre­ate a TreeMap and 
the (key,value) pair will be (level,elements at that level).
At the end iter­ate through the TreeMap and print the results.

//THIS WORDKS FINE FOR special binary tree, especially for non-right nodes
recurseTraverse(final Node<Integer> node, final Map<Integer, List<Node<Integer>>> columnmap, final int column) {
    if (node == null) {
        return;
    }
    List<Node<Integer>> list = columnmap.get(column);
    if (list == null) {
        list = new ArrayList<Node<Integer>>();
        columnmap.put(column, list);
    }
    list.add(node);
    recurse(node.left(), columnmap, column - 1);
    recurse(node.right(), columnmap, column + 1);

}
public void traverse(Node<Integer> root) {
    TreeMap<Integer, List<Node<Integer>>> columnMap = new TreeMap<>();

    recurseTraverse(root, columnMap, 0);

    for (Entry<Integer, List<Node<Integer>>> entry: columnMap.entrySet()) {
        System.out.println("Column - " + entry.getKey() + " : " + entry.getValue());
    }
}
///////BFS
public static final <N> void traverse(Node<N> root) {

    final TreeMap<Integer, List<Node<N>>> columnMap = new TreeMap<>();
    final Queue<ColumnFind<N>> queue = new LinkedList<>();

    queueChild(0, root, queue);

    while (!queue.isEmpty()) {
        ColumnFind<N> cf = queue.remove();
        int column = cf.column;
        Node<N> node = cf.node;
        columnMap.computeIfAbsent(column, c -> new ArrayList<>()).add(node);
        queueChild(column - 1, node.left(), queue);
        queueChild(column + 1, node.right(), queue);
    }

    for (Entry<Integer, List<Node<N>>> entry: columnMap.entrySet()) {
        System.out.println("Column - " + entry.getKey() + " : " + entry.getValue());
    }
}

private static final <N> void queueChild(int column, Node<N> node, 
                 Queue<ColumnFind<N>> queue) {
    if (node == null) {
        return;
    }
    queue.add(new ColumnFind<>(column, node));
}


2 sum - all pairs ouput no dups allowed
public List<List<Integer>> twoSum(int target, int[] nums){
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if(nums.length < 2 || nums==null){
		return res;
	}
	Arrays.sort(nums);

	int left = 0;
	int right = nums.length-1;
	while(left<right){
		int sum = nums[left] + nums[right];
		if(sum == target){
			ArrayList<Integer> sub = new ArrayList<>();
			sub.add(left+1);
			sub.add(right+1);
			res.add(sub);

			//******important*****////
			left++;
			right--;

		}else if(sum < target){
			left++;
			while(left<right && nums[left] == nums[left-1]){
				left++;
			}
		}else{
			right--;
			while(left<right && nums[right] == nums[right+1]){
				right--;
			}
		}
	}

	return res;
}

public int[] twoSum(int target, int[] nums){

	if(nums.length < 2 || nums==null){
		return null;
	}
	Arrays.sort(nums);
	int left = 0;
	int right = nums.length-1;
	while(left<right){
		int sum = nums[left] + nums[right];
		if(sum == target){
			return new int[]{left+1, right+1};
			//do not need 
			//left++ && right-- if we only need one pair
		}else if(sum < target){
			left++;
		}else{
			right--;
		}
	}

	return null;
}
关键点在于对重复数字的处理，用hashmap存储值，然后判断value-num == num的情况下，count是否>=2.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

public class TwoSum {
    HashMap<Integer, Integer> map;
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }
    
    public void add(int x) {
        if (map.containsKey(x)) {
            map.put(x, map.get(x)+1);
        }
        else {
            map.put(x, 1);
        }
    }
    
    public boolean find(int target) {
        for (int i : map.keySet()) {
            if (map.containsKey(target-i)) {
                if (target - i != i) return true;
                else if (map.get(i) >= 2) return true;
            }
        }
        return false;
    }
}


import java.util.*;
public class BinaryTreeInVerticalOrder {
    public static void main(String[] args) {
        TreeNode[] nodes = new TreeNode[] {
            null,
            new TreeNode(1),
            new TreeNode(2),
            new TreeNode(3),
            new TreeNode(4),
            new TreeNode(5),
            new TreeNode(6),
            new TreeNode(7),
            new TreeNode(8),
            new TreeNode(9)
        };
        nodes[1].left = nodes[2];
        nodes[1].right = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        nodes[3].left = nodes[6];
        nodes[3].right = nodes[7];
        nodes[6].right = nodes[8];
        nodes[7].right = nodes[9];
        TreeNode root = nodes[1];
        printVertically(root);
    }
    private static void printVertically(TreeNode root) {
        Hashtable<Integer, List<TreeNode>> ht = new Hashtable<Integer, List<TreeNode>>();
        int level = 0;
        traverse(root, ht, level);
        // Iterate over all possible keys instead of all actual keys.
        // Note, there are still O(n) possible keys.
        int min = 0;
        int max = 0;
        for (Integer i : ht.keySet()) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        for (int i = min; i <= max; i++) {
            if (ht.containsKey(i)) {
                printList(ht.get(i));
            }
        }
    }
    private static void printList(List<TreeNode> list) {
        for (TreeNode node : list) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }
    private static void traverse(TreeNode root, Hashtable<Integer, List<TreeNode>> ht, int level) {
        if (root != null) {
            // Use a pre-order traversal instead of in-order traversal.
            addNode(root, ht, level);
            traverse(root.left, ht, level-1);
            traverse(root.right, ht, level+1);
        }
    }
    private static void addNode(TreeNode root, Hashtable<Integer, List<TreeNode>> ht, int level) {
        if (ht.containsKey(level)) {
            ht.get(level).add(root);
        } else {
            LinkedList<TreeNode> newList = new LinkedList<TreeNode>();
            newList.add(root);
            ht.put(level, newList);
        }
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}

Binary Tree Upside Down的三种解法
Given a binary tree where all the right nodes are either leaf nodes with a
 sibling (a left node that shares the same parent node) or empty, flip it 
 upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
1
/ \
2 3
/ \
4 5

return the root of the binary tree [4,5,2,#,#,3,1].
4
/ \
5 2
  / \
 3 1
这题有一个重要的限制就是，整个数的任何一个右孩子都不会再生枝节，基本就是一个梳子的形状。
对于树类型的题目，首先可以想到一种递归的思路：把左子树继续颠倒，颠倒完后，原来的那个左孩子
的左右孩子指针分别指向原来的根节点以及原来的右兄弟节点即可。

1. Recursively traverse to the leftmost node. 
2. This becomes the NewRoot, and keep returning this value, up the chain. 
3. Make the following changes 
- CurrentRoot.Left.Left = CurrentRoot.Right 
- CurrentRoot.Left.Right = CurrentRoot 
- CurrentRoot.Left = CurrentRoot.Right = NULL
Node FlipTree ( Node root )
{
    if (root == NULL)
        return NULL;
    
    // Working base condition
    if( root.Left == NULL && root.Right == NULL) 
    {
        return root.Left;
    }
    
    Node newRoot = FlipTree(root.Left);
    
    root.Left.Left = root.Right;
    root.Left.Right = root;
    root.Left = NULL;
    root.Right = NULL;
    
    return newRoot;
}

-----------------------UnitTest Junit---------------------
public class MyUnit {

    public String concatenate(String one, String two){
        return one + two;
    }
}
public class MyUnitTest {

    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }
}
