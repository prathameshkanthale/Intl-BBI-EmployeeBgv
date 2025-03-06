package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

@Entity
public class TwelfthMarksheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileId = UUID.randomUUID().toString();

    @Lob
    @JsonIgnore
    private byte[] marksheetData;

    @OneToOne
    @JoinColumn(name = "detail_id", referencedColumnName = "detailId", unique = true, nullable = false)
    @JsonBackReference
    private User user;

    public TwelfthMarksheet() {}

    public TwelfthMarksheet(byte[] marksheetData, User user) {
        this.marksheetData = marksheetData;
        this.user = user;
    }

    public String getFileId() {
        return fileId;
    }

    public byte[] getMarksheetData() {
        return marksheetData;
    }

    public void setMarksheetData(byte[] marksheetData) {
        this.marksheetData = marksheetData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}