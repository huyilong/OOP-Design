Data Stream Median
An unbounded priority queue based on a priority heap. 
The elements of the priority queue are ordered according to their natural ordering, 
or by a Comparator provided at queue construction time, depending on which constructor is used. A priority queue does not permit null elements. 
A priority queue relying on natural ordering also does not permit insertion of non-comparable objects (doing so may result in ClassCastException).
The head of this queue is the least element with respect to the specified ordering. If multiple elements are tied for least value, the head is one 
of those elements -- ties are broken arbitrarily. The queue retrieval operations poll, remove, peek, and element access the element at the head of the queue.

A priority queue is unbounded, but has an internal capacity governing the size of an array used to store the elements on the queue. 
It is always at least as large as the queue size. As elements are added to a priority queue, its capacity grows automatically. 
The details of the growth policy are not specified.

This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces. 
The Iterator provided in method iterator() is not guaranteed to traverse the elements of the priority queue in any particular order.
 If you need ordered traversal, consider using Arrays.sort(pq.toArray()).

Note that this implementation is not synchronized. Multiple threads should not access a PriorityQueue instance concurrently if any of the threads
 modifies the queue. Instead, use the thread-safe PriorityBlockingQueue class.
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: [2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure. 
double findMedian() - Return the median of all elements so far. For example:

add(1) add(2) findMedian() -> 1.5 add(3) findMedian() -> 2

最大最小堆

复杂度

时间 O(NlogN) 空间 O(N)

思路 - max heap & min heap together work out

维护一个最大堆，一个最小堆。
最大堆存的是到目前为止较小的那一半数，
最小堆存的是到目前为止较大的那一半数，
这样中位数只有可能是堆顶或者堆顶两个数的均值。
而维护两个堆的技巧在于判断堆顶数和新来的数的大小关系，还有两个堆的大小关系。我们将新数加入堆后，要保证两个堆的大小之差不超过1。
先判断堆顶数和新数的大小关系，有如下三种情况：最小堆堆顶小于新数时，说明新数在所有数的上半部分。
最小堆堆顶大于新数，但最大堆堆顶小于新数时，说明新数将处在最小堆堆顶或最大堆堆顶，也就是一半的位置。
最大堆堆顶大于新数时，说明新数将处在所有数的下半部分。
再判断两个堆的大小关系，如果新数不在中间，那目标堆不大于另一个堆时，将新数加入目标堆，否则将目标堆的堆顶加入另一个堆，再把新数加入目标堆。
如果新数在中间，那加到大小较小的那个堆就行了（一样大的话随便，代码中是加入最大堆）。
这样，每次新加进来一个数以后，如果两个堆一样大，则中位数是两个堆顶的均值，否则中位数是较大的那个堆的堆顶。

Java中实现最大堆是在初始化优先队列时加入一个自定义的Comparator，默认初始堆大小是11。
Comparator实现compare方法时，用arg1 - arg0来表示大的值在前面 
Priority queue default as min heap -- i.e. poll out the min value so far
we could use return o2-o1 to implement max heap with priority -- i.e. poll out the max value so far

class MedianFinder {
    
    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;
    
    public MedianFinder(){
        // 新建最大堆
        maxheap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        // 新建最小堆 - default as min heap
        minheap = new PriorityQueue<Integer>();
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        // 如果最大堆为空，或者该数小于最大堆堆顶，则加入最大堆   -- max heap store smaller values -- num <= maxheap.peek()
        if(maxheap.size() == 0 || num <= maxheap.peek()){
            // 如果最大堆大小超过最小堆，则要平衡一下
            if(maxheap.size() > minheap.size()){
                minheap.offer(maxheap.poll());
            }
            maxheap.offer(num);
        // 数字大于最小堆堆顶，加入最小堆的情况 -- min heap store larger values -- num > minheap.peek()
        } else if (minheap.size() == 0 || num > minheap.peek()){
            if(minheap.size() > maxheap.size()){
                maxheap.offer(minheap.poll());
            }
            minheap.offer(num);
        // 数字在两个堆顶之间的情况
        } else {
            if(maxheap.size() <= minheap.size()){
                maxheap.offer(num);
            } else {
                minheap.offer(num);
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        // 返回大小较大的那个堆堆顶，如果大小一样说明是偶数个，则返回堆顶均值
        if(maxheap.size() > minheap.size()){
            return maxheap.peek();
        } else if (maxheap.size() < minheap.size()){
            return minheap.peek();
        } else if (maxheap.isEmpty() && minheap.isEmpty()){
            return 0;
        } else {
            return (maxheap.peek() + minheap.peek()) / 2.0;
        }
    }
};

class MedianFinder {
    
    PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(Collections.reverseOrder()); /// using the default reverse comparator
    //Collections.reverseOrder()
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        maxheap.offer(num);
        minheap.offer(maxheap.poll());
        if(maxheap.size() < minheap.size()){
            maxheap.offer(minheap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return maxheap.size() == minheap.size() ? (double)(maxheap.peek() + minheap.peek()) / 2.0 : maxheap.peek();
    }
};

Q：如果要求第n/10个数字该怎么做？
A：改变两个堆的大小比例，当求n/2即中位数时，两个堆是一样大的。而n/10时，说明有n/10个数小于目标数，9n/10个数大于目标数。所以我们保证最小堆是最大堆的9倍大小就行了。

Add and Search Word - Data structure design 
Design a data structure that supports the following two operations:
void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only lettersa-z or.. A . means it can represent any one letter.
For example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

[思路]
1. 用一个set存. 超时.
2. 用trie来存. 

public class WordDictionary {
    Set<String> dict = new HashSet<String>();
    // Adds a word into the data structure.
    public void addWord(String word) {
        dict.add(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if(dict.contains(word) ) return true;
        else if(!word.contains(".")) return false;
        
        for(int c='a'; c<='z'; c++) {
            int i=0;
            while(word.charAt(i++) != '.'){}
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(i-1, (char)c);
            if( search(sb.toString()) ) return true;
        }
        return false;
    }
}
public class WordDictionary {
    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        Map<Character, TrieNode> children = root.children;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            TrieNode t;
            if(children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }
            children = t.children;
            if(i==word.length()-1) t.leaf=true;
        }
    }

    public boolean search(String word) {
        return searchNode(word, root);
    }
    
    public boolean searchNode(String word, TrieNode tn) {
        if(tn==null) return false;
        if(word.length() == 0 ) return tn.leaf;
        
        Map<Character, TrieNode> children = tn.children;
        TrieNode t = null;
        char c = word.charAt(0);
        if(c=='.') {
            for(char key : children.keySet() ) {
                if(searchNode(word.substring(1), children.get(key) )) return true;
            }
            return false;
        } else if(!children.containsKey(c)) {
            return false;
        } else {
            t = children.get(c);
            return searchNode(word.substring(1), t);
        }
    }    
}

class TrieNode {
    // Initialize your data structure here.
    char c;
    boolean leaf;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    
    public TrieNode(char c) {
        this.c = c;
    }
        
    public TrieNode(){};
}

------------------------------------------------------------------------------------
median of two sorted arrays
/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        return (
            findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)
        ) / 2.0;
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){       
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }
        
        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE; 
        
        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
}

