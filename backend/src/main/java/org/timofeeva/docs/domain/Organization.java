package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(schema = "docs", name = "organization")
public class Organization {

    @Id
    @SequenceGenerator(
            name = "seq_organization_id",
            sequenceName = "seq_organization_id",
            schema = "docs",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_organization_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "actual_address")
    private String actualAddress;

    @OneToOne
    @JoinColumn(name = "head_employee_id", referencedColumnName = "id")
    private Employee headEmployee;

    @OneToMany
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Set<Department> departments;

}
