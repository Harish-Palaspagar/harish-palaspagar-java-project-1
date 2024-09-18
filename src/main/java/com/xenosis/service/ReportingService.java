package com.xenosis.service;

import com.xenosis.dto.AttendanceReportDto;
import com.xenosis.dto.SalaryReportDto;

import java.util.List;
import java.util.Map;

public interface ReportingService {

    /**
     * Generates an attendance report for all employees.
     *
     * @return A list of AttendanceReportDto containing attendance details
     */

    List<AttendanceReportDto> generateAttendanceReport();

    /**
     * Generates a salary report for all employees.
     *
     * @return A list of SalaryReportDto containing salary details
     */

    List<SalaryReportDto> generateSalaryReport();

    /**
     * Retrieves the count of employees grouped by department.
     *
     * @return A list of maps where each map contains department details and their corresponding employee count
     */

    List<Map<String, Object>> getDepartmentEmployeeCount();

}
