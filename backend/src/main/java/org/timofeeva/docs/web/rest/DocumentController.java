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
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.DocumentFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/document")
@RequiredArgsConstructor
public class DocumentController {

    private final LocalizedMessageService messageService;

    private final DocumentFacade facade;

    @GetMapping
    public Page<DocumentView> getDocuments(
            @QuerydslPredicate(root = Document.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        return facade.findDocumentList(predicate, pageable);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> saveDepartment(@RequestBody @Valid DocumentDTO dto,
                                                          NativeWebRequest request) {
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveDocument(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse> deleteDepartment(@RequestParam Long id, NativeWebRequest request) {
        facade.deleteDocument(id);
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
