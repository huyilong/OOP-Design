public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // init the adjacency list
        List<Set> posts = new ArrayList<Set>();
        for (int i = 0; i < numCourses; i++) {
            posts.add(new HashSet<Integer>());
        }
        
        // fill the adjacency list
        for (int i = 0; i < prerequisites.length; i++) {
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        // count the pre-courses
        int[] preNums = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            Set set = posts.get(i);
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
            	preNums[it.next()]++;
            }
        }
        
        // remove a non-pre course each time
        for (int i = 0; i < numCourses; i++) {
            // find a non-pre course
            int j = 0;
            for ( ; j < numCourses; j++) {
                if (preNums[j] == 0) break;
            }
            
            // if not find a non-pre course
            if (j == numCourses) return false;
            
            preNums[j] = -1;
            
            // decrease courses that post the course
            Set set = posts.get(j);
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
            	preNums[it.next()]--;
            }
        }
        
        return true;
    }
}


典型的拓扑排序。原理也很简单，在一个有向图中，每次找到一个没有前驱节点的节点
（也就是入度为0的节点），然后把它指向其他节点的边都去掉，重复这个过程（BFS），
直到所有节点已被找到，或者没有符合条件的节点（如果图中有环存在）。

回顾一下图的三种表示方式：边表示法（即题目中表示方法），邻接表法，邻接矩阵。
用邻接表存储图比较方便寻找入度为0的节点。




因为只要返回任意一种合法的拓扑排序结果即可，所以只需在BFS过程中记录下来先后访问的节点即可。

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Set<Integer>> adjLists = new ArrayList<Set<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            adjLists.add(new HashSet<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            adjLists.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        int[] indegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int x : adjLists.get(i)) {
                indegrees[x]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] res = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int x : adjLists.get(cur)) {
                indegrees[x]--;
                if (indegrees[x] == 0) {
                    queue.offer(x);
                }
            }
            res[count++] = cur;
        }
        
        if (count == numCourses) return res;
        return new int[0];
    }
}
