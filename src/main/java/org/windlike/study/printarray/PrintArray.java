package org.windlike.study.printarray;

import java.util.Arrays;

/**
 * 面向过程与面向对象对比
 * 打印数组 格式为[10, 20, 30, 40, 50]
 */
public class PrintArray {

    public static void main(String[] args) {
        int[] array = {10, 20, 30, 40, 50};
        // 传统方式：面向过程
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                System.out.print(array[i] + ", ");
            } else {
                System.out.println(array[i] + "]");
            }
        }
        System.out.println("===================");
        // 现代方式：面向对象
        System.out.println(Arrays.toString(array));
    }
}
