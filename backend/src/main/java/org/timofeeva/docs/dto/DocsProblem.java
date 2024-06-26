package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Единый формат ответа при ошибке")
public class DocsProblem {

    @Schema(description = "Текст ошибки")
    private String errorText;

}
