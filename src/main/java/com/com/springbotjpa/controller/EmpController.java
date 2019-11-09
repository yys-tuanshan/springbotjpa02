package com.com.springbotjpa.controller;

import com.com.springbotjpa.bean.Emp;
import com.com.springbotjpa.dao.DeptDao;
import com.com.springbotjpa.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmpController {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private DeptDao deptDao;



    //登录
    @RequestMapping("login")
    public String login(Integer eid,String ename,Model model, HttpSession session){
        Emp byEidAndEname = empDao.findByEidAndEname(eid, ename);
        if (byEidAndEname!=null){
            session.setAttribute("activeUser",byEidAndEname);
            return "redirect:showAllInfo";
        }else{
            model.addAttribute("msg","登录失败");
            return "login";
        }

    }
    //展示所有信息
    @RequestMapping("showAllInfo")
    public String showAllInfo(Model model ,@RequestParam(name = "cp",defaultValue ="0" ) Integer currentPage){
      // Page<Emp> pages =  empDao.showAllInfo(currentPage);
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"eid");
        Sort sort = Sort.by(order);
        PageRequest pageRequest = PageRequest.of(currentPage,1,sort);
        Page<Emp> page  = empDao.findAll(pageRequest);
        model.addAttribute("pageInfo",page);

        model.addAttribute("list",page.getContent());
        return "show";

    }
    //根据eid删除信息
    @GetMapping("deleteInfo")
    public String deleteInfo(Integer eid){
        empDao.deleteById(eid);
        return "redirect:showAllInfo";
    }
    //更新时回显
    @GetMapping("updateInfo")
    public String updateInfo(Integer eid, HttpSession session){
        Emp emp = empDao.findByEid(eid);
        session.setAttribute("oneInfo",emp);
        session.setAttribute("depts",deptDao.findDeptByDeptnoIsNot(emp.getDept().getDeptno()));
        return "edit";
    }
    //更新
    @PostMapping("updateByEid")
    public String updateByEid(Emp emp){
        empDao.save(emp);
        return "redirect:showAllInfo";
    }




}
