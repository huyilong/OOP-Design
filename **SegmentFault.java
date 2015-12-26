Remove Duplicate Letters

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
分析

这道题可以采用Greedy的思想，因为最后想要的结果是最小值，
所以我们在满足要求的情况下把不断append最小的字符, 最后便可得到最小的字符串.

关键点就是要满足什么要求以及怎么样去满足。
首先要求就是得保证在原来的字符串中存在当前字符append的顺序，
即要保证每次append的字符的次数大于0。

我们可以用一个数组记录每个字符出现的次数，用一个指针从左到右扫，
过程中减小对应字符次数，找当前最小字符, 
    找的过程中终止条件是发现某个字符次数等于0，因为继续扫的话最终结果很有可能缺那个字符.

复杂度

time: O(kn), space: O(k), k表示原字符串中unique字符的个数

代码

public class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null ||s.length() == 0)
            return s;
            
        // 记录每个字符出现的次数    
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        
        // 找出当前最小字符
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos))
                pos = i;
            
            // 避免无字符可用
            if (--cnt[s.charAt(i) - 'a'] == 0)
                break;
        }
        
        // 除去字符串中已经append的字符的所有重复值
        return s.charAt(pos) + 
                    removeDuplicateLetters(s.substring(pos + 1).
                                    replaceAll("" + s.charAt(pos), ""));
    }
}