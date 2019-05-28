package net.zackzhang.code.codinginterviews.problems;

public class LeftRotateString {

    private String rotate(String str, int n) {
        if (str.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < n; i++) {
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    // 真正反映考点的解
    private String rotate2(String str, int n) {
        if (str.isEmpty()) return "";
        char[] chars = str.toCharArray();
        int len = chars.length;
        // n 可能大于 len
        n %= len;
        // 先旋转前 n 个元素
        reverse(chars, 0, n - 1);
        // 再旋转后 len - n 个元素
        reverse(chars, n, len - 1);
        // 再全部旋转
        reverse(chars, 0, len - 1);
        return String.valueOf(chars);
    }

    /** 旋转 chars 中下标为 i、j 之间的元素 */
    private void reverse(char[] chars, int i, int j) {
        char temp;
        while (i < j) {
            temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
    }

    public static void test() {
        System.out.println(new LeftRotateString().rotate2("abcXYZdef", 3));
    }
}
