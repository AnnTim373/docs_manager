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
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.dto.DepartmentDTO;
import org.timofeeva.docs.dto.DepartmentView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.DepartmentFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/department")
@Tag(name = "Операции с подразделениями")
@RequiredArgsConstructor
public class DepartmentController {

    private final LocalizedMessageService messageService;

    private final DepartmentFacade facade;

    @GetMapping
    @Operation(description = "Получение списка подразделений (поиск)")
    public Page<DepartmentView> getDepartments(
            @Parameter(description = "Предикаты для поиска")
            @QuerydslPredicate(root = Department.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        log.info("Enter getDepartments with: {}", predicate);
        return facade.findDepartmentList(predicate, pageable);
    }

    @PostMapping
    @Operation(description = "Создание/изменение подразделений")
    public ResponseEntity<SuccessResponse> saveDepartment(@RequestBody @Valid DepartmentDTO dto,
                                                          NativeWebRequest request) {
        log.info("Enter saveDepartment with: {}", dto);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveDepartment(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление подразделения")
    public ResponseEntity<SuccessResponse> deleteDepartment(@Parameter(description = "Идентификатор подразделения")
                                                            @PathVariable Long id, NativeWebRequest request) {
        log.info("Enter deleteDepartment with: {}", id);
        facade.deleteDepartment(id);
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
