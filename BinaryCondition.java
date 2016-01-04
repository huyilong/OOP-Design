这个我有一个很好的方法，是这样的，我认为所有情况下都可以用 left = mid + 1 和 right = mid - 1

并且这样子code是最高效的，而且看上去十分简洁，

一般binary search 就处理两种情况：
第一种情况：假设数组是按从小到大的顺序排列，从数组v中找一个元素，exactly 和 target相等，
那么code是这样(返回 index)

int index(vector<int> v, int target) {
    int left = 0;
    int right = int(v.size()) - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (v[mid] > target) {
            right = mid - 1;
        } else if (v[mid] < target) {
            left = mid + 1;
        } else {
            return mid;
        }
    }
    return -1;
}


第二种情况：我们有个性质叫 f, 是用一个函数来表示， f(x) 为真，表示x具有此性质， f(x)为假，表示x不具有此性质，举个例子：f(x) := x > 3 就表示 “x是否>3
这个性质，然后呢？我们需要假设v具有某种二分性质，其中所有满足 f(x)为假的x都在左半边， f(x)为真的都在右半边，
现在要求找出 “第一个 f(x)为真的 那个x” (leetcode: first bad version)，那么code是这样的：

int index(vector<int> v, int target) {
    int left = 0;
    int right = int(v.size()) - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (f(v[mid])) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

注意这个code处理了所有情况，其中left就是所要求的index，那如果数组中所有元素都满足f(x)为假会怎么样呢？
这时候 left最终输出的值就是 数组的size，也就是 v.end() 对应的position，可以理解为 “数组中第一个满足f(x)性质的元素的index是 v.end()”，其实就是说所有v[i] 都不满足性质，是不是非常和谐？哈哈

如果在情况2里面 不使用 +-1, 而用一些类似 right = mid; 这样的东西，那最后还要根据mid的值来判断到底是怎样的情况，code就不elegent了，
另外对于 “找出最后一个不满足 f(x) 性质” 的code，完全可以类似以上情况2去写， 最终结果返回如果是 -1 的话，说明所有v[i]都满足性质。
