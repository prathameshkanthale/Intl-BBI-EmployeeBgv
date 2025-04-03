package Intl_BBI_EmployeeBgv.EmployeeVerification.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserLoginDto;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserVerificationDTO;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Service.LoginService;

@RestController
@RequestMapping("/user-login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    // Create a new user
    
    @PostMapping(value = "/addUser")
    public ResponseEntity<UserLoginDto> createUser(@RequestBody UserLoginDto userLoginDto) {
        UserLoginTable userLoginTable = loginService.createUser(userLoginDto);
        return ResponseEntity.ok(userLoginDto.fromEntity(userLoginTable));
    }

    // Fetch all users
    @GetMapping("/getUsers")
    public String  fetchUsers() {   	
        //List<UserLoginTable> users = loginService.fetchUsers();
        return "ok";
    }

    // Update user role and password
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserLoginDto> updateRole(@PathVariable Long userId, @RequestBody UserLoginDto userLoginDto) {
        UserLoginTable updatedUser = loginService.updateRole(userId, userLoginDto);
        return ResponseEntity.ok(UserLoginDto.fromEntity(updatedUser));
    }

    // Update specific fields dynamically (email, password, role, isActive)
    @PatchMapping("/update-fields/{userId}")
    public ResponseEntity<UserLoginDto> updateUserFields(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        UserLoginTable updatedUser = loginService.updateUserFields(userId, updates);
        return ResponseEntity.ok(UserLoginDto.fromEntity(updatedUser));
    }
    
    // validate user 
    
    @RestController // This removes the need for @ResponseBody on methods
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        private LoginService loginService;

        @PostMapping("/validateUser") // Change to POST since it takes a request body
        public ResponseEntity<UserVerificationDTO> verificationUser(@RequestBody Map<String,String>
        UserLogin ) {
            
            return loginService.verificationOfUser(UserLogin);
            
        }
    }
    
    
    
    @PatchMapping("/update-details/{userId}")
    public ResponseEntity<UserLoginTable> updateUserDetails(@PathVariable("userId") Long userId, 
                                                          @RequestBody Map<String, UserLoginTable> updates) {
    	UserLoginTable u=updates.get("user");
        UserLoginTable updatedUser = loginService.updateUserDetails(u);
        
        return ResponseEntity.ok(updatedUser); 
    }

    
    @PostMapping("/changePassword/{userId}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordDetails) {
        try {
            // Extract fields from the request body
            String oldPassword = passwordDetails.get("oldPassword");
            String newPassword = passwordDetails.get("newPassword");
            String confirmPassword = passwordDetails.get("confirmPassword");

            // Validate the request
            if (oldPassword == null || newPassword == null || confirmPassword == null) {
                return ResponseEntity.badRequest().body("All fields are required.");
            }

            if (!newPassword.equals(confirmPassword)) {
                return ResponseEntity.badRequest().body("New password and confirm password do not match.");
            }

            if (newPassword.length() < 8) {
                return ResponseEntity.badRequest().body("Password must be at least 8 characters long.");
            }

            // Call the service to change the password
            boolean isPasswordChanged = loginService.changePassword(userId, oldPassword, newPassword);

            if (isPasswordChanged) {
                return ResponseEntity.ok("Password changed successfully.");
            } else {
                return ResponseEntity.badRequest().body("Failed to change password. Please check your old password.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
    
    @GetMapping("/getInfo")
    public ResponseEntity <List<UserLoginTable>> allUserInfo()
    {
    	 System.out.println("inside all users");
    	 return ResponseEntity.ok(loginService.fetchUsers());
    	 
    }


  
    
    
}