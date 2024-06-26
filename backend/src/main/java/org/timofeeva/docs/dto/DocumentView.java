package org.timofeeva.docs.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class DocumentView {
    private Long id;

    private EmployeeView author;

    private Set<EmployeeView> executor;

    private String subject;

    private String text;

    private LocalDateTime deadline;

    private String state;
}
