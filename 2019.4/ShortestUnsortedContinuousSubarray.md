# Shortest Unsorted Continuous Subarray

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

**Example 1:**
```
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
```

**Note:**
1. Then length of the input array is in range [1, 10,000].
2. The input array may contain duplicates, so ascending order here means <=.

## 思路
1. 从头开始，找到第一个开始递减的元素的索引go
2. 从尾部开始，找到第一个开始递增的元素的索引back
3. 找到[go,back]之间的最大值和最小值。
4. 从头开始扫描，go停在比min大的元素
5. 从尾部开始扫描,back停在比max小的元素处。
6. 返回back-go+1

```
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        //用2个指针一个从头，一个从尾部开始移动，
        if(nums==null || nums.length <= 1){
            return 0;
        }
        int go = nums.length -1,back = 0;
        for(int i = 1;i < nums.length;i++){
            if(nums[i-1] > nums[i]){
                go = i-1;
                break;
            }
        }
        for(int j = nums.length -1;j > 0;j--){
            if(nums[j-1] > nums[j]){
                back = j;
                break;
            }
        }
        if(go == nums.length - 1){
            return 0;
        }
        int[] maxAndMin = findMaxAndMin(nums,go,back);
        for(int i = 0;i < go;i++){
            if(nums[i] > maxAndMin[0]){
                go = i;
                break;
            }
        }
        for(int j = nums.length -1;j >= back;j--){
            if(nums[j] < maxAndMin[1]){
                back = j;
                break;
            }
            
        }
        return back - go + 1;
    }
    private int[] findMaxAndMin(int[] nums,int start,int end){
        int max = nums[start],min = nums[end];
        for(int i = start;i <= end;i++){
            if(nums[i] > max){
                max = nums[i];
            }
            if(nums[i] < min){
                min = nums[i];
            }
        }
        int[] res = new int[2];
        res[0] = min;
        res[1] = max;
        return res;
    }
}
```
题目不难，很多时候边界条件不对，直接翻车。
