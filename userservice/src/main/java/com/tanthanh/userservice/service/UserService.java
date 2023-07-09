package com.tanthanh.userservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tanthanh.userservice.data.User;
import com.tanthanh.userservice.data.UserRepository;
import com.tanthanh.userservice.model.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public UserDTO saveUser(UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return UserDTO.entityToDTO(userRepository.save(UserDTO.dtoToEntity(userDTO)));
	}
	public UserDTO login(String username, String password) {
		User user = userRepository.findByUsername(username);
		UserDTO dto = new UserDTO();
		if(user !=null) {
			BeanUtils.copyProperties(user, dto);
			if(passwordEncoder.matches(password, dto.getPassword())) {
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				String accessToken = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+ (1 * 60 * 10000)))
						.sign(algorithm);
				String refreshtoken = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+ (10080 * 60 * 10000)))
						.sign(algorithm);
				dto.setToken(accessToken);
				dto.setRefreshtoken(refreshtoken);
			}
		}
		return dto;
	}
}
