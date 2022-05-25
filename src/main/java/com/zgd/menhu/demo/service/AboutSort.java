package com.zgd.menhu.demo.service;

public class AboutSort {

    public static void main(String[] args) {
        AboutSort sort = new AboutSort();
        int arr[] = {-2,3,-5};
        sort.heapSort(arr);
    }

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
}
