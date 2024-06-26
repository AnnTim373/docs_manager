package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "docs", name = "user")
public class User {

    @Id
    @SequenceGenerator(
            name = "seq_user_id",
            sequenceName = "seq_user_id",
            schema = "docs",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
