package net.zackzhang.code.codinginterviews.problems;

import java.util.Stack;

/** 二叉搜索树的第k个结点 */
public class KthNode {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int mRank = 0;
    private int mK;
    private TreeNode mResult = null;

    // 递归（自写）
    private TreeNode find(TreeNode pRoot, int k) {
        mK = k;
        traverse(pRoot);
        return mResult;
    }

    private void traverse(TreeNode pRoot) {
        if (pRoot == null) return;
        traverse(pRoot.left);
        if (mResult != null) return;
        if (++mRank == mK) {
            mResult = pRoot;
            return;
        }
        traverse(pRoot.right);
    }

    // 递归
    private TreeNode find2(TreeNode pRoot, int k) {
        if (pRoot == null) return null;
        // 如果在左子树找到了符合条件的节点，left 就是这个节点
        // 否则 left 为空
        TreeNode left = find2(pRoot.left, k);
        if (left != null) return left;
        // 中序遍历到该节点，mRank 自增
        // 即 mRank 就是 pRoot 在 BST 中的的大小顺序
        if (++mRank == k) return pRoot;
        // 右子树同理
        return find2(pRoot.right, k);
    }

    // 非递归
    private TreeNode find3(TreeNode pRoot, int k) {
        if (pRoot == null) return null;
        Stack<TreeNode> s = new Stack<>();
        int rank = 0;
        while (pRoot != null || s.size() > 0) {
            while (pRoot != null) {
                s.push(pRoot);
                pRoot = pRoot.left;
            }
            pRoot = s.pop();
            if (++rank == k) return pRoot;
            pRoot = pRoot.right;
        }
        return null;
    }

    public static void test() {
        // 构造二叉搜索树
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(7);
        TreeNode d = new TreeNode(1);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(8);
        TreeNode h = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        System.out.println(new KthNode().find3(a, 4).val);
    }
}
