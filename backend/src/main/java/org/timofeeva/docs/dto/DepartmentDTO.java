package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;
import org.timofeeva.docs.validator.Unique;


@Data
@Schema(name = "Подразделение (Запрос)")
public class DepartmentDTO {

    @Schema(description = "Идентификатор")
    @Reference(entity = Department.class)
    private Long id;

    @Schema(description = "Название подразделения", required = true)
    @NotNull
    @Unique(entity = Department.class, fieldName = "name")
    private String name;

    @Schema(description = "Контактная информация")
    private String contacts;

    @Schema(description = "Идентификатор руководителя")
    @Reference(entity = Employee.class)
    private Long headEmployee;

    @Schema(description = "Идентификатор организации", required = true)
    @NotNull
    @Reference(entity = Organization.class)
    private Long organizationId;

}
