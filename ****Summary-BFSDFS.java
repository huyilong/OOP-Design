public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null || board.length == 0 || board[0].length == 0){
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(helper(board, word, i, j, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    private boolean helper(char[][] board, String word, int x, int y, int index, boolean[][] visited){
        //dfs only care about 
        //1.termination
        if(index == word.length()){
            return true;
        }
        //2.failed subset
        if(x<0 || y<0 || x>=board.length || y>=board[0].length){
            return false;
        }
        if(board[x][y] != word.charAt(index)){
            return false;
        }
        if(visited[x][y] == true){
            return false;
        }
        
        //mark
        visited[x][y] = true;
        //try and do sth useful
        boolean res = helper(board, word, x+1, y, index+1, visited) || helper(board, word, x-1, y, index+1, visited) || helper(board, word, x, y+1, index+1, visited) || helper(board, word, x, y-1, index+1, visited);
        //unmark
        visited[x][y] = false;
        //return res
        return res;
    }
}


public class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int found_so_far){
        if(found_so_far == word.length()){
            return true;
        }
        
        if(i<0 || i>board.length-1 || j<0 || j>board[0].length-1){
            return false;
        }
        if(board[i][j] != word.charAt(found_so_far)){
             return false;
         }
        
        WORD SEARCH -- WE ONLY care about false return and final success
        middle match == charAt we do not return true -- we return false otherwise!!!
        
        // if(board[i][j] == word.charAt(found_so_far)){
        //     return true;
        // }
        char temp = board[i][j];
        board[i][j] = '*';
        boolean res = dfs(board, word, i+1, j, found_so_far+1) || dfs(board, word, i-1, j, found_so_far+1) || dfs(board, word, i, j+1, found_so_far+1) ||dfs(board, word, i, j-1, found_so_far+1);
        board[i][j] = temp;
        
        return res;
        
    }         
} 
----------------------------------------------------
public int numIslands(char[][] grid) {
        int count  = 0;
        if(grid == null ||grid.length == 0) return count;
        int m = grid.length;
        int n = grid[0].length;
        int i = 0, j = 0;
        int imax = 0, jmax = 0;
        //bfs explore the map;
        HashSet<Pair> set = new HashSet<Pair>();// mark all 1 in the grid.
        for(i = 0; i < m; i++){
            for(j = 0; j < n; j++){
                if(grid【i】[j] == '1'){
                   BFS(i, j, grid);
                   count++;
                }
            }
        }
        return count;
}

class Pair{
    int x, y;
    public Pair(int i, int j){
        this.x = i;
        this.y = j;
    }
}

private void BFS(int i, int j ,char[][] grid){
    int m = grid.length;
    int n = grid[0].length;
    //use as start to explore the grid
    Queue<Pair> q = new LinkedList<Pair>();
    q.offer(new Pair(i, j));
    grid[i][j] = '#';// mark as visited
    while(q.size() > 0){
        //we do not care level here
        //as long as BFS
        //but for tree we need to int size = q.size();
        Pair cur = q.poll();

        if(cur.y + 1 < n && grid[cur.x][cur.y + 1] == '1'){
            q.offer(new Pair(cur.x, cur.y + 1));
            grid[cur.x][cur.y + 1] = '#';
        }

        if(cur.y - 1 >= 0 && grid[cur.x][cur.y - 1] == '1'){
            q.offer(new Pair(cur.x, cur.y -1));
             grid[cur.x][cur.y - 1] = '#';
        }
       
        if(cur.x - 1 >= 0 && grid[cur.x -1][cur.y] == '1'){
            q.offer(new Pair(cur.x - 1, cur.y));
             grid[cur.x -1][cur.y] = '#';
        }

        if(cur.x + 1 < m && grid[cur.x + 1][cur.y] == '1'){
            q.offer(new Pair(cur.x + 1, cur.y));
            grid[cur.x + 1][cur.y ] = '#';
        }
    }
}


DFS --- RECURSION!!!!!!! -- STACK!!!!! -- care about success termination/fail subset
BFS --- QUEUE!!!!!! -- care about success termination/success subset -- enqueue


