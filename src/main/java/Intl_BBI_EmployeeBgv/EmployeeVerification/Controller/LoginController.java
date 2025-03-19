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
        public UserVerificationDTO verificationUser(@RequestBody UserLoginDto userLoginDto) {
            return loginService.verificationOfUser(userLoginDto);
        }
    }

}
