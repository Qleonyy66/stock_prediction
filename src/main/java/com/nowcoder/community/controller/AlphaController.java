package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author ftxcn
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //huoqu qingqiu shuju
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getServletPath() = " + request.getServletPath());
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // gei liulanqi fanhui xiangying shuju
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>PlatForm for final design</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get qingqiu de chuli
    //yongyu huoqu mouxie shuju

    //查询所有学生 /students?curpage=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //查询一个学生 by id
    // /student/123
    @RequestMapping(path = "student/{idd}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @PathVariable("idd") int id
    ) {
        System.out.println(id);
        return "a student";
    }


    //Post shili

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //xiangying dongtai html

    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getteacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "zhangsan");
        mav.addObject("age", 40);
        mav.setViewName("/demo/view");
        return mav;
    }

    //chaxun xuexiao
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "zhangsansansan");
        model.addAttribute("age", 44);

        return "/demo/view";
    }

    //响应json数据
    //用于异步请求之中
    //当前网页不刷新，访问服务器
    //java对象 -> json字符串 -> JS对象 （跨语言）
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getemp() {
        Map<String, Object> emp = new HashMap<>();

        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary", 800.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getemps() {
        List<Map<String, Object>> emps = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();

        emp.put("name", "zhangsan");
        emp.put("age", 23);
        emp.put("salary", 800.00);
        emps.add(emp);

        emp = new HashMap<>();

        emp.put("name", "zhangsan1");
        emp.put("age", 231);
        emp.put("salary", 8001.00);
        emps.add(emp);
        emp = new HashMap<>();

        emp.put("name", "zhangsan2");
        emp.put("age", 232);
        emp.put("salary", 8002.00);
        emps.add(emp);

        return emps;
    }

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());

        cookie.setPath("/community/alpha");

        cookie.setMaxAge(60 * 10);

        response.addCookie(cookie);

        return "set cookie";

    }


    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getcookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }


    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "setSession";
    }

    @RequestMapping(path= "/session/get",method =RequestMethod.GET)
    @ResponseBody
    public String getSession (HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "getSession";

    }

}
