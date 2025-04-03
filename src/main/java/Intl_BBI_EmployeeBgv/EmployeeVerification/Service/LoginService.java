package Intl_BBI_EmployeeBgv.EmployeeVerification.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserLoginDto;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Dto.UserVerificationDTO;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.User;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Entity.UserLoginTable;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserLoginRepository;
import Intl_BBI_EmployeeBgv.EmployeeVerification.Repository.UserRepository;

@Service
public class LoginService<T> {

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
        userLoginTable.setPassword(userLoginDto.getPassword());
        

        // Fetch User entity if provided
//        if (userLoginDto.getUserDetailId() != null) {
//            Optional<User> userOptional = userRepository.findById(userLoginDto.getUserDetailId());
//            userOptional.ifPresent(userLoginTable::setUser);
//        }
        
        User user = new User ();
        user.setFirstName(userLoginDto.getFirstName());
        user.setLastName(userLoginDto.getLastName());
       User userResult=userRepository.save(user);
       userLoginTable.setUser(userResult);
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
// validate user 
    public ResponseEntity<UserVerificationDTO> verificationOfUser(Map<String,String > UserLogin ) {
        Optional<UserLoginTable> user = userLoginRepository.findByEmail(UserLogin.get("emailId"));
        UserVerificationDTO response = new UserVerificationDTO();

        if (user.isPresent()) { // Check if user exists
            if (UserLogin.get("password").equals(user.get().getPassword())) { // Use equals() for comparison
                response.setResponse("verified");
                response.setEmail(user.get().getEmail());
                response.setRole(user.get().getRole().name()); // Convert Enum to String
                response.setUserDetailId(user.get().getUser().getDetailId());
                response.setUserId(user.get().getUserId());
               return ResponseEntity.ok(response);
            } else {
                response.setResponse("no password match");
                
              return  ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setResponse("false");
            return ResponseEntity.notFound().build();
        }
        

       // return response;
    }
    
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        // Fetch the user by userId
        UserLoginTable user = userLoginRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Validate the old password (consider using password encoder if you have one)
        if (!user.getPassword().equals(oldPassword)) {
            return false; // Old password does not match
        }

        // Don't allow same password
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password must be different from old password");
        }

        // Update the password
        user.setPassword(newPassword);
        userLoginRepository.save(user);

        return true; // Password changed successfully
    }
    
    // Get all users info
    public List<UserLoginTable> allUserInfo() {
        List<UserLoginTable> users;
		try {
			users = userLoginRepository.findAll();
			System.out.print("Fetched {} users info  from database."+users.size());
	        return users;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			users=new ArrayList<UserLoginTable>();
			return users;
		 
		}
        
    }

	public UserLoginTable updateUserDetails(UserLoginTable u) {
		userLoginRepository.save(u);
		return u;
	}
    
    
    
   
    
    
}