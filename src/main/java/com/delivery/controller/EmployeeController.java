package com.delivery.controller;

import com.delivery.exception.CostumedException;
import com.delivery.model.Employee;
import com.delivery.service.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    Employee employee;

    //поиск
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> main(Model mav, @PathVariable String id) throws CostumedException {
        employee = employeeService.findEmployeeById(Integer.parseInt(id));
        if (employee!=null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        throw new CostumedException(" Employee not found ", 404);
    }

    // удаление
    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> delete(Model mav,
                                                   @PathVariable String id) throws CostumedException {
        boolean deleted = employeeService.delete(Integer.parseInt(id));
        if (deleted) {
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new CostumedException(" Employee not found ", 404);
    }



    // изменить данные
    @PutMapping(path = "/employee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> editEngagement(Model mav,
                                                   @PathVariable String id,
                                                   @RequestBody Employee employee) throws CostumedException {
        if (employee!=null) {
            employee = employeeService.edit(Integer.parseInt(id), employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        throw new CostumedException(" Employee not found ", 404);
    }

    // добавить нового сотрудника
    @PostMapping(path = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> addEmployee (Model mav,
                                                 @NotNull @RequestBody Employee employee) throws CostumedException {
        if (employee.getName() != null && employee.getRole() != null
                                            && employee.getSalary() != 0) {
            employee = employeeService.create(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        throw new CostumedException(" Bad request ", 403);

    }

}
