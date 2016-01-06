
来一发wayfair lab的面经。他家lab项目没有onsite, 刚电面完。hr- > code sample，然后oa，然后电面 （刚结束）。. 
oa就是给n个string array, 要求每个array取一个词组成一个新的string。返回所有的string。 他不让用递归，我就brute force做了。。。
电面就是著名的正负数换位置那题。好像只需要最简单的两种方法。另一种比较tricky的方法大家可以考古一下，挺有意思。然后给了一些php，看看学习的速度。都是最简单的语法，还是比较直观。

然后12.3接HR电话，接着发给了她一份课上的project的代码，一周后12.10收到OA，一道题做一天，我的题是String的Combination，输入List<List<String>>, 
要求输出所有的String的不同组合，和Leetcode的combination很像，但是题目要求不能用recursive写，于是自己写了一个不用迭代的方法，具体就是拿List<List<String>> ret,
作为result，第一次先装第一个List里面的每个String，然后每个取出来依次继续装第二个List的String，没用stack，time complexity是O(len1 * len2 ...lenn), 
觉得不放心又加了DFS的方法一起交上去了。

11月份底面的 当时正处于没offer的恐慌期 wayfair给了个小offer至少可以帮忙保个底 
面的题目很常规，都是地里的面经题然后在coderpad上现写代码和testcase （1）negative positive number的排序，
 要求keep relative sequence. O(n) space 和 constant space都实现了一遍 (2) leetcode single number

Input: [-6, 2, 1, 2, -10, -4, 8]
Output: [-6, -10, -4, 2, 1, 2, 8]
一共实现了了3种方法，一种O（N）space O（n）time的， 一种O（1）space O（n^2）time的

sort color -- counting sort
whenever meet with a negative number push it to the head in front!!!

还有一个tricky但有limit的方法实现了O（1）space O（n）time。写完这三个就剩10分钟不到了，
他就贴了一段PHP卖鞋的代码上来让我找bug，当时太久不看PHP一时蒙圈没答上来，但后来一想其实还是很明显的。
主要就是size为空时显示的问题，但似乎并不影响最后的面试结果，一周后顺利拿offer。要面他家的小伙伴加油啊，
总体题都不难，还是很好拿offer的~

电面就是著名的正负数换位置那题。好像只需要最简单的两种方法。另一种比较tricky的方法大家可以考古一下，
挺有意思。然后给了一些php，看看学习的速度。都是最简单的语法，还是比较直观。

我做了他家的OA， 我的题目和他们说的不一样，不过我觉得更简单一些。 输入是List of String List， 然后让你找出所有的combination。
 例如输入 {{"a", "b", "haha"}, {"12", "35"}, {"woqu", "biequ"}}, 那么输出就是
"a12woqu", "a12biequ", "a35woqu", "a35biequ", "b12woqu"....依此类推


Wayfair是波士顿的一家电商公司，虽然没有FLAG那么高大上，但感觉发展前途还不错。
第一轮HR电面：因为算是朋友内推，所以比较快拿到了Recruiter的回复。但是反复约了好久才最终确认时间进行了电面。
主要就是问了些background信息，简历上的内容和简单的Java和SQL基础都没怎么问。

第二轮算是提交Sample Code：就直接把github的链接发了过去。
要写一份Short Description介绍Project。

第三轮OA：发了一个link，一道简单的Maximun Subarray。
和leetcode不同的是不需要返回最大值而是返回Subarray。

第四轮Engineer电面：应该是个美国小哥，人感觉挺nice，但电话声音不是很清楚。上来先过了一遍简历，
讲了一下Work Experience和Project。然后出了两道题：

分别是给一个Array，里面是positive integer和negative integer，要把negative的移到左边，
positive的移到右边。写的时候才问了0如何处理，回答说和positive一样。
写完后会继续follow up如何分别提高time performance和space performance。.

第二道题是single number，我把用HashSet和Bit Manipulation的两种方法都说了，
结果让我用HashSet写的时候忘记了HashSet的Iterator的正确用法纠结了一会。虽然小哥说理解了我的意思，但感觉会有点影响。

最后问了几个generic的问题。

发帖攒人品求过！


上一个wayfair的电面面经吧。感觉地里面他家的人不多。
1. inner join 和 outer join的区别。
2. abstract class和interface的区别。 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
这些都是嘴上说说就可以了。
下面是写代码
3.给一个数组，返回一个没有duplicate item的数组。
4. 给2个string，判断短的那个是不是长的那个的substring。没有wildcard。

