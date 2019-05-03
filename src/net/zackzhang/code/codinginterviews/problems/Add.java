package net.zackzhang.code.codinginterviews.problems;

public class Add {

    public int add(int num1, int num2) {
        int temp;
        while (num2 != 0) {
            // 不算进位加，相当于按位异或
            temp = num1 ^ num2;
            // 单独计算进位
            num2 = (num1 & num2) << 1;
            num1 = temp;
            // 将不算进位加的结果赋给 num1，单独计算进位的结果赋给 num2
            // 重复上述过程，直到单独计算进位的结果为 0，不算进位加的结果就是最终结果
        }
        return num1;
    }

    public static void test() {
        System.out.println(new Add().add(0, 1));
    }
}
