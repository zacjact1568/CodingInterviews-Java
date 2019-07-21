package net.zackzhang.code.codinginterviews.problems;

/** 树的子结构 */
public class HasSubtree {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private boolean hasSubtree(TreeNode root1, TreeNode root2) {
        // 空树不是子结构
        if (root1 == null || root2 == null) return false;
        return
                // 先比较根
                isSubtree(root1, root2) ||
                // 若从当前根开始，小树不是大树的子结构
                // 在大树的左子树中找子结构
                hasSubtree(root1.left, root2) ||
                // 若左子树中未找到，在右子树中找
                hasSubtree(root1.right, root2);
    }

    // 从根开始，小树是否是大树的子结构
    private boolean isSubtree(TreeNode root1, TreeNode root2) {
        // 先判断小树节点，若为空：
        // 1. 大树节点为空，符合条件
        // 2. 大树节点不为空，也符合条件，说明大树还有向下延伸的节点
        if (root2 == null) return true;
        // 若小树节点不为空，而大树节点为空
        // 则小树有增生的节点，不符合条件
        if (root1 == null) return false;
        // 若两节点都不为空，比较值
        if (root1.val != root2.val) return false;
        // 若两节点值相等，递归比较它们的左右子树
        return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
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
        TreeNode h = new TreeNode(10);
        TreeNode i = new TreeNode(9);
        TreeNode j = new TreeNode(11);
        h.left = i;
        h.right = j;
        System.out.println(new HasSubtree().hasSubtree(a, h));
    }
}
