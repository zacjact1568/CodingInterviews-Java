package net.zackzhang.code.codinginterviews.problems;

/** 二叉搜索树与双向链表 */
public class Convert {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 前一个遍历的节点
    private TreeNode pre = null;
    // 链表的头节点
    private TreeNode head = null;

    private TreeNode convert(TreeNode pRootOfTree) {
        convertSub(pRootOfTree);
        return head;
    }

    private void convertSub(TreeNode root) {
        // root 是当前遍历的节点
        if (root == null) return;
        convertSub(root.left);
        if (head == null) {
            // 初次进入该段代码，是最左边的叶子节点，也是链表的头节点
            head = root;
        } else {
            // 非链表头，调整指针
            pre.right = root;
            root.left = pre;
        }
        // 对于右子树，前一个遍历的节点是 root
        pre = root;
        convertSub(root.right);
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
        System.out.println(new Convert().convert(a).val);
    }
}
