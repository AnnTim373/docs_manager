package org.timofeeva.docs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.dto.EmployeeDTO;
import org.timofeeva.docs.dto.EmployeeView;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "position", source = "position")
    EmployeeView toView(Employee employee);

    Set<EmployeeView> toViews(Set<Employee> employees);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "departmentId", source = "departmentId")
    Employee fromDTO(EmployeeDTO employee);

    default Employee fromLong(Long id) {
        if (id == null) return null;
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }

    default Long toLong(Employee employee) {
        return employee.getId();
    }

}
