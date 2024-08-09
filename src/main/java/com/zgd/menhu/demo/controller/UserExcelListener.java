package com.zgd.menhu.demo.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserExcelListener extends AnalysisEventListener<User> {

    private int i = 0;

    private static Map<Integer, List<User>>  userListMap = new HashMap<>();

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        i ++;
        if (i<5) {
            return;
        }
        if (user.getFamily().contains("子")) {
            user.setFather(user.getFamily().substring(0,3));
        }

        if (user.getFamily().contains("妻")) {
            user.setHusbandName(user.getFamily().substring(0,3));
        }
        user.setFamilyLevel(User.getFamilyLevel(user.getFamily()));

        String mingchetTest = user.getMingchen();
        if (mingchetTest.contains("考")) {
            user.setSexChinaName("考");
        }

        if (mingchetTest.contains("妣")) {
            user.setSexChinaName("妣");
        }

        if (mingchetTest.contains("姑")) {
            user.setSexChinaName("姑");
        }


        mingchetTest = mingchetTest.replace("世", "");
        mingchetTest = mingchetTest.replace("考", "");
        mingchetTest = mingchetTest.replace("妣", "");
        if (user.getMingchen().contains("已故")){
            mingchetTest = mingchetTest.replace("已故", "");
        }
        user.setLevel(User.chineseNumber2Int(mingchetTest));

        user.setRealLevel(user.getLevel() - 5);
        System.out.println("检测到一条数据: " + user.toString());

        if (userListMap.get(user.getRealLevel()) != null) {
            userListMap.get(user.getRealLevel()).add(user);
        } else {
            List<User> userList = new ArrayList<>();
            userList.add(user);
            userListMap.put(user.getRealLevel(), userList);
        }

        if (userListMap.size() == 9) {
            test();
        }
    }

    private static void test() {
        Map<String, List<User>> sonMap = new HashMap<>();
        Map<String, List<User>> daughterMap = new HashMap<>();
        Map<String, User> wifeMap = new HashMap<>();
        for (Map.Entry<Integer, List<User>> entry : userListMap.entrySet()) {
            for (User user: entry.getValue()) {
                if (user.getFamily().contains("子")) {
                    if (sonMap.get(user.getFamily().substring(0,3)) != null) {
                        sonMap.get(user.getFamily().substring(0,3)).add(user);
                    } else {
                        List<User> users = new ArrayList<>();
                        users.add(user);
                        sonMap.put(user.getFamily().substring(0,3), users);
                    }

                }


                if (user.getFamily().contains("女")) {
                    if (daughterMap.get(user.getFamily().substring(0,3)) != null) {
                        daughterMap.get(user.getFamily().substring(0,3)).add(user);
                    } else {
                        List<User> users = new ArrayList<>();
                        users.add(user);
                        daughterMap.put(user.getFamily().substring(0,3), users);
                    }

                }


                if (user.getFamily().contains("之妻")) {
                    wifeMap.put(user.getFamily().substring(0,3), user);
                }
            }
        }
        XWPFDocument xwpfDocument = new XWPFDocument();
        for (Map.Entry<Integer, List<User>> entry : userListMap.entrySet()) {
            for (User user: entry.getValue()) {
                XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
                xwpfParagraph.setAlignment(ParagraphAlignment.LEFT);
                xwpfParagraph.setFirstLineIndent(420);
                XWPFRun xwpfRun1 = xwpfParagraph.createRun();
                XWPFRun xwpfRun = xwpfParagraph.createRun();


                xwpfRun1.setText(user.getName() + ": ");
                xwpfRun1.setBold(true);

                StringBuilder text = new StringBuilder();
                text.append("  ").append("生于").append(user.getBirthDay()).append(",");

                if (wifeMap.get(user.getName()) != null) {
                    text.append("配偶").append(wifeMap.get(user.getName()).getName()).append(", ");
                }

                if (sonMap.get(user.getName()) != null) {
                    text.append("生子: ");
                    for (User son: sonMap.get(user.getName())) {
                        text.append(son.getName().substring(1,3));
                    }
                    text.append(", ");
                }

                if (daughterMap.get(user.getName()) != null) {
                    text.append("生女: ");
                    for (User dughter: daughterMap.get(user.getName())) {
                        text.append(dughter.getName());
                    }
                    text.append(", ");
                }
                if (user.getDeadDay() != null) {
                    text.append("殁于").append(user.getDeadDay()).append(", ");
                    text.append("安葬于").append(user.getFengmuLocaltion()).append(", ").append(user.getFengmuLocaltionFangxiang()).append(", ");
                }

                xwpfRun.setText(text.toString());
            }




        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.docx");
            xwpfDocument.write(fileOutputStream);
            xwpfDocument.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
