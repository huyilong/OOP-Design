class MyQueue {
    //Queue<String> myQueue = new LinkedList<String>();
//Queue<Integer> myNumbers = new LinkedList<Integer>();
    Stack<Integer> temp = new Stack<>();
    //we need to get the value very carefully
    //create the value stack very carefully
    //put the integers into the stack --  we need to get the value in this queue
    //we need to reverse the order of the queue using a auxilary stack
    Stack<Integer> q = new Stack<>(); 
    
    
    // Push element x to the back of queue.
    public void push(int x) {
        if(q.isEmpty()){
            //there is nothing in the queue right now
            //we could directly put the thing into it
            q.push(x);
        }else{
            //q is not empty we need to use an additional stack to reverse 
            //the order to make LIFO - >FIFO
            while(!q.isEmpty()){
                temp.push(q.pop());
            }
            
            //clear q we could put this one in q
            q.push(x);
            
            while(!temp.isEmpty()){
                q.push(temp.pop());
            }
            
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        //after we implement the push correctly
        //pop could be as same as a real queue to use poll
        q.pop();
    }

    // Get the front element.
    public int peek() {
        return q.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}



class MyStack {
    //Queue<String> myQueue = new LinkedList<String>();
//Queue<Integer> myNumbers = new LinkedList<Integer>();

    Queue<Integer> temp = new LinkedList<>();
    Queue<Integer> stack = new LinkedList<>();
    
    // Push element x onto stack.
    public void push(int x) {
        if(empty()){
            stack.offer(x);
        }else{
            //the critical point is:
            //using two data structures to reverse the sequence in one 
            while(!stack.isEmpty()){
                temp.offer(stack.poll());
            }
            
            stack.offer(x);
            
            while(!temp.isEmpty()){
                stack.offer(temp.poll());
            }
            
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        stack.poll();
    }

    // Get the top element.
    public int top() {
        return stack.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
}
