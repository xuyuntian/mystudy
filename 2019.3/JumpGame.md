# Jump Game
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

**Example 1:**
```
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**
```
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
jump length is 0, which makes it impossible to reach the last index.
```

## 思路
1. 广度优先遍历
2. 从一个步开始推断。
3. 得到能走的范围min , max.
4. 继续从[min,max] --->[min,max]。直到 max< min,返回false.或者max>= nums.length-1 返回true
```
class Solution {
    public boolean canJump(int[] nums) {
        //1.从第一步开始推导第二步能够到达的最远地点，与最短地点。
        int min = 1,max = 0;
        max = nums[0]+max;
        if(max>=nums.length-1)
            return true;
        while(max>=min){
            int currentmax = max;
            for(int i = min;i<=max&&i<nums.length;i++){
                if(currentmax < nums[i]+i){
                    currentmax = nums[i]+i; 
                }
            }
            min = max + 1;
            max = currentmax;
            if(currentmax>=nums.length-1)
                return true;
        }
        return false;
    }
}
```

## 易错点
1. 如果只有一个元素存在，且元素数字为0，要提前判断。
2. 边界条件max > nums.length - 1 。