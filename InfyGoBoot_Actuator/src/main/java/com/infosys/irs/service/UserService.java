package com.infosys.irs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User getUserDetails(String userId)  {

		UserEntity ue = userRepository.getOne(userId);

		User user = new User();
		user.setCity(ue.getCity());
		user.setEmail(ue.getEmail());
		user.setName(ue.getName());
		user.setPhone(ue.getPhone());
		user.setUserId(ue.getUserId());

		return user;

	}

}
