package net.zackzhang.code.codinginterviews.problems;

/** 数组中的逆序对 */
public class InversePairs {

    private int mCount = 0;

    private int inversePairs(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return mCount;
    }

    // 归并排序
    private void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, mid + 1, end);
        }
    }

    // 将数组的前后两个有序部分合并为一个有序部分
    private void merge(int[] array, int startA, int endA, int startB, int endB) {
        // i、j 初始化为两个有序部分的末尾下标
        int i = endA, j = endB;
        // k + 1 为 temp 的长度
        // k 初始化为 temp 末尾下标
        int k = endB - startA;
        // 辅助数组，其中数字递增存放
        int[] temp = new int[k + 1];
        while (i >= startA && j >= startB) {
            if (array[i] > array[j]) {
                // 如果下标 i 的数字大于 j 的
                // 则它也大于 j 前所有数字
                // 它与 j 及其前面所有数字组成逆序对
                // 将下标 i 的数（较大）放入 temp
                temp[k--] = array[i--];
                // j 及其前面所有数字个数为 j - startB + 1
                // 累加
                mCount = mCount + j - startB + 1;
                // 组合律，可先求模，避免溢出？
                mCount %= 1000000007;
            } else {
                // 否则无逆序对
                // 将下标 j 的数（较大）放入 temp
                temp[k--] = array[j--];
            }
        }
        // 处理剩下的数
        // i 和 j 可能不会同时到达两个有序部分的起始之前
        // 当 i 未到达起始之前而 j 已到达
        // i 及之前的数都是较小的
        while (i >= startA) {
            temp[k--] = array[i--];
        }
        // 当 j 未到达起始之前而 i 已到达
        // j 及之前的数都是较小的
        while (j >= startB) {
            temp[k--] = array[j--];
        }
        // 将 temp 中的有序数复制到 array 中的对应位置
        int s = startA;
        for (int num : temp) {
            array[s++] = num;
        }
    }

    public static void test() {
        System.out.println(new InversePairs().inversePairs(
                new int[]{1, 2, 3, 4, 5, 6, 7, 0}
        ));
    }
}
