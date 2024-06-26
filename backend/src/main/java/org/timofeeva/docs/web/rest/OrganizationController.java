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
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.dto.OrganizationDTO;
import org.timofeeva.docs.dto.OrganizationView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.OrganizationFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Операции с организациями")
@RestController
@RequestMapping(value = "/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final LocalizedMessageService messageService;

    private final OrganizationFacade facade;

    @GetMapping
    @Operation(description = "Получение списка организаций (поиск)")
    public Page<OrganizationView> getOrganizations(
            @Parameter(description = "Предикаты для поиска")
            @QuerydslPredicate(root = Organization.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        log.info("Enter getOrganizations with: {}", predicate);
        return facade.findOrganizationList(predicate, pageable);
    }

    @PostMapping
    @Operation(description = "Добавление/изменение организации")
    public ResponseEntity<SuccessResponse> saveOrganization(@RequestBody @Valid OrganizationDTO dto,
                                                            NativeWebRequest request) {
        log.info("Enter saveOrganization with: {}", dto);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveOrganization(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление организации")
    public ResponseEntity<SuccessResponse> deleteOrganization(@Parameter(description = "Идентификатор организации")
                                                              @PathVariable Long id, NativeWebRequest request) {
        log.info("Enter deleteOrganization with: {}", id);
        facade.deleteOrganization(id);
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
