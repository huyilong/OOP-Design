
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
x leetcdoe的新题，看大家都是dfs做的，试了下bfs的解法 

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


DFS --- RECURSION!!!!!!! -- STACK!!!!!
BFS --- QUEUE!!!!!!


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
