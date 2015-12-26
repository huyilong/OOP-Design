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




class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private boolean peekFlag = false;
    private Integer nextElement = null;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!peekFlag) {
            nextElement = iterator.next();
            peekFlag = true;
        }
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!peekFlag) {
            return iterator.next();
        }
        peekFlag = false;
        Integer result = nextElement;
        nextElement = null;
        return result;
    }

    @Override
    public boolean hasNext() {
        return peekFlag || iterator.hasNext();
    }

}