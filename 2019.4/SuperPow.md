# Super Pow
Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

**Example 1:**
```
Input: a = 2, b = [3]
Output: 8
```

**Example 2:**
```
Input: a = 2, b = [1,0]
Output: 1024
```

## 思路
将 b拆分。如果b = [1,1,1,1];<br>
pow(a,b) = pow(a,10^3) * pow(a,10^10) *pow(a,10) *pow(a,1);<br>
所以如果要计算 n 次，n为数组的长度。
每次计算都会计算pow(a,num)%1337;

1. 第一次写的代码
```
class Solution {
    public int superPow(int a, int[] b) {
        //拆分。。
        int res = 1;
        if(a==0 || a ==1)
        {
            return a;
        }
        for(int i = 0;i < b.length; i++){
            int temp = a;
            for(int j = 0;j<i;j++){
                temp = powAndMod1337(temp,10);
            }
            res = res*powAndMod1337(temp,b[b.length-i-1])%1337;
        }
        return res;
    }
    public int powAndMod1337(int a,int b){
        int res = 1;    
        while(b>0){
            res = (res * a)%1337 ;//防止溢出
            b--;            
        }      
        return res;
    }
}
```
其实temp每次的迭代的结果都可以利用不必重复计算，改动如下
```
class Solution {
    public int superPow(int a, int[] b) {
        //拆分。。
        int res = 1;
        if(a==0 || a ==1)
        {
            return a;
        }
        int temp = a;
        for(int i = 0;i < b.length; i++){
            res = res*powAndMod1337(temp,b[b.length-i-1])%1337;
            temp = powAndMod1337(temp,10);
        }
        return res;
    }
    public int powAndMod1337(int a,int b){
        int res = 1;    
        while(b>0){
            res = (res * a)%1337 //防止溢出;
            b--;            
        }      
        return res;
    }
}
```