package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Stack;

/** 按之字形顺序打印二叉树 */
public class PrintTreeInZ {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) return result;
        // 奇数层节点
        Stack<TreeNode> odd = new Stack<>();
        // 偶数层节点
        Stack<TreeNode> even = new Stack<>();
        odd.push(pRoot);
        int depth = 1;
        while (!odd.empty() || !even.empty()) {
            ArrayList<Integer> layer = new ArrayList<>();
            if ((depth & 1) == 1) {
                // 奇数层
                while (!odd.empty()) {
                    TreeNode node = odd.pop();
                    if (node != null) {
                        layer.add(node.val);
                        // 将当前节点的子节点先左后右压入 even
                        // 这样下次循环（访问 even）时，弹出顺序和压入顺序相反，实现了反向打印
                        even.push(node.left);
                        even.push(node.right);
                    }
                }
                // 当 node 为 null 时，layer 为空
                if (!layer.isEmpty()) {
                    result.add(layer);
                    depth++;
                }
            } else {
                // 偶数层
                while (!even.empty()) {
                    TreeNode node = even.pop();
                    if (node != null) {
                        layer.add(node.val);
                        // 注意，先右后左
                        odd.push(node.right);
                        odd.push(node.left);
                    }
                }
                if (!layer.isEmpty()) {
                    result.add(layer);
                    depth++;
                }
            }
        }
        return result;
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
        System.out.println(new PrintTreeInZ().print(a));
    }
}
