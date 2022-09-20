package com.zgd.menhu.demo.service.test;



import java.util.*;

public class Test {

    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        String input =  in.nextLine();

        TreeMap<Character, Integer> out = new TreeMap<>();
        for (Character t : input.toCharArray()) {
            if (out.get(t) != null) {
                Integer num = out.get(t);
                num = num + 1;
                out.put(t, num);
            } else {
                out.put(t, 1);
            }
        }
        StringBuilder output = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : out.entrySet()) {

            output.append(entry.getKey()).append(entry.getValue());
        }
        System.out.println(output);*/

        //Scanner in = new Scanner(System.in);
        int i = 0;
        int num = 5;
        Integer [] arrays = new Integer[5];
        /*while (in.hasNextInt()) {
            if (i == 0) {
                num = in.nextInt();
                arrays = new Integer[num];
            } else {
                arrays[i] = in.nextInt();
                i ++;
            }
        }*/
        arrays[0] = 1;
        arrays[1] = 1;
        arrays[2] = 2;
        arrays[3] = 22;
        arrays[4] = 23;


        int input = getNum(arrays, num - 1, 24);
        System.out.println(input);

    }

    //有target的子集合数量  递归调用，
    public static int getNum(Integer[] array, int right, int target) {

        if (target == 0) {
            return 0;
        }

        if (right == 1) {
            if (target == array[0] || target == array[1]) {
                return 1;
            }

            if (target == (array[0] + array[1])) {
                return 1;
            }
            return 0;
        }
        int input = getNum(array, right - 1, target - array[right]) + getNum(array, right - 1, target );
        return input;
    }

}