周一时候同一天拿到Amazon跟Wayfair Lab的offer，估计最后会从了Amazon，也算是对这将近半年的辛苦搬家找工作的回报吧。看地里Wayfair Lab的面经不多，就大概说一下我的经历吧。
先是timeline： 11/19 接HR Meghan电话，问了下情况算是一面吧 —— 11/23 发code sample —— 11/30 完成OA —— 12/8 电面 —— 12/14 拿到offer
OA的题跟地里大多人的一样combination， 用dfs做掉就好，有一天的时间随便登陆CoderPad，所以时间还是非常充裕的，我反正就登了一次花了不到半小时就写完了。. 1point3acres.com/bbs
然后是一个小时的电面， 面试官是老印，上来先问了下简历让我讲一遍OA的题，
然后是那道给一个数组把小于0的数放到前面，大于0得数移到后面的题，写题过程中我说0我会稍后处理的时候他说把0看做正数就好
Example:.

Input: [-6, 2, 1, 2, -10, -4, 8]
Output: [-6, -10, -4, 2, 1, 2, 8]
一共实现了了3种方法，一种O（N）space & O（n）time的， 一种O（1）space & O（n^2）time的，
还有一个tricky但有limit的方法实现了O（1）space & O（n）time。写完这三个就剩10分钟不到了，
他就贴了一段PHP卖鞋的代码上来让我找bug，当时太久不看PHP一时蒙圈没答上来，但后来一想其实还是很明显的。
主要就是size为空时显示的问题，但似乎并不影响最后的面试结果，一周后顺利拿offer。要面他家的小伙伴加油啊，
总体题都不难，还是很好拿offer的~


还有一个tricky但有limit的方法实现了O（1）space & O（n）time”这个能不能详细说下？
还有那个PHP code是不是就是有一个变量的size在一直变，并且这个size会影响输出内容？

PHP的那题就是给鞋子那个类赋值的时候有的鞋子是没有size的，而输出print的时候没有考虑到这种情况而已。. 
至于那个tricky的方法说起来有点复杂，大概思路就是先用所有值减去最小值，
把所有数变成正数，算出新的最大值M，扫一边把原来小于0的数找出来，乘以M依次加到排序后应该在的位置的值上
，所以这样一个位置就存了两个值，每个space的值mod M就是未排序前的值，除M就是排序后的值，大于0的同理。
最后除M还原数组就行了，所以limit就是数组中的数不能太大，要不该超int最大限制了

补充内容 (2015-12-17 12:01):
说白了就是用一个space存两个值，至于怎么存其实还是有很多方法的，比如int前16位存新值，
后16位存原值之类的，总之用这样的方法限制就是数不能太大


感谢楼主分享， 还想问下PHP那段代码给出鞋子的完整class了吗？__construct（）里没有给size属性赋值吗?
如果是这样的话要修这个bug应该就是在print的时候检查size是否大于等于1吧，这段代码楼主还能想起来别的bug吗？谢谢楼主～～


楼主，这一步：扫一边把原来小于0的数找出来，乘以M依次加到排序后应该在的位置的值上，
就是原来的正负数怎么处理才能不用多余空间啊，比如找到第一个负数，应该在的位置是第一位，
而第一位原先值是正的，如果先处理了<0的，那这原先的第一位值就已经覆盖了啊，怎么分别记录正负数的位置呢，还是我理解有误？

楼主 那个O(n) 的方法是不是有 bug 比如 {1, -1, -2} 这个数组，选出最小数 -2，然后减去 {3, 1, 0}，
而如果用最大数 3 乘以 1 （第一个负数） 加上 第一位原值 3 的话是 6， 按照楼主的方法后面取模或者除以得到 
排序前或者排序后的原值 这样的话 6 mod 3 是 0 ，不符合原值... 楼主是怎么考虑这个情况的呢

