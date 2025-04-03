package Intl_BBI_EmployeeBgv.EmployeeVerification.Dto;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable.Role;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;

public class UserLoginDto {
	

    
    private Long userId;
    private String email;
    private boolean isActive = true;
    private Role role;
    private String password;
    private Long userDetailId; // Change type to Long
 // Reference to User entity
    private String firstName;
    private String lastName;
    private String verificationStatus; // ✅ New Field

    // Constructors
    public UserLoginDto() {}

    public UserLoginDto(Long userId, String email, boolean isActive, Role role, String password, 
                        String firstName, String lastName, Long detailId, String verificationStatus) {
        this.userId = userId;
        this.email = email;
        this.isActive = isActive;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userDetailId = detailId;
        this.verificationStatus = verificationStatus;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    // ✅ Update `fromEntity` Method
    public static UserLoginDto fromEntity(UserLoginTable userLogin) {
        User user = userLogin.getUser();
        return new UserLoginDto(
                userLogin.getUserId(),
                userLogin.getEmail(),
                userLogin.getIsActive(),
                userLogin.getRole(),
                userLogin.getPassword(),
                user != null ? user.getFirstName() : null,
                user != null ? user.getLastName() : null,
                user != null ? user.getDetailId() : null,
                user != null ? user.getVerificationStatus().name(): null // ✅ Convert Enum to String
        );
    }
}
