package org.timofeeva.docs.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.repository.OrganizationRepository;
import org.timofeeva.docs.service.OrganizationService;

@Service
@RequiredArgsConstructor
class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    @Override
    public Page<Organization> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Long save(Organization organization) {
        return repository.save(organization).getId();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

}
