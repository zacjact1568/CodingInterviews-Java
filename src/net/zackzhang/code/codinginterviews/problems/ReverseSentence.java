package net.zackzhang.code.codinginterviews.problems;

/** 翻转单词顺序列 */
public class ReverseSentence {

    // 注：该方法可处理多个空格分隔单词，也能把多个空格替换为一个空格
    private String reverse(String str) {
        // str 可能由多个连续空格组成
        // 使用 trim 去掉首尾空格
        if (str.trim().length() == 0) return str;
        // + 匹配前面的子表达式至少 1 次
        // 可处理多个空格分隔的单词
        String[] words = str.split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 注：该方法可处理多个空格分隔单词，但不能把多个空格替换为一个空格
    private String reverse2(String str) {
        if (str.trim().length() == 0) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        int i = 0, j = 0;
        while (j < sb.length()) {
            // 跳过空格（i 与 j 相等且一起走）
            while (i < sb.length() && sb.charAt(i) == ' ') {
                i++;
                j++;
            }
            // 寻找单词结束位置的下一位置（j）
            while (j < sb.length() && sb.charAt(j) != ' ') {
                j++;
            }
            // 翻转 [i, j) 部分
            int start = i, end = j - 1;
            while (start < end) {
                char temp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);
                start++;
                end--;
            }
            // 让 i 也表示单词结束位置的下一位置
            i = j;
        }
        return sb.toString();
    }

    public static void test() {
        System.out.println(new ReverseSentence().reverse2("student. a am I"));
    }
}
