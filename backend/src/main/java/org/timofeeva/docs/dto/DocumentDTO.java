package org.timofeeva.docs.dto;

import lombok.Data;
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class DocumentDTO {

    @Reference(entity = Document.class)
    private Long id;

    @NotNull
    @Reference(entity = Employee.class)
    private Long author;

    @NotNull
    private Set<Long> executor;

    private String subject;

    @NotNull
    private String text;

    private LocalDateTime deadline;

    @NotNull
    private String state;

}
