# Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

**Note:** For the purpose of this problem, we define empty string as valid palindrome.

**Example 1:**
```
Input: "A man, a plan, a canal: Panama"
Output: true
```

**Example 2:**
```
Input: "race a car"
Output: false
```
## 思路
*个人思路*
1. 用2个指针i,j分别从起点和终点相对运动
2. 绕过不符合条件的字符
3. 进行对比，如果出现数字字符不一致,字母不一致(不区分大小写),返回false,一直比较到i>=j为止。
4. 其他情况返回ture

```
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0,j = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        do{
           
            while(i<s.length() && !isAlpha(sb,i)){
                i++;
            }
            while(j> 0 &&!isAlpha(sb,j)){
                j--;
            }            
            if(i<j&&i<s.length()&&j>=0){
                int num1 = s.charAt(i);
                int num2 = s.charAt(j);
                int check = Math.abs(num1-num2);
                //判断是不是存在数字
                if( (num1<='9'&&num1>='0')||(num2<='9'&&num2>='0')){
                    if(check!=0){
                        return false;
                    }
                }
                //判断字母
                if(check!=32&&check!=0)
                    return false;
            }
            i++;
            j--;
        }while(i<j);
        return true;
    }
    private boolean isAlpha(StringBuilder s,int i){
         boolean express = (s.charAt(i)>='A' && s.charAt(i)<= 'Z') || (s.charAt(i) >= 'a' && s.charAt(i)<='z') || (s.charAt(i)>='0'&&s.charAt(i)<='9');
         return express;
    }
}
```

*速度比较快的的写法 1ms的例子*
```
class Solution {
    public boolean isPalindrome(String s) {
        char[] charMap = new char[128];
        
        for (int i = 0; i < 10; i++) {
            charMap[i+'0'] = (char)(1+i);
        }
        for (int i = 0; i < 26; i++) {
            charMap[i+'A'] = (char)(11+i);
            charMap[i+'a'] = charMap[i+'A'];
        }
    
        char[]pChars = s.toCharArray();
        int start = 0, end = pChars.length - 1;
        char cS, cE;
        
        while(start < end) {
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if (cS != 0 && cE != 0) {
                if (cS != cE) return false;
                start++;
                end--;
            } else {
                if (cS == 0) start++;
                if (cE == 0) end--;
            }
        }
        
        return true;
    }
```
大致思路如下:

1. 将字母表做映射，保证字符0 - 9 对应的key里面存的值不为0
2. 保证a-z,A-Z,对应key的值不为0，且charMap['A'] == charMap['a'],以此类推。
3. 用start和end2个指针前进，比较并跳过索引对应的值为0的字符串。如果对应的值不一致返回false;

如果连续使用该charMap的话最好将它变成常量。
```
class Solution {
    private static final char[] charMap = new char[128];
    static{
         for (int i = 0; i < 10; i++) {
            charMap[i+'0'] = (char)(1+i);
        }
        for (int i = 0; i < 26; i++) {
            charMap[i+'A'] = (char)(11+i);
            charMap[i+'a'] = charMap[i+'A'];
        }
    }
    public boolean isPalindrome(String s) {
        char[]pChars = s.toCharArray();
        int start = 0, end = pChars.length - 1;
        char cS, cE;
        
        while(start < end) {
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if (cS != 0 && cE != 0) {
                if (cS != cE) return false;
                start++;
                end--;
            } else {
                if (cS == 0) start++;
                if (cE == 0) end--;
            }
        }
        
        return true;
    }
}
```