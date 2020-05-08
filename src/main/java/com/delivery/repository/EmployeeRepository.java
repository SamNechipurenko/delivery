package com.delivery.repository;

import com.delivery.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeesByRole(String role);
    //Employee findFirstByEmployeeByRoleAndEngagedFalse(String role);
    Employee findFirstByRoleAndEngagedFalse(String role);
    List<Employee> findEmployeesByRoleInAndEngagedFalse(List<String> stringList);

}
