package com.employeeManagment.EmployeeManagmentWebsite.repository;

import com.employeeManagment.EmployeeManagmentWebsite.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
