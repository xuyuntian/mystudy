# Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

**Example:**
```
Given 1->2->3->4, you should return the list as 2->1->4->3.
```

## 思路
这个题目不是很难，但容易错。
1. 当元素个数<=1时返回head.
2. 元素个数>=2 时返回head.next;
3. 用3个指针记录状态。current，next，tail
4. 每次交换前先用tail连接第二个数，然后用current连接第二个数后面的数。最后将next连接current。

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
    public ListNode swapPairs(ListNode head) {
        ListNode res;//保存返回结果
        ListNode current,next,tail = null;
        if(head == null||head.next == null){
            return head;
        }
        res = head.next;
        current = head;
        while(current!= null && current.next != null){
            next = current.next;
            if(tail != null){
                tail.next = next;    
            }
            current.next = next.next;
            next.next = current;
            tail = current;
            current = current.next;
        }
        return res;
    }
}
```