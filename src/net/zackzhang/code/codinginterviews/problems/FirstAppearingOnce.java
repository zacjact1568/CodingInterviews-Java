package net.zackzhang.code.codinginterviews.problems;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstAppearingOnce {

    // *********** 使用 LinkedHashMap ***********

    private LinkedHashMap<Character, Integer> mReadChars = new LinkedHashMap<>();

    // 向字符流中送入字符，find 函数即处理所有送入的字符
    private void insert(char ch) {
        // 将 ch 键对应的值 + 1
        mReadChars.put(ch, mReadChars.getOrDefault(ch, 0) + 1);
    }

    private char find() {
        // LinkedHashMap 保证迭代顺序与放入顺序一致
        for (Map.Entry<Character, Integer> e : mReadChars.entrySet()) {
            // 最先找到值为 1 的键就是第一个不重复的字符
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        // 如果没找到值为 1 的键，返回 #
        return '#';
    }

    // *********** 使用 ASCII 字符数组 ***********

    // ASCII 字符（包括扩展）共 256 个
    // 每个元素默认初始化为 0
    private char[] mChars = new char[256];
    // 记录字符顺序
    private StringBuilder mSb = new StringBuilder();

    private void insert2(char ch) {
        mChars[ch]++;
        mSb.append(ch);
    }

    private char find2() {
        for (char ch : mSb.toString().toCharArray()) {
            if (mChars[ch] == 1) {
                return ch;
            }
        }
        return '#';
    }

    public static void test() {
        FirstAppearingOnce fao = new FirstAppearingOnce();
        fao.insert2('g');
        fao.insert2('o');
        fao.insert2('o');
        fao.insert2('g');
        fao.insert2('l');
        fao.insert2('e');
        System.out.println(fao.find2());
    }
}
