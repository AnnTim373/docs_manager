package org.timofeeva.docs.web.rest;

import com.querydsl.core.types.Predicate;
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
import org.timofeeva.docs.dto.response.DepartmentResponse;
import org.timofeeva.docs.facade.DepartmentFacade;
import org.timofeeva.docs.service.LocalizedMessageService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final LocalizedMessageService messageService;

    private final DepartmentFacade facade;

    @GetMapping
    public Page<DepartmentView> getDepartments(
            @QuerydslPredicate(root = Department.class) Predicate predicate,
            Pageable pageable
    ) {
        return facade.findDepartmentList(predicate, pageable);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> saveDepartment(@RequestBody @Valid DepartmentDTO dto, NativeWebRequest request) {
        try {
            return ResponseEntity.ok(
                    DepartmentResponse.builder()
                            .id(facade.saveDepartment(dto))
                            .result(messageService.getLocalizedMessage(
                                    "response.create.success",
                                    request))
                            .isSuccess(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    DepartmentResponse.builder()
                            .result((messageService.getLocalizedMessage(
                                    "response.create.error",
                                    request)))
                            .isSuccess(false)
                            .errorText(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping
    public ResponseEntity<DepartmentResponse> deleteDepartment(@RequestParam Long id, NativeWebRequest request) {
        try {
            facade.deleteDepartment(id);
            return ResponseEntity.ok(
                    DepartmentResponse.builder()
                            .id(id)
                            .result(messageService.getLocalizedMessage(
                                    "response.delete.success",
                                    request))
                            .isSuccess(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    DepartmentResponse.builder()
                            .id(id)
                            .result((messageService.getLocalizedMessage(
                                    "response.delete.error",
                                    request)))
                            .isSuccess(false)
                            .errorText(e.getMessage())
                            .build());
        }

    }

}
