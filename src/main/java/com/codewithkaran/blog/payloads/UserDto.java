package com.codewithkaran.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    
    @NotEmpty   // checks for null and blank string @NotNull checks for only null value not blank..
    @Size(min=4, message="Username must be min of 4 characters !!")
	private String name;
    
    @Email(message="Email address is not valid !!")
	private String email;
    
    @NotEmpty
    @Size(min =3, max=10, message="Password must be min of 3 chars and max of 10 chars !!")  //@Pattern can be used for pattern regular expression inside of pattern 
	private String password;
    
    @NotEmpty
	private String about;
	
}
