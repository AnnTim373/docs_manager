package org.timofeeva.docs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.timofeeva.docs.domain.Organization;
import org.timofeeva.docs.dto.OrganizationDTO;
import org.timofeeva.docs.dto.OrganizationView;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface OrganizationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "legalAddress", source = "legalAddress")
    @Mapping(target = "headEmployee", source = "headEmployee")
    @Mapping(target = "actualAddress", source = "actualAddress")
    @Mapping(target = "departments", source = "departments")
    OrganizationView toView(Organization organization);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "legalAddress", source = "legalAddress")
    @Mapping(target = "headEmployee", source = "headEmployee")
    @Mapping(target = "actualAddress", source = "actualAddress")
    Organization fromDTO(OrganizationDTO dto);

}