public class Solution {
    public int numIslands(char[][] grid) {
        int count =0;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    count++;
                    helper(i, j, grid);
                }
            }
        }
        
        
        return count;
    }
    
    private void helper(int i, int j , char[][] grid){
        //invalid position
        if(i<0 || i>grid.length-1 || j<0 || j>grid[0].length-1){
            return;
        }
        //real boundary
        if(grid[i][j] == '0'){
            return;
        }
        
        //here we are changing the current position to be 0
        grid[i][j] = '0';
        
        // if(grid[i][j] == '1'){
        //     grid[i][j] = '0';
        // }
        
        helper(i+1, j, grid);
        helper(i-1, j, grid);
        helper(i, j+1, grid);
        helper(i, j-1, grid);
        
    }
}


/*
You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle. 0 - A gate. INF - Infinity means an empty room. We use the value 
231 - 1 = 2147483647 to represent INF as you may assume that the distance to a 
gate is less than 2147483647. Fill each empty room with the distance to its nearest gate. 
If it is impossible to reach a gate, it should be filled with INF.
For example, given the 2D grid: INF -1 0 INF INF INF INF -1 INF -1 INF -1 0 -1 INF INF After running your function, 
the 2D grid should be: 3 -1 0 1 2 2 1 -1 1 -1 2 -1 0 -1 3 4
*/

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms==null || rooms.length==0 || rooms[0].length==0) {
            return;
        }

        int row = rooms.length;
        int col = rooms[0].length;

        boolean[][] visited = new boolean[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                //start from gate
                if(rooms[i][j] == 0) {
                    visit(rooms, i, j, 0, visited);
                }
            }
        }
    }


    public void visit(int[][] rooms, int i, int j, int distance, boolean[][] visited) {
        int row = rooms.length;
        int col = rooms[0].length;

        //invalid position
        if(i<0 || j<0 || i>=row || j>=col) {
            return;
        }
        //visited room
        if(visited[i][j]) {
            return;
        }
        //obstacle
        if(rooms[i][j] == -1) {
            return;
        }
        //larger distance to get here
        if(distance > rooms[i][j]) {
            return;
        }

        visited[i][j] = true;
        rooms[i][j] = distance; // do not need to check because it is already success
        //after the check above
        // if(distance < rooms[i][j]) {
        //     rooms[i][j] = distance;
        // }

        //visit neighbors
        visit(rooms, i-1, j, distance+1, visited);
        visit(rooms, i+1, j, distance+1, visited);
        visit(rooms, i, j-1, distance+1, visited);
        visit(rooms, i, j+1, distance+1, visited);


        //backtraking after each turn we need to refresh
        //the state and then for next turn it could visit here again
        visited[i][j] = false;
    }
}

-----------------------!!!!!wordladder!!!!!!--------------------------
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        //把每个单词作为一个node进行BFS。
        //当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果在字典里面，就入队，
        //并将下层count++，并且为了避免环路，需把在字典里检测到的单词从字典里删除。
        //这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。
        
        if (beginWord == null || endWord == null || beginWord.length() == 0 || endWord.length() == 0 || beginWord.length() != endWord.length())
            return 0;
            
        LinkedList<String> q = new LinkedList<>();
        int level = 1;
        //enqueue init
        q.offer(beginWord);
        
        while(!q.isEmpty()){
            int size = q.size();
            //for only one level
            //for one level we will do something like
            //for(int i=0 i<size i++)
            //out of this for we will level++ or add to level order
            for(int i=0; i<size; i++){
                String out = q.poll();
                for(int j=0; j<out.length(); j++){
                    char[] replace = out.toCharArray();
                    for(char k='a'; k<='z'; k++){
                        replace[j] = k;
                        String temp = new String(replace);
                        
                        if(temp.equals(endWord)){
                            return level+1;
                        }
                        //whether dict contains that made up word
                        if(wordList.contains(temp)){
                            wordList.remove(temp);
                            q.offer(temp);
                        }
                    }
                }
            }
            
            level++;
        }
        return 0;
    }
}
-----------------------course schedule-------------------------
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //典型的拓扑排序。原理也很简单，在一个有向图中，每次找到一个没有前驱节点的节点（也就是入度为0的节点）no need for prereq ，然后把它指向其他节点的边都去掉，重复这个过程（BFS），直到所有节点已被找到，或者没有符合条件的节点（如果图中有环存在）。
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


