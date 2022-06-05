package com.example.Taller1QuinteroLuisa.backend.model.person;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Taller1QuinteroLuisa.backend.validation.CompleteInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.PersonalInfoValidation;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserApp{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotBlank(groups = CredentialInfoValidation.class)
	@Size(min = 3, groups = { CredentialInfoValidation.class}, message="User must be at least 3 characters")
	private String username;
	
	@NotNull(groups = CompleteInfoValidation.class, message = "Size must be 8 characters")
	@Size(min = 8, groups = { CredentialInfoValidation.class })
	private String password;
	
	@Transient
	@NotNull(groups = CompleteInfoValidation.class, message = "Passwords doesn't match")
	private String repeatPassword;
	
	@NotNull(groups= {PersonalInfoValidation.class, CompleteInfoValidation.class}, message= "Type can't be null")
	private UserType type;
}
