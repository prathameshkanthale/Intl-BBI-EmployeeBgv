package Intl_BBI_EmployeeBgv.EmployeeVerification.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository <Skill , Long  > {
	 Optional <Skill> findBySkillName(String names);
}