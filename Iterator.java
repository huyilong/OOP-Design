////zigzag iterator
Zigzag Iterator
Total Accepted: 964 Total Submissions: 2714 Difficulty: Medium
Given two 1d vectors, implement an iterator to return their elements alternately.
For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
Clarification for the follow up question 
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
[思路]
iterator都放到一个list里, 用一个count循环, 

public class ZigzagIterator {
    List<Iterator<Integer> > iters = new ArrayList<Iterator<Integer> >(); 
    
    int count = 0;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if( !v1.isEmpty() ) iters.add(v1.iterator());
        if( !v2.isEmpty() ) iters.add(v2.iterator());
    }

    public int next() {
        int x = iters.get(count).next();
        if(!iters.get(count).hasNext()){
            //remove those that are null and terminated!!!
            iters.remove(count);
        }else{
            //circular the buffer to round robin
            count++;
        } 
        
        if(iters.size()!=0){
            //zigzag it!!!!
            count %= iters.size();
        }
        return x;
    }

    public boolean hasNext() {
        return !iters.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

class Vector2D {
	private Iterator<List<Integer>> row = null;
    private Iterator<Integer> col = null;

    
    public Vector2D(List<List<Integer>> vec2d) {
        //inside the iterator's type we need to use the
        //sub element inside the list!!!!
        //Iterator<List<Integer>> rowIt = llist.iterator();
        //
        //if(rowIt.hasNext())
        //Iterator<Integer> colIt = rowIt.next().iterator()



        //Iterator<subtype of the list> it = list.iterator();
        //while(it.hasNext())
        //int out = it.next()

        row = vec2d.iterator();
        if(row.hasNext())
            col = row.next().iterator();
    }

    public int next() {
        int lastValue = col.next();
        return lastValue;
    }

    public boolean hasNext() {
        if(col == null) {
            //if col itself is null then false
            //this is just for the very special first case -- no cols at all
            return false;
        }
        if(col.hasNext()) {
            //if it is not null and has next
            return true;
        } else {
            //if it is not full and does not has next
            //then we need to check next row
            while(row.hasNext()) {
                col = row.next().iterator();
                if(col.hasNext())
                    return true;
            }
            //if next row's col iterator still is null
            //we need to return false
            return false;
        }
    }
}



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
        //reuse code here
        if(!hasNext()){
            return 0;
        }
        TreeNode out = s.pop();
        int res = out.val;

        ///!!!!!!
        //int res = out.val
        if(out.right!=null){
            //once one of the old left node is poped and its right node not null
            //we need to *continuously* push right node at first
            //and all its left child node until down to null
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


// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    //Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
    
    
    
    private Iterator<Integer> iter = null; 
    private int nextValue = 0;  
    //we need a end variable to make sure it is not end right now
    private boolean end = false; 
    
    
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
         this.iter = iterator; 
         //check if there is any element
         if(iterator.hasNext()) {  
             //initilize the nextValue in constructor as well
             nextValue = iterator.next();  
         }else {  
             end = true;
         }
        
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
         if(end == false){
             //return the buffer for next value
             //if the boolean for this buffer mark is false
             return nextValue;
         }else{
             //throw new NoSuchElementException();  
             return 0;
         }
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        //buffer the real next
        int current = nextValue;  
        if(iter.hasNext()){
            //update the nextval
            //correspondingly after next() operation
            //we update nextValue in next() right after the operation
            nextValue = iter.next();
        }else{
            end = true;
        }
        
        return current;//return the buffer
    }

    @Override
    public boolean hasNext() {
        return end != true;
    }
}


