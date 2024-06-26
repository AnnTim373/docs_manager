package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Работник (Ответ)")
public class EmployeeView {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Отчество")
    private String middleName;

    @Schema(description = "Должность")
    private String position;

}
