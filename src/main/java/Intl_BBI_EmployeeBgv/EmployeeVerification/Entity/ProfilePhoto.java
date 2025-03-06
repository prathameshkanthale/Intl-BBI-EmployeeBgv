package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.*;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ProfilePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JsonIgnore
    private byte[] photoData;
   
    private String fileId = UUID.randomUUID().toString();
    @OneToOne
    @JoinColumn(name = "detail_id", referencedColumnName = "detailId", unique = true, nullable = false)
    @JsonBackReference
    private User user;

    public ProfilePhoto() {}

    public ProfilePhoto(byte[] photoData, User user) {
        this.photoData = photoData;
        this.user = user;
    }
    
    public String getFileId() {
        return fileId;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}