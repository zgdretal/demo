package com.zgd.menhu.demo.service;

import java.util.Random;

public class AboutSort {

    public static void main(String[] args) {
        AboutSort sort = new AboutSort();
        int arr[] = {5,1,2,0,0};
        //sort.heapSort(arr);
        sort.qucikSort(arr, 0, arr.length  -1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "->");
        }
    }



    public void qucikSort(int[] nums, int left, int right) {
        if (left < right) {
            int partion = partition(nums, left, right);
            qucikSort(nums, left, partion - 1);
            qucikSort(nums, partion + 1, right);
        }
    }

    private int partition(int [] arr, int left, int right) {
        int posrandom = new Random().nextInt(right - left +1) + left;
        swap(arr, posrandom, left);
        int pos = left;
        int temp = arr[left];
        while (left < right) {

            while (arr[right] >= temp && left < right) {
                right --;
            }

            while (arr[left] <= temp && left < right) {
                left ++;
            }

            if (right > left) {
                swap(arr, left, right);
            }
        }
        arr[pos] = arr[left];
        arr[left] = temp;
        return left;
    }







    /**
     * 堆排序
     * @param arr
     */
    public void heapSort(int arr[]) {
        //建堆
        for (int i = (arr.length - 1)/2; i >=0; i -- ) {
            //从底部开始调整
            ajustHeap(arr, i, arr.length);
        }

        //不断调整堆
        for (int i = arr.length -1; i > 0; i --) {
            swap(arr, 0, i);
            ajustHeap(arr, 0, i);
        }
        for (int k = 0; k < arr.length; k ++) {
            System.out.println(arr[k] + " ");
        }

    }

    public void swap(int arr[], int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     *  k: 需要下沉的二叉树位置下标
     * @param arr
     * @param k
     * @param length
     */
    public void ajustHeap(int arr[], int k, int length) {

        //
        for (int i = 2*k + 1; i < length; i = 2*i + 1) {
            if (i + 1 <= length -1 && arr[i] < arr[i +1]) {
                i = i +1;
            }
            if (arr[i] > arr[k]) {
                swap(arr, i, k);
            } else {
                break;
            }
            k = i;
        }
    }


    /**
     * 归并排序
     * @param arr
     */
    public void mergeSort(int arr[]) {

    }
}
