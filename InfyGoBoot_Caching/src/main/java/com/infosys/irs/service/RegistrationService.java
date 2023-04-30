package com.infosys.irs.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;

	public void registerUser(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserId(user.getUserId());
		userRepository.saveAndFlush(userEntity);
	}

	/*
	 * To intercept proxy, use the external method call. i.e., the effect of
	 * self-invocation is a method within the target object calling the other
	 * method, will not end in actual cache interception during runtime even the
	 * method is annotated using @Cacheable
	 */
	@Cacheable("users")
	public Optional<UserEntity> findUser(String userId) {
		System.out.println("Entered find user method");
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		return userEntity;
	}

}
