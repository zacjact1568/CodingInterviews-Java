package net.zackzhang.code.codinginterviews.problems;

public class Mirror {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public void mirror(TreeNode root) {
        if (root == null) return;
        mirror(root.left);
        mirror(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
    }

    public static void test() {
        // 构造二叉树
        TreeNode a = new TreeNode(8);
        TreeNode b = new TreeNode(6);
        TreeNode c = new TreeNode(10);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(7);
        TreeNode f = new TreeNode(9);
        TreeNode g = new TreeNode(11);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        new Mirror().mirror(a);
    }
}
