package Intl_BBI_EmployeeBgv.EmployeeVerification.Dto;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable.Role;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;

public class UserLoginDto {
    
    private Long userId;
    private String email;
    private boolean isActive;
    private Role role;
    private String password;
    private Long userDetailId; // Reference to User entity
    
    // Constructors
    public UserLoginDto() {
    }

    public UserLoginDto(Long userId, String email, boolean isActive, Role role, Long userDetailId) {
        this.userId = userId;
        this.email = email;
        this.isActive = isActive;
        this.role = role;
        this.password=  password;    
       this.userDetailId = userDetailId;
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

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }
    
    

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// Static method to convert entity to DTO
    public static UserLoginDto fromEntity(UserLoginTable userLogin) {
        return new UserLoginDto(
                userLogin.getUserId(),
                userLogin.getEmail(),
                userLogin.getIsActive(),
                userLogin.getRole(),
                userLogin.getUser() != null ? userLogin.getUser().getDetailId() : null
        );
    }
}
