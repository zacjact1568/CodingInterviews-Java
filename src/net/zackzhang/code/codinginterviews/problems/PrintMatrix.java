package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;

/** 顺时针打印矩阵 */
public class PrintMatrix {

    private ArrayList<Integer> print(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        // 初始位置为 (0, -1)
        int minRow = 0, maxRow = matrix.length - 1;
        int minCol = -1, maxCol = matrix[0].length - 1;
        int i = 0, j = -1;
        // 组合成上下左右方向
        boolean horizontal = false, ascend = false;
        while (true) {
            if (!horizontal && !ascend && i == minRow) {
                // 左上角
                minCol++;
                // 接下来该往右走
                horizontal = true;
                ascend = true;
                // 检查当前是不是已在最大列
                // 如果是，说明已到头
                if (j == maxCol) break;
            } else if (horizontal && ascend && j == maxCol) {
                // 右上角
                minRow++;
                // 接下来该往下走
                horizontal = false;
                // 检查当前是不是已在最大行
                // 如果是，说明已到头
                if (i == maxRow) break;
            } else if (!horizontal && ascend && i == maxRow) {
                // 右下角
                maxCol--;
                // 接下来该往左走
                horizontal = true;
                ascend = false;
                // 检查当前是不是已在最小列
                // 如果是，说明已到头
                if (j == minCol) break;
            } else if (horizontal && !ascend && j == minCol) {
                // 左下角
                maxRow--;
                // 接下来该往上走
                horizontal = false;
                // 检查当前是不是已在最小行
                // 如果是，说明已到头
                if (i == minRow) break;
            }
            // 计算下一位置
            if (horizontal) {
                j += ascend ? 1 : -1;
            } else {
                i += ascend ? 1 : -1;
            }
            res.add(matrix[i][j]);
        }
        return res;
    }

    public static void test() {
        System.out.println(new PrintMatrix().print(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        }));
    }
}
