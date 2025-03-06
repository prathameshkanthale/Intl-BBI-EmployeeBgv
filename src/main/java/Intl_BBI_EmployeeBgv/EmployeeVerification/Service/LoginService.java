package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserLoginRepository;

@Service
public class LoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    //  Create a new user
    public UserLoginTable createUser(UserLoginTable userLoginTable) {
        return userLoginRepository.save(userLoginTable);
    }

    //  Fetch all users
    public List<UserLoginTable> fetchUsers() {
        return userLoginRepository.findAll();
    }

    //  Update user role and password
    public UserLoginTable updateRole(Long userId, UserLoginTable updatedUser) {
        UserLoginTable user = userLoginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setRole(updatedUser.getRole());
        user.setIsActive(updatedUser.getIsActive());
        user.setPassword(updatedUser.getPassword());

        return userLoginRepository.save(user);
    }

    //  Update email, password, role, and isActive dynamically
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
