package Intl_BBI_EmployeeBgv.EmployeeVerification.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="adminTable")
public class AdminTable {
	
     @Id
	
	@Column(name="aID")
	private int aID;

    @Column(name="Name")
     private String  Name;
	
	@Column(name="Email")
	private String  Email;
	
	@Column(name="Password")
	private String  Password; 
	
	

}
