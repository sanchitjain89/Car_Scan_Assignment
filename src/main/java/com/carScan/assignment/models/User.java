package com.carScan.assignment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "mobile_number")})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	@Size(max = 20)
	private String firstName;
	
	@Column(name = "last_name")
	@Size(max = 20)
	private String lastName;
	
	@Column(name = "city")
	@Size(max = 20)
	private String city;
	
	@Column(name = "mobile_number")
	@NotBlank(message = "Mobile Number is mandatory")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumber;

	@Size(max = 200)
	private String password;
	
	private Date date;
}