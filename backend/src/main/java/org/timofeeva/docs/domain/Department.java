package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}
