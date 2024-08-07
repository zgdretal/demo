package com.zgd.menhu.demo.controller;

import com.zgd.menhu.demo.dao.UserMapper;
import com.zgd.menhu.demo.model.UserModel;
import com.zgd.menhu.demo.service.TranserInfo;
import com.zgd.menhu.demo.service.TransferType;
import com.zgd.menhu.demo.service.User;
import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.*;

@RestController
//@RequestMapping("/web")
public class DemoTestController {

    @Autowired
    private UserMapper userMapper;

    private Map<Integer, User> userMaps = new HashMap<>();

    @RequestMapping("/test")
    public String test() {
        UserModel userModel = userMapper.getUser("test");
        return "陈芳";
    }

    @RequestMapping("/admin/login")
    @CrossOrigin
    public Map<String ,Object> login(UserModel userModel) {
        Map<String ,Object> map = new HashMap<>();
        try {
            InetAddress addr = InetAddress.getLocalHost();
            /*System.out.println("Local HostAddress:"  +
                    addr.getHostAddress() + "name" +
                    addr.getHostName());*/
        } catch (Exception e) {
        }

        //System.out.println("Local host name: "+hostname);
        if (userModel == null || StringUtils.isEmpty(userModel.getName())) {
            map.put("msg", "请输入用户名");
            return map;
        }

        UserModel user = userMapper.getUser(userModel.getName());

        if (user == null) {
            map.put("msg", "该用户还没注册，请先完成注册申请");
            map.put("code", 10032);
            return map;
        }
        if (!user.getPassword().equals(userModel.getPassword())) {
            map.put("msg", "密码错误");
            map.put("code", 10032);
            return map;
        }

        map.put("code", 200);
        map.put("msg", "成功");
        return map;
    }

    @RequestMapping("/admin/register")
    @CrossOrigin
    public Map<String ,Object> register(UserModel userModel) {

        Map<String ,Object> map = new HashMap<>();
        if (userModel == null || StringUtils.isEmpty(userModel.getName())) {
            map.put("msg", "请输入用户名");
            return map;
        }

        if (userModel == null || StringUtils.isEmpty(userModel.getPassword())) {
            map.put("msg", "请输入密码");
            return map;
        }

        UserModel user = userMapper.getUser(userModel.getName());
        if (user != null) {
            map.put("msg", "该用户名已被注册");
            return map;
        }
        int res = userMapper.registerUser(userModel.getName(), userModel.getPassword());
        map.put("code", 200);
        map.put("msg", "成功");
        return map;
    }


