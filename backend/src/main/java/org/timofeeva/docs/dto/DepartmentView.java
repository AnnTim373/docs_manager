package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;


@Data
@Schema(name = "Подразделение (ответ)")
public class DepartmentView {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Контактная информация")
    private String contacts;

    @Schema(description = "Идентификатор руководителя")
    private EmployeeView headEmployee;

    @Schema(description = "Список работников")
    private Set<EmployeeView> employees;

}
