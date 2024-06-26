package org.timofeeva.docs.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(schema = "docs", name = "document")
public class Document {

    @Id
    @SequenceGenerator(
            name = "seq_document_id",
            sequenceName = "seq_document_id",
            schema = "docs",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document_id")
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(
            schema = "docs",
            name = "executor",
            joinColumns = @JoinColumn(name = "doc_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "executing_employee_id", referencedColumnName = "id")
    )
    private Set<Employee> executor;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Employee author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "text")
    private String text;

    @Column(name = "state")
    private String state;

}
