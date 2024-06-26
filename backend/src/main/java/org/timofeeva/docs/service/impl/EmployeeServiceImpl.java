package org.timofeeva.docs.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Employee;
import org.timofeeva.docs.repository.EmployeeRepository;
import org.timofeeva.docs.service.EmployeeService;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Page<Employee> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Long save(Employee employee) {
        return repository.save(employee).getId();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
