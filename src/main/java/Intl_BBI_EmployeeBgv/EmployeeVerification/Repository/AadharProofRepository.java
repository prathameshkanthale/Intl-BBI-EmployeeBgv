package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.AadharProof;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AadharProofRepository extends JpaRepository<AadharProof, Long> {
    Optional<AadharProof> findByUser_detailId(Long detailId);  //  Add this method
    void deleteByUser_detailId(Long detailId);  // Add this method for deletion
}
