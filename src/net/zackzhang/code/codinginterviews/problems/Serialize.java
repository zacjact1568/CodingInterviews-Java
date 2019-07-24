package net.zackzhang.code.codinginterviews.problems;

/** 序列化二叉树 */
public class Serialize {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ********* 序列化 *********

    private StringBuilder mSb;

    private String serialize(TreeNode root) {
        mSb = new StringBuilder();
        traverse(root);
        return mSb.toString();
    }

    // 先序遍历
    private void traverse(TreeNode root) {
        if (root == null) {
            // 空节点使用 # 占位
            // 使用空格分隔
            mSb.append('#').append(' ');
            return;
        }
        mSb.append(root.val).append(' ');
        traverse(root.left);
        traverse(root.right);
    }

    // ********* 反序列化 *********

    private int mOffset;

    private TreeNode deserialize(String str) {
        mOffset = 0;
        return traverse(str);
    }

    // 先序遍历
    private TreeNode traverse(String str) {
        char ch = str.charAt(mOffset);
        if (ch == '#') {
            // 跳过 # 与空格
            mOffset += 2;
            return null;
        }
        // 扫描连续的数字字符，转换为数字
        int num = 0;
        while (ch != ' ') {
            // 累计为数字
            num = num * 10 + ch - '0';
            // 取下一个字符
            ch = str.charAt(++mOffset);
        }
        // 此时 offset 指向空格，跳过
        mOffset++;
        // 新建节点
        TreeNode node = new TreeNode(num);
        // 遍历重建该节点的左右子树
        node.left = traverse(str);
        node.right = traverse(str);
        return node;
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
        Serialize ser = new Serialize();
        String str = ser.serialize(a);
        System.out.println(str);
        System.out.println(ser.deserialize(str));
    }
}
