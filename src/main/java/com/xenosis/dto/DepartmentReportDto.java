package com.xenosis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for department report with total employees.
 * This class represents a report for a specific department,
 * including the department name and the total number of employees.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for department report with total employees")
public class DepartmentReportDto {

    @Schema(description = "Name of the department", example = "HR")
    private String department;

    @Schema(description = "Total number of employees in the department", example = "100")
    private Long totalEmployees;

}
