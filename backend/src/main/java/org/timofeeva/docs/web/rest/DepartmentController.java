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
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.DepartmentFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

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
            @QuerydslPredicate(root = Department.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        return facade.findDepartmentList(predicate, pageable);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> saveDepartment(@RequestBody @Valid DepartmentDTO dto,
                                                          NativeWebRequest request) {
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveDepartment(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse> deleteDepartment(@RequestParam Long id, NativeWebRequest request) {
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
