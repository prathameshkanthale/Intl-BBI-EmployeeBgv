package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="UserLoginTable")
public class UserLoginTable {
	
@Id
	
	@Column(name="userID")
	private int userID;
	
	@Column(name="Email")
	private String  Email;
	
	@Column(name="Password")
	private String  Password;

}
