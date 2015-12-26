public class Solution {
    public List<String> letterCombinations(String digits) {
        //combinations and permutations
        //combinations and permutations
        //combinations and permutations
        
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
        //String[] keyboard={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        //digit could be 013 -- all possible char combinations
        //we could use the things before the current char
        int num = digits.charAt(index) - '0';
        
        //this is just same as the "combinations and word break!!!!!!!!!!!!!!!!!!! --> combinations NP problem"
        //or "permutations"  we could conclude with a mode ------  however
        
        //we need to be careful!!!!!!!!!!!
        for(int i=0; i<keyboard[num].length(); i++){
            sb.append(keyboard[num].charAt(i));
            helper(digits, index+1 , sb, keyboard, res);
            sb.deleteCharAt(sb.length()-1);
        }
              
    }
}


public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        /*首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，
        我们来获得res[i]的表达式。思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，*/
        /*那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。代码如下：*/
        if(s == null || s.length()== 0 ){
            return true;
        }
        
        //why +1?
        boolean[] dp = new boolean[s.length()+1];
        
        dp[0] = true;
        for(int i=0; i<=s.length(); i++){
            //for each i we need to search from the past states
            //from and when dp[j] is true and also wordDict is true
            //then dp [i] is also true
            //j is always start from i-1 and then to 0
            for(int j=0; j<i; j++){
            //for(int j =i-1; j>=0; --j){
                String word = s.substring(j,i);
                if(dp[j] && wordDict.contains(word)){
                    dp[i] = true;
                    //break just jump out of the current loop
                    //dynamic programming
                    break;
                }
            }
        }
        
        
        return dp[s.length()];
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//      //The same letter cell may not be used more than once. we need to mark it as visited
//      //word!!!
//      //number of islands
//      int col = board.length;
//      int row = board[0].length;
//      for(int i=0; i<col; i++){
//          for(int j =0; j<row; j++){
//              //the the last number is index to memorize the length of the current string
//              if(dfs(board, word, i, j, 0)==true){
//                  return true;//we find a valid path
//              }
//          }
//      }
     
//      return false;
     
//     }
    
//     private boolean dfs(char[][] board, String word, int y, int x, int i){
//         if(i == word.length()){
//             return true;//done
//         }
//         if(y<0 || x<0 || x>=board[0].length || y>=board.length){
//             return false;//valid
//         }
//         /////we just need to specify when it is false
//         if(board[y][x] != word.charAt(i)){
//             return false;
//         }
//         char temp = board[y][x];
//         board[y][x] = '*';//visited
        
//       // boolean res = dfs(board, word, y+1, x, i+1) || dfs(board, word, y-1, x, i+1) || dfs(board, word, y, x+1, i+1) ||dfs(board, word, y, x-1, i+1);
//         if(dfs(board, word, y+1, x, i+1) || dfs(board, word, y-1, x, i+1) || dfs(board, word, y, x+1, i+1) ||dfs(board, word, y, x-1, i+1)){
//             return true;
//         }
        
        
//         board[y][x] = temp;//restore
//         //return res;
//         return false;
//     }
// }
/*
 private static boolean bfs(int[][] maze, int startx, int starty) {
            // TODO Auto-generated method stub
            if(maze == null)
                  return false;
            if(maze.length == 0 || maze[0].length == 0)
                  return false;
            LinkedList<Node>queue = new LinkedList<Node>();
            int[][] Direction = {{-1,0}, {0, -1}, {1, 0}, {0, 1}}; //方向：左上右下     
            Noden1 = new Node(0, 0, maze[0][0]);
            queue.offer(n1);
      
            int M = maze.length;
            int N = maze[0].length;
            
            while (!queue.isEmpty()) {
                  Nodenode = queue.poll();
                  if (node.val == 9) {
                        return true;
                  }
                  for(int i = 0; i < 4; i++){
                        int x = node.x + Direction[i][0];
                        int y = node.y + Direction[i][1];
                        //bfs
                        if(x >= 0 && x < M && y >= 0 && y < N && maze[x][y] > 0){
                              NodenewNode = new Node(x, y, maze[x][y]);
                              queue.offer(newNode);
                              maze[x][y] = -1;
                        }
                  }
            }
            return false;
      }
      */
/*
public boolean exist(char[][] board, String word) {
 2         int m = board.length;  
 3         int n = board[0].length;  
 //we actually do not need to create a new matrix!!!! just change the board itself is enough
 4         boolean[][] visited = new boolean[m][n];  
 5         for (int i = 0; i < m; i++) {  
 6             for (int j = 0; j < n; j++) {  
 7                 if (dfs(board, word, 0, i, j, visited))  
 8                     return true;  
 9             }  
10         }  
11         return false;  
12     }
13     
14     public boolean dfs(char[][] board, String word, int index, int rowindex, int colindex, boolean[][] visited) {  
15         if (index == word.length())  
16             return true;  
17         if (rowindex < 0 || colindex < 0 || rowindex >=board.length || colindex >= board[0].length)  
18             return false;  
//for each search we could not turn around and visited the position we have visited
19         if (visited[rowindex][colindex])  
20             return false;  
21         if (board[rowindex][colindex] != word.charAt(index))  
22             return false;  
23         visited[rowindex][colindex] = true;  
24         boolean res = dfs(board, word, index + 1, rowindex - 1, colindex, visited)  
26                 || dfs(board, word, index + 1, rowindex + 1, colindex, visited)  
27                 || dfs(board, word, index + 1, rowindex, colindex + 1, visited)  
28                 || dfs(board, word, index + 1, rowindex, colindex - 1, visited);  
//we need to restore the value of this position so that the next element could also visit it 
29         visited[rowindex][colindex] = false;  
30         return res;  
31             }
*/