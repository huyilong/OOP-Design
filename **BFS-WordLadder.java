For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.


把每个单词作为一个node进行BFS。
当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果在字典里面，就入队，
并将下层count++，并且为了避免环路，需把在字典里检测到的单词从字典里删除。
这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。



 正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），
 那么return的路径肯定是最短的。

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

 像给的例子 start = hit，end = cog，dict = [hot, dot, dog, lot, log]

 按照上述解题思路的走法就是：

  level = 1    hit   dict = [hot, dot, dog, lot, log]

         ait bit cit ... xit yit zit ，  hat hbt hct ... hot ... hxt hyt hzt ，  hia hib hic ... hix hiy hiz

 

  level = 2    hot  dict = [dot, dog, lot, log]

         aot bot cot dot ...  lot ... xot yot zot，hat hbt hct ... hxt hyt hzt，hoa hob hoc ... hox hoy hoz

 

  level = 3    dot lot  dict = [dog log]

         aot bot ... yot zot，dat dbt ...dyt dzt，doa dob ... dog .. doy doz，

         aot bot ... yot zot，lat lbt ... lyt lzt，loa lob ... log... loy loz

 

  level = 4   dog log dict = [] 

         aog bog cog

 

  level = 5   cog  dict = []


public int ladderLength(String start, String end, HashSet<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())  
        return 0; 
        
        LinkedList<String> wordQueue = new LinkedList<String>();
        int level = 1; //the start string already count for 1
        int curnum = 1;//the candidate num on current level
        int nextnum = 0;//counter for next level
        
        wordQueue.add(start);
        
        while(!wordQueue.isEmpty()){
            String word = wordQueue.poll();
            curnum--;
            
            for(int i = 0; i < word.length(); i++){
                char[] wordunit = word.toCharArray();
                for(char j = 'a'; j <= 'z'; j++){
                    wordunit[i] = j;
                    String temp = new String(wordunit);  
                    
                    if(temp.equals(end))
                        return level+1;
                    if(dict.contains(temp)){
                        wordQueue.add(temp);
                        nextnum++;
                        dict.remove(temp);
                    }
                }
            }
            
            if(curnum == 0){
                curnum = nextnum;
                nextnum = 0;
                level++;
            }
        }
        
        return 0;
    }



  你用了curnum和nextnum这两个变量去跟踪queue的大小与level的大小，
  其实我觉得我下面的方法，让代码看起来更简洁一些。而且相对不容易出错。

public static int ladderLength(String start, String end, Set<String> dict) {

if (start == null || end == null || start.length() == 0 || end.length() == 0 || start.length() != end.length())
return 0;

LinkedList<String> wordQueue = new LinkedList<String>();
int level = 1; //the start string already count for 1
wordQueue.add(start);

while (!wordQueue.isEmpty()){

	int size = wordQueue.size();

	while (size > 0) {
		String word = wordQueue.poll();
		for (int i = 0; i < word.length(); i++) 
		{
			char[] wordunit = word.toCharArray();
			for (char j = 'a'; j <= 'z'; j++) {
				wordunit[i] = j;
				String temp = new String(wordunit);

				if (temp.equals(end))
					return level + 1;
				if (dict.contains(temp)) {
					//enqueue and remove from the dict
					wordQueue.add(temp);
					dict.remove(temp);
				}
			}
		}
		size--;
	}

	level++;
}
return 0;
}
