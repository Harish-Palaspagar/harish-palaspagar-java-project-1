package com.xenosis.implementation;

import com.xenosis.dto.AttendanceReportDto;
import com.xenosis.dto.SalaryReportDto;
import com.xenosis.repository.EmployeeRepository;
import com.xenosis.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the ReportingService interface, providing methods
 * to generate reports related to employee attendance and salary.
 */

@Service
public class ReportingServiceImplementation implements ReportingService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor for ReportingServiceImplementation.
     *
     * @param employeeRepository the repository to access employee data.
     */

    public ReportingServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Generates a report of employee attendance.
     *
     * @return a list of AttendanceReportDto containing employee attendance details.
     */

    @Override
    public List<AttendanceReportDto> generateAttendanceReport() {

        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getAttendanceDays() != null && employee.getAttendanceDays() > 0)
                .map(employee -> new AttendanceReportDto(
                        employee.getId(),
                        employee.getFirstname() + " " + employee.getLastname(),
                        employee.getAttendanceDays()
                ))
                .collect(Collectors.toList());

    }

    /**
     * Generates a report of employee salaries.
     *
     * @return a list of SalaryReportDto containing employee salary details.
     */

    @Override
    public List<SalaryReportDto> generateSalaryReport() {

        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getSalary() != null && employee.getSalary().compareTo(BigDecimal.ZERO) > 0)
                .map(employee -> new SalaryReportDto(
                        employee.getId(),
                        employee.getFirstname() + " " + employee.getLastname(),
                        employee.getSalary()
                ))
                .collect(Collectors.toList());

    }

    /**
     * Retrieves the count of employees in each department.
     *
     * @return a list of maps containing department names and their employee counts.
     */

    @Override
    public List<Map<String, Object>> getDepartmentEmployeeCount() {

        List<Object[]> rawResult = employeeRepository.getDepartmentWiseReport();

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : rawResult) {
            Map<String, Object> departmentData = new HashMap<>();
            departmentData.put("departmentName", row[0]);
            departmentData.put("employeeCount", row[1]);
            response.add(departmentData);
        }

        return response;
    }

}
