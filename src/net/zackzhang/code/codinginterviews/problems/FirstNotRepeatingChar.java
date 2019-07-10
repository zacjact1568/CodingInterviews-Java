package net.zackzhang.code.codinginterviews.problems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** 第一个只出现一次的字符 */
public class FirstNotRepeatingChar {

    private int find(String str) {
        LinkedHashMap<Character, Integer> lhm = new LinkedHashMap<>();
        // 储存每个字符第一次出现的位置
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            lhm.put(ch, lhm.getOrDefault(ch, 0) + 1);
            if (!hm.containsKey(ch)) {
                hm.put(ch, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : lhm.entrySet()) {
            if (entry.getValue() == 1) {
                return hm.get(entry.getKey());
            }
        }
        return -1;
    }

    private int find2(String str) {
        int len = str.length();
        HashMap<Character, Integer> lhm = new HashMap<>();
        // 统计每个字符的出现次数
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            lhm.put(ch, lhm.getOrDefault(ch, 0) + 1);
        }
        // 从前往后找，保证是第一个出现一次的字符
        for (int i = 0; i < len; i++) {
            if (lhm.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    private int find3(String str) {
        // str 全部由字母组成，A-Z 和 a-z 共 58 个
        int[] times = new int[58];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            times[map(str.charAt(i))]++;
        }
        for (int i = 0; i < len; i++) {
            if (times[map(str.charAt(i))] == 1) {
                return i;
            }
        }
        return -1;
    }

    private int map(char ch) {
        int offset = 0;
        if (ch >= 'A' && ch <= 'Z') {
            offset = 'A';
        } else if (ch >= 'a' && ch <= 'z') {
            offset = 'a' - 26;
        }
        return ch - offset;
    }

    public static void test() {
        System.out.println(new FirstNotRepeatingChar().find3("abbcabdeda"));
    }
}
