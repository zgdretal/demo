package com.zgd.menhu.demo.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserExcelListener extends AnalysisEventListener<User> {

    private int i = 0;

    Map<Integer, List<User>>  userListMap = new HashMap<>();

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
    }

    private void test() {
        XWPFDocument xwpfDocument = new XWPFDocument();
        for (Map.Entry<Integer, List<User>> entry : userListMap.entrySet()) {
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            xwpfParagraph.setAlignment(ParagraphAlignment.LEFT);
            xwpfParagraph.setFirstLineIndent(420);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
