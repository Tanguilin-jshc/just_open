package com.tgl.restfulcruddemo.controller;

import com.tgl.restfulcruddemo.dao.DepartmentDao;
import com.tgl.restfulcruddemo.dao.EmployeeDao;
import com.tgl.restfulcruddemo.utill.Department;
import com.tgl.restfulcruddemo.utill.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmploeeController {

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(value="/emps")
    public String list(Model model){
        Collection<Employee> employees=employeeDao.getEmp();
        model.addAttribute("emps",employees);
        //thumeleaf默认就会拼串,会从类路径下classpath:/template/xxx.html下拼接
        return "emp/list";
    }

    @Autowired
    DepartmentDao departmentDao;
    //来到添加页面
    @GetMapping("/emp")
    public String addPage(Model model){
        //添加页面,查出所有的部门在页面显示
        Collection<Department> depts=departmentDao.getDept();
        model.addAttribute("depts",depts);
        return "emp/addpage";
    }

    //员工添加
    //springmvc自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javabean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        //来到员工添加列表页面
        //redurect 表示重定向到一个地址 forward 表示转发到一个地址 /代表当前项目路径
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String editEmp(@PathVariable("id") Integer id,Model model){
        Employee employee=employeeDao.getEmpById(id);
        model.addAttribute("emp",employee);

        //查出部门,页面要显示所有的部门列表
        Collection<Department> depts=departmentDao.getDept();
        model.addAttribute("depts",depts);

        //回到修改页面(add是一个修改添加二合一的页面）
        return "emp/addpage";
    }

    //员工修改
    @PutMapping("/emp")
    public String UpdateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(){
        //employeeDao.deleteById(id);
        return "redirect:/emps";
    }
}
