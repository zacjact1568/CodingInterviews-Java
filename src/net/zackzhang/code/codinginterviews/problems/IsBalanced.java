package net.zackzhang.code.codinginterviews.problems;

public class IsBalanced {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ********* 解 1 *********

    /** int 的包装类 */
    private class IntWrapper {
        int val;
        IntWrapper(int val) {this.val = val;}
    }

    private boolean isBalanced(TreeNode root) {
        return isBalanced(root, new IntWrapper(0));
    }

    /** 返回以 root 为根节点的子树是否为平衡二叉树 */
    private boolean isBalanced(TreeNode root, IntWrapper depth) {
        if (root == null) {
            depth.val = 0;
            return true;
        }
        IntWrapper lDepth = new IntWrapper(0), rDepth = new IntWrapper(0);
        if (isBalanced(root.left, lDepth) && isBalanced(root.right, rDepth)) {
            // 若左右子树都是平衡二叉树
            // 它们的深度由 lDepth 和 rDepth 传出（Java 无法返回两个值，因此用指针参数的方式）
            if (Math.abs(lDepth.val - rDepth.val) <= 1) {
                // 若左右子树的深度差小于等于 1
                // NOTE：形参 depth 用 Integer 的话，向其赋值是自动装箱，相当于
                //       重新 new 一个 Integer 对象，让 depth 指向这个新对象，
                //       因此实参（递归调用的 lDepth 和 rDepth）的值并没有改变，
                //       也就是说无法将新值传递出这个函数，解决方法是自定义 IntWrapper，
                //       内含一个 int，修改 depth 中这个 int 的值即可
                depth.val = Math.max(lDepth.val, rDepth.val) + 1;
                return true;
            }
        }
        return false;
    }

    // ********* 解 2 *********

    private boolean isBalanced = true;

    private boolean isBalanced2(TreeNode root) {
        depth(root);
        return isBalanced;
    }

    /** 返回以 root 为根节点的子树的深度 */
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int lDepth = depth(root.left);
        int rDepth = depth(root.right);
        if (Math.abs(lDepth - rDepth) > 1) {
            // 若以 root 为根节点的子树不是平衡二叉树
            // NOTE：isBalanced 被改为 false 后，就再也无法改回去了，
            //       也就是说，只要有一个子树不平衡，最终结果就是不平衡
            isBalanced = false;
        }
        return Math.max(lDepth, rDepth) + 1;
    }

    // ********* 解 3 *********

    private boolean isBalanced3(TreeNode root) {
        return depth2(root) != -1;
    }

    /** 返回以 root 为根节点的子树的深度 */
    private int depth2(TreeNode root) {
        if (root == null) return 0;
        int lDepth = depth(root.left);
        // 用深度为 -1 来表示左子树不平衡，直接返回 -1（既然左子树都不平衡了，那加上 root 的子树也不平衡了）
        if (lDepth == -1) return -1;
        int rDepth = depth(root.right);
        if (rDepth == -1) return -1;
        // 若左右子树都平衡，但加上 root 就不平衡了，也返回 -1
        return Math.abs(lDepth - rDepth) > 1 ? -1 : Math.max(lDepth, rDepth) + 1;
    }

    public static void test() {
        // 构造平衡二叉树
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
        c.left = f;
        c.right = g;
        e.left = h;
        System.out.println(new IsBalanced().isBalanced3(a));
    }
}
