package net.zackzhang.code.codinginterviews.problems;

/** 机器人的运动范围 */
public class MovingCount {

    // 记录格子是否已遍历过
    private boolean[][] mWalked;
    private int mRowNum, mColNum, mTh;

    private int count(int threshold, int rows, int cols) {
        mRowNum = rows;
        mColNum = cols;
        mTh = threshold;
        mWalked = new boolean[rows][cols];
        return walk(0, 0);
    }

    private int walk(int row, int col) {
        // 如果搜索到边界外
        // 或数位之和大于阈值
        // 或之前已遍历过
        // 返回 0（表示不计这个格子）
        if (row < 0 || row >= mRowNum || col < 0 || col >= mColNum ||
                digitSum(row) + digitSum(col) > mTh || mWalked[row][col]) return 0;
        // 这个格子符合条件
        // 标记为已遍历
        mWalked[row][col] = true;
        // 继续递归遍历上下左右的格子
        // 加上当前的格子
        return walk(row - 1, col) + walk(row + 1, col) +
                walk(row, col - 1) + walk(row, col + 1) + 1;
    }

    private int digitSum(int num) {
        int sum = 0;
        int quot = num;
        while (quot > 0) {
            sum += quot % 10;
            quot /= 10;
        }
        return sum;
    }

    public static void test() {
        System.out.println(new MovingCount().count(5, 10, 10));
    }
}
