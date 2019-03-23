# Daily Temperatures
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

**Note:** The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

## 我的思路
### 暴力破解
1. 获取当天的温度，从下一天开始寻找到第一个温度比当天温度高的时间。
2. 继续计算第二天，第三天......

```
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for(int i = 0;i < T.length;i++){
            for(int j = i+1;j <= T.length;j++){
                if( j==T.length){
                    res[i] = 0;
                    break;
                }else if(T[j]>T[i]){
                    res[i] = j-i;
                    break;
                }                    
            }
        }
        return res;
    }
}
```
## 官方解
官方使用了栈这种数据结构来逆推。而暴力破解是正推，多了很多多余的步骤。<br>
比如说对于温度 30,31,25,22,45.<br>
使用栈 :<br>
i = 0, stack:[]<br>
i = 1, stack:[0]; T[1]>T[0] ,pop(0) ,res[0] = 1 - 0;
i = 2, stack:[1];T[2]<T[i];<br>
i = 3, stack:[1,2];T[3]<T[2];<br>
i = 4, stack:[1,2,3],<br> 
T[4] > T[3], pop(3),res[3] = 4 - 3;<br>
T[4] > T[2], pop(2),res[2] = 4 - 2 ;<br>
T[4] > T[1], pop(1),res[1] = 4 - 1; <br>
```
class Solution {
    public int[] dailyTemperatures(int[] T) {
        //根据官方的思路重写
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < T.length;i++){
            while(!stack.isEmpty() && T[stack.peek()]<T[i]){
                int indexOfRes = stack.pop();
                res[indexOfRes] = i - indexOfRes;
            }
            stack.push(i);
        }
        return res;
    }
}
```