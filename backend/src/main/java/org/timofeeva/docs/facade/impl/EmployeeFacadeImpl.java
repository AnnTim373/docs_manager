package org.timofeeva.docs.facade.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.EmployeeDTO;
import org.timofeeva.docs.dto.EmployeeView;
import org.timofeeva.docs.error.NotFoundException;
import org.timofeeva.docs.facade.EmployeeFacade;
import org.timofeeva.docs.mapper.EmployeeMapper;
import org.timofeeva.docs.service.EmployeeService;

@Component
@RequiredArgsConstructor
class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    @Override
    public Page<EmployeeView> findEmployeeList(Predicate predicate, Pageable pageable) {
        return service.findAll(predicate, pageable).map(mapper::toView);
    }

    @Override
    public Long saveEmployee(EmployeeDTO dto) {
        return service.save(mapper.fromDTO(dto));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (service.existById(id)) {
            service.delete(id);
        } else throw new NotFoundException("id");
    }

}
