package net.zackzhang.code.codinginterviews.problems;

public class MinNumberInRotateArray {

    private int num(int[] array) {
        if (array.length == 0) return 0;
        int first = array[0];
        for (int val : array) {
            if (first > val) return val;
        }
        return 0;
    }

    // 二分查找
    private int num2(int[] array) {
        if (array.length == 0) return 0;
        int start = 0, end = array.length - 1, mid;
        while (start < end) {
            // 最后一定会走到这个情况，此时升降发生在 start 到 end 区间内
            // 而 start 和 end 相差 1，也就是说，start 最大，end 最小
            if (end - start == 1) break;
            // 调整 start 和 end，保证升降发生在 start 到 end 区间内
            mid = (start + end) / 2;
            if (array[mid] >= array[start]) {
                // start 和 mid 之间只有升（非减），最小值一定不在此区间（一定在 mid 到 end 的区间）
                // 因此将 start 移至 mid 的位置
                start = mid;
            } else {
                // start 和 mid 之间有升降，最小值一定在此区间
                // 因此将 end 移至 mid 的位置
                end = mid;
            }
        }
        return array[end];
    }

    public static void test() {
        System.out.println(new MinNumberInRotateArray().num2(new int[]{3, 4, 5, 1, 2}));
    }
}
