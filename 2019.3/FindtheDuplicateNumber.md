# Find the Duplicate Number
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

**Example 1:**<br>
```
Input: [1,3,4,2,2]
Output: 2
```
**Example 2:**<br>
```
Input: [3,1,3,4,2]
Output: 3
```
**Note**
1. You must not modify the array (assume the array is read only).
2. You must use only constant, O(1) extra space.
3. Your runtime complexity should be less than O(n2).
4. There is only one duplicate number in the array, but it could be repeated more than once.

## 思路以及代码
该算法的思路来源于用数组表示链表，以及链表成环。
```
class Solution {
    public int findDuplicate(int[] nums) {
        //不能原地排序求解
        //不能使用hashMap，hashSet或者外部排序。
        //不能暴力求解。
        //将数组变形成链表。数组nums[0]为第一个元素。
        //当前元素的next为它存储值的索引
        //由于只存在一个重复的数字，所以采取快慢指针法。可以快速定位
        //重复的数字。(数字重复链表会成环)
        int quick = nums[0],slow = nums[0];
        do{
            quick = nums[nums[quick]];
            slow = nums[slow];
        }while(quick!=slow);
        //新的指针slow2从头指针出发，slow继续前进。当
        //slow2 == slow时，返回slow.这结论可以证明得到。
        //设不成环的长度为l.成环的长度为r.
        //快慢指针相遇时,慢指针走了x步。停在环的A位置。
        //x = l+A , 2x = kr+l+A,x = kr
        //l+A = kr。再走l步一定会在环的起点处。
        //由于不知道l的数组，只能用另外一个指针去探测该距离。
        int slow2 = nums[0];
        while(slow2 != slow){
            slow2 = nums[slow2];
            slow = nums[slow];
        }
        return slow;
    }
}
```
