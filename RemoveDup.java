思路：Remove Duplicates from Sorted Array I
双指针，一个指针 i 扫描数组，一个指针 end 记录没有重复数字的新数组的尾部。
(1) A[end]=A[i]，A[i]为重复数字，跳过。
(2) A[end]!=A[i]，将A[i]放到A[end+1]位置，end++


14
class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if(n<2) return n;
        int end=0;
        for(int i=1; i<n; i++) {
            if(A[i]!=A[end]) {
                end++;
                if(i!=end) A[end] = A[i];
            }
        }
        return end+1;
    }
};



思路：Remove Duplicates from Sorted Array II

和I一样的思路，区别仅仅在于当A[end-1] = A[end] = A[i]时，A[i]为重复元素需跳过。
而实际只需要比较A[end-1]和A[i]，因为当A[end-1] = A[i]时，根据sorted arry特性必然也有A[end]=A[end-1]。


class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if(n<3) return n;
        int end = 1;
        for(int i=2; i<n; i++) {
            if(A[i]!=A[end-1]) 
                A[++end] = A[i];
        }
        return end+1;
    }
};