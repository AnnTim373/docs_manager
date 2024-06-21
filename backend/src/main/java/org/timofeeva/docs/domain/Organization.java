package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "docs", name = "organization")
public class Organization {

    @Id
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

    @OneToMany(mappedBy = "organization")
    private List<Department> departments;


}
