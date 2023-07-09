package com.tanthanh.userservice.model;

import com.tanthanh.userservice.data.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String employeeId;
	private String token;
	private String refreshtoken;
	

	public static UserDTO entityToDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setPassword(user.getPassword());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmployeeId(user.getEmployeeId());
		return userDTO;
	}
	public static User dtoToEntity(UserDTO dto){
		User user = new User();
		user.setId(dto.getId());
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		user.setEmployeeId(dto.getEmployeeId());
		return user;
	}
}
