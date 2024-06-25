package org.timofeeva.docs.dto;

import lombok.Data;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;
import org.timofeeva.docs.validator.Unique;


@Data
public class DepartmentDTO {

    @Reference(entity = Department.class)
    private Long id;

    @NotNull
    @Unique(entity = Department.class, fieldName = "name")
    private String name;

    private String contacts;

    @Reference(entity = Employee.class)
    private Long headEmployee;

    @NotNull
    @Reference(entity = Organization.class)
    private Long organization;

}
