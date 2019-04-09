#  K-diff Pairs in an Array

Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

**Example 1:**
```
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
```

**Example 2:**
```
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
```

**Example 3:**
```
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
```

Note:
```
1. The pairs (i, j) and (j, i) count as the same pair.
2. The length of the array won't exceed 10,000.
3. All the integers in the given input belong to the range: [-1e7, 1e7].
```

## 思路
**暴力破解**

1. 排序
2. 对比
```
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        if(k<0 || nums==null || nums.length ==0){
            return 0;
        }
        Arrays.sort(nums);
        int i = nums[0] - 1;//pair(i,j)
        for(int index = 0;index < nums.length;index++){
            if(i == nums[index]){
                continue;
            }
            i = nums[index];
            for(int next = index+1;next<nums.length;next++){
                if(nums[next]==i+k){
                    res++;
                    break;
                }else if(nums[next] >i +k){
                    break;
                }
            }
        }
        return res;
    }
}
```
**使用map**

分3种情况<br>
1. k<0
2. k == 0
3. k > 0

如果k == 0 统计数量大于1 的元素<br>
如果k > 0 ,从单方向计算 e + k 存不存在
```
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        if(k<0 || nums==null || nums.length ==0){
            return 0;
        }
        //防止重复
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for(int e : nums){
            Integer val = map.get(e) == null? 0 : map.get(e);
            map.put(e,val+1);
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        for(Map.Entry<Integer,Integer> e : set){
            if(k == 0){
                if(e.getValue()>1){
                    res++;
                }
            }else{
                if(map.get(e.getKey()+k)!=null){
                    res++;
                }
            }
        }
        return res;
    }
}
```