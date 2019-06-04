package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PrintTree {

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
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.offer(pRoot);
        depthQueue.offer(1);
        int currentDepth = 0;
        ArrayList<Integer> layer = null;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            // depthQueue 中的元素不可能为空
            int depth = depthQueue.poll();
            if (currentDepth + 1 == depth) {
                currentDepth++;
                layer = new ArrayList<>();
                result.add(layer);
            }
            // 第一次肯定会执行上面的 if，也就是说 layer 不可能为空
            layer.add(node.val);
            if (node.left != null) {
                nodeQueue.offer(node.left);
                depthQueue.offer(depth + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                depthQueue.offer(depth + 1);
            }
        }
        return result;
    }

    private ArrayList<ArrayList<Integer>> print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) return result;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        // 当前遍历层的最右节点
        TreeNode last = pRoot;
        // 下一层的最右节点
        TreeNode nLast = null;
        nodeQueue.offer(pRoot);
        ArrayList<Integer> temp = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            temp.add(node.val);
            if (node.left != null) {
                nodeQueue.offer(node.left);
                nLast = node.left;
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                nLast = node.right;
            }
            // 若出队的节点是本层最右的节点
            if (node == last) {
                last = nLast;
                result.add(new ArrayList<>(temp));
                temp.clear();
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
        System.out.println(new PrintTree().print2(a));
    }
}
