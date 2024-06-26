package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Schema(name = "Документ (Запрос)")
public class DocumentDTO {

    @Schema(description = "Идентификатор")
    @Reference(entity = Document.class)
    private Long id;

    @Schema(description = "Идентификатор работника, создавшего документ", required = true)
    @NotNull
    @Reference(entity = Employee.class)
    private Long author;

    @Schema(description =
            "Список идентификаторов работников, которые назначены на выполнение поручения (документа)",
            required = true
    )
    @NotNull
    private Set<Long> executor;

    @Schema(description = "Предмет поручения")
    private String subject;

    @Schema(description = "Текст поручения", required = true)
    @NotNull
    private String text;

    @Schema(description = "Срок исполнения")
    private LocalDateTime deadline;

    @Schema(description = "Состояние", required = true)
    @NotNull
    private String state;

}
