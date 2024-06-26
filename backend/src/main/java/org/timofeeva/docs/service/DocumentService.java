package org.timofeeva.docs.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Document;

@Service
public interface DocumentService {
    Page<Document> findAll(Predicate predicate, Pageable pageable);

    Long save(Document document);

    void delete(Long id);
}
