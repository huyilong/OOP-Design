Number of 1 Bits 位1的个数
check whether a bit is 1 we need to use (n & 1) to plus
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res += (n & 1);
            n = n >> 1;
        }
        return res;
    }
};

对于这道题，我们只需要把要翻转的数从右向左一位位的取出来，然后加到新生成的数的最低位即可，代码如下：
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t res = 0;

        for (int i = 0; i < 32; ++i) {
            if (n & 1 == 1) {
                res = (res << 1) + 1;
            } else {
                res = res << 1;
            }
            n = n >> 1;
        }

        return res;
    }
};


Given an array of integers, every element appears twice except for one.
 Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
