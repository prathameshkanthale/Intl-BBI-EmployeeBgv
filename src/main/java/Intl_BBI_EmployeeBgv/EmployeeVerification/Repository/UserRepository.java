package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository; 

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
