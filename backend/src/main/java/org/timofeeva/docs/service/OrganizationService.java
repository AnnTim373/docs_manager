package org.timofeeva.docs.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Organization;

@Service
public interface OrganizationService {

    Page<Organization> findAll(Predicate predicate, Pageable pageable);

    Long save(Organization fromDTO);

    void delete(Long id);

    boolean existById(Long id);

}
