package com.xenosis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO for employee salary report.
 * This class is used to transfer salary report data between layers.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for employee salary report")
public class SalaryReportDto {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Integer employeeId;

    @Schema(description = "Name of the employee", example = "Harish Palaspagar")
    private String employeeName;

    @Schema(description = "Salary of the employee", example = "75000.00")
    private BigDecimal salary;

}
