package com.xenosis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for employee attendance report.
 * This class represents a report for an employee's attendance,
 * including the employee's unique identifier, name, and the
 * number of attendance days.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for employee attendance report")
public class AttendanceReportDto {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Integer employeeId;

    @Schema(description = "Name of the employee", example = "Harish Palaspagar")
    private String employeeName;

    @Schema(description = "Number of attendance days for the employee", example = "20")
    private Integer attendanceDays;

}
