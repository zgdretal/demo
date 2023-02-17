package com.zgd.menhu.demo.service.test;

import org.springframework.stereotype.Service;

@Service
public class Target implements  TargetInterface{

    public void test(String test) {
        System.out.println(test);
    }


    /**
     * 给定一个n个元素的数组，其中元素值只有 0，1，2，请给这个数组排序
     */

    public int[] sort(int [] a) {
        int length = a.length;
        int a0 = 0;
        int a1 = 0;
        int a2 = 0;
        for(int i = 0; i < length; i++) {

            if (a[i] == 0) {
                a0 ++;
            }
            if (a[i] == 1) {
                a1 ++;
            }
        }
        int target[] = new int[length];
        for (int i = a0; i < target.length; i ++) {
            if (i < a0 + a1) {
                target[i] = 1;
            } else {
                target[i] = 2;
            }
        }
        return target;

    }

    public int[] highSort(int[] a) {
        int tail = a.length - 1;
        for (int i = 0; i < a.length; i ++) {
            if (a[i] == 0) {
                continue;
            }
            if (a[i] == 1) {
                while ( tail == i ) {

                }
                if (a[tail] == 1 || a[tail] == 0) {

                }
            }
        }
        return null;

    }
}
