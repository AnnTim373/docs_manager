package org.timofeeva.docs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.validator.NotNull;
import org.timofeeva.docs.validator.Reference;

@Data
@Schema(name = "Работник (Запрос)")
public class EmployeeDTO {

    @Schema(description = "Идентификатор")
    @Reference(entity = Employee.class)
    private Long id;

    @Schema(description = "Фамилия", required = true)
    @NotNull
    private String lastName;

    @Schema(description = "Имя", required = true)
    @NotNull
    private String firstName;

    @Schema(description = "Отчество")
    private String middleName;

    @Schema(description = "Должность", required = true)
    @NotNull
    private String position;

    @Schema(description = "Идентификатор подразделения", required = true)
    @NotNull
    @Reference(entity = Department.class)
    private Long departmentId;

}
