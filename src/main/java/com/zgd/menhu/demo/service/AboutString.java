package com.zgd.menhu.demo.service;

import org.springframework.util.StringUtils;

import java.util.*;

public class AboutString {
    /**
     * 字符串中最长无重复字符的子串长度
     * @param text
     * @return
     */
    public int getSubStringLen(String text) {
        if (StringUtils.isEmpty(text)) {
            return 0;
        }
        int max = 0;
        int left = 0;

        Map<Character, Integer> leftMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (leftMap.containsKey(text.charAt(i))) {
                left = Math.max(max, leftMap.get(text.charAt(i)) + 1);
            }
            leftMap.put(text.charAt(i), i);
            left = Math.max(left, i - left + 1);
        }
        return max;
    }

    /**
     * 寻找最长回文子串 leetcode 5
     *
     * 递归：
     * P(i,j)=P(i+1,j−1)∧(Si == sj)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        int len = s.length();
        boolean[][] flag = new boolean[len][len];
        char[] chars = s.toCharArray();

        for (int i = 0; i < len; i++) {
            flag[i][i] = true;
        }
        int begin = 0;
        int maxLen = 1;
        for (int j = 1; j < len; j ++) {
            for (int k = 0; k < j; k ++) {
                if (chars[j] == chars[k]) {
                    //aba 这种情况处理
                    if (j - k  + 1 < 4) {
                        flag[k][j] = true;
                    } else {
                        flag[k][j] = flag[k + 1][j - 1];
                    }

                } else {
                    flag[k][j] = false;
                }

                if (flag[k][j] && j - k + 1 > maxLen) {
                    begin = k;
                    maxLen = j - k + 1;
                }
            }
        }
        return s.substring(begin,  begin + maxLen);
    }

    /**
     * 成水最多的 两条线 leetcode 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        //移动长板容量一定变小或者不变，移动短板则容量不变或者变大
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }


    /**
     * 最长公共前缀 leetcode 14
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 电话号码组合 leetcode 17
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, digits, 0, phoneMap, new StringBuilder());
        return combinations;
    }


    public void backtrack(List<String> combinations, String digits, int index, Map<Character, String> phoneMap, StringBuilder combination) {

        if(index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            String letters = phoneMap.get(digits.charAt(index));
            int len = letters.length();
            for (int i = 0; i < len; i ++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, digits, index + 1, phoneMap, combination);
                combination.deleteCharAt(index);
            }
        }

    }


    /**
     * 全排列 leetcode46
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean [] visited = new boolean[nums.length];
        backtrack(res, visited, nums, new ArrayList<>());
        return res;
    }

    public void backtrack(List<List<Integer>> res, boolean [] visited, int[] num, List<Integer> combination) {
        if (combination.size() == num.length) {
            res.add(new ArrayList<>(combination));
        } else {
            for (int i = 0; i < num.length; i ++) {
                if (visited[i]) {
                    continue;
                }
                combination.add(num[i]);
                visited[i] = true;
                backtrack(res, visited, num, combination);
                visited[i] = false;
                combination.remove(combination.size() -1);
            }
        }
    }


    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列 leetcode47
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Boolean>  visitedMap = new HashMap();
        Arrays.sort(nums);
        backtrack1(res, visitedMap, nums, new ArrayList<>());
        return res;
    }

    public void backtrack1(List<List<Integer>> res, Map<Integer, Boolean>  visitedMap, int[] num, List<Integer> combination) {
        if (combination.size() == num.length) {
            res.add(new ArrayList<>(combination));
        } else {
            for (int i = 0; i < num.length; i ++) {
                if ((visitedMap.get(i) != null && visitedMap.get(i)) || (i > 0 && num[i] == num[i - 1] && !visitedMap.get(i - 1))) {
                    continue;
                }
                combination.add(num[i]);
                visitedMap.put(i, true);
                backtrack1(res, visitedMap, num, combination);
                visitedMap.put(i, false);
                combination.remove(combination.size() -1);
            }
        }
    }

    /**
     * 括号生成  leetcode22
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n < 1) {
            return null;
        }
        List<String> res = new ArrayList<>();
        backtackGenerateParenthesis(res, n, n, n, new StringBuilder());
        return res;
    }

    public void backtackGenerateParenthesis(List<String> res, int left, int right, int n, StringBuilder temp) {
        if (temp.length() == n * 2) {
            res.add(temp.toString());
            return;
        }

        if (left > 0) {
            temp.append("(");
            backtackGenerateParenthesis(res, left - 1, right, n, temp);
            temp.deleteCharAt(temp.length() -1);
        }

        if (right > 0 && left < right) {
            temp.append(")");
            backtackGenerateParenthesis(res, left, right-1, n, temp);
            temp.deleteCharAt(temp.length() -1);
        }

    }

    /**
     * 复原 IP 地址 leetccode93
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        return null;
    }

    /**
     * 生成括号，输入n对括号，看看生成多少种合法的括号组合
     * https://leetcode.cn/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     * @param n
     * @return
     */
    /*public List<String> generateParenthesis(int n) {
        Collections.swap();

    }*/

}
