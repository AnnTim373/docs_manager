package org.timofeeva.docs.web.rest;

import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.dto.EmployeeDTO;
import org.timofeeva.docs.dto.EmployeeView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.EmployeeFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/employee")
@Tag(name = "Операции с сотрудниками")
@RequiredArgsConstructor
public class EmployeeController {

    private final LocalizedMessageService messageService;

    private final EmployeeFacade facade;

    @GetMapping
    @Operation(description = "Получение списка сотрудников (поиск)")
    public Page<EmployeeView> getEmployees(
            @Parameter(description = "Предикаты для поиска")
            @QuerydslPredicate(root = Employee.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        log.info("Enter getEmployees with: {}", predicate);
        return facade.findEmployeeList(predicate, pageable);
    }

    @PostMapping
    @Operation(description = "Создание/изменение сотрудника")
    public ResponseEntity<SuccessResponse> saveEmployee(@RequestBody @Valid EmployeeDTO dto,
                                                        NativeWebRequest request) {
        log.info("Enter saveEmployee with: {}", dto);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveEmployee(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление сотрудника")
    public ResponseEntity<SuccessResponse> deleteEmployee(@Parameter(description = "Идентификатор сотрудника")
                                                          @PathVariable Long id, NativeWebRequest request) {
        log.info("Enter deleteEmployee with: {}", id);
        facade.deleteEmployee(id);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(id)
                        .result(messageService.getLocalizedMessage(
                                "response.delete.success",
                                request))
                        .build()
        );
    }

}