public class Solution {
    public List<String> generateParenthesis(int n) {
        //dfs   we just need to record the left and right
        //and we need to decrease n and increase item
        //when 0 -- add result
        //when left>right  we discard because the )( is not good formatted we need to discard return these tracks
        //which means right ) is added more while left is less than right in the string
        ArrayList<String> res = new ArrayList<>();
        String sub = new String();
        
        if(n<0){
            //we firstly created the result container and then return it directly
            return res;
        }
        
        dfs(res, sub, n, n);
        return res;
        
    }
    
    private void dfs(ArrayList<String> res, String sub, int left, int right){
        if(left> right){
            return;
        }
        
        if(left ==0 && right ==0){
            res.add(sub);
        }
        
        if(left>0){
            dfs(res, sub+"(", left-1, right);
        }
        
        if(right>0){
            dfs(res, sub+")" , left, right-1);
        }
    }
}

public class Solution {
    public boolean isValid(String s) {
        //left -> push
        //right -> is Empty? pop() -- 2 places to check whether the stack is empty!!!
        //s.end -> Empty? -> yes then return true
        
        if(s.length() == 0 ||s.length() ==1){
            return false;
            
        }
        
        Stack<Character> x = new Stack<>();
        for(int i=0; i<s.length(); ++i){
            // == and  '' not ""
            //whenever meet with a left parenthesis we need to push into the stack
            if(s.charAt(i) == '(' || s.charAt(i) == '[' ||s.charAt(i) == '{'){
                x.push(s.charAt(i));
            }else{
                //we need to pop and check
                if(x.isEmpty()){
                    return false;
                }
                char top = x.pop();//pop we do not need to peek
                if(s.charAt(i) == ')'){
                    if(top !='('){
                        return false;
                    }
                }
                if(s.charAt(i) == ']'){
                    if(top !='['){
                        return false;
                    }
                }
                if(s.charAt(i) == '}'){
                    if(top !='{'){
                        return false;
                    }
                }
            }
        }
        
//after all
 //we need to check the stack empty
        return x.isEmpty(); 
    }
}

public class Solution {
    public List<String> letterCombinations(String digits) {
        
        //this is a problem to get the combinations 
        //we neeed to n digits, k chars --> time complexity O(k^n) which is also the space complexity np problem
        
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() ==0){
            return res;
        }
        //we could initialize the keyboard by using {} and = directly without "new" keyword
        String[] keyboard={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        StringBuilder sb = new StringBuilder();
        
        //int index=0;
        helper(digits, 0, sb, keyboard, res);
        
        return res;
        
    }
    
    private void helper(String digits, int index, StringBuilder sb, String[] keyboard, List<String> res){
        //the exit entry of the recursion
        if(index == digits.length()){
            res.add( sb.toString());
            return;
        }
        
        //we need to get the charater to digit
        int num = digits.charAt(index) - '0';
        
        //this is just same as the "combinations and word break!!!!!!!!!!!!!!!!!!! --> combinations NP problem"
        //or "permutations"  we could conclude with a mode ------  however
        //permutations we just need to let int i = pos;
        //instead of int i=0
        // for(int i=0; i<digits.length(); i++){
        //     sb.append(digits.charAt(i));
        //     helper(digits, index+1 , sb, keyboard, res);
        //     sb.remove()
        // }
        //we need to be careful!!!!!!!!!!!
        for(int i=0; i<keyboard[num].length(); i++){
            sb.append(keyboard[num].charAt(i));
            helper(digits, index+1 , sb, keyboard, res);
            sb.deleteCharAt(sb.length()-1);
        }
        
        
    }
}