package org.timofeeva.docs.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrganizationView {

    private Long id;

    private String name;

    private String legalAddress;

    private String actualAddress;

    private EmployeeView headEmployee;

    private Set<DepartmentView> departments;

}
