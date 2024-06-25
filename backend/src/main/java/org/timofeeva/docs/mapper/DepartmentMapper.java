package org.timofeeva.docs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.dto.DepartmentDTO;
import org.timofeeva.docs.dto.DepartmentView;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, OrganizationMapper.class})
public interface DepartmentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "headEmployee", source = "headEmployee")
    @Mapping(target = "organization", source = "organization")
    DepartmentView toView(Department department);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "headEmployee", source = "headEmployee")
    @Mapping(target = "organization", source = "organization")
    Department fromDTO(DepartmentDTO dto);

}
