package org.timofeeva.docs.dto;

import lombok.Data;

@Data
public class EmployeeView {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String position;

}
