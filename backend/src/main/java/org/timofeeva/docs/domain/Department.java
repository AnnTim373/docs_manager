package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "docs", name = "department")
public class Department {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contacts")
    private String legalAddress;

    @OneToOne
    @JoinColumn(name = "head_employee_id", referencedColumnName = "id")
    private Employee headEmployee;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}
