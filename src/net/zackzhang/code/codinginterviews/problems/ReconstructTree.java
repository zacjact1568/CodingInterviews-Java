package net.zackzhang.code.codinginterviews.problems;

/** 重建二叉树 */
public class ReconstructTree {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private TreeNode reconstruct(int[] pre, int[] in) {
        return divide(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode divide(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        // 结束条件
        if (preStart > preEnd || inStart > inEnd) return null;
        // 先序遍历，首元素就是根
        int preRoot = pre[preStart];
        TreeNode root = new TreeNode(preRoot);
        // 寻找中序遍历中根的位置
        int inRoot = inStart;
        while (in[inRoot] != preRoot) {
            inRoot++;
        }
        // 左子树先序遍历的结束位置
        int leftPreEnd = preStart + inRoot - inStart;
        // 递归重建左右子树
        root.left = divide(pre, preStart + 1, leftPreEnd, in, inStart, inRoot - 1);
        root.right = divide(pre, leftPreEnd + 1, preEnd, in, inRoot + 1, inEnd);
        return root;
    }

    public static void test() {
        System.out.println(new ReconstructTree().reconstruct(
                new int[]{8, 6, 5, 7, 10, 9, 11},
                new int[]{5, 6, 7, 8, 9, 10, 11}
        ));
    }
}
