package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserDetailsRequest;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.*;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private ProfilePhotoRepository profilePhotoRepository;
    @Autowired private ResumeRepository resumeRepository;
    @Autowired private AadharProofRepository aadharProofRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create a new user
    public User createUser(UserDetailsRequest userDetailsRequest) {
        User user = new User();
        user.setName(userDetailsRequest.getName());
        user.setEmail(userDetailsRequest.getEmail());
        user.setPhone(userDetailsRequest.getPhone());
        user.setAddress(userDetailsRequest.getAddress());
        user.setEducation(userDetailsRequest.getEducation());
        user.setCurrentEmployer(userDetailsRequest.getCurrentEmployer());
        user.setDesignation(userDetailsRequest.getDesignation());
        user.setExperience(String.valueOf(userDetailsRequest.getExperience())); 
        user.setPassportId(userDetailsRequest.getPassportId());
        user.setPfId(userDetailsRequest.getPfId());
        user.setPanNo(userDetailsRequest.getPanNo());
        return userRepository.save(user);
    }

    // Update user partially
    public User updateUser(Long userId, Map<String, Object> updates) {
        User user = getUserById(userId);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name": 
                    user.setName((String) value); 
                    break;
                case "email": 
                    user.setEmail((String) value); 
                    break;
                case "phone": 
                    user.setPhone((String) value); 
                    break;
                case "address": 
                    user.setAddress((String) value); 
                    break;
                case "education": 
                    user.setEducation((String) value); 
                    break;
                case "currentEmployer": 
                    user.setCurrentEmployer((String) value); 
                    break;
                case "designation": 
                    user.setDesignation((String) value); 
                    break;
                case "experience": 
                    user.setExperience(String.valueOf(value)); // Convert int to String
                    break;
            }
        });
        return userRepository.save(user);
    }


    // Delete user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);	
    }

    // Upload file and generate short URL
    public String uploadFile(Long userId, String type, MultipartFile file) throws IOException {
        User user = getUserById(userId);
        byte[] fileData = file.getBytes();
        String fileId = UUID.randomUUID().toString();

        switch (type) {
            case "profile-photo":
                profilePhotoRepository.save(new ProfilePhoto(fileData, user));
                break;
            case "resume":
                resumeRepository.save(new Resume(fileData, user));
                break;
            case "aadhar-proof":
                aadharProofRepository.save(new AadharProof(fileData, user));
                break;
            default:
                throw new IllegalArgumentException("Invalid file type: " + type);
        }
        return "https://yourdomain.com/files/" + fileId;
    }

    // Get file as short URL instead of large Base64
    public String getFileUrl(Long userId, String type) {
        String fileId = UUID.randomUUID().toString();
        return "https://yourdomain.com/files/" + fileId;
    }

    // Delete file
    public void deleteFile(Long userId, String type) {
        switch (type) {
            case "profile-photo":
                profilePhotoRepository.deleteByUser_UserId(userId);
                break;
            case "resume":
                resumeRepository.deleteByUser_UserId(userId);
                break;
            case "aadhar-proof":
                aadharProofRepository.deleteByUser_UserId(userId);
                break;
            default:
                throw new IllegalArgumentException("Invalid file type: " + type);
        }
    }
}
