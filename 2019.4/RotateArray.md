# Rotate Array
Given an array, rotate the array to the right by k steps, where k is non-negative.

**Example 1:**
```
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```
**Example 2:**
```
Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```
**Note:**
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.<br>
Could you do it in-place with O(1) extra space?

## 思路
1. 直接复制。

速度快，但是需要额外空间。
```
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] temp = new int[nums.length];
        System.arraycopy(nums,0,temp,k,nums.length - k);
        System.arraycopy(nums,nums.length -k,temp,0,k);
        System.arraycopy(temp,0,nums,0,nums.length);
    }
}
```
2. 连续交换。
```
class Solution {
    public void rotate(int[] nums, int k) {
       //自己设计的思路无法覆盖全部
       //空间复杂度0(1) 
       //该思路参考官方的Cyclic Replacements
       if(nums == null || nums.length <=1){
           return;
       } 
       k = k%nums.length; 
       if(k == 0){
           return ;
       }
       int count = 0; 
       for(int i = 0;i< nums.length;i++){
           if(count >= nums.length){
               break;
           }
           int pre = nums[i];
           int next;
           int start = i;
           do{
               next = (start + k)%nums.length;
               int temp = nums[next];
               nums[next] = pre;
               pre = temp;
               start = next;
               count++;
           }while(start != i);
       } 
    }
}
```