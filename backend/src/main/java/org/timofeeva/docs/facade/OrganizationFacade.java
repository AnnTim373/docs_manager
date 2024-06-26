package org.timofeeva.docs.facade;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.OrganizationDTO;
import org.timofeeva.docs.dto.OrganizationView;

@Component
public interface OrganizationFacade {

    Page<OrganizationView> findOrganizationList(Predicate predicate, Pageable pageable);

    Long saveOrganization(OrganizationDTO dto);

    void deleteOrganization(Long id);

}
