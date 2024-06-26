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
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;
import org.timofeeva.docs.dto.response.SuccessResponse;
import org.timofeeva.docs.facade.DocumentFacade;
import org.timofeeva.docs.service.LocalizedMessageService;
import org.timofeeva.docs.util.MyBinderCustomizer;
import org.timofeeva.docs.util.SecurityUtils;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/document")
@Tag(name = "Опреации с документами")
@RequiredArgsConstructor
public class DocumentController {

    private final LocalizedMessageService messageService;

    private final DocumentFacade facade;

    @GetMapping
    @Operation(description = "Получение списка документов (поиск)")
    public Page<DocumentView> getDocuments(
            @Parameter(description = "Предикаты для поиска")
            @QuerydslPredicate(root = Document.class, bindings = MyBinderCustomizer.class) Predicate predicate,
            Pageable pageable
    ) {
        log.info("Enter getDocuments with: {}", predicate);
        return facade.findDocumentList(predicate, pageable);
    }

    @GetMapping("/my")
    @Operation(description = "Получение списка документов по автору из авторизации")
    public Page<DocumentView> getMyDocuments(Pageable pageable) {
        String login = SecurityUtils.getCurrentUserLogin();
        log.info("Enter getMyDocuments with login: {}", login);
        return facade.findMyDocumentList(login, pageable);
    }

    @GetMapping("/executing")
    @Operation(description = "Получение списка документов по исполнителю из авторизации")
    public Page<DocumentView> getMyExecutingDocuments(Pageable pageable) {
        String login = SecurityUtils.getCurrentUserLogin();
        log.info("Enter getMyExecutingDocuments with login: {}", login);
        return facade.findMyExecutingDocumentList(login, pageable);
    }

    @PostMapping
    @Operation(description = "Создание/изменение документа")
    public ResponseEntity<SuccessResponse> saveDocument(@RequestBody @Valid DocumentDTO dto,
                                                        NativeWebRequest request) {
        log.info("Enter saveDocument with: {}", dto);
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .id(facade.saveDocument(dto))
                        .result(messageService.getLocalizedMessage(
                                "response.create.success",
                                request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление документа")
    public ResponseEntity<SuccessResponse> deleteDocument(@Parameter(description = "Идентификатор документа")
                                                          @PathVariable Long id, NativeWebRequest request) {
        log.info("Enter deleteDocument with: {}", id);
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
