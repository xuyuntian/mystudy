# Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
```
  1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
```

**Example 1:**
```
Input: 1
Output: "A"
```

**Example 2:**
```
Input: 28
Output: "AB"
```

**Example 3:**
```
Input: 701
Output: "ZY"
```

## 思路
1. 求mod26的余数remainder
2. 将n减去remainder并除以26;将remainder与字符映射，加入StringBuilder中但存在特殊情况余数为0那么减去的值应该为26；
3. 如果 n 不为 0 继续从1开始执行
4. 返回sb.reverse.toString();
```
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        char[] map = new char[26];
        map[0] = 'Z';
        for(int i = 1;i < map.length; i++){
            map[i] = (char)('A' + i -1);
        }
        do{
            int remainder = n % 26;
            sb.append(map[remainder]);
            //特殊情况
            if(remainder == 0){
                remainder = 26;
            }
            n = (n -remainder) / 26;            
        }while(n!=0);
        return sb.reverse().toString();
    }
}
```
**最佳写法**(作者未知)
```
    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            char ch = (char)('A' + n % 26);
            sb.append(ch);
            n /= 26;
        }
        return sb.reverse().toString();
    }

}
```
越是简单的题目越能体现出一个人的内功，自己还远远不够。