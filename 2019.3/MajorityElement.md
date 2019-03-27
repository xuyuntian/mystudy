# Majority Element
Given an array of size n, find the majority element. The majority element is the element that appears **more than** ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

**Example1**
```
Input: [3,2,3]
Output: 3
```
**Example2**
```
Input: [2,2,1,1,1,2,2]
Output: 2
```

## 我的思路
1. 遍历数组，计数,并比较。用key保存元素的值,maxCount保存最大数量
2. 返回maxCount
```
class Solution {
    public int majorityElement(int[] nums) {
        int maxCount=0,key = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int e :nums){
            if(!map.containsKey(e)){
                map.put(e,1);
                if(maxCount == 0){
                    key = e;
                    maxCount = 1;
                }
            }else{
                int current = map.get(e)+1;
                if(current>maxCount){
                    key = e;
                    maxCount = current;
                }
                map.put(e,current);
            }
        }
        return key;
    }
}
```
**遍历2次**
1. 遍历数组
2. 遍历hashMap
3. 返回最大值。
```
class Solution {
    public int majorityElement(int[] nums) {
        int maxCount=0,key = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int e :nums){
            if(!map.containsKey(e)){
                map.put(e,1);
            }else{
                map.put(e,map.get(e)+1);
            }
        }
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,Integer> e= (Map.Entry)it.next();
            if(maxCount < e.getValue()){
                key = e.getKey();
                maxCount = e.getValue();
            }
        }
        return key;
    }
}
```
## 评论区最快的代码
思路: 摩尔计数法,由于majority num>n/2<br>
1. 给第一个数计数
2. 从数组的第二个元素开始，与数组中的数比较如果与当前元素相同，则当前元素数目+1，若与当前元素不同---->1.若数量为0，数量加1，将当前元素换为数组元素。2.数量减去1.
3. 返回当前元素。
```
class Solution {
    public int majorityElement(int[] nums) {
        int majElement = nums[0];
        int count = 1;
        
        for(int i=1;i<nums.length;i++){
            if(majElement==nums[i]){
                count++;
            }else{
                if(count!=0){
                    count--;
                }else{
                    majElement = nums[i];
                    count++;
                }   
            }           
        }
        
        return majElement;       
    }
}
```
