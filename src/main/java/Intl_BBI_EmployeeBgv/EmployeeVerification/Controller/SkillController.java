package Intl_BBI_EmployeeBgv.EmployeeVerification.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.Skill;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getSkill();
    }

    @PostMapping("/addSkill")
    public Skill createSkill(@RequestBody Skill skill)
    {
        return skillService.createSkill(skill);
    }

    // ✅ Add skill to a user
    @PostMapping("/{detailId}/addSkill/{skillId}")
    public ResponseEntity<String> addSkillToUser(@PathVariable Long detailId, @PathVariable Long skillId) {
        skillService.addSkillToUser(detailId, skillId);
        return ResponseEntity.ok("Skill added successfully to user " + detailId);
    } 
    @PutMapping("/{skillId}")
    public Skill updateSkill(@PathVariable Long detailId, @RequestBody Skill skill) 
    {
        return skillService.updateSkill(detailId, skill);
    }
    
    
    @PatchMapping("/skills/{skillId}")
    public ResponseEntity<Skill> updateSkillPartial(@PathVariable Long skillId, @RequestBody Map<String, Object> updates) {
        Skill updatedSkill = skillService.updateSkillPartial(skillId, updates);
        return ResponseEntity.ok(updatedSkill);
    }
    
    
 // Delete skill by skillId
    @DeleteMapping("/{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long skillId) {
        skillService.deleteSkill(skillId);
        return ResponseEntity.ok("Skill removed successfully");
    }
    
    
    

}