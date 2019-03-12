# 2 Keys Keyboard
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
1. **Copy All**: You can copy all the characters present on the notepad (partial copy is not allowed).
2. **Paste**: You can paste the characters which are copied last time.

**Example 1**
```
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
```
**Note**:The n will be in the range [1, 1000].
## 我的思路
**逆推:**<br>
1. 最后一步的前一步一定是粘贴。
2. 判断前一步是复制还是粘贴
3. 继续推导，直到到达1个字符为止

**限定条件**<br>
找出当前步骤字符数量的**最大因数**,它本身除外。<br>
由于复制粘贴操作不能局部复制，所以,**前一步的因数<=后面的因数**<br>

### 代码
```
class Solution {
    public int minSteps(int n) {
        int current = n;//纪录当前的状态
        int pre = 0;//纪录之前的状态
        int limit = getMaxFactorLimit(n,-1);//纪录粘贴的数量。后面的数量>=前面的
        int step = 0;
        //直到回到初始状态
        if(n==1){
            return 0;
        }
        while(current!=1){
            limit = getMaxFactorLimit(current,limit);
            pre = current - limit;
            //如果粘贴的数量刚好是当前的字符，那么，有复制操作
            if(pre == limit){
                step++;
            }
            step++;
            //将之前的状态变为当前
            current = pre;
        }
        return step;
    }
    //获取这个数的最大因子，但是要小于limit,limit为-1时没有这个限制
    private int getMaxFactorLimit(int n,int limit){
        int sqrtN = (int)Math.sqrt(n);
        int factor = 1;
        if(limit==-1){
            for(int i = 2;i<=sqrtN;i++){
                if(n%i==0){
                    int max = n/i>i ? n/i : i;
                    if(max>factor)
                    {
                        factor = max;
                    }
                }
            }  
        }else{
            for(int i = 2;i<=sqrtN;i++){
                if(n%i==0){
                    int max = n/i>i ? n/i : i;
                    if(max>factor&&max<=limit)
                    {
                        factor = max;
                    }
                }
            } 
        }        
        return factor;
    }
}
```
### 我的思考
犯错: 遗漏了条件**前一步的因数<=后面的因数**。花了十分钟的时间判断自己的逻辑没有问题，之后进行调试，快速定位了错误。<br>
发现: 该代码可以改进。当n为质数时，可以直接推导它需要进行n次操作得到。
#### 改进后的代码
```
class Solution {
    public int minSteps(int n) {
        int current = n;//纪录当前的状态
        int pre = 0;//纪录之前的状态
        int limit = getMaxFactorLimit(n,-1);//纪录粘贴的数量。后面的数量>=前面的
        int step = 0;
        //直到回到初始状态
        if(n==1){
            return 0;
        }
        //----改进的代码块----
        if(limit==1)
        {
            return n;
        }
        while(current!=1){
            limit = getMaxFactorLimit(current,limit);
            pre = current - limit;
            step++;
            //---改进的代码块-------
            if(limit ==1)
            {
                step += pre;
                break;
            }
            //如果粘贴的数量刚好是当前的字符，那么，有复制操作
            if(pre == limit){
                step++;
            }
            //将之前的状态变为当前
            current = pre;
        }
        return step;
    }
    //获取这个数的最大因子，但是要小于limit,limit为-1时没有这个限制
    private int getMaxFactorLimit(int n,int limit){
        int sqrtN = (int)Math.sqrt(n);
        int factor = 1;
        if(limit==-1){
            for(int i = 2;i<=sqrtN;i++){
                if(n%i==0){
                    int max = n/i>i ? n/i : i;
                    if(max>factor)
                    {
                        factor = max;
                    }
                }
            }  
        }else{
            for(int i = 2;i<=sqrtN;i++){
                if(n%i==0){
                    int max = n/i>i ? n/i : i;
                    if(max>factor&&max<=limit)
                    {
                        factor = max;
                    }
                }
            } 
        }        
        return factor;
    }
}
```
#### 改进时犯的错误
```
新增的代码块，位置应该在stpe++,之后。因为上一步粘贴后才会到达当前状态。
```
## 思考
这个问题的**灵感**来自于最大公约数。也就是我之前做的卡片分组的题目。算法题做得多了，思考的多了，有时候你的思想都跟不上你的大脑了。你的大脑已经给出了你答案，而你自己却没有反应过来(**证明这是对的**)。我称它为**灵感**。
我看过一本书，忘了叫什么名字了。说的是专家能通过预感来快速判断这个事情对不对.....