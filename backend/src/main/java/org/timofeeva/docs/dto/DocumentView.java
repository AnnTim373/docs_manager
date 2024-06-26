package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Schema(name = "Документ (Ответ)")
public class DocumentView {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Работник, создавший документ")
    private EmployeeView author;

    @Schema(description = "Список работников, которые назначены на выполнение поручения (документа)")
    private Set<EmployeeView> executor;

    @Schema(description = "Предмет поручения")
    private String subject;

    @Schema(description = "Текст поручения")
    private String text;

    @Schema(description = "Срок исполнения")
    private LocalDateTime deadline;

    @Schema(description = "Состояние")
    private String state;

}
