package org.timofeeva.docs.facade.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.OrganizationDTO;
import org.timofeeva.docs.dto.OrganizationView;
import org.timofeeva.docs.facade.OrganizationFacade;
import org.timofeeva.docs.mapper.OrganizationMapper;
import org.timofeeva.docs.service.OrganizationService;

@Component
@RequiredArgsConstructor
class OrganizationFacadeImpl implements OrganizationFacade {

    private final OrganizationService service;
    private final OrganizationMapper mapper;

    @Override
    public Page<OrganizationView> findOrganizationList(Predicate predicate, Pageable pageable) {
        return service.findAll(predicate, pageable).map(mapper::toView);
    }

    @Override
    public Long saveOrganization(OrganizationDTO dto) {
        return service.save(mapper.fromDTO(dto));
    }

    @Override
    public void deleteOrganization(Long id) {
        service.delete(id);
    }

}
