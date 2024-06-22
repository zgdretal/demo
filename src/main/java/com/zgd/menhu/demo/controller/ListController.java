package com.zgd.menhu.demo.controller;

import com.zgd.menhu.demo.dao.UserMapper;
import com.zgd.menhu.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ListController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView();
        List<UserModel> userModelList = userMapper.getUserList("");

        mv.addObject("infoList", userModelList);
        mv.addObject("totalCount", userModelList.size());
        mv.setViewName("/info-list");
        return mv;
    }

    @GetMapping("/userDetail")
    public ModelAndView userDetail(Integer id, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest) throws Exception {

        ModelAndView mv = new ModelAndView();
        UserModel userModel = userMapper.getUserById(id);
        mv.addObject("user", userModel);
        mv.setViewName("/user");
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        List<UserModel> userModelList = userMapper.getUserList("");

        mv.addObject("infoList", userModelList);
        mv.setViewName("/user/add");
        return mv;
    }

    @GetMapping("/addUser")
    public void addUser(String name, String phone, Integer groupId, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone)) {
            throw new RuntimeException("存在空值");
        }
        userMapper.addUser(name, phone, groupId);
        mv.addObject("cc", 1);
        httpResponse.sendRedirect("http://localhost:8080/list");
    }

    @GetMapping("/deleteUser")
    public void deleteUser(String id, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest) throws Exception {
        userMapper.deleteUserById(id);
        httpResponse.sendRedirect("http://localhost:8080/list");
    }

    @GetMapping("/searchUser")
    public ModelAndView searchUser(String search, HttpServletResponse httpResponse, HttpServletRequest httpServletRequest) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<UserModel> userModelList = userMapper.search(search);

        mv.addObject("infoList", userModelList);
        mv.addObject("totalCount", userModelList.size());
        mv.setViewName("/info-list");
        return mv;
    }

    public static void main(String[] args) {

    }

}
