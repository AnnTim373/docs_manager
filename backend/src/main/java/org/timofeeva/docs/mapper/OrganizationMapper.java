package org.timofeeva.docs.mapper;

import org.mapstruct.Mapper;
import org.timofeeva.docs.domain.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    default Long toLong(Organization organization) {
        return organization.getId();
    }

    default Organization fromLong(Long id) {
        if (id == null) return null;
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }

}
