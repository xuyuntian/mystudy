# Linked List Cycle2
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

**Note:** Do not modify the linked list.

**Example 1:**
```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

**Example 2:**
```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```
**Example 3:**
```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```
**Follow up:**
Can you solve it without using extra space?

## 我的思路
与Linked List Cycle中的思路一致，只不过返回值变成了节点
```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> path = new HashSet<>();
        while(head!=null){
            if(!path.add(head))
                return head;
            head = head.next;
        }
        return null;
    }
}
```

## O(1)空间复杂度的思路
```
public class Solution {
            public ListNode detectCycle(ListNode head) {
                ListNode slow = head;
                ListNode fast = head;
        
                while (fast!=null && fast.next!=null){
                    fast = fast.next.next;
                    slow = slow.next;
                    
                    if (fast == slow){
                        ListNode slow2 = head; 
                        while (slow2 != slow){
                            slow = slow.next;
                            slow2 = slow2.next;
                        }
                        return slow;
                    }
                }
                return null;
            }
        }
```
思路如下:<br>
设环的长度为r,
从开始到环的第一个节点的长度为l
当快慢指针相遇时有如下条件。
设x为慢指针的步数。
x+kr = 2x;为整数。
x = kr 
当走的步数满足l+kr时，指针在第一个节点处。
使另外一个指针slow2在head处。
如果slow2==slow，则，slow2走了l步，slow共走了x+l = kr+l步。他们在成环的第一个节点处相遇。