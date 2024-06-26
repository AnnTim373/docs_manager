package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
@Schema(name = "Организация (Ответ)")
public class OrganizationView {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Название организации")
    private String name;

    @Schema(description = "Юридический адрес")
    private String legalAddress;

    @Schema(description = "Фактический адрес")
    private String actualAddress;

    @Schema(description = "Идентификатор руководителя")
    private EmployeeView headEmployee;

    @Schema(description = "Список подразделений")
    private Set<DepartmentView> departments;

}
