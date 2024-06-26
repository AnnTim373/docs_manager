package org.timofeeva.docs.dto;

import lombok.Data;

import java.util.Set;


@Data
public class DepartmentView {

    private Long id;

    private String name;

    private String contacts;

    private EmployeeView headEmployee;

    private Set<EmployeeView> employees;

}
