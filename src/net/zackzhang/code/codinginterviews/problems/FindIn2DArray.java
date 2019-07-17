package net.zackzhang.code.codinginterviews.problems;

/** 二维数组中的查找 */
public class FindIn2DArray {

    public boolean find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0] == null || array[0].length == 0) return false;
        for (int[] d1 : array) {
            // 当某一行最后的元素小于 target，这一行都不可能
            if (d1[d1.length - 1] < target) continue;
            // 当某一行第一个元素大于 target，这一行都不可能
            if (d1[0] > target) continue;
            // 可能的行，遍历
            for (int num : d1) {
                if (num == target) return true;
            }
        }
        return false;
    }

    public boolean find2(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0] == null || array[0].length == 0) return false;
        // 从右上角开始查找
        int row = 0, col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            int num = array[row][col];
            if (num == target) {
                return true;
            } else if (num < target) {
                // 如果当前数比 target 小，向下查找
                row++;
            } else {
                // 如果当前数比 target 大，向左查找
                col--;
            }
        }
        return false;
    }

    public static void test() {
        System.out.println(new FindIn2DArray().find2(
                12,
                new int[][]{{1, 2, 3, 4}, {2, 4, 6, 8}, {3, 6, 9, 12}, {13, 14, 15, 16}}
        ));
    }
}
