package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.Skill;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.SkillRepository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Skill> getSkill() {
        return skillRepository.findAll();
    }

    public Skill createSkill(Skill skills) {
        return skillRepository.save(skills);
    }

    public Skill updateSkill(Long skillId, Skill skills) {
        Skill existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        existingSkill.setSkillName(skills.getSkillName());
        return skillRepository.save(existingSkill);
    }

    public Skill updateSkillPartial(Long skillId, Map<String, Object> updates) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (updates.containsKey("skillName")) {
            skill.setSkillName((String) updates.get("skillName"));
        }

        return skillRepository.save(skill);
    }

    @Transactional
    public void addSkillToUser(Long detailId, Long skillId) {
        User user = userRepository.findById(detailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        user.getSkills().add(skill);  // Ensure `User` entity has `List<Skill>`
        System.out.println("USER..............."+user);
        userRepository.save(user);
    }
    
    
    
    public void addSkillsToUser(Long detailId, List<Long> skillIds) {
        for (Long skillId : skillIds) {
            addSkillToUser(detailId, skillId);
        }
    }


    public void deleteSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }

 // Add this new method to SkillService
//    @Transactional
//    public void addSkillsToUser(Long detailId, List<String> skillNames) {
//        User user = userRepository.findById(detailId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Clear existing skills to avoid duplicates
//        user.getSkills().clear();
//
//        for (String skillName : skillNames) {
//            // Find or create the skill
//            Skill skill = skillRepository.findBySkillName(skillName)
//                    .orElseGet(() -> {
//                        Skill newSkill = new Skill();
//                        newSkill.setSkillName(skillName);
//                        return skillRepository.save(newSkill);
//                    });
//            
//            user.getSkills().add(skill);
//        }
//
//        userRepository.save(user);
//    }
}
