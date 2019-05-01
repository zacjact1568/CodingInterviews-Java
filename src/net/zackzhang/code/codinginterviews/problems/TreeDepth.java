package net.zackzhang.code.codinginterviews.problems;

public class TreeDepth {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /** 返回以 root 为根节点的子树的深度 */
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 0, rightDepth = 0;
        if (root.left != null) {
            leftDepth = depth(root.left);
        }
        if (root.right != null) {
            rightDepth = depth(root.right);
        }
        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        } else {
            return rightDepth + 1;
        }
    }

    public static void test() {
        // 构造二叉树
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(8);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = f;
        e.right = g;
        g.left = h;
        System.out.println(new TreeDepth().depth(a));
    }
}
