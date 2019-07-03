package net.zackzhang.code.codinginterviews.problems;

import java.util.Arrays;

/** 把数组排成最小的数 */
public class PrintMinNumber {

    private String print(int[] numbers) {
        int len = numbers.length;
        String[] strs = new String[len];
        StringBuilder sb = new StringBuilder();
        // 将数字数组转换为字符串数组
        for (int i = 0; i < len; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        // 按自定义的规则排序字符串数组
        // 规则：两个字符串，从首字符开始，如果第一个的 Unicode 值大于第二个，
        // 则返回正整数，否则返回负整数，若相同则比较下一个
        // e.g. 313 和 331，返回负整数
        Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        // 拼接排序好的字符串数组
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void test() {
        System.out.println(new PrintMinNumber().print(new int[]{3, 32, 321}));
    }
}
