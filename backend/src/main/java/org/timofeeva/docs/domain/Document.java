package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(schema = "docs", name = "document")
public class Document {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(
            schema = "docs",
            name = "executor",
            joinColumns = @JoinColumn(name = "doc_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "executing_employee_id", referencedColumnName = "id")
    )
    private List<Employee> author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "text")
    private String text;

    @Column(name = "state")
    private String state;

}
