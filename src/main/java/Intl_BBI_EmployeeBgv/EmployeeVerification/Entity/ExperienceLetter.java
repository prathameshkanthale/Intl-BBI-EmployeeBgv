package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

@Entity
public class ExperienceLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileId = UUID.randomUUID().toString();

    @Lob
    @JsonIgnore
    private byte[] experienceLetterData;

    
    @JsonIgnore 
    @OneToOne
    @JoinColumn(name = "detail_id", referencedColumnName = "detailId", unique = true, nullable = false)
    @JsonBackReference
    private User user;

    public ExperienceLetter() {}

    public ExperienceLetter(byte[] experienceLetterData, User user) {
        this.experienceLetterData = experienceLetterData;
        this.user = user;
    }

    public String getFileId() {
        return fileId;
    }

    public byte[] getExperienceLetterData() {
        return experienceLetterData;
    }

    public void setExperienceLetterData(byte[] experienceLetterData) {
        this.experienceLetterData = experienceLetterData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}