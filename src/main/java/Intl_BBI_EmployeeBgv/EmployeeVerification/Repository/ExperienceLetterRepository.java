package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.ExperienceLetter;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceLetterRepository extends JpaRepository<ExperienceLetter, Long> {
	Optional<ExperienceLetter> findByUser_detailId(Long detailId);  // ✅ Add this method
    void deleteByUser_detailId(Long detailId);  // ✅ Add this method for deletion
}
