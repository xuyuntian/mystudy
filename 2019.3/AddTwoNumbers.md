# Add Two Numbers
You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.<br>

You may assume the two numbers do not contain any leading zero, except the number 0 itself.<br>

**Example**
```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

## 我的思路
1. 新建一个节点，保存结果的头节点(不存数据)
2. 遍历2个链表，直到到达2个节点的末尾,每次创建一个新的节点，并将它与上个新的节点连接。
3. 将2个节点的值相加，如果小于十则直接存入存入新的的节点，否则进行计算，并创建新的节点保存要进位的数
4. 返回头节点的下个节点

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        while(l1!=null || l2!=null){
            int val1 = l1 == null? 0 : l1.val;
            int val2 = l2 == null? 0 : l2.val;
            if(current.next!=null){
                current = current.next;
            }else{
                current.next = new ListNode(0);
                current = current.next;
            }
            current.val = current.val + val1 + val2;
            if(current.val>=10){
                current.next = new ListNode(current.val/10);
                current.val %= 10 ;
            }
            l1 = mov(l1);//出错点 忘记赋值
            l2 = mov(l2);//出错点
        }
        return head.next;
        
    }
    private ListNode mov(ListNode node){
        if(node == null){
            return null;
        }
        return node.next;
    }
}
```
**通过全部测试用例**
<table>
<th>
运行时间
</th>
<th>
内存</th>
<tr>
<td>21ms</td>
<td>48.5MB</td>
</tr>
</table>

### 思考
使用链表必须要区分引用和对象，否则极容易出错。