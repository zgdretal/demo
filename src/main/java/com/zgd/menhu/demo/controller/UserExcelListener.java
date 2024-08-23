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

        if (userListMap.size() == 10) {
            test();
        }
    }

    private static void test() {
        Map<String, List<User>> sonMap = new HashMap<>();
        Map<String, List<User>> daughterMap = new HashMap<>();
        Map<String, List<User>> wifeMap = new HashMap<>();
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
                    if (wifeMap.get(user.getFamily().substring(0,3)) != null) {
                        wifeMap.get(user.getFamily().substring(0,3)).add(user);
                    } else {
                        List<User> users = new ArrayList<>();
                        users.add(user);
                        wifeMap.put(user.getFamily().substring(0,3), users);
                    }

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
                if (user.getBirthDay().equals("不详")) {
                    text.append("出生日期不详,");
                } else {
                    text.append("  ").append("生于").append(user.getBirthDay()).append(",");
                }


                if (wifeMap.get(user.getName()) != null) {
                    List<User> wifeList = wifeMap.get(user.getName());
                    String wifeName = "";
                    for (User wife: wifeList) {
                        wifeName = wifeName + wife.getName() + ",";
                    }

                    text.append("配偶").append(wifeName.substring(0, wifeName.length() -1)).append(", ");
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
                    if (user.getDeadDay().equals("不详")) {
                        text.append("死亡日期不详, ");
                    } if (user.getDeadDay().equals("不祥")) {
                        text.append("死亡日期不详, ");
                    }else {
                        text.append("殁于").append(user.getDeadDay()).append(", ");
                    }

                    text.append("安葬于").append(user.getFengmuLocaltion()).append(", ");
                    if (!StringUtils.isEmpty(user.getFengmuLocaltionFangxiang()) &&
                            !user.getFengmuLocaltionFangxiang().equals("不详") &&
                            !user.getFengmuLocaltionFangxiang().equals("不祥")) {
                        text.append(user.getFengmuLocaltionFangxiang().substring(0,1)).append("山")
                                .append(user.getFengmuLocaltionFangxiang().substring(1,2)).append("向").append(", ");
                    }

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
