# Jump Game II
Given an array of **non-negative integers**, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps

**Example:**
```
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
**Note:**

You can assume that you can always reach the last index.
## 我的思路
从尾部往前递推,找到所有能到达该点的index。
从已有的index继续往前推导，知道找到index==0;
## 代码
```
class Solution {
    public int jump(int[] nums) {
        if(nums.length==1||nums==null){
            return 0;
        }
        int step = 0;
        HashSet<Integer> nextIndexs;
        HashSet<Integer> currentIndexs = new HashSet();
        currentIndexs.add(nums.length-1);
        boolean test =true;
        while(test){
            nextIndexs = new HashSet();
            step++;
            Iterator<Integer> it = currentIndexs.iterator();
            while(it.hasNext()){
                if(preStep(nextIndexs,nums,it.next())){
                    test = false;
                    break;
                }        
            }
            currentIndexs = nextIndexs;
        }
        return step;
    }
    //add all result to set.
    private boolean preStep(HashSet<Integer> set,int[] nums,Integer index){
        for(int i = 0;i<index;i++){
            if(i+nums[i]>=index){
                set.add(i);
                if(i==0) return true;
            }
        }
        return false;
    }
}
```
Ps:即使我知道思路我也花了1个多小时。循环多了容易把人绕晕，代码逻辑都理不清楚了。。。直到我用一个方法封装了另外一个循环<br>
通过所有测试用例
<table>
<th>运行时间</th>
<th>内存</th>
<tr>
    <td> 237ms</td>
    <td>40.1MB</td>
</tr>
</table>

## 改进后的代码
### 如何改进？参考玩家的思想广度优先遍历 (所有代码我手动写)

从前往后跳。保存能跳的索引的min,和max。没必要保存所有的元素。如果有元素能到达max。一定能到达比它小的任何一处。除非当前索引存的数为0。<br>因为0完全可以忽略。因为它不会对后面的结果产生干扰。索引index处的值为0那么它便无法从此处起跳。(我的大脑一开始是这种思路，但是看到了**non-negative integers**这个条件，导致我无法判断这么搞对不对。所以没仔细想了，就换了另外一种方法,现在回想起来真的尴尬。。。)。
下一次跳跃从[min,max]处开始,然后得到新的min和max。直到max>=nums.length-1;<br>新的min>上一个结果的max。保证不会重复。
```
class Solution {
    public int jump(int[] nums) {
        if(nums==null||nums.length==1)
            return 0;
        int step = 0;
        int min = 0,max = 0;
        int nextMin =0,nextMax =0;
        boolean arrive = false;
        while(!arrive){
            step++;
            //保证不会重复
            nextMin = max+1;
            for(int i=min;i<=max;i++){
                if(nums[i]+i>=nums.length-1)
                {
                    arrive = true;
                    break;
                }else{
                    nextMax = Math.max(nextMax,nums[i]+i);
                }
            }
            min = nextMin;
            max = nextMax;
        }
        return step;
    }
}
```
我居然只用不到20分钟便改写好了。。。。,思路对了写代码果然快(我写完思路就直接修改了，然后运行)。<br>

通过所有测试用例
<table>
<th>运行时间</th>
<th>内存</th>
<tr>
    <td> 4ms</td>
    <td>40MB</td>
</tr>
</table>
注意的点:
我写的2种代码我都忽略了它。特殊情况,数组的元素为1的时候。step为0.

### 写给自己的话
算法的逻辑最为重要。如果一开始设计好逻辑，写代码的速度完全取决于你的熟练度。前期是你的思路是对的。我现在基础不够强，很难证明我的思路是正确的。一旦思路是错误的。你之后的所有操作都是浪费时间。