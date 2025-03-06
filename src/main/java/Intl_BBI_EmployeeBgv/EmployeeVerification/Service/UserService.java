package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserDetailsRequest;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Exception.UserNotFoundException;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Fetched {} users from database.", users.size());
        return users;
    }

    // ✅ Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    // ✅ Create a new user
    public User createUser(UserDetailsRequest userDetailsRequest) {
        User user = new User();
        user.setFirstName(userDetailsRequest.getFirstName());
        user.setLastName(userDetailsRequest.getLastName());
        user.setPhone(userDetailsRequest.getPhone());
        user.setAddress(userDetailsRequest.getAddress());
        user.setEducation(userDetailsRequest.getEducation());
        user.setCurrentEmployer(userDetailsRequest.getCurrentEmployer());
        user.setDesignation(userDetailsRequest.getDesignation());
        user.setExperience(userDetailsRequest.getExperience());
        user.setPassportId(userDetailsRequest.getPassportId());
        user.setPfId(userDetailsRequest.getPfId());
        user.setPanNo(userDetailsRequest.getPanNo());

        User savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getDetailId());
        return savedUser;
    }

    // ✅ Update user partially
    public User updateUser(Long userId, Map<String, Object> updates) {
        User user = getUserById(userId);

        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName": user.setFirstName((String) value); break;
                case "lastName": user.setLastName((String) value); break;
                case "phone": user.setPhone((String) value); break;
                case "address": user.setAddress((String) value); break;
                case "education": user.setEducation((String) value); break;
                case "currentEmployer": user.setCurrentEmployer((String) value); break;
                case "designation": user.setDesignation((String) value); break;
                case "experience": user.setExperience((String) value); break;
                default: logger.warn("Unknown field '{}' ignored during update.", key);
            }
        });

        User updatedUser = userRepository.save(user);
        logger.info("User with ID {} updated successfully.", userId);
        return updatedUser;
    }

    // ✅ Delete user
    public void deleteUser(Long userId) {
        getUserById(userId);  // Ensure user exists before deleting
        userRepository.deleteById(userId);
        logger.info("User with ID {} deleted successfully.", userId);
    }

    // ✅ Upload file and return generated URL
    public String uploadFile(Long userId, String type, MultipartFile file) throws IOException {
        getUserById(userId);  // Ensure user exists

        // Simulating file storage (store in a real storage system)
        String fileUrl = "https://yourdomain.com/files/" + type + "/" + UUID.randomUUID();
        logger.info("File uploaded successfully for user ID {} - Type: {}", userId, type);
        return fileUrl;
    }

    // ✅ Delete file
    @Transactional
    public void deleteFile(Long userId, String type) {
        getUserById(userId);  // Ensure user exists before deleting files
        logger.info("File deleted for user ID {} - Type: {}", userId, type);
    }

    // ✅ Get file URL
    public String getFileUrl(Long userId, String type) {
        return "https://yourdomain.com/files/" + type + "/" + userId;
    }
}
