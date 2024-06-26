package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(schema = "docs", name = "department")
public class Department {

    @Id
    @SequenceGenerator(
            name = "seq_department_id",
            sequenceName = "seq_department_id",
            schema = "docs",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_department_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contacts")
    private String contacts;

    @OneToOne
    @JoinColumn(name = "head_employee_id", referencedColumnName = "id")
    private Employee headEmployee;

    @OneToMany
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Set<Employee> employees;

    @Column(name = "organization_id")
    private Long organizationId;

}
