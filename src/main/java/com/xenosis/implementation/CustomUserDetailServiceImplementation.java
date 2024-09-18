package com.xenosis.implementation;

import com.xenosis.entity.Employee;
import com.xenosis.exception.EmployeeNotFoundException;
import com.xenosis.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of UserDetailsService for loading user-specific data.
 */

@Service
public class CustomUserDetailServiceImplementation implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor for CustomUserDetailServiceImplementation.
     *
     * @param employeeRepository the repository to access employee data.
     */

    public CustomUserDetailServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Loads user details by username (email).
     *
     * @param username the email of the user to be loaded.
     * @return UserDetails object containing employee information.
     * @throws UsernameNotFoundException if the employee is not found.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmail(username).orElse(null);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found !!");
        }

        return employeeRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email : " + username));
    }

}
