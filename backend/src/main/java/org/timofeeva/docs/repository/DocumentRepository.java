package org.timofeeva.docs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.timofeeva.docs.domain.Document;

@Repository
public interface DocumentRepository extends
        JpaRepository<Document, Long>,
        QuerydslPredicateExecutor<Document> {

    Page<Document> findAllByAuthorId(Long authorId, Pageable pageable);

    @Query(nativeQuery = true, value =
            "select doc.* from docs.document doc " +
                    "join docs.executor ex on doc.id = ex.doc_id " +
                    "where ex.executing_employee_id = :execId")
    Page<Document> findAllByExecutorId(@Param("execId") Long execId, Pageable pageable);

}