言归正题，wayfair是自己在网上投的，正好有同学之前也拿了这家的offer，所以认真准备好好面。
Wayfair规模不比google这类公司，但这几年发展挺快，好像去年上市，一直招人挺多，待遇也挺不错。一共四轮，
第一轮hr随便聊聊，然后发code sample，据说发lc上的ac答案都可以过，
我谨慎了一下发了之前学校做的网站的PHP和一个算法的JAVA过去。第二天收到OA，和地里面经一样，是个backtracking的组合题，
一个list of list，把每个list里面拿一个出来组合，不难，自己写test case，
考虑了如果有空list是全部返回空还是直接跳过取下一个。过几天收到电面，这家没onsite，电面就终面。
面之前把所有地里和glassdoor面经刷完，以为轻松愉快结果还是被坑了。一般new grad职位就考一道算法，
大多数是正负数左右移那个，no extra space，结果那天电面果然是原题，先装了个逼然后给出几种解法，
面试官很满意的样子，说我们来点不一样的PHP，我一听好虚，不是junior才考PHP吗，哥几个月没写PHP了，
不会是因为code sample发了PHP就要考我吧。面试官把题打出来，一大段代码，用户查询系统内商品剩余信息的，
和wayfair电商网站很类似，估计是直接把公司代码搬来了，然后他问这段代码有啥问题，卧槽当时都冒冷汗了，
这PHP还写得有点复杂，结构语法比学校写的难多了。看了十分钟大概看懂每段具体功能，在面试官提醒下还是找出了问题，
也是机智和当时运气好，不然肯定gg。最后面试官说this is not easy, it is about real industry，
问了点问题就挂电话。之前看面经都是2天出结果，po主等到第三天就坐不住了，催hr说manager出差了，
好消息是feedback是positive，但没出最后结果还是虚的一逼。最后等了一周+2天拿到offer，当晚连夜就去了波士顿。
其实感觉和这个offer挺有缘分，面试前刚好去波士顿玩了一次接触了个妹子是波士顿的，感觉不错就在想努努力offer妹子都拿下。
总的来说这么小半年还是挺纠结挺不容易的，没offer的时候什么都不想干，遇到码农形势不好，但大家都以为你们很好找，
其实并不是。面了很多家，各种电面onsite被三哥坑，题都写出来了第二天收据信，最蛋疼的还是面到终面自我感觉良好过几天早上收据信
，还是挺打击自信的。
各位还在找的加油加油加油，相信the best is yet to come！多攒RP多攒RP多攒RP，重要的话说三次！
火车上一晃一晃的码字，头脑不是很清楚，写的条理不清的请见谅！祝大家都拿好offer！

（1）negative positive number的排序， 要求keep relative sequence.  
O(n) space 和 constant space都实现了一遍   (2) leetcode single number

第一轮：coding。sql的一对select join update。
然后valid parentheses（leetcode原题）；然后web相关的问题，type url之后发生了什么，
怎么improve webserver performance等等
第二轮：聊天。了解项目
第三轮：coding。又是一堆sql的select join update。然后given a number n, 
count number of 2s from 0 to n;.
然后就是跟hr谈工资期望了。。。。
最后挂掉了

下午面的。。
一上来就聊聊公司，过一遍简历，聊聊实习经历。.
然后讲上一次做OA的那题目
接着算法题：
给了个array 有negtive和positive， 将所有negtive 放到左边，positive 放到右边。

反应很快过了2个小时就联系我说feedback了。 

public ArrayList<ArrayList<String>> combination(ArrayList<ArrayList<String>> dict){
    ArrayList<ArrayList<String>> rst = new ArrayList<ArrayList<String>>();
    ArrayList<String> tmp = new ArrayList<String>();
    Stack<Integer> s = new Stack<Integer>();
    s.push(0);

    while(!s.isEmpty()){
      while(s.size() <= dict.size()){
        if(dict.get(s.size() - 1).size() == 0){
          tmp.add(""); 
        }else{
          tmp.add(dict.get(s.size() - 1).get(s.peek()));
        }
        if(s.size() == dict.size()){
          rst.add(new ArrayList<String>(tmp));
        }
        s.push(0);
      }

      while(s.size() > dict.size() || s.peek() >= dict.get(s.size() - 1).size()){
        s.pop();
        if(s.isEmpty()){
          return rst;
        }
        tmp.remove(tmp.size() - 1);-google 1point3acres
        s.push(s.pop() + 1);
      }
    }
    return rst；
  }
}

