# UTF-8 Validation
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:
```
Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
```
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

**Note:**
The input is an array of integers. Only the **least significant 8 bits** of each integer is used to store the data. This means each integer represents only 1 byte of data.
<br>
**Example 1:**
```
data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
```
**Example 2:**
```
data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
```
## 我的代码
```
class Solution {
    public boolean validUtf8(int[] data) {
        //输入的为数据流。。。
        int headIndex = 0;
        if(data==null)
            return false;
        while(headIndex<data.length){
            //需要验证的字符数
           int nextChar = testLinkCountOfOne(data[headIndex])-1;
            //nextChar的格式不对，直接返回false;
            if(nextChar==-1){
                return false;
            }    
            //未通过认证
            if(!testNextChar(data,headIndex+1,headIndex+nextChar)){
                return false;
            }
            headIndex = nextChar + headIndex +1;
        }
        return true;
    }
    private int testLinkCountOfOne(int head){
       head = head&0b1111_1111;
       int count = 0;
        //记录连续1的数量
       for(int i = 7;i>2;i--){
           if( ((head>>i)&0b1 ) ==1)
           {
               count++;
           }else{
               break;
           }
       }
       if(count==0){
           return 1;
       }else if(count==1||count>4){
           return 0;
       }else{
           return count;
       } 
    }
    //Test nextbyte in [begain,end] of data;
    private boolean testNextChar(int[] data,int begin,int end){
        if(end >= data.length){
            return false;
        }
        for(int i = begin;i<=end;i++){
            if((data[i]&0b1111_1111)>>6!=0b10){
                return false;
            }
        }
        return true;
    }
}
```
### 思路
从头开始依次进行判断，如果第一个byte为符合条件的head的话找它的下N个byte是否符合要求。直到到达字符流的末尾。返回true;
如果存在head不符合条件，或者nextchar不符合条件的话返回false
### 出现的问题
判断判断head的条件有问题，导致一直出错。
应该存在5中情况，0XXX_XXXX,11XX_XXXX,111X_XXXX,1111_0XXX,
其他情况。