/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return helper(numbers, 0, numbers.size() - 1, k);
    }
    
    public int helper(ArrayList<Integer> numbers, int l, int r, int k) {
        if (l == r) {
            return numbers.get(l);
        }
        int position = partition(numbers, l, r);
        if (position + 1 == k) {
            return numbers.get(position);
        } else if (position + 1 < k) {
            return helper(numbers, position + 1, r, k);
        }  else {
            return helper(numbers, l, position - 1, k);
        }
    }
    
    public int partition(ArrayList<Integer> numbers, int l, int r) {
        if (l == r) {
            return l;
        }
        int num = numbers.get(r);
        int index = l;
        for (int i = l; i < r; i ++) {
            if (numbers.get(i) >= num) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(index));
                numbers.set(index, temp);
                index ++;
            }
        }      
        numbers.set(r, numbers.get(index));
        numbers.set(index, num);
        return index;         
    }
};

Gas Station
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas stations index if you can travel around the circuit once, otherwise return -1.


public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //用两个变量存+= gas[i] - cost[i]。一个帮助判断当前这个点作为gas station的起点合不合适，一个帮助判断总的需求是不是大于供给。如果总的需求大于供给那么肯定是无解的，如果需求小于等于供给，就可以返回刚才找到的起始点。
        if (gas==null|| cost==null||gas.length==0||cost.length==0||gas.length!=cost.length)
         return -1;
         
        int sum = 0;  
        int total = 0;  
        int index = 0;  
        for(int i = 0; i < gas.length; i++){  
            sum += gas[i]-cost[i];  
            total += gas[i]-cost[i];  
            if(sum < 0){  
                index=i+1; 
                sum = 0;   
            } 
        }  
        if(total<0)
            return -1;  
        else
            return index;  
    }
}

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

首先要理解题意:
"a"对应"a", 这种匹配不解释了
任意字母对应".", 这也是正则常见
0到多个相同字符x,对应"x*", 比起普通正则,这个地方多出来一个前缀x. x代表的是 相同的字符中取一个,比如"aaaab"对应是"a*b"
"*"还有一个易于疏忽的地方就是它的"贪婪性"要有一个限度.比如"aaa"对应"a*a", 代码逻辑不能一路贪婪到底
正则表达式如果期望着一个字符一个字符的匹配,是非常不现实的.而"匹配"这个问题,非 常容易转换成"匹配了一部分",整个匹配不匹配,
要看"剩下的匹配"情况.这就很好的把 一个大的问题转换成了规模较小的问题:递归
确定了递归以后,使用java来实现这个问题,会遇到很多和c不一样的地方,因为java对字符 的控制不像c语言指针那么灵活charAt一定要确定某个位置存在才可以使用.
如果pattern是"x*"类型的话,那么pattern每次要两个两个的减少.否则,就是一个一个 的减少. 无论怎样减少,都要保证pattern有那么多个.比如s.substring(n), 其中n 最大也就是s.length()

