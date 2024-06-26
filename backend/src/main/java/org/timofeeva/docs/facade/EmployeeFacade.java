package org.timofeeva.docs.facade;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.timofeeva.docs.dto.EmployeeDTO;
import org.timofeeva.docs.dto.EmployeeView;

@Component
public interface EmployeeFacade {

    Page<EmployeeView> findEmployeeList(Predicate predicate, Pageable pageable);

    Long saveEmployee(EmployeeDTO dto);

    void deleteEmployee(Long id);

}
