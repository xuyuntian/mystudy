# Minimum Absolute Difference in BST

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

**Example:**
```
Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
```
**Note:** There are at least two nodes in this BST.

## 思路
1. 中序遍历二叉搜索树
2. 计算当前节点与上个节点的差
3. 纪录最小的值。

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Integer pre = null;
    private int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        // inOrderWithRecursive(root);
        inOrderWithStack(root);
        return min;
    }
    public void inOrderWithRecursive(TreeNode root){
        if(root!=null){
            inOrderWithRecursive(root.left);
            if(pre!=null){
                min = Math.min(Math.abs(root.val-pre),min);
            }
            pre = root.val;
            inOrderWithRecursive(root.right);
        }
    }
    public void inOrderWithStack(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !s.isEmpty()){
            //左子树不为空，入栈。
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }            
            if(!s.isEmpty()){
                cur = s.pop();
                if(pre!=null && cur!=null){
                    min = Math.min(Math.abs(cur.val-pre),min);
                }
                if(cur != null){
                     pre = cur.val;
                }
                cur = cur.right;
            }
        }
    }

}
```
有2种方式进行中序遍历，但是，非递归写法的性能稍微比递归写法的低。