public ArrayList<ArrayList<String>> combination(ArrayList<ArrayList<String>> dict){
    ArrayList<ArrayList<String>> rst = new ArrayList<ArrayList<String>>();
    ArrayList<String> tmp = new ArrayList<String>();
    Stack<Integer> s = new Stack<Integer>();
    s.push(0);
     while(s.size() < dict.size()){
        if(dict.get(s.size() - 1).size() == 0){
          tmp.add("");
        }else{
          tmp.add(dict.get(s.size() - 1).get(s.peek()));
        }
        s.push(0);
      }

      if(s.size() == dict.size()){. 1point3acres.com/bbs
                if(dict.get(s.size() - 1).size() == 0){
          tmp.add("");
        }else{. more info on 1point3acres.com
          tmp.add(dict.get(s.size() - 1).get(s.peek()));
        }
        rst.add(new ArrayList<String>(tmp));
        s.push(0);
     }

      while(s.size() > dict.size() || s.peek() >= dict.get(s.size() - 1).size()){
        s.pop();
        if(s.isEmpty()){
          return rst;
        }
        tmp.remove(tmp.size() - 1);
        s.push(s.pop() + 1);
      }
    }
    return rst;
  }

  12.11下午1点交的OA，3点多HR就回邮件说约电话面试了，12.16下午一点开始的电面，上来问我简历和project就花了快二十分钟，
  然后做题，输入array，把negative放在左边，positive放在右边的题目，问了一下0和相对顺序可以打乱么，
  面试官回答0按正数处理，相对的顺序不可以打乱。先写了一个O(n) space的方法，然后用一个pointer写了一个in place的方法
  ，面试官很满意。然后说给一段PHP代码，问代码是神马意思，之前自己没学过PHP，地里面的面经当时也没有PHP相关，自己看了一下
  ，大概是有几种不同的鞋子，分不同的种类啊（high heel，sneaker什么的），颜色啊，尺码啊，价格啊什么的，
  然后把他们显示出来。我就大概和他讲了每段代码啥意思都，然后面试官运行了一下，说里面有个错误，你能找到哪里错了吗，
  我记得显示的是，小于50块钱的鞋子当中，红色的尺码有5,6,7,6.5什么的，黄色的尺码有什么什么，然后蓝色的尺码没有显示，
  于是开始找哪里有错误，发现在最上面蓝色那里size这个attribute是空的，于是跟面试官说了，然后给他随便加了一些size,
   运行显示了一下，面试官很开心。然后说那我再问你一下，如果我让你显示的是 A blue XX shoe out of stock你如何修改
   ，这个是在中间那块改的，就照猫画虎加上了显示‘ out of stock的语句，还有删除了之前的一些，给他显示出来了。
   面试官说OK 你的反应好快啊，我的问题都被你解答出来问完了，你有什么问题问我的吗，于是自己问了下大概每天的工作
   是什么之类的，面试官回答了一下，到2点的时候面试就结束了，相互祝好运之后面试官就挂掉了电话。


 后来说电面还会有一道题，是给个n个数的array数组，随机挑出1个数，然后follow up是随机挑出k个数，我的解法in place的话需要移动array。

那道题time complexity average 是O(n), space complexity O(1).

额，不好意思，仔细一想我写的那个in place的方法time complexity 差不多是O(n^2),就是遇到负数就一直往前移，
直到最前面或者遇到前面的数也是负数。

那我们算法一样, 最后随机挑数那道题楼主你是面试中被问到了吗？你用的就是Reservoir sampling Algorithm吗？谢谢楼主～～

我的算法是每次random出0到m的一个数，然后把这个数和下标为m的数换一下，下次random一个0到m-1的数
已经拒了他家了…懂你的这个算法了，只是有一个问题，这个水塘取样的时间复杂度是O(n-k)，我提到的那个是O(k)

比如1到10十个数，想选3个数，先随机一个1到10的数，假如是7，把7放到结果里，并且把7和10交换个位置，
原数组变成1，2，3，4，5，6，10，8，9，7，这时候随机一个1到9的数，假设是3，把3放到结果里，并且把3和9交换位置
，数组变成1，2，9，4，5，6，10，8，3，7，再随机一个1到8的数，比如还是7，这时候把第7个数就是10放到结果里，
然后把10和8交换，数组变成1，2，9，4，5，6，8，10，3，7. 如果还有后面的话就继续随机1到7的数，
其实原数组的最后k位也是最后想要的结果

