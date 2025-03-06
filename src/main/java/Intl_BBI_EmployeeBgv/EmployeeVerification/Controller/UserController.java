package Intl_BBI_EmployeeBgv.EmployeeVerification.Controller;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserDetailsRequest;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ Get a single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // ✅ Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        return ResponseEntity.ok(userService.createUser(userDetailsRequest));
    }

    // ✅ Update user partially
    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserPartial(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(userService.updateUser(userId, updates));
    }

    // ✅ Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // ✅ Upload any document (ProfilePhoto, Resume, AadharProof, etc.)
    @PostMapping("/{userId}/upload/{type}")
    public ResponseEntity<String> uploadFile(@PathVariable Long userId, @PathVariable String type,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(userService.uploadFile(userId, type, file));
    }

    // ✅ Retrieve file URL
    @GetMapping("/{userId}/file/{type}")
    public ResponseEntity<String> getFileUrl(@PathVariable Long userId, @PathVariable String type) {
        return ResponseEntity.ok(userService.getFileUrl(userId, type));
    }

    // ✅ Delete uploaded file
    @DeleteMapping("/{userId}/file/{type}")
    public ResponseEntity<String> deleteFile(@PathVariable Long userId, @PathVariable String type) {
        userService.deleteFile(userId, type);
        return ResponseEntity.ok("File deleted successfully.");
    }
}
