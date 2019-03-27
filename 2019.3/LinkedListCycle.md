# Linked List Cycle
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer **pos** which represents the position (0-indexed) in the linked list where tail connects to. If **pos** is *-1*, then there is no cycle in the linked list.

**Example1**
```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```
**Example 2:**
```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```
**Example 3:**
```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```
**Follow up:**

Can you solve it using O(1) (i.e. constant) memory?

## 我的思路
1. 用一个HashSet保存走过的节点。
2. 遍历链表，若新的节点在set中返回true
3. 返回false.(节点不成环)
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
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> path = new HashSet<>();
        while(head!=null){
            if(!path.add(head))
                return true;
            head = head.next;
        }
        return false;
    }
}
```
**O(1)空间复杂度的思路**
1. 用2个指针p1,p2保存节点
2. 当p2不为空时，指针p1每次走一步，指针p2每次走2步。若指针2指针为空，break，返回false。若指针1==指针2，返回true。
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
    public boolean hasCycle(ListNode head) {
        ListNode p1=head,p2=head;
        while(p2!=null){
            p1 = p1.next;
            p2 = p2.next;
            if(p2==null)
            {
                break;
            } 
            p2 = p2.next;
            if(p1==p2){
                return true;
            }
        }
        return false;
    }
}
```