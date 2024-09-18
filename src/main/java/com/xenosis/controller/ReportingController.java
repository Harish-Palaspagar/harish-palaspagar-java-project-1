package com.xenosis.controller;

import com.xenosis.dto.AttendanceReportDto;
import com.xenosis.dto.DepartmentReportDto;
import com.xenosis.dto.SalaryReportDto;
import com.xenosis.service.ReportingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    /**
     * Retrieves the attendance report for the current period.
     *
     * @return ResponseEntity containing a list of AttendanceReportDto and HTTP status code
     */

    @GetMapping("attendance")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get attendance report",
            description = "Fetch the report of employee attendance for the current period.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched the attendance report",
            content = @Content(schema = @Schema(implementation = AttendanceReportDto.class)))
    public ResponseEntity<List<AttendanceReportDto>> getAttendanceReport() {
        List<AttendanceReportDto> report = reportingService.generateAttendanceReport();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /**
     * Retrieves the salary report for the current period.
     *
     * @return ResponseEntity containing a list of SalaryReportDto and HTTP status code
     */

    @GetMapping("salary")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get salary report",
            description = "Fetch the report of employee salaries for the current period.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched the salary report",
            content = @Content(schema = @Schema(implementation = SalaryReportDto.class)))
    public ResponseEntity<List<SalaryReportDto>> getSalaryReport() {
        List<SalaryReportDto> report = reportingService.generateSalaryReport();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /**
     * Retrieves the report of employees and department wise employee count.
     *
     * @return ResponseEntity containing a list of maps with department data and HTTP status code
     */

    @GetMapping("department")
    @Operation(summary = "Get department report",
            description = "Fetch the report of employees and their employee count per department")
    @ApiResponse(responseCode = "200", description = "Successfully fetched the report",
            content = @Content(schema = @Schema(implementation = DepartmentReportDto.class)))
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getDepartmentEmployeeCount() {

        List<Map<String, Object>> response = reportingService.getDepartmentEmployeeCount();
        return ResponseEntity.ok(response);

    }

}

