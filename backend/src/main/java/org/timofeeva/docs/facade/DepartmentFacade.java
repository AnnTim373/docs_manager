package org.timofeeva.docs.facade;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.timofeeva.docs.dto.DepartmentDTO;
import org.timofeeva.docs.dto.DepartmentView;

public interface DepartmentFacade {

    Page<DepartmentView> findDepartmentList(Predicate predicate, Pageable pageable);

    Long saveDepartment(DepartmentDTO dto);

    void deleteDepartment(Long id);

}
