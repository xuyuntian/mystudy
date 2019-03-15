# Decode String
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

**Examples:**
```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```
## 我的代码
```
class Solution {
    private int index;
    public String decodeString(String s) {
        //递归。
        index = 0;
       return decode(s);
    }
    //用递归解应该可以搞定
    public String decode(String s){
        int numLeftIndex = -1;
        int currentNum = 0;
        StringBuilder sb = new StringBuilder();
        for(;index<s.length();index++){
            char c = s.charAt(index);
            if(c=='['){
                currentNum = new Integer(s.substring(numLeftIndex,index));
                numLeftIndex = -1;
                ++index;
                String sp = repeat(decode(s),currentNum);
                sb.append(sp);
            }else if(c==']'){
                return sb.toString();
            }else if(c>=48&&c<=57){
                if(numLeftIndex==-1){
                    numLeftIndex = index;
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public String repeat(String s,int num){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < num;i++){
            sb.append(s);
        }
        return sb.toString();
    }
  
}
```
## 我的思路
**递归**<br>
递归先解决最内侧的[]里面的内容。<br>
然后解决次要的[]。<br>
最终解决最外层<br>
终止条件:index==s.length;

## 我的感想
一开始我用栈，把我绕晕了。然后换成递归的算法。递归的算法之前是错误了，因为我在else if(c==']')的代码块中增加了index++;其实这个完全没必要。每当 if(c=='[')语句块执行完毕之后index会自增。而我自动将它忽略。而这个语句块中为什么要增加++index这一句。因为递归进去的下个索引不能是当前的 '[' 处。必须要在它的后一位。<br>
能写出这种递归的代码，我一开始认为我做不到。但我写着写着就做到了，可能是我大脑的潜意识已经知道该怎么做了。而我自己的主观意识跟不上节奏。<br>
低级错误:将sp变成了s。导致加错了字符串。