Find the kth smallest number in at row and column sorted matrix.

Example
Given k = 4 and a matrix:

[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
return 5

Challenge
O(k log n), n is the maximal number in width and height.


对第一列或者第一行push进minHeap，依次pop出K个最小数，一边pop一边加入pop出的那个位置的下一个元素
复杂度是KLog(Min(M,N,K))

顶二楼，先把第一行加到一个minheap里面去，用另一个idx指针指到第二行开始处，然后minheap开始pop，
如果minheap上最小的一个数小于idx指针指向的那个数，那idx指针那一行都加进去，然后idx指针下一行，直到收集到k个元素。

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
     
    public class Point {
        public int x, y, val;
        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    } 
    
    Comparator<Point> comp = new Comparator<Point>() {
        public int compare(Point left, Point right) {
            return left.val - right.val;
        }
    };
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (k > matrix.length * matrix[0].length) {
            return 0;
        }
        return horizontal(matrix, k);
    }
    
    private int horizontal(int[][] matrix, int k) {
        Queue<Point> heap = new PriorityQueue<Point>(k, comp);
        for (int i = 0; i < Math.min(matrix.length, k); i++) {
            heap.offer(new Point(i, 0, matrix[i][0]));
        }
        for (int i = 0; i < k - 1; i++) {
            Point curr = heap.poll();
            if (curr.y + 1 < matrix[0].length) {
                heap.offer(new Point(curr.x, curr.y + 1, matrix[curr.x][curr.y + 1]));
            }
        }
        return heap.peek().val;
    }
    
    private int vertical(int[][] matrix, int k) {
        Queue<Point> heap = new PriorityQueue<Point>(k, comp);
        for (int i = 0; i < Math.min(matrix[0].length, k); i++) {
            heap.offer(new Point(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Point curr = heap.poll();
            if (curr.x + 1 < matrix.length) {
                heap.offer(new Point(curr.x + 1, curr.y, matrix[curr.x + 1][curr.y]));
            }
        }
        return heap.peek().val;
    }
}