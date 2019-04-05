# Water and Jug Problem
You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within **one or both buckets** by the end.

Operations allowed:

**Example 1:**
```
Input: x = 3, y = 5, z = 4
Output: True
```
**Example 2:**
```
Input: x = 2, y = 6, z = 5
Output: False
```

## 思路
这题目纯粹数学题。
其中有个命题大概如下:
若z%gcd(x,y) = 0,则ax + by = z;a,b为整数，一定成立。

暂且留到最后思考吧。
```
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        //这题目没2把刷子搞不定了，完全是数学题。
        //参考大佬思路重写
        //代码执行顺序大致如下
        //1. x+y < z一定不成立。
        //2. 如果说 x == z,y ==z, x+y ==z那么一定成立。
        //3. 如果z == 0,那么一定成立。
        //4. gcd(x,y) == 0;不成立
        //5. z%gcd(x,y) ==0 成立
        if(x + y <  z){ return false;};
        if((x!=0)&&(z%x ==0) || (y!=0)&&(z%y == 0)|| z == 0){
            return true;
        }
        int sub = gcd(x,y);
        if(sub == 0){
            return false;
        }
        return z%sub == 0;
        
    }
    private int gcd(int a,int b){
        if(a == 0){
            return b;
        }
        if(b ==0){
            return a;
        }
        return gcd(b%a,a);
    }
}
```