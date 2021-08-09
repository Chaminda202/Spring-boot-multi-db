package com.spring.multi.db.service.base;

import com.spring.multi.db.model.base.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> findAll();
}
