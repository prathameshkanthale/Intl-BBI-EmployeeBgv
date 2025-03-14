package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserDetailsRequest;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.*;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Exception.UserNotFoundException;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired private UserRepository userRepository;
    @Autowired private ProfilePhotoRepository profilePhotoRepository;
    @Autowired private ResumeRepository resumeRepository;
    @Autowired private AadharProofRepository aadharProofRepository;
    @Autowired private TenthMarksheetRepository tenthMarksheetRepository;
    @Autowired private TwelfthMarksheetRepository twelfthMarksheetRepository;
    @Autowired private DiplomaMarksheetRepository diplomaMarksheetRepository;
    @Autowired private GraduationMarksheetRepository graduationMarksheetRepository;
    @Autowired private PostGraduationMarksheetRepository postGraduationMarksheetRepository;
    @Autowired private ExperienceLetterRepository experienceLetterRepository;

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        logger.info("Fetched {} users from database.", users.size());
        return users;
    }

    // Get user by ID
    public User getUserById(Long detailId) {
        return userRepository.findById(detailId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + detailId + " not found"));
    }

    // Create a new user
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
        logger.info("User created successfully with ID: {}", savedUser. getDetailId());
        return savedUser;
    }

    // Update user partially
    public User updateUser(Long detailId, Map<String, Object> updates) {
        User user = getUserById(detailId);

        updates.forEach((key, value) -> {
            switch (key) {
            case"firstName":user.setFirstName((String) value) ; break;
            case"lastName":user.setLastName((String) value) ; break;
                case "phone": user.setPhone((String) value); break;
                case "address": user.setAddress((String) value); break;
                case "education": user.setEducation((String) value); break;
                case "currentEmployer": user.setCurrentEmployer((String) value); break;
                case "designation": user.setDesignation((String) value); break;
                case "experience":user.setExperience((String)value);
                	

                    break;
            }
        });

        User updatedUser = userRepository.save(user);
        logger.info("User with ID {} updated successfully.", detailId);
        return updatedUser;
    }

    // Delete user
    public void deleteUser(Long detailId) {
        getUserById(detailId);  // Ensure user exists before deleting
        userRepository.deleteById(detailId);
        logger.info("User with ID {} deleted successfully.",detailId);
    }

    // Upload file and generate short URL
    public String uploadFile(Long detailId, String type, MultipartFile file) throws IOException {
        User user = getUserById(detailId);
        byte[] fileData = file.getBytes();

        switch (type) {
            case "profile-photo":
                profilePhotoRepository.save(new ProfilePhoto(fileData,user));
                break;
            case "resume":
                resumeRepository.save(new Resume(fileData,user));
                break;
            case "aadhar-proof":
                aadharProofRepository.save(new AadharProof(fileData,user));
                break;
            case "10th-marksheet":
                tenthMarksheetRepository.save(new TenthMarksheet(fileData, user));
                break;
            case "12th-marksheet":
                twelfthMarksheetRepository.save(new TwelfthMarksheet(fileData, user));
                break;
            case "diploma-marksheet":
                diplomaMarksheetRepository.save(new DiplomaMarksheet(fileData, user));
                break;
            case "graduation-marksheet":
                graduationMarksheetRepository.save(new GraduationMarksheet(fileData, user));
                break;
            case "post-graduation-marksheet":
                postGraduationMarksheetRepository.save(new PostGraduationMarksheet(fileData, user));
                break;
            case "experience-letter":
                experienceLetterRepository.save(new ExperienceLetter(fileData, user));
                break;
            default:
                throw new IllegalArgumentException("Invalid file type: " + type);
        }

        userRepository.save(user);  // Ensure user is updated with file reference
        logger.info("File uploaded successfully for user ID {} - Type: {}", detailId, type);
        return "https://yourdomain.com/files/" + type + "/" + UUID.randomUUID();
    }

    // Delete file
    @Transactional
    public void deleteFile(Long detailId, String type) {
        getUserById(detailId);  // Ensure user exists before deleting files

        switch (type) {
            case "profile-photo": profilePhotoRepository.deleteByUser_detailId(detailId); break;
            case "resume": resumeRepository.deleteByUser_detailId(detailId); break;
            case "aadhar-proof": aadharProofRepository.deleteByUser_detailId(detailId); break;
            case "10th-marksheet": tenthMarksheetRepository.deleteByUser_detailId(detailId); break;
            case "12th-marksheet": twelfthMarksheetRepository.deleteByUser_detailId(detailId); break;
            case "diploma-marksheet": diplomaMarksheetRepository.deleteByUser_detailId(detailId); break;
            case "graduation-marksheet": graduationMarksheetRepository.deleteByUser_detailId(detailId); break;
            case "post-graduation-marksheet": postGraduationMarksheetRepository.deleteByUser_detailId(detailId); break;
            case "experience-letter": experienceLetterRepository.deleteByUser_detailId(detailId); break;
            default: throw new IllegalArgumentException("Invalid file type: " + type);
        }

        logger.info("File deleted for user ID {} - Type: {}", detailId, type); 
    }

    // Get file URL
    public String getFileUrl(Long detailId, String type) {
        return "https://yourdomain.com/files/" + type + "/" + detailId;
    }
}