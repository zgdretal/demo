package com.zgd.menhu.demo.service;

import java.util.*;

public class AboutNumber {

    /**
     * 三数之和 leetcode 15
     * @param num
     * @return
     */
    public List<List<Integer>> threeNumberSum(int [] num) {
        List<List<Integer>> res  = new ArrayList<>();
        if (num == null || num.length < 3) {
            return res;
        }
        Arrays.sort(num);
        for (int i = 0; i<num.length; i++) {
            if (num[i] > 0) {
                break;
            }
            if (i > 0  && num[i] == num[i-1]) {
                continue;
            }
            int target = -num[i];
            int left = i+1;
            int right = num.length -1;
            while (left < right) {
                if (num[left] + num[right] == target) {
                    res.add(new ArrayList<>(Arrays.asList(num[i], num[left], num[right])));
                    left++;
                    right--;
                    while (left < right && num[left] == num[left - 1]) {
                        left++;
                    }
                    while (left < right && num[right] == num[right + 1]) {
                        right--;
                    }

                } else if (num[left] + num[right] < target) {
                    left++;
                } else if (num[left] + num[right] > target){
                    right--;
                }

            }
        }
        return res;
    }

    /**
     * 两数之和 leetcode 1
     * @param num
     * @return
     */
    public int[] twoNumberSum(int [] num, int target) {
        int [] targetInt = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(target - num[0], 0);
        for (int i = 1; i < num.length; i ++) {
            if (map.containsKey(num[i])) {
                targetInt[0] = map.get(num[i]);
                targetInt[1] = i;
            }
            map.put(target - num[i], i);
        }
        return targetInt;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 两数相加 leetcode 2
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = new ListNode(sum%10);
                tail = new ListNode(sum%10);

            } else {
                tail.next = new ListNode(sum%10);
                tail = tail.next;
            }

            carry = sum/10;
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }

        }
        return head;
    }

    /**
     * 求平方根 https://leetcode.cn/problems/jJ0w9p/
     */
    public int mySqrt(int x) {

        return 0;
    }
}
