package org.timofeeva.docs.facade.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.DepartmentDTO;
import org.timofeeva.docs.dto.DepartmentView;
import org.timofeeva.docs.error.NotFoundException;
import org.timofeeva.docs.facade.DepartmentFacade;
import org.timofeeva.docs.mapper.DepartmentMapper;
import org.timofeeva.docs.service.DepartmentService;

@Component
@RequiredArgsConstructor
class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @Override
    public Page<DepartmentView> findDepartmentList(Predicate predicate, Pageable pageable) {
        return service.findAll(predicate, pageable).map(mapper::toView);
    }

    @Override
    public Long saveDepartment(DepartmentDTO dto) {
        return service.save(mapper.fromDTO(dto));
    }

    @Override
    public void deleteDepartment(Long id) {
        if (service.existById(id)) {
            service.delete(id);
        } else throw new NotFoundException("id");
    }

}
