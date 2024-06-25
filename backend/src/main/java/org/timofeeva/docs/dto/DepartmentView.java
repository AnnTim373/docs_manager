package org.timofeeva.docs.dto;

import lombok.Data;


@Data
public class DepartmentView {

    private Long id;

    private String name;

    private String contacts;

    private EmployeeView headEmployee;

    private Long organization;

}
