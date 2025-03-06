package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}
