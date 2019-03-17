# Letter Case Permutation
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create

```
Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
```
**Note:**
S will be a string with length between 1 and 12.
S will consist only of letters or digits.

## 我的思路
碰到字母，将字母进行转换(大小变小写，小写变大写)进行递归。<br>
这代码怎么写出来的。。。个人对回溯算法比较陌生，虽然写出来了。
但也是试出来的。后期会修改该文件补充。


## 代码
```
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        backTracking(list,S,0);
        return list;
    }
    private void backTracking(List<String> list,String current,int index){
            //如果不是字母找下一个
            while(index<current.length()){
                char ch = current.charAt(index);
                if(isAaZz(ch)){
                    String nextStr = changeChar(current,index);
                    backTracking(list,nextStr,index+1);
                    index++; 
                }else{
                    index++;
                }
                
            }           
           list.add(current);               
               
        
    }
    //将指定位置字符串大小写反转后返回新字符串
    private String changeChar(String str,int index){
        StringBuilder sb = new StringBuilder(str);
        char ch = str.charAt(index);
        if(ch>='a'&&ch<='z'){
            ch = (char)(ch -32);
        }else{
            ch = (char)(ch + 32);
        }
        sb.setCharAt(index,ch);
        return sb.toString();
    }
    //判断是不是字母
    private boolean isAaZz(char ch){
        return (ch>='A'&&ch<='Z') || (ch>='a'&&ch<='z');
    }
}
```
## 运行时间与空间
<table>
<th>运行时间</th>
<th>内存</th>
<tr>
<td>4 ms</td>
<td>38.4 MB</td>
<tr>
</table>

## 感想
对有些回溯的递归算法比较陌生，这些类型的题目熟练度过低，浪费大量时间也很难写出准确的答案。