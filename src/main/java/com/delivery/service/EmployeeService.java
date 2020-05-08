package com.delivery.service;

import com.delivery.model.Employee;
import com.delivery.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(){
    }

    // редактировать сотрудника
    public Employee edit(int id, Employee employee){
         Employee newEmployee = findEmployeeById(id);
         employee.setEmployeeId(newEmployee.getEmployeeId());
         employeeRepository.save(employee);
         return employee;
    }

    // удаление
    public boolean delete(int id){
        Employee employee = findEmployeeById(id);
        boolean deleted;
        if (employee!=null) {
            employeeRepository.delete(employee);
            return true;
        }
        return false;
    }

    // поиск сотрудника
    public Employee findEmployeeById(int id){
        return employeeRepository.findById(id).get();
    }

    // список сотрудников по должности
    public List<Employee> findEmployeesByRole(String role){
        return employeeRepository.findEmployeesByRole(role);
    }

    // создать сотрудника
    public Employee create(Employee employee){
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setRole(employee.getRole());
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setEngaged(false);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }


}
