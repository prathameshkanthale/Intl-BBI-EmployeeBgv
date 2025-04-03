package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLoginTable, Long> {
    
    // Find user by userId or email
    Optional<UserLoginTable> findByUserId(Long userId);
    
    Optional<UserLoginTable> findByEmail(String email);
   
    // Find user by userId and password (for authentication)
    Optional<UserLoginTable> findByUserIdAndPassword(Long userId, String password);
}
