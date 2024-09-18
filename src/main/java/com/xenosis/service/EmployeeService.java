package com.xenosis.service;

import com.xenosis.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Creates a new employee.
     *
     * @param employee The Employee object to be created
     * @return The created Employee object
     */

    Employee createEmployee(Employee employee);

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve
     * @return The Employee object corresponding to the given ID, or null if not found
     */

    Employee retrieveEmployeeById(Integer id);

    /**
     * Deletes an employee by their ID.
     *
     * @param id The ID of the employee to delete
     */

    void deleteEmployeeById(Integer id);

    /**
     * Updates an existing employee's details by their ID.
     *
     * @param id              The ID of the employee to update
     * @param updatedEmployee The Employee object containing updated details
     * @return The updated Employee object
     */

    Employee updateEmployeeById(Integer id, Employee updatedEmployee);

    /**
     * Retrieves all employees.
     *
     * @return A list of all Employee objects
     */

    List<Employee> retrieveAllEmployees();

}
