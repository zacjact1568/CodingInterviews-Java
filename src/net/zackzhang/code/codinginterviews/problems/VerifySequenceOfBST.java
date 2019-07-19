package net.zackzhang.code.codinginterviews.problems;

/** 二叉搜索树的后序遍历序列 */
public class VerifySequenceOfBST {

    // 递归
    private boolean verify(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] seq, int start, int end) {
        // 虽然写的是大于等于，但 start 只能比 end 大 1
        // 适用于递归调用子树为空的情况
        // 此时，rs 最小等于 start（左子树为空），或 rs 最大等于 end（右子树为空）
        if (start >= end) return true;
        // 最后一个节点是根节点
        int root = seq[end];
        // 遍历数组，找到第一个不小于根节点的数
        // rs 是该数的下标
        int rs = start;
        while (seq[rs] < root) {
            rs++;
        }
        // 判断 rs 之后的数是否都不小于 root
        int r = rs + 1;
        while (r <= end) {
            // 如果有小于的，不符合 BST
            if (seq[r++] < root) return false;
        }
        // 递归前后两部分
        return verify(seq, start, rs - 1) && verify(seq, rs, end - 1);
    }

    // 非递归
    // 检查数组是否可划分为比最尾元素大和比最尾元素小的两部分（可为空）
    // 删除尾部元素，再继续上述操作
    // 如何证明？
    private boolean verify2(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        int root = sequence.length;
        int i;
        while (--root > 0) {
            i = 0;
            while (sequence[i] < sequence[root]) {
                i++;
            }
            while (sequence[i] > sequence[root]) {
                i++;
            }
            if (i < root) return false;
        }
        return true;
    }

    public static void test() {
        System.out.println(new VerifySequenceOfBST().verify2(
                new int[]{1, 3, 4, 2, 6, 8, 7, 5}
        ));
    }
}
