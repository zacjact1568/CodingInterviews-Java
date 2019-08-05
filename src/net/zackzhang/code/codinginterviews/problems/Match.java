package net.zackzhang.code.codinginterviews.problems;

/** 正则表达式匹配 */
public class Match {

    private boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int strPos, char[] pat, int patPos) {
        if (strPos == str.length && patPos == pat.length) return true;
        if (strPos != str.length && patPos == pat.length) return false;
        if (patPos < pat.length - 1 && pat[patPos + 1] == '*') {
            // 若模板第二个字符是 *
            if (strPos != str.length && (pat[patPos] == str[strPos] || pat[patPos] == '.')) {
                // 若字符串与模板第一个字符相等，或模板第一个字符是 .
                return
                        // * 之前的字符出现 0 次的情况，跳过 * 及之前的字符
                        match(str, strPos, pat, patPos + 2) ||
                        // * 之前的字符出现多次的情况（但先只考虑 1 个）
                        match(str, strPos + 1, pat, patPos) ||
                        // 第一个字符匹配的情况（可省略？）
                        match(str, strPos + 1, pat, patPos + 2);
            } else {
                // 字符串与模板第一个字符不等，且模板第一个字符不是 .
                // 只能是 * 之前的字符出现 0 次的情况
                return match(str, strPos, pat, patPos + 2);
            }
        }
        // 模板第二个字符不是 *
        if (strPos != str.length && (pat[patPos] == str[strPos] || pat[patPos] == '.')) {
            // 若字符串与模板第一个字符相等，或模板第一个字符是 .
            return match(str, strPos + 1, pat, patPos + 1);
        }
        return false;
    }

    // 动态规划
    private boolean match2(char[] str, char[] pattern) {
        // 每维多 1 是因为要考虑空串
        boolean[][] isMatched = new boolean[str.length + 1][pattern.length + 1];
        isMatched[str.length][pattern.length] = true;
        // 反向遍历
        // e.g. str = "baa"，pattern = "ba*"
        // 外层 for：空串，a，aa，baa
        // 内层 for：*，a*，ba*
        for (int i = str.length; i >= 0; i--) {
            for (int j = pattern.length - 1; j >= 0; j--) {
                if (j < pattern.length - 1 && pattern[j + 1] == '*') {
                    if (i != str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                        isMatched[i][j] = isMatched[i][j + 2] || isMatched[i + 1][j];
                    } else {
                        isMatched[i][j] = isMatched[i][j + 2];
                    }
                } else {
                    if (i != str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                        isMatched[i][j] = isMatched[i + 1][j + 1];
                    }
                }
            }
        }
        return isMatched[0][0];
    }

    public static void test() {
        System.out.println(new Match().match2(
                "baa".toCharArray(),
                "ba*".toCharArray()
        ));
    }
}
