package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;
import org.timofeeva.docs.validator.Unique;

@Data
@Schema(name = "Организация (Запрос)")
public class OrganizationDTO {

    @Schema(description = "Идентификатор")
    @Reference(entity = Organization.class)
    private Long id;

    @Schema(description = "Название организации", required = true)
    @NotNull
    @Unique(entity = Organization.class, fieldName = "name")
    private String name;

    @Schema(description = "Юридический адрес", required = true)
    @NotNull
    private String legalAddress;

    @Schema(description = "Фактический адрес (если не заполнен - автоматически будет заполнено юр.адресом)")
    private String actualAddress;

    @Schema(description = "Идентификатор руководителя")
    @Reference(entity = Employee.class)
    private Long headEmployee;

}
