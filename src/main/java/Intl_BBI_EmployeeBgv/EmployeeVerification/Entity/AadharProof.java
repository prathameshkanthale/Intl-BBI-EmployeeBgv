package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.UUID;

@Entity
public class AadharProof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileId = UUID.randomUUID().toString();

    @Lob
    @JsonIgnore
    private byte[] aadharData;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true, nullable = false)
    @JsonBackReference
    private User user;

    public AadharProof() {}

    public AadharProof(byte[] aadharData, User user) {
        this.aadharData = aadharData;
        this.user = user;
    }

    public String getFileId() {
        return fileId;
    }

    public byte[] getAadharData() {
        return aadharData;
    }

    public void setAadharData(byte[] aadharData) {
        this.aadharData = aadharData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