补充内容 (2015-12-23 10:03):.
而且是等概率的，比如上面这个例子，对某一个数来说，第一次被选中的概率是1/10，
第二次中的概率是9/10 ＊1/9 ＝ 1/10，第三次中的概率是9/10 ＊ 8/9 ＊ 1/8 ＝ 1/10，
总体被抽中的概率是3/10，所以这个算法是对的

 有一个疑问 每次 random 比如 第一次 random 取数的时候 你们是用的 比如如果java的话 Random 方法吗 还是别的呢 感谢
 对，应该是，你最近也要面吗？就用java的random再转成int

 Reservoir sampling is a family of randomized algorithms for randomly choosing a sample of k items 
 from a list S containing n items, where n is either a very large or unknown number.
  Typically n is large enough that the list doesnt fit into main memory.

Example: sample size 1
Suppose we see a sequence of items, one at a time. We want to keep a single item in memory,
 and we want it to be selected at random from the sequence. If we know the total number of items (n), 
 then the solution is easy: select an index i between 1 and n with equal probability, and keep 
 the i-th element. The problem is that we do not always know n in advance. A possible solution 
 is the following:

Keep the first item in memory.
When the i-th item arrives (for i>1):
with probability 1/i, keep the new item instead of the current item;
with probability 1-1/i, keep the current item and discard the new item.
So:

when there is only one item, it is kept with probability 1;
when there are 2 items, each of them is kept with probability 1/2;
when there are 3 items, the third item is kept with probability 1/3, and each of 
the previous 2 items is also kept with probability (1/2)(1-1/3) = (1/2)(2/3) = 1/3;
by induction, it is easy to prove that when there are n items, each item is kept with probability 1/n.

Wayfair的面试
第一轮，介绍一下自己的项目，然后问了点sql的问题。最后面了一个智力题。苹果橘子的问题
第二轮，直接出了一道算法题，给了一棵树，叶子节点全是数字，其他的节点都是+号，让把这个表达式 
从左往右返回一个string，差不多就是一个深度优先的遍历。接下来又问了一堆sql的问题，
例如说外连接和内连接的区别之类的，index的概念。还有web的基本知识，打开一个网页，
背后到底发生了什么。又出了一个智力题，题目有点忘了，具体就是沙漏拼接算时间，这个问题在提示下做了出来。

现在还在纠结，对个人发展来说，选择哪个更加好一点呢？

def partition(L):
    j = 0
 
    for i in range(1, len(L)):
        val = L[i]
        k = i
       
        while k > j and val < 0:
            L[k] = L[k - 1]
            k -= 1
           
            if j == k:
                j += 1
 
        L[k] = val
 
L = [9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2]
partition(L)
 
# result: [-3, -7, -6, -5, 9, 2, 1, 0, 0, 3, 2]

up vote
3
down vote
Here is a constriant version of O(n) time O(1) space solution, 
it assume maxValue*(maxValue+1) is less than Integer.MAX_VALUE, 
where maxValue is the result of maxmum value minus minmum value in the array.
 It utilize the original array as the temporary array to store the result.

public static void specialSort(int[] A){
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for(int i=0; i<A.length; i++){
        if(A[i] > max)
            max = A[i];
        if(A[i] < min)
            min = A[i];
    }
    //Change all values to Positive
    for(int i=0; i<A.length; i++)
        A[i]-= min;

    int newMax = max-min+1;

    //Save original negative values into new positions
    int currNegativeIndex = 0;
    for(int i=0; i<A.length; i++)
        if(A[i]%newMax < (-min))
            A[currNegativeIndex++] += (A[i]%newMax)*newMax;
    //Save original positive values into new positions
    int currPositiveIndex = currNegativeIndex;
    for(int i=0; i<A.length; i++)
        if(A[i]%newMax > (-min))
            A[currPositiveIndex++] += (A[i]%newMax)*newMax;
    //Recover to original value 
    for(int i=0; i<A.length; i++){
        A[i] = A[i]/newMax + min; 
    }
}

