package org.timofeeva.docs.facade;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;

public interface DocumentFacade {

    Page<DocumentView> findDocumentList(Predicate predicate, Pageable pageable);

    Long saveDocument(DocumentDTO dto);

    void deleteDocument(Long id);

}
