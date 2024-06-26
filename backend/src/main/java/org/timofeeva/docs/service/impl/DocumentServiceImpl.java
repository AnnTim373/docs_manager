package org.timofeeva.docs.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.repository.DocumentRepository;
import org.timofeeva.docs.service.DocumentService;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;

    @Override
    public Page<Document> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Long save(Document employee) {
        return repository.save(employee).getId();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Page<Document> findAllByAuthor(Pageable pageable, Long authorId) {
        return repository.findAllByAuthorId(authorId, pageable);
    }

    @Override
    public Page<Document> findAllByExecutor(Pageable pageable, Long executorId) {
        return repository.findAllByExecutorId(executorId, pageable);
    }

}
