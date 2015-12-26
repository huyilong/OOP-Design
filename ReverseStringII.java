但是有以下限制：输入的字符串不包含前缀或者后缀空格，然后字符串只有以单个空格分隔。 
要求不开辟任何其他存储空间，在原先字符串上进行替换。

O(n) runtime, O(1) space – 在原先字符串上进行翻转: 
指定第i个单词是wi，它的翻转字符串是wi’，注意当你翻转一个单词两次，你就会获取原来的单词。即(wi’)’ = wi。 
此方案就是翻转两次，一遍是所有的字符串进行翻转，第二次就是按照每个单词进行翻转。

public void reverseWords(char[] s) {
    // 从头到末尾翻转
    reverse(s, 0, s.length);
    // 从头到尾遍历
    for (int i = 0, j = 0; j <= s.length; j++) {
        // 如果碰到空格（或者已经是末尾了），则翻转i到j位置的字符串，即一个单词。
        if (j == s.length || s[j] == ' ') {
            reverse(s, i, j);
            i = j + 1;
        }
    }
}

private void reverse(char[] s, int begin, int end) {
    // 从头到中间遍历
    for (int i = 0; i < (end - begin) / 2; i++) {
        // 左右两边字符（i和j位置）交换
        char temp = s[begin + i];
        s[begin + i] = s[end - i - 1];
        s[end - i - 1] = temp;
    }
}