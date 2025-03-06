package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;


import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.TwelfthMarksheet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwelfthMarksheetRepository extends JpaRepository<TwelfthMarksheet, Long> {
	Optional<TwelfthMarksheet> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}