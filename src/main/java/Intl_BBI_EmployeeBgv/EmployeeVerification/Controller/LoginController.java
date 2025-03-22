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
    public ResponseEntity<List<UserLoginDto>> fetchUsers() {
        List<UserLoginDto> users = loginService.fetchUsers().stream()
                .map(UserLoginDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
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
        public UserVerificationDTO verificationUser(@RequestBody Map<String,String>
        UserLogin ) {
            return loginService.verificationOfUser(UserLogin);
        }
    }
    
    @PostMapping("/changePassword/{userId}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordDetails) {
        try {
            // Log the received payload for debugging
            System.out.println("Received passwordDetails: " + passwordDetails);

            // Extract fields from the request body
            String oldPassword = passwordDetails.get("oldPassword");
            String newPassword = passwordDetails.get("newPassword");
            String confirmPassword = passwordDetails.get("confirmPassword");

            // Log the extracted values for debugging
            System.out.println("oldPassword: " + oldPassword);
            System.out.println("newPassword: " + newPassword);
            System.out.println("confirmPassword: " + confirmPassword);

            // Validate the request
            if (oldPassword == null || newPassword == null || confirmPassword == null) {
                return ResponseEntity.badRequest().body("All fields are required.");
            }

            if (!newPassword.equals(confirmPassword)) {
                return ResponseEntity.badRequest().body("New password and confirm password do not match.");
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
}