package com.employeeManagment.EmployeeManagmentWebsite.controller;

import com.employeeManagment.EmployeeManagmentWebsite.model.Employee;
import com.employeeManagment.EmployeeManagmentWebsite.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @RequestMapping(value = {"","/","/home"})
    public ModelAndView testing()
    {
        ModelAndView mavc = new ModelAndView("list-employees");
        mavc.addObject("employees",employeeRepository.findAll());
        return mavc;
    }

    @RequestMapping(value = {"/addEmployee"})
    public ModelAndView addEmployee()
    {
        ModelAndView mavc = new ModelAndView("add-employee");
        mavc.addObject("employee",new Employee());
        return mavc;
    }

    @PostMapping(value = "/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee)
    {
        if (employee.getEmail()!=null) {
            employeeRepository.save(employee);
        }
        else
        {
            System.out.println("No data entered");
        }
        return "redirect:/home";
    }

    @GetMapping("/updateEmployee")
    public ModelAndView updateEmployee(@RequestParam(name = "employeeId") Integer empid)
    {
        ModelAndView mavc = new ModelAndView("add-employee");
        mavc.addObject("employee",employeeRepository.findById(empid).get());
        return mavc;
    }
    @GetMapping("/deleteEmployee/{empid}")
    public String deleteEmployee(@PathVariable(name = "empid") Integer empid)
    {
        employeeRepository.deleteById(empid);
        return "redirect:/home";
    }
}
