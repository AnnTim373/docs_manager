package org.timofeeva.docs.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Department;
import org.timofeeva.docs.repository.DepartmentRepository;
import org.timofeeva.docs.service.DepartmentService;

@Service
@RequiredArgsConstructor
class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    @Override
    public Page<Department> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Long save(Department department) {
        return repository.save(department).getId();
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
