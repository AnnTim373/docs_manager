package org.timofeeva.docs.dto;

import lombok.Data;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;
import org.timofeeva.docs.validator.Unique;

@Data
public class OrganizationDTO {

    @Reference(entity = Organization.class)
    private Long id;

    @NotNull
    @Unique(entity = Organization.class, fieldName = "name")
    private String name;

    @NotNull
    private String legalAddress;

    private String actualAddress;

    @Reference(entity = Employee.class)
    private Long headEmployee;

}
