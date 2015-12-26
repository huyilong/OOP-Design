
leetcode里，
Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
        public int compare(Integer a, Integer b) {
            return b - a;//从大向小排
        }
    });

这样重写是AC的。

但是在eclipse里，在new Comparator之前，要加入初始化的值才可以编译通过，比如这一题就要加入2*buildings.length。但是如果在leetcode里加入这个参数，又说是illegal的。所以到底是怎么回事呢？

求java大神解释。

//
Java 7中自定义comparator的pq初始化时候需要initial capacity这个参数
Java 8里面可以不用 估计你的eclipse版本比较旧 配置的JDK7吧


//priority queue 
//offer and poll could be 
Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
	public int compare(Integer o1, Integer o2){
		return o2 - o1;//descending order
		//in this case whenever we poll a value it is the highest priority
	}
});


import java.util.Comparator;
import java.util.PriorityQueue;
 
public class PriorityQueueTest {
 
	static class PQsort implements Comparator<Integer> {
 
		public int compare(Integer one, Integer two) {
			return two - one;
		}
	}
 
	public static void main(String[] args) {
		int[] ia = { 1, 10, 5, 3, 4, 7, 6, 9, 8 };
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
 
		// use offer() method to add elements to the PriorityQueue pq1
		for (int x : ia) {
			pq1.offer(x);
		}
 
		System.out.println("pq1: " + pq1);
 
		PQsort pqs = new PQsort();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(10, pqs);
		// In this particular case, we can simply use Collections.reverseOrder()
		// instead of self-defined comparator
		for (int x : ia) {
			pq2.offer(x);
		}
 
		System.out.println("pq2: " + pq2);
 
		// print size
		System.out.println("size: " + pq2.size());
		// return highest priority element in the queue without removing it
		System.out.println("peek: " + pq2.peek());
		// print size
		System.out.println("size: " + pq2.size());
		// return highest priority element and removes it from the queue
		System.out.println("poll: " + pq2.poll());
		// print size
		System.out.println("size: " + pq2.size());
 
		System.out.print("pq2: " + pq2);
 
	}
}
Output:

pq1: [1, 3, 5, 8, 4, 7, 6, 10, 9]
pq2: [10, 9, 7, 8, 3, 5, 6, 1, 4]
size: 9
peek: 10
size: 9
poll: 10
size: 8
pq2: [9, 8, 7, 4, 3, 5, 6, 1]