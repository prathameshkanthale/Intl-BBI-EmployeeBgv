package Intl_BBI_EmployeeBgv.EmployeeVerification.Dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class UserDetailsRequest {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String currentEmployer;
    private String designation;
    private String experience;
    private String passportId;
    private String pfId;
    private String panNo;
    
    private MultipartFile profilePhotoFile;
    private MultipartFile aadharProofFile;
    private MultipartFile resumeFile;
    
    private List<String> skillNames;

    // Default Constructor
    public UserDetailsRequest() { }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

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

    public List<String> getSkillNames() { return skillNames; }
    public void setSkillNames(List<String> skillNames) { this.skillNames = skillNames; }
}
