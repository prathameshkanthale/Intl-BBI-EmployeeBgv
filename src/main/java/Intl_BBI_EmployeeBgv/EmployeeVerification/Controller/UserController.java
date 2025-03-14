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
    @GetMapping("/{detailId}")
    public ResponseEntity<User> getUserById(@PathVariable Long detailId) {
        return ResponseEntity.ok(userService.getUserById(detailId));
    }

    // ✅ Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        return ResponseEntity.ok(userService.createUser(userDetailsRequest));
    }

    // ✅ Update user partially
    @PatchMapping("/{detailId}")
    public ResponseEntity<User> updateUserPartial(@PathVariable Long detailId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(userService.updateUser(detailId, updates));
    }

    // ✅ Delete a user
    @DeleteMapping("/{detailId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long detailId) {
        userService.deleteUser(detailId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // ✅ Upload any document (ProfilePhoto, Resume, AadharProof, etc.)
    @PostMapping("/{detailId}/upload/{type}")
    public ResponseEntity<String> uploadFile(@PathVariable Long detailId, @PathVariable String type,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(userService.uploadFile(detailId, type, file));
    }

    // ✅ Retrieve file URL
    @GetMapping("/{detailId}/file/{type}")
    public ResponseEntity<String> getFileUrl(@PathVariable Long detailId, @PathVariable String type) {
        return ResponseEntity.ok(userService.getFileUrl(detailId, type));
    }

    // ✅ Delete uploaded file
    @DeleteMapping("/{detailId}/file/{type}")
    public ResponseEntity<String> deleteFile(@PathVariable Long detailId, @PathVariable String type) {
        userService.deleteFile(detailId, type);
        return ResponseEntity.ok("File deleted successfully.");
    }
}
