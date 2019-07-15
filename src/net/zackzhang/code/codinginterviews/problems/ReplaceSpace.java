package net.zackzhang.code.codinginterviews.problems;

/** 替换空格 */
public class ReplaceSpace {

    private String replace(StringBuffer str) {
        String ins = "%20";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, ins);
                i += 2;
            }
        }
        return str.toString();
    }

    private String replace2(StringBuffer str) {
        // 统计空格出现的次数
        int bsCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                bsCount++;
            }
        }
        // 扩展 str 的长度
        // 每个空格替换为三个字符（新增两个字符），填充 Unicode 的 0
        int ext = bsCount * 2;
        str.setLength(str.length() + ext);
        // 从后往前调整字符
        // j 是要被替换的字符位置
        int j = str.length() - 1;
        // i 是要用于替换的字符位置
        for (int i = str.length() - ext - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                str.setCharAt(j--, '0');
                str.setCharAt(j--, '2');
                str.setCharAt(j--, '%');
            } else {
                str.setCharAt(j--, ch);
            }
        }
        return str.toString();
    }

    public static void test() {
        System.out.println(new ReplaceSpace().replace2(new StringBuffer("We Are Happy")));
    }
}
