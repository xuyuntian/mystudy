# Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

**Example 1:**
```
Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
```

**Example 2:**
```
Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.
```

**Note:**

Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.

In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in **Example 2** above the input represents the signed integer -3 and the output represents the signed integer -1073741825.

## 思路
先左移，再右移进行逆转。

```
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        //32位。
        int res = 0;
        byte[] bits = new byte[32];
        for(int i = 0; i<32; i++){
            bits[i] = (byte)(n & 1);
            n = n>>>1;
        }
        //逆转
        for(int j = 0;j <bits.length; j++){
            res = res<<1;
            res = res^bits[j];
        }
        return res;
    }
}
```
这里面有个陷阱，逆转的时候不需要改变数组的顺序，因为一开始左移存入数据(bits的结果已经是逆转的了)，后面右移会直接逆转顺序。