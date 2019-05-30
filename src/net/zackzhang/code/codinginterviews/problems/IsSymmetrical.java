package net.zackzhang.code.codinginterviews.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IsSymmetrical {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    // 使用递归
    // 参数是两个对称的节点
    private boolean isSymmetrical(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) return true;
        if (leftNode != null && rightNode != null) {
            // 如果两个对称节点的值相等，
            // 且左节点的左孩子的值等于右节点的右孩子的值，
            // 且左节点的右孩子的值等于右节点的左孩子的值，
            // 则对称
            return leftNode.val == rightNode.val &&
                    isSymmetrical(leftNode.left, rightNode.right) &&
                    isSymmetrical(leftNode.right, rightNode.left);
        }
        return false;
    }

    // 使用栈（DFS）
    private boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) return true;
        Stack<TreeNode> s = new Stack<>();
        // 成对入栈
        s.push(pRoot.left);
        s.push(pRoot.right);
        while (!s.isEmpty()) {
            // 成对出栈（每次取两个对称的节点来比较）
            TreeNode right = s.pop();
            TreeNode left = s.pop();
            // 已到达叶子节点
            if (left == null && right == null) continue;
            // 左右节点有一个为空而另一个不为空（上一行已经考虑了两个节点都为空的情况）
            if(left == null || right == null) return false;
            // 左右节点值不同
            if(left.val != right.val) return false;
            // 成对入栈
            s.push(left.left);
            s.push(right.right);
            s.push(left.right);
            s.push(right.left);
        }
        return true;
    }

    // 使用队列（BFS）
    private boolean isSymmetrical3(TreeNode pRoot) {
        if(pRoot == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(pRoot.left);
        q.offer(pRoot.right);
        while(!q.isEmpty()) {
            TreeNode right = q.poll();
            TreeNode left = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            q.offer(left.left);
            q.offer(right.right);
            q.offer(left.right);
            q.offer(right.left);
        }
        return true;
    }

    public static void test() {
        // 构造二叉树
        TreeNode a = new TreeNode(8);
        TreeNode b = new TreeNode(6);
        TreeNode c = new TreeNode(6);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(7);
        TreeNode f = new TreeNode(7);
        TreeNode g = new TreeNode(5);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        System.out.println(new IsSymmetrical().isSymmetrical(a));
    }
}
