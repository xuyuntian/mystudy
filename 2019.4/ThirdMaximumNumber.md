# Third Maximum Number
Given a **non-empty** array of integers, return the **third** maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

**Example 1:**
```
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
```

**Example 2:**
```
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
```

**Example 3:**
```
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
```
## 思路
**数组**

1. 先让数组保存3个不重复的元素
2. 将其他元素插入数组
3. 返回特定的元素

```
class Solution {
    public int thirdMax(int[] nums) {
        int[] top3 = new int[3];
        int count = 0;
        int begain = 0;
        for(int i = 0;i < nums.length;i++){
            begain = i;
            if(count < 3){
                boolean isRepeat = false;
                for(int j = 0;j < count ;j++){
                    if(top3[j] == nums[i]){
                        isRepeat = true;
                        break;
                    }
                }
                if(!isRepeat){
                    top3[count++] = nums[i];
                }
            }else{
                break;
            }
        }
        if(count == 1){
            return top3[0];
        }
        if(count == 2){
            return top3[0]>top3[1]?top3[0] :top3[1];
        }
        Arrays.sort(top3);
        for(int i = begain;i < nums.length; i++){
            int index = -1;
            for(int j = 0;j<3;j++){
                if(nums[i] >= top3[j]){
                    index = j;
                }
            }
            if(index!=-1){
                if(nums[i]!=top3[index]){
                    insertAfter(top3,index,nums[i]);
                }
            }
        }
            return top3[0];
    }
    private void insertAfter(int[] a,int pos,int num){
        for(int i = 0;i < pos;i++){
            a[i] = a[i+1];
        }
        a[pos] = num;
    }
    
}
```
**优先队列**
1. 用优先队列维护
2. 如果队列元素低于3，返回最大元素，反之，返回队列的头部元素。
```
PriorityQueue<Integer> p = new PriorityQueue<>(3);
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            if(count<3){
                if(!p.contains(nums[i])){
                    p.add(nums[i]);
                    count++;
                }
            }else{
                if(nums[i]>p.peek()){
                    if(!p.contains(nums[i])){
                        p.poll();
                        p.add(nums[i]);
                    }
                }
            }
        }
        if(count<3){
            Integer[] res = new Integer[3];
            p.toArray(res);
            return res[count-1];
        }
        return p.peek();
    }
```