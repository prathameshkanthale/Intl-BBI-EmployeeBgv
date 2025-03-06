package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;


import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.TenthMarksheet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenthMarksheetRepository extends JpaRepository<TenthMarksheet, Long> {

	 Optional<TenthMarksheet> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}