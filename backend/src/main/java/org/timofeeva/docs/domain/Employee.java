package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "docs", name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "seq_employee_id",
            sequenceName = "seq_employee_id",
            schema = "docs",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "position")
    private String position;

    @Column(name = "department_id")
    private Long departmentId;

}
