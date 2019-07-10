package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Stack;

/** 二叉树中和为某一值的路径 */
public class FindPath {

    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private ArrayList<ArrayList<Integer>> mResult = new ArrayList<>();
    private ArrayList<Integer> mTemp = new ArrayList<>();

    // 递归
    private ArrayList<ArrayList<Integer>> find(TreeNode root, int target) {
        findSub(root, target);
        // 排序（数组长度大的在前）
        mResult.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));
        return mResult;
    }

    private ArrayList<ArrayList<Integer>> findSub(TreeNode root, int target) {
        if (root == null) return mResult;
        mTemp.add(root.val);
        int newTarget = target - root.val;
        if (root.left == null && root.right == null && newTarget == 0) {
            // root 是叶子节点且路径满足要求，将 mTemp 中所有元素加入新列表，再加到 mResult 中
            mResult.add(new ArrayList<>(mTemp));
        } else {
            // root 不是叶子节点，继续向下查找
            // 或是叶子节点但路径不满足要求，下面两个递归都不会做处理（直接返回 mResult）
            findSub(root.left, newTarget);
            findSub(root.right, newTarget);
        }
        // 回退时在 mTemp 移除该节点（末尾元素）
        mTemp.remove(mTemp.size() - 1);
        return mResult;
    }

    // 非递归
    private ArrayList<ArrayList<Integer>> find2(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int curTarget = target;
        while (node != null || !stack.isEmpty()) {
            // 将一条路径上的节点入栈
            while (node != null) {
                stack.push(node);
                temp.add(node.val);
                // 当前栈顶节点引导的子树的目标
                curTarget -= node.val;
                if (node.left != null) {
                    // 有左子节点，向左子树遍历
                    node = node.left;
                } else {
                    // 否则向右子树遍历
                    // 如果也没有右子节点，则为叶子节点
                    // node 为空，结束 while 循环
                    node = node.right;
                }
            }
            // 弹出栈顶节点
            node = stack.pop();
            // 判断其是否是叶子节点，且符合条件
            if (node.left == null && node.right == null && curTarget == 0) {
                result.add(new ArrayList<>(temp));
            }
            // 在 temp 移除该节点（末尾元素）
            temp.remove(temp.size() - 1);
            curTarget += node.val;
            if (!stack.isEmpty() && stack.peek().left == node) {
                // 如果当前考虑的（node）是父节点的左子节点
                // 则下次考虑右子节点
                node = stack.peek().right;
            } else {
                // 如果当前考虑的（node）不是父节点的左子节点，那就一定考虑的是右子节点
                // 说明父节点的两个子节点已考虑完，可以考虑该父节点了（可以看出这是个后序遍历）
                // 将 node 设为空，就不会进入内部的 while 循环，也就不会再考虑子节点了
                node = null;
            }
        }
        // 排序
        result.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));
        return result;
    }

    public static void test() {
        TreeNode a = new TreeNode(8);
        TreeNode b = new TreeNode(6);
        TreeNode c = new TreeNode(10);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(7);
        TreeNode f = new TreeNode(2);
        TreeNode g = new TreeNode(11);
        TreeNode h = new TreeNode(1);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        f.left = h;
        System.out.println(new FindPath().find2(a, 21));
    }
}