代码如下：
public static boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;

        // length == 1 is the case that is easy to forget.
        // as p is subtracted 2 each time, so if original
        // p is odd, then finally it will face the length 1
        if (p.length() == 1)
            return (s.length() == 1) && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        // next char is not '*': must match current character
        if (p.charAt(1) != '*') {
            if (s.length() == 0)
                return false;
            else
                return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                        && isMatch(s.substring(1), p.substring(1));
        }else{
            // next char is *
            while (s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s, p.substring(2))) 
                    return true;
                s = s.substring(1);
            }
            return isMatch(s, p.substring(2));
        }
    }
}
Wildcard Matching
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

一开始是非常想用递归的方法做的，因为前面已经有做正则表达式的经验了，所以认为这一题应该是同样的思路做。
但是小数据集还好，大数据集根本过不去，分析了一下，主要是要回朔的地方太多了，或者是需要处理的分支实在太多。

参考了网上一个挺巧妙的方法，对目标字符串和通配符字符串分别设置索引，指向当前位置。在*和*之间自然是逐个匹配；
当遇到*号时，在目标字符串和通配符字符串都记录下当前位置；如果遇到无法匹配的情况，先检查之前没有*号的位置记录，
如果有则将目标字符串和通配符字符串的索引都回退到当时保存的位置，然后字符串索引自加之后继续开始匹配。

这个算法对*的处理就是，当第一次遇到时，默认不匹配目标字符串中的任何内容，当后面的内容遇到了无法匹配的情况时，
再进行回退，每次回退相当于利用之前的*多匹配一个目标字符串中的字符，直到全部匹配完或是无法匹配退出。

public class Solution {
    public boolean isMatch(String s, String p) {
        int posS = 0, posP = 0;
        int posStar = -1, flagInS = -1;
        while (posS < s.length()) {
            if (posP < p.length() && (s.charAt(posS) == p.charAt(posP) || p.charAt(posP) == '?')) {
                posS++;
                posP++;
            } else if (posP < p.length() && (p.charAt(posP) == '*')) {
                flagInS = posS;
                posStar = posP;
                posP++;
            } else if (posStar != -1) {
                posS = ++flagInS;
                posP = posStar + 1;
            } else {
                return false;
            }
        }
        while (posP < p.length() && p.charAt(posP) == '*') {
            posP++;
        }
        return posS == s.length() && posP == p.length();
    }
}

skyline problem
For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, 
where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

把每一个building拆成两个edge，一个入一个出。所有的edge加入到一个list中。再对这个list进行排序，排序顺序为：如果两个边的position不一样，那么按pos排，否则根据edge是入还是出来排。
根据position从前到后扫描每一个edge，将edge根据是入还是出来将当前height加入或者移除heap。再得到当前最高点来决定是否加入最终结果。非常巧妙，值得思考。

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        List<Edge> edges = new ArrayList<Edge>();
        for (int[] building : buildings) {
            Edge startEdge = new Edge(building[0], building[2], true);
            edges.add(startEdge);
            Edge endEdge = new Edge(building[1], building[2], false);
            edges.add(endEdge);
        }
        //sort edges according to position, height, and if the edge is start or end
        edges.sort(new Comparator<Edge>(){
            public int compare(Edge l1, Edge l2) {
                if (l1.pos != l2.pos)
                    return Integer.compare(l1.pos, l2.pos);
                if (l1.isStart && l2.isStart) {
                    return Integer.compare(l2.height, l1.height);
                }
                if (!l1.isStart && !l2.isStart) {
                    return Integer.compare(l1.height, l2.height);
                }
                return l1.isStart ? -1 : 1;
            }
        });
        //heap of height
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        for (Edge edge : edges) {
            if (edge.isStart) {
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    res.add(new int[]{edge.pos, edge.height});
                }
                heap.add(edge.height);
            } else {
                heap.remove(edge.height);
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    res.add(heap.isEmpty() ? new int[]{edge.pos,0} : new int[]{edge.pos, heap.peek()});
                }
            }
        }
        return res;
    }
    class Edge implements Comparable<Edge>{
        int pos;
        int height;
        boolean isStart;
        public Edge(int pos, int height, boolean isStart) {
            this.pos = pos;
            this.height = height;
            this.isStart = isStart;
        }
    }
}

public class Solution {
    public String longestPalindrome(String s) {
        String res = new String();
        String temp = new String();
        int len = s.length();
        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                temp = s.substring(i,j);
                if(temp.length() > res.length() && helper(temp)){
                    res = temp;
                }
            }
        }
        
        return res;
    }
    
    private boolean helper(String s){
        if(s == null || s.length() == 0){
            return true;
        }
        int input_len = s.length();
        for(int i=0; i<(input_len/2); i++){
            if(s.charAt(i)!=s.charAt(input_len-i-1)){
                return false;
            }
        }
        
        //
        return true;
    }
}
