package com.infosys.irs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class RegistrationService {

	private final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	private UserRepository userRepository;

	public void registerUser(User user) throws UserIdAlreadyPresentException {
		Long time1;
		Long time2;
		time1 = System.currentTimeMillis();
		//UserEntity ue = findUser(user.getUserId());
		time2 = System.currentTimeMillis();
		
		String logInfoMsg="Amount of time taken:" + (time2 - time1) + " miliseconds.";
		logger.info(logInfoMsg);
		
		//if (ue != null)
			//throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
		UserEntity userEntity = new UserEntity();
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserId(user.getUserId());
		userRepository.saveAndFlush(userEntity);
	}

	@SuppressWarnings("deprecation")
	@Cacheable("user")
	public UserEntity findUser(String userId) {
		return userRepository.getOne(userId);
	}

}
