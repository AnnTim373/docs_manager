package org.timofeeva.docs.facade.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;
import org.timofeeva.docs.facade.DocumentFacade;
import org.timofeeva.docs.mapper.DocumentMapper;
import org.timofeeva.docs.service.DocumentService;

@Component
@RequiredArgsConstructor
public class DocumentFacadeImpl implements DocumentFacade {

    private final DocumentService service;

    private final DocumentMapper mapper;

    @Override
    public Page<DocumentView> findDocumentList(Predicate predicate, Pageable pageable) {
        return service.findAll(predicate, pageable).map(mapper::toView);
    }

    @Override
    public Long saveDocument(DocumentDTO dto) {
        return service.save(mapper.fromDTO(dto));
    }

    @Override
    public void deleteDocument(Long id) {
        service.delete(id);
    }

}
