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
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.dto.OrganizationDTO;
import org.timofeeva.docs.dto.OrganizationView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.OrganizationFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final LocalizedMessageService messageService;

    private final OrganizationFacade facade;

    @GetMapping
    public Page<OrganizationView> getOrganizations(
            @QuerydslPredicate(root = Organization.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        return facade.findOrganizationList(predicate, pageable);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> saveOrganization(@RequestBody @Valid OrganizationDTO dto,
                                                            NativeWebRequest request) {
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveOrganization(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse> deleteOrganization(@RequestParam Long id, NativeWebRequest request) {
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