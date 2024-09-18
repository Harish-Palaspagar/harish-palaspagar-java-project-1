package com.xenosis.entity;

import com.xenosis.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for employee attendance report.
 * Represents an employee's attendance details, including ID, name, and attendance days.
 */

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(description = "Employee entity representing the employee details")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the employee", example = "1", requiredMode = Schema.RequiredMode.AUTO)
    private Integer id;

    @NotBlank(message = "Firstname is required !!")
    @Schema(description = "First name of the employee", example = "Harish", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstname;

    @NotBlank(message = "Lastname is required !!")
    @Schema(description = "Last name of the employee", example = "Palaspagar", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastname;

    @Email(message = "Email is not valid !!")
    @NotBlank(message = "Email is required !!")
    @Column(unique = true)
    @Schema(description = "Unique email of the employee", example = "harishpalaspagar4@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must contain at least 8 characters, including one digit, one lowercase letter, and one uppercase letter !!")
    @NotBlank(message = "Password is required !!")
    @Schema(description = "Password for employee authentication", example = "Password@123", requiredMode = Schema.RequiredMode.AUTO)
    private String password;

    @Past(message = "Date of Birth must be in the past !!")
    @NotNull(message = "Date of Birth is required !!")
    @Schema(description = "Employee's date of birth", example = "1990-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Department is required !!")
    @Schema(description = "Department of the employee", example = "HR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String department;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero !!")
    @Column(precision = 10, scale = 2)
    @Schema(description = "Salary of the employee", example = "50000.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal salary;

    @Min(value = 0, message = "Attendance days cannot be negative !!")
    @Max(value = 31, message = "Attendance days cannot exceed 31 !!")
    @NotNull(message = "Attendance days are required !!")
    @Schema(description = "Number of attendance days for the employee in the current month", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer attendanceDays;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role")
    @NotEmpty(message = "At least one role is required !!")
    @Schema(description = "Roles assigned to the employee", example = "[\"USER\", \"ADMIN\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
