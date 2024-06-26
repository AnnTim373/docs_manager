package org.timofeeva.docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.timofeeva.docs.domain.Document;

@Repository
public interface DocumentRepository extends
        JpaRepository<Document, Long>,
        QuerydslPredicateExecutor<Document> {
}
