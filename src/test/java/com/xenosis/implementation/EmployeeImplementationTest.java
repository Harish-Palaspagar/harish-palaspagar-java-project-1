package com.xenosis.implementation;


import com.xenosis.entity.Employee;
import com.xenosis.exception.EmployeeNotFoundException;
import com.xenosis.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class EmployeeImplementationTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImplementation employeeService;

    private Employee employee1;

    private Employee employee2;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstname("Harish");
        employee1.setLastname("Palaspagar");
        employee1.setEmail("harish@gmail.com");
        employee1.setPassword("Harish@123");
        employee1.setDepartment("IT");

        employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstname("Rahul");
        employee2.setLastname("Kumar");
        employee2.setEmail("rahul@gmail.com");
        employee2.setPassword("Rahul@123");
        employee2.setDepartment("HR");

    }

    /* Test for retrieving all employees */

    @Test
    void testRetrieveAllEmployees() {

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee1, employee2));
        List<Employee> employees = employeeService.retrieveAllEmployees();
        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals(employee1.getEmail(), employees.get(0).getEmail());
        assertEquals(employee2.getEmail(), employees.get(1).getEmail());
        verify(employeeRepository, times(1)).findAll();

    }

    /* Test for retrieving an employee by ID (successful case) */

    @Test
    void testRetrieveEmployeeByIdSuccess() {

        when(employeeRepository
                .findById(1)).thenReturn(Optional.of(employee1));
        Employee foundEmployee = employeeService.retrieveEmployeeById(1);
        assertNotNull(foundEmployee);
        assertEquals(employee1.getEmail(), foundEmployee.getEmail());
        verify(employeeRepository, times(1)).findById(1);

    }

    /* Test for retrieving an employee by ID (not found case) */

    @Test
    void testRetrieveEmployeeByIdNotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());
        EmployeeNotFoundException exception =
                assertThrows(EmployeeNotFoundException.class, () -> employeeService.retrieveEmployeeById(1));

        assertEquals("Employee not found with id : 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1);

    }

    /* Test for updating an employee by ID (not found case) */

    @Test
    void testUpdateEmployeeByIdNotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());
        EmployeeNotFoundException exception =
                assertThrows(EmployeeNotFoundException.class,
                        () -> employeeService.updateEmployeeById(1, employee1));
        assertEquals("Employee not found with id : 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1);

    }

    /* Test for deleting an employee by ID (successful case) */

    @Test
    void testDeleteEmployeeByIdSuccess() {

        when(employeeRepository.existsById(1)).thenReturn(true);
        assertDoesNotThrow(() -> employeeService.deleteEmployeeById(1));
        verify(employeeRepository, times(1)).deleteById(1);

    }

    /* Test for deleting an employee by ID (not found case) */

    @Test
    void testDeleteEmployeeByIdNotFound() {

        when(employeeRepository.existsById(1))
                .thenReturn(false);
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployeeById(1));
        assertEquals("Employee not found with id : 1", exception.getMessage());
        verify(employeeRepository, never()).deleteById(anyInt());

    }
}
