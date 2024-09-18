package com.xenosis.implementation;

import com.xenosis.entity.Employee;
import com.xenosis.exception.DuplicateEmailException;
import com.xenosis.exception.EmployeeNotFoundException;
import com.xenosis.repository.EmployeeRepository;
import com.xenosis.service.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the EmployeeService interface, providing CRUD operations for Employee entities.
 */

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for EmployeeServiceImplementation.
     *
     * @param employeeRepository the repository to access employee data.
     * @param passwordEncoder    the encoder for encoding passwords.
     */

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new employee and encodes the password.
     *
     * @param employee the employee to be created.
     * @return the created employee.
     * @throws DuplicateEmailException if the email address already exists.
     */

    @Override
    public Employee createEmployee(Employee employee) {

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEmailException
                    ("Email address already exists : " + employee.getEmail());
        }

    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return the found employee.
     * @throws EmployeeNotFoundException if the employee is not found.
     */

    @Override
    public Employee retrieveEmployeeById(Integer id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id : " + id));

    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id the ID of the employee to delete.
     * @throws EmployeeNotFoundException if the employee is not found.
     */

    @Override
    public void deleteEmployeeById(Integer id) {

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id : " + id);
        }
        employeeRepository.deleteById(id);

    }

    /**
     * Updates an existing employee's details.
     *
     * @param id              the ID of the employee to update.
     * @param updatedEmployee the updated employee data.
     * @return the updated employee.
     * @throws EmployeeNotFoundException if the employee is not found.
     * @throws DuplicateEmailException   if the email address already exists.
     */

    @Override
    public Employee updateEmployeeById(Integer id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));

        employee.setFirstname(updatedEmployee.getFirstname());
        employee.setLastname(updatedEmployee.getLastname());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setAttendanceDays(updatedEmployee.getAttendanceDays());
        employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        employee.setRoles(updatedEmployee.getRoles());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));

        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEmailException("Email address already exists : " + updatedEmployee.getEmail());
        }

    }

    /**
     * Retrieves all employees from the repository.
     *
     * @return a list of all employees.
     */

    @Override
    public List<Employee> retrieveAllEmployees() {

        return employeeRepository.findAll();
    }

}