Insertion sort is a simple algorithm and swaps adjacent numbers in O(1) space. 
This is the general behavior were looking for. In the code below, the integers are 
continuously swapped until the beginning of the list, initially represented by `j`, 
is reached. Once `j` is reached, `j` is incremented by 1 to prevent the partitioned 
part of the list from being altered. ``` def partition(L): j = 0 for i in range(1, 
len(L)): val = L[i] k = i while k > j and val < 0: L[k] = L[k - 1] k -= 1 if j == k:
j += 1 L[k] = val L = [9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2] partition(L) # result:
[-3, -7, -6, -5, 9, 2, 1, 0, 0, 3, 2] ``` Heres a visual of how the partitioning
works with the integers -3 and -7. -3 is swapped until it reaches the L[0]. Next, 
-7 is swapped until it reaches L[1] and so on. ``` # -3 [9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2] # 
first pass [9, -3, 2, 1, 0, 0, -7, -6, 3, -5, 2] # second pass [-3, 9, 2, 1, 0, 0, -7, -6, 3, -5, 2] # third pass # 
-7 [-3, 9, 2, 1, 0, 0, -7, -6, 3, -5, 2] # first pass [-3, 9, 2, 1, 0, -7, 0, -6, 3, -5, 2] # second pass [-3, 9, 2, 1, -7, 0, 0, -6, 3, -5, 2]
[-3, 9, 2, -7, 1, 0, 0, -6, 3, -5, 2] [-3, 9, -7, 2, 1, 0, 0, -6, 3, -5, 2] [-3, -7, 9, 2, 1, 0, 0, -6, 3, -5, 2] ... ```

It can be done in O(n) and space O(1).

We need to scan 3 times through the array and change some values carefully.

Assumption: the max value in the array with size N is should be smaller than (N+1) * Integer.MAX_VALUE.

We need this assumption since we well change some positive values in the array.

In the first scan, find # of negative and positive values, and the max.
In the second scan we create the negative section of array as follows:
We start from the beginning of the array and we "swap" the first found positive number (e.g. at index i) with the first found negative number (e.g. j). Since negative numbers are being considered with respect to their location, the swap will be okay.

The problem is the positive numbers because there might be some other positive numbers between i and j. To handle this issue, we have to somehow encode the index of the positive number in that value before swapping. So then we can realize where it was at the first point. We can do this by a[i]=(i+1)*(max)+a[i].

In the third scan, we create the positive section of array. by end of the second scan, the negative array is constructed, and the positive numbers are shifted to the right side, but their location may not be correct. So we go though it and correct their position since this info was encoded their value.
Here is the code:

import java.util.Arrays;

public class LinearShifting {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a = {-1,7,3,-5,4,-3,1,2};
        sort(a);
        System.out.println(Arrays.toString(a));  //output: [-1, -5, -3, 7, 3, 4, 1, 2]
    }
    public static void sort(int[] a){
        int pos = 0;
        int neg = 0;
        int i,j;
        int max = Integer.MIN_VALUE;
        for(i=0; i<a.length; i++){
            if(a[i]<0) neg++;
            else pos++;
            if(a[i]>max) max = a[i];
        }
        max++;
        if(neg==0 || pos == 0) return;//already sorted
        i=0;
        j=1;
        while(true){
            while(i<=neg && a[i]<0) i++;
            while(j<a.length && a[j]>=0) j++;
            if(i>neg || j>=a.length) break;
            a[i]+= max*(i+1);
            swap(a,i,j);
        }

        i = a.length-1;
        while(i>=neg){
            int div = a[i]/max;
            if(div == 0) i--;
            else{
                a[i]%=max;
                swap(a,i,neg+div-2);// minus 2 since a[i]+= max*(i+1);
            }
        }

    }
    private static void swap(int[] a, int i , int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

Onsite : Find the depth of a binary tree, Given an array of size N and a random number M 
(M &lt; N), generate an array of size M that contains random element from the main array 
(this is in CTCI)

What the difference between http post vs http get  
1. Determine whether invalid {}, [], () exists in a string 
2. Three buttons, one for coke, one for pepsi and one for randomly selection. 
All of them are connected to the wrong one (pepsi botton connects to coke etc. ). 
How do you figure out which button is which in three steps. 
public class Solution {
    public boolean isValid(String s) {
        //left -> push
        //right -> is Empty? pop() -- 2 places to check whether the stack is empty!!!  
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
 //we need to check the stack empty
        return x.isEmpty();         
    }
}    

public class Solution {
    public int singleNumber(int[] nums) {
        //由于数字在计算机是以二进制存储的，每位上都是0或1，如果我们把两个相同的数字异或，0与0异或是0,
        //1与1异或也是0，那么我们会得到0。根据这个特点，我们把数组中所有的数字都异或起来，              
        //则每对相同的数字都会得0，然后最后剩下来的数字就是那个只有1次的数字。
        int res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
        	res = res^nums[i];
        }
        return res;
    }
}