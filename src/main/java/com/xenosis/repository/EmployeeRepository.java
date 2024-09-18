package com.xenosis.repository;

import com.xenosis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Find an employee by their email address.
     *
     * @param email the email of the employee.
     * @return an Optional containing the found employee, or empty if not found.
     */

    Optional<Employee> findByEmail(String email);

    /**
     * Retrieve a report of employee count grouped by department.
     *
     * @return a list of objects containing department names and their respective employee counts.
     */

    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getDepartmentWiseReport();

}
