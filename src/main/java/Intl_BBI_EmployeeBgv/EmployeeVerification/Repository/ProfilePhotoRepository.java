package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
    Optional<ProfilePhoto> findByUser_UserId(Long userId);  // ✅ Add this method
    void deleteByUser_UserId(Long userId);  // ✅ Add this method for deletion
}
