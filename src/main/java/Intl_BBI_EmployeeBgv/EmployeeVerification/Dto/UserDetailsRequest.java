package Intl_BBI_EmployeeBgv.EmployeeVerification.Dto;

import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public class UserDetailsRequest {

    private Long detailId;  // Required for updates
    private String FirstName;
    private String LastName;
    private int age;
    private String phone;
    private String address;
    private String education;
    private String currentEmployer;
    private String designation;
    private String experience;
    private String passportId;
    private String pfId;
    private String panNo;

    // File uploads
    private MultipartFile profilePhotoFile;
    private MultipartFile aadharProofFile;
    private MultipartFile resumeFile;
    private MultipartFile tenthMarksheetFile;
    private MultipartFile twelfthMarksheetFile;
    private MultipartFile diplomaMarksheetFile;
    private MultipartFile graduationMarksheetFile;
    private MultipartFile postGraduationMarksheetFile;
    private MultipartFile experienceLetterFile;

    // Skill Handling
    private Set<String> skillNames;  // Storing skill names as Strings, can be converted to Skill objects later

    

    // Constructors
    public UserDetailsRequest() {}

    // Getters & Setters
    public Long getdetailId() { return detailId; }
    public void setdetailId(Long detailId) { this.detailId = detailId; }

    public String getFirstName() { return FirstName; }
    public void setFirstName(String FirstName) { this.FirstName= FirstName; }
    
    public String getLastName() { return LastName; }
    public void setLastName(String LastName) { this.LastName= LastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getCurrentEmployer() { return currentEmployer; }
    public void setCurrentEmployer(String currentEmployer) { this.currentEmployer = currentEmployer; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getPassportId() { return passportId; }
    public void setPassportId(String passportId) { this.passportId = passportId; }

    public String getPfId() { return pfId; }
    public void setPfId(String pfId) { this.pfId = pfId; }

    public String getPanNo() { return panNo; }
    public void setPanNo(String panNo) { this.panNo = panNo; }

    public MultipartFile getProfilePhotoFile() { return profilePhotoFile; }
    public void setProfilePhotoFile(MultipartFile profilePhotoFile) { this.profilePhotoFile = profilePhotoFile; }

    public MultipartFile getAadharProofFile() { return aadharProofFile; }
    public void setAadharProofFile(MultipartFile aadharProofFile) { this.aadharProofFile = aadharProofFile; }

    public MultipartFile getResumeFile() { return resumeFile; }
    public void setResumeFile(MultipartFile resumeFile) { this.resumeFile = resumeFile; }

    public MultipartFile getTenthMarksheetFile() { return tenthMarksheetFile; }
    public void setTenthMarksheetFile(MultipartFile tenthMarksheetFile) { this.tenthMarksheetFile = tenthMarksheetFile; }

    public MultipartFile getTwelfthMarksheetFile() { return twelfthMarksheetFile; }
    public void setTwelfthMarksheetFile(MultipartFile twelfthMarksheetFile) { this.twelfthMarksheetFile = twelfthMarksheetFile; }

    public MultipartFile getDiplomaMarksheetFile() { return diplomaMarksheetFile; }
    public void setDiplomaMarksheetFile(MultipartFile diplomaMarksheetFile) { this.diplomaMarksheetFile = diplomaMarksheetFile; }

    public MultipartFile getGraduationMarksheetFile() { return graduationMarksheetFile; }
    public void setGraduationMarksheetFile(MultipartFile graduationMarksheetFile) { this.graduationMarksheetFile = graduationMarksheetFile; }

    public MultipartFile getPostGraduationMarksheetFile() { return postGraduationMarksheetFile; }
    public void setPostGraduationMarksheetFile(MultipartFile postGraduationMarksheetFile) { this.postGraduationMarksheetFile = postGraduationMarksheetFile; }

    public MultipartFile getExperienceLetterFile() { return experienceLetterFile; }
    public void setExperienceLetterFile(MultipartFile experienceLetterFile) { this.experienceLetterFile = experienceLetterFile; }

    public Set<String> getSkillNames() { return skillNames; }
    public void setSkillNames(Set<String> skillNames) { this.skillNames = skillNames; }

    
   
}
