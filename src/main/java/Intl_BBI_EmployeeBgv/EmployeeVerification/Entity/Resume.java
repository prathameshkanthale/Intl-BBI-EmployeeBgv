package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.UUID;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileId = UUID.randomUUID().toString();

    @Lob
    @JsonIgnore
    private byte[] resumeData;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true, nullable = false)
    @JsonBackReference
    private User user;

    public Resume() {}

    public Resume(byte[] resumeData, User user) {
        this.resumeData = resumeData;
        this.user = user;
    }

    public String getFileId() {
        return fileId;
    }

    public byte[] getResumeData() {
        return resumeData;
    }

    public void setResumeData(byte[] resumeData) {
        this.resumeData = resumeData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
