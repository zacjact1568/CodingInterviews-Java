package net.zackzhang.code.codinginterviews.problems;

/** 矩阵中的路径 */
public class HasPath {

    private char[] mMat, mStr;
    private int mRowNum, mColNum;
    private boolean[] mWalked;

    private boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        mMat = matrix;
        mRowNum = rows;
        mColNum = cols;
        mStr = str;
        mWalked = new boolean[matrix.length];
        // 尝试每一个格子作为起始
        for (int i = 0; i < rows * cols; i++) {
            if (walk(i, 0)) return true;
        }
        return false;
    }

    private boolean walk(int pos, int offset) {
        // 将 pos 转换为 row 和 col
        int row = pos / mColNum;
        int col = pos % mColNum;
        // 如果搜索到边界外
        // 或当前偏移位置的字符不匹配
        // 或之前已遍历过
        // 返回 false（表示这个格子不符合条件）
        if (row < 0 || row >= mRowNum || col < 0 || col >= mColNum ||
                mMat[pos] != mStr[offset] || mWalked[pos]) return false;
        // 这个格子暂时符合条件（偏移位置之前匹配）
        // 标记为已遍历
        mWalked[pos] = true;
        // 最后一个字符匹配
        if (offset == mStr.length - 1) return true;
        // 否则增加偏移
        offset++;
        // 继续递归遍历上下左右的格子
        // 只要有一个符合条件即可
        if (walk(pos - mColNum, offset) || walk(pos + mColNum, offset) ||
                walk(pos - 1, offset) || walk(pos + 1, offset)) return true;
        // 否则说明这个格子不符合条件（偏移位置之后不匹配）
        // 标记为未遍历
        mWalked[pos] = false;
        return false;
    }

    public static void test() {
        System.out.println(new HasPath().hasPath(
                "abcesfcsadee".toCharArray(),
                3,
                4,
                "bcced".toCharArray()
        ));
    }
}
