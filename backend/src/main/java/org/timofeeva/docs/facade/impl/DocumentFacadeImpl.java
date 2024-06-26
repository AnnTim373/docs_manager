package org.timofeeva.docs.facade.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;
import org.timofeeva.docs.error.NotFoundException;
import org.timofeeva.docs.facade.DocumentFacade;
import org.timofeeva.docs.mapper.DocumentMapper;
import org.timofeeva.docs.service.DocumentService;
import org.timofeeva.docs.service.UserService;

@Component
@RequiredArgsConstructor
public class DocumentFacadeImpl implements DocumentFacade {

    private final DocumentService documentService;
    private final UserService userService;

    private final DocumentMapper mapper;

    @Override
    public Page<DocumentView> findDocumentList(Predicate predicate, Pageable pageable) {
        return documentService.findAll(predicate, pageable).map(mapper::toView);
    }

    @Override
    public Page<DocumentView> findMyDocumentList(String login, Pageable pageable) {
        return documentService.findAllByAuthor(pageable, userService.findByLogin(login).getEmployee().getId())
                .map(mapper::toView);
    }

    @Override
    public Page<DocumentView> findMyExecutingDocumentList(String login, Pageable pageable) {
        return documentService.findAllByExecutor(pageable, userService.findByLogin(login).getEmployee().getId())
                .map(mapper::toView);
    }

    @Override
    public Long saveDocument(DocumentDTO dto) {
        return documentService.save(mapper.fromDTO(dto));
    }

    @Override
    public void deleteDocument(Long id) {
        if (documentService.existById(id)) {
            documentService.delete(id);
        } else throw new NotFoundException("id");
    }

}
