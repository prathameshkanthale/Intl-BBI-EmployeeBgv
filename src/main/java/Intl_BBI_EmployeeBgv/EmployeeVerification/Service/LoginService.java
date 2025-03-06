package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserLoginDto;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserLoginRepository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserLoginTable createUser(UserLoginDto userLoginDto) {
        UserLoginTable userLoginTable = new UserLoginTable();
        userLoginTable.setEmail(userLoginDto.getEmail());
        userLoginTable.setIsActive(userLoginDto.getIsActive());
        userLoginTable.setRole(userLoginDto.getRole());

        // Fetch User entity if provided
        if (userLoginDto.getUserDetailId() != null) {
            Optional<User> userOptional = userRepository.findById(userLoginDto.getUserDetailId());
            userOptional.ifPresent(userLoginTable::setUser);
        }

        return userLoginRepository.save(userLoginTable);
    }

    // Fetch all users
    public List<UserLoginTable> fetchUsers() {
        return userLoginRepository.findAll();
    }

    // Update user role and status
    public UserLoginTable updateRole(Long userId, UserLoginDto updatedUserDto) {
        UserLoginTable user = userLoginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setRole(updatedUserDto.getRole());
        user.setIsActive(updatedUserDto.getIsActive());

        return userLoginRepository.save(user);
    }

    // Update email, password, role, and isActive dynamically
    public UserLoginTable updateUserFields(Long userId, Map<String, Object> updates) {
        UserLoginTable user = userLoginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        updates.forEach((key, value) -> {
            switch (key) {
                case "email":
                    user.setEmail((String) value);
                    break;
                case "password":
                    user.setPassword((String) value);
                    break;
                case "role":
                    user.setRole(UserLoginTable.Role.valueOf((String) value));
                    break;
                case "isActive":
                    user.setIsActive((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return userLoginRepository.save(user);
    }
}
