package org.timofeeva.docs.dto;

import lombok.Data;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;

@Data
public class EmployeeDTO {

    @Reference(entity = Employee.class)
    private Long id;

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String position;

    @NotNull
    @Reference(entity = Department.class)
    private Long departmentId;

}
