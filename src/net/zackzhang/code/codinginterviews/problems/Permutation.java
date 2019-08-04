package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Collections;

/** 字符串的排列 */
public class Permutation {

    private ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) return res;
        permutation(str.toCharArray(), 0, res);
        // 按字典序排序
        Collections.sort(res);
        return res;
    }

    private void permutation(char[] chs, int start, ArrayList<String> res) {
        if (start == chs.length - 1) {
            // 结束条件：最后一个位置
            // 先判断 res 里有没有相同的字符串
            // 原字符串里有重复字符的话，会出现重复
            String str = String.valueOf(chs);
            if (!res.contains(str)) {
                res.add(str);
            }
        } else {
            // 将 start 位置的字符依次与后面所有字符交换
            for (int i = start; i < chs.length; i++) {
                // 先将 start 与 i 位置的字符交换
                swap(chs, start, i);
                // 固定 start，递归求后面字符的排列
                permutation(chs, start + 1, res);
                // 复位，让字符顺序与这次 for 开始时相同
                // 这样下次 for 才能保证只是将 start 与 i + 1 的字符交换
                // 如果不复位，经过递归就会存在其他元素的交换
                swap(chs, start, i);
            }
        }
    }

    private void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }

    public static void test() {
        System.out.println(new Permutation().permutation("AbCd"));
    }
}
