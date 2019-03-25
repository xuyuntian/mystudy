# 3 Sum
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

**Note:**
The solution set must not contain duplicate triplets.

**Example:**
```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```
## 我的思路
1. 排序
2. 用hashMap保存值+索引(最大的索引)。
3. 用2个集合保存num1,num2为了确保前2个数不重复。
4. 遍历数组--->
5. --> 根据num1,num2推导出num3.
6. --> 从map中找num3,如果存在，判断它的index是否大于num2的 index,若是则将这3个数添加到结果中。

**代码**
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<Integer> num1s = new HashSet<>();
        HashSet<Integer> num2s = null;
        HashMap<Integer,Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length<3){
            return res;
        }
        // O(N*logN);
        Arrays.sort(nums);
        //O(N);相同会覆盖到最远的索引处
        for(int index = 2;index < nums.length;index++){
            map.put(nums[index],index);
        }
        //使用2个Set保证不重复。
        //O(N^2);
        for(int i = 0;i < nums.length - 2;i++){
            if(!num1s.contains(nums[i])){
                num2s = new HashSet<>();
                num1s.add(nums[i]);
                for(int j = i+1; j< nums.length -1;j++){
                    if(!num2s.contains(nums[j])){
                        num2s.add(nums[j]);
                        int k = -(nums[i]+nums[j]);
                        if(map.get(k) != null){
                            int indexOfK = map.get(k);
                            if(indexOfK>j){
                                List<Integer> temp = new ArrayList<>();
                                temp.add(nums[i]);
                                temp.add(nums[j]);
                                temp.add(k);
                                res.add(temp);   
                            }
                        }
                    }                    
                }                                        
            }
        }
        return res;
    }
   
}
```
提交后发现该代码的效率不高。
Runtime: 306ms<br>

## 改进
1. 将集合换成2个变量，保存之前的num1,num2.由于数组是排序过的可以进行第二步直接判断是否重复。
2. if(nums[currentIndex] == num1) continue;
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int num1;
        int num2;
        HashMap<Integer,Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length<3){
            return res;
        }
        // O(N*logN);
        Arrays.sort(nums);
        num2 = num1 = nums[0] -1;//避免重复
        //O(N);相同会覆盖到最远的索引处
        for(int index = 2;index < nums.length;index++){
            map.put(nums[index],index);
        }
        //使用2个Set保证不重复。
        //O(N^2);
        for(int i = 0;i < nums.length - 2;i++){
            if(num1!=nums[i]){
                num1 = nums[i];
                for(int j = i+1; j< nums.length -1;j++){
                    if(num2!=nums[j]){
                        num2 = nums[j];
                        int k = -(nums[i]+nums[j]);
                        if(map.get(k) != null){
                            int indexOfK = map.get(k);
                            if(indexOfK>j){
                                List<Integer> temp = new ArrayList<>();
                                temp.add(nums[i]);
                                temp.add(nums[j]);
                                temp.add(k);
                                res.add(temp);   
                            }
                        }
                    }                    
                }                                        
            }
        }
        return res;
    }
   
}
```

**RunTime:** 97ms.

## Runtime 18 ms的大佬写的答案
```
class Solution { 
	public List<List<Integer>> threeSum(int[] nums) { 
		if (nums.length < 3) return Collections.emptyList(); 
		List<List<Integer>> res = new ArrayList<>(); 
		int minValue = Integer.MAX_VALUE; 
		int maxValue = Integer.MIN_VALUE; 
		int negSize = 0; int posSize = 0; 
		int zeroSize = 0; 
		for (int v : nums) { 
			if (v < minValue) minValue = v; 
			if (v > maxValue) maxValue = v; 
			if (v > 0) posSize++; 
			else if (v < 0) negSize++; 
			else zeroSize++; 
			}
		if (zeroSize >= 3) 
			res.add(Arrays.asList(0, 0, 0)); 
		if (negSize == 0 || posSize == 0) return res; 
		if (minValue * 2 + maxValue > 0) maxValue = -minValue * 2; 
		else if (maxValue * 2 + minValue < 0) minValue = -maxValue * 2;
        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {
                if (map[v - minValue]++ == 0) {
                    if (v > 0)
                        poses[posSize++] = v;
                    else if (v < 0)
                        negs[negSize++] = v;
                }
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {
            int nv = negs[i];
            int minp = (-nv) >>> 1;
            while (basej < posSize && poses[basej] < minp)
                basej++;
            for (int j = basej; j < posSize; j++) {
                int pv = poses[j];
                int cv = 0 - nv - pv;
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1)
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1)
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        if (map[cv - minValue] > 0)
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv)
                    break;
            }
        }
        return res;   
	}
}
```
大概的思路如下
1. 遍历数组，计数(正数数量和负数数量) + 求出最大值和最小值。还有0的数量。若0的数量>=3向结果中添加[0,0,0]
2. 自定义map. map[i]  = count. key--> minValue + i;
3. 遍历数组，将正数和负数都放入分开的数组中。
4. ---->从负数的数组中取出一个值给nv,计算所需要最小的正数minp。
5. ---->找到第一个>=minp的正数的索引basej.
6. ---->从basej开始遍历正数数组。找出第二个数pv,并推导第三个数cv。
7. ---->---->判断cv的数是否符合条件。如果符合在map中查找。若cv与pv或者cv中的任何一个相同，则map的数量要>1,若都不相同数量>0;并将[nv,pv,cv]添加到结果中。
8. 返回结果