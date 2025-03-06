package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.DiplomaMarksheet;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaMarksheetRepository extends JpaRepository<DiplomaMarksheet, Long> {
	Optional<DiplomaMarksheet> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}
