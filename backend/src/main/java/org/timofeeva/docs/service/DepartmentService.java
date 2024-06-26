package org.timofeeva.docs.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.timofeeva.docs.domain.Department;

@Service
public interface DepartmentService {

    Page<Department> findAll(Predicate predicate, Pageable pageable);

    Long save(Department department);

    void delete(Long id);

    boolean existById(Long id);

}
