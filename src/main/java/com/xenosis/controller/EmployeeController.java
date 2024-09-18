package com.xenosis.controller;

import com.xenosis.entity.Employee;
import com.xenosis.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Creates a new employee.
     *
     * @param employee Employee object containing details to be added
     * @return ResponseEntity containing the created Employee object and HTTP status 201 (CREATED)
     */

    @PostMapping
    @Operation(summary = "Create a new employee",
            description = "Adds a new employee to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - Duplicate email address",
                    content = @Content)
    })
    public ResponseEntity<Employee> saveEmployeeDetails(@Valid @RequestBody Employee employee) {

        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    /**
     * Retrieves all employees.
     *
     * @return ResponseEntity containing a list of employees and HTTP status 200 (OK) or 204 (NO CONTENT) if no employees are found
     */

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Retrieve all employees",
            description = "Fetch a list of all employees.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees",
            content = @Content(schema = @Schema(implementation = Employee.class)))
    @ApiResponse(responseCode = "204", description = "No employees found")
    public ResponseEntity<List<Employee>> retrieveAllEmployeesPageable() {

        List<Employee> employees = employeeService.retrieveAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }

    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve
     * @return ResponseEntity containing the Employee object and HTTP status 200 (OK) or 404 (NOT FOUND) if the employee is not found
     */

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Operation(summary = "Retrieve employee by ID",
            description = "Fetch the details of an employee using their ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employee",
            content = @Content(schema = @Schema(implementation = Employee.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<Employee> retrieveEmployeeById(@PathVariable Integer id) {

        Employee employee = employeeService.retrieveEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);

    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete
     * @return ResponseEntity containing a success message and HTTP status 200 (OK)
     */

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete employee by ID",
            description = "Remove an employee's record using their ID.")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully !!", HttpStatus.OK);

    }

    /**
     * Updates employee details by ID.
     *
     * @param id                     The ID of the employee to update
     * @param updatedEmployeeDetails Employee object containing updated details
     * @return ResponseEntity containing the updated Employee object and HTTP status 200 (OK)
     */

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update employee details",
            description = "Modify the details of an existing employee using their ID.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully",
            content = @Content(schema = @Schema(implementation = Employee.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<Employee> updateEmployeeDetailsById
    (@PathVariable Integer id, @Valid @RequestBody Employee updatedEmployeeDetails) {

        Employee updatedEmployee = employeeService.updateEmployeeById(id, updatedEmployeeDetails);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    }


}
