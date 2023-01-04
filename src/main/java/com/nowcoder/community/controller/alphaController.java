package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
@RequestMapping("/alpha")
public class alphaController {

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
            writer.write("<h1>niukewang</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get qingqiu de chuli
    //yongyu huoqu mouxie shuju

    //查询所有学生 /students?curpage=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name="limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //查询一个学生 by id
    // /student/123
    @RequestMapping(path = "student/{idd}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @PathVariable("idd") int id
    ){
        System.out.println(id);
        return "a student";
    }


    //Post shili

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //xiangying dongtai html

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getteacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","zhangsan");
        mav.addObject("age",40);
        mav.setViewName("/demo/view");
        return mav;
    }

    //chaxun xuexiao
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","zhangsansansan");
        model.addAttribute("age",44);

        return "/demo/view";
    }
}