    @RequestMapping("/admin/logout")
    @CrossOrigin
    public Map<String ,Object> logout(UserModel userModel) {
        Map<String ,Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "成功");
        return map;
    }

    @RequestMapping("/config")
    public String config(@RequestParam("num") Integer num) {
        for (int i = 1; i <= num; i++) {
            User user = new User();
            user.setUid(i);
            user.setUserName("玩家" + i);
            user.setAccount(100);
            userMaps.put(i, user);
        }
        return "ok";
    }

    @RequestMapping("/count")
    public Object count(@RequestParam("uid") Integer uid, @RequestParam("targetUid") Integer targetUid,
                        @RequestParam("uuid") String uuid, @RequestParam("num") Integer num,
                        HttpServletResponse httpServletResponse) {
        User user = userMaps.get(uid);
        User targetUser = userMaps.get(targetUid);
        List<TranserInfo> transerInfoList = user.getTranserInfoList();
        List<TranserInfo> targetTranserInfoList = targetUser.getTranserInfoList();
        iteratorRemove(transerInfoList, uuid);
        iteratorRemove(targetTranserInfoList, uuid);
        user.setAccount(user.getAccount() - num);
        targetUser.setAccount(targetUser.getAccount() + num);

        try {

            httpServletResponse.sendRedirect("/web/index?id=" + uid);
        } catch (Exception e) {

        }
        return index(uid);
    }

    public boolean iteratorRemove(List<TranserInfo> list, String element) {
        Iterator<TranserInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            TranserInfo cur = iterator.next();
            if (cur.getId().equals(element)) {
                // 注意！！！这里时Iterator.remove()!!!而不是list.remove()!!!
                iterator.remove();
            }
        }
        return true;
    }

    @RequestMapping("/sumNum")
    public Object sumNum(@RequestParam("sourceUid") Integer sourceUid, @RequestParam("uid") Integer uid,
                         @RequestParam("num") Integer num, HttpServletResponse httpServletResponse) {

        if (num == null) {
            throw new RuntimeException("请输入收款金额");
        }

        String uuid = UUID.randomUUID().toString();
        //1.收款信息存储
        User user = userMaps.get(sourceUid);
        TranserInfo transerInfo = new TranserInfo();
        transerInfo.setId(uuid);
        transerInfo.setTargetNum(num);
        transerInfo.setTargetUserId(uid);

        transerInfo.setTargetUserName(userMaps.get(uid).getUserName());
        transerInfo.setType(TransferType.SHOUKUAN.getTypeId());
        List<TranserInfo> transerInfoList = user.getTranserInfoList();
        if (transerInfoList == null) {
            List<TranserInfo> newTranserInfoList = new ArrayList<>();
            newTranserInfoList.add(transerInfo);
            user.setTranserInfoList(newTranserInfoList);
        } else {
            transerInfoList.add(transerInfo);
        }
        //2.付款方信息添加

        User targetUser = userMaps.get(uid);
        TranserInfo targetTranserInfo = new TranserInfo();
        targetTranserInfo.setId(uuid);
        targetTranserInfo.setTargetNum(num);
        targetTranserInfo.setTargetUserId(sourceUid);
        targetTranserInfo.setTargetUserName(userMaps.get(sourceUid).getUserName());
        targetTranserInfo.setType(TransferType.PAY.getTypeId());
        List<TranserInfo> targetTranserInfoList = targetUser.getTranserInfoList();

        if (targetTranserInfoList == null) {
            List<TranserInfo> newTranserInfoList = new ArrayList<>();
            newTranserInfoList.add(targetTranserInfo);
            targetUser.setTranserInfoList(newTranserInfoList);
        } else {
            targetTranserInfoList.add(targetTranserInfo);
        }

        try {

            httpServletResponse.sendRedirect("/web/index?id=" + sourceUid);
        } catch (Exception e) {

        }
        return index(sourceUid);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam("id") Integer id) {
        ModelAndView result = new ModelAndView();
        List<User> userList = new ArrayList<>();
        User ownerUser = null;
        for (Map.Entry<Integer, User> entry : userMaps.entrySet()) {
            if (entry.getKey().intValue() == id) {
                ownerUser = entry.getValue();
                result.addObject("user", entry.getValue());
                continue;
            }
            userList.add(entry.getValue());
        }

        if (userList.size() == userMaps.size()) {
            throw new RuntimeException("身份信息错误");
        }
        List<TranserInfo> transerInfoList = ownerUser.getTranserInfoList();

        List<TranserInfo> payTranserInfoList = new ArrayList<>();

        List<TranserInfo> shoukuanTranserInfoList = new ArrayList<>();

        if (transerInfoList != null) {
            for (TranserInfo transerInfo : transerInfoList) {
                if (transerInfo.getType().intValue() == TransferType.PAY.getTypeId()) {
                    payTranserInfoList.add(transerInfo);
                }

                if (transerInfo.getType().intValue() == TransferType.SHOUKUAN.getTypeId()) {
                    shoukuanTranserInfoList.add(transerInfo);
                }
            }
        }

        result.addObject("payTranserInfoList", payTranserInfoList);
        result.addObject("shoukuanTranserInfoList", shoukuanTranserInfoList);

        result.setViewName("index");
        result.addObject("userList", userList);
        return result;
    }

    @RequestMapping("/index111")
    public void index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception{
        UserModel userModel = userMapper.getUser("");
        ModelAndView modelAndView = new ModelAndView("/index222");
        modelAndView.addObject("name11", "zgdaaaaa");
        modelAndView.addObject("user", userModel);
        request.setAttribute("name11", "name11");
        request.setAttribute("user", userModel);
        request.getRequestDispatcher("/").forward(request, response);
    }

    @RequestMapping("/index222")
    public ModelAndView index222(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception{
        UserModel userModel = userMapper.getUser("");
        ModelAndView modelAndView = new ModelAndView("/indesx.jsp");
        modelAndView.addObject("name11", "zgdaaaaa");
        modelAndView.addObject("user", userModel);
        return modelAndView;
    }
}
