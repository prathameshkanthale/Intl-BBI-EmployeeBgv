package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.GraduationMarksheet;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduationMarksheetRepository extends JpaRepository<GraduationMarksheet, Long> {
	Optional<GraduationMarksheet> